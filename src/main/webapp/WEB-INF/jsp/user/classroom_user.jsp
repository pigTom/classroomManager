<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="m-b-md">
    <form class="form-inline" role="form" id="searchForm"
          name="searchForm">
        <div class="form-group">
            <label class="control-label"> <span
                    class="h4 font-thin v-middle">&ensp;&ensp;教室:</span></label> <input
                class="input-medium ui-autocomplete-input" title="11" id="className"
                name="userFormMap.accountName">
        </div>
        <a href="javascript:void(0)" class="btn btn-default" id="search" onclick="query()">查询</a>
    </form>
</div>
<header class="panel-heading">
    <div class="doc-buttons">

    </div>
</header>
<div class="table-responsive">
    <table class="table">
        <tr>
            <td class="row">教学楼</td>
            <td class="row">教室号</td>
            <td class="row">是否可预定</td>
            <td class="row">教室大小</td>
        </tr>

        <c:if test="${classroom_pages != null}">
            <c:forEach items="${classroom_pages.data}" var="classroom" varStatus="status">

                <tr class="data">
                    <td class="row">${classroom.buildingName}</td>
                    <td class="row"><a href="javascript:void(0)" onclick="doOrder(${classroom.id})">${classroom.classroomName}</a></td>
                    <td class="row">${classroom.available}</td>
                    <td class="row">${classroom.classroomSeats}</td>
                </tr>

            </c:forEach>
        </c:if>
    </table>

    <a href="javascript:void(0)" onclick="change(${classroom_pages.currPage}-1, ${classroom_pages.totalPage})">上一页</a>
    <span>跳转到第</span><input type="text" title="21" maxlength="2" size="2" id="pagenum">&ensp;页
    <input type="button" value="跳转" onclick="change1()">
    <a href="javascript:void(0)" onclick="change(${classroom_pages.currPage}+1, ${classroom_pages.totalPage})">下一页</a>
    <span>&ensp;共
        <c:choose>
            <c:when test="${classroom_pages == null || classroom_pages == ''}">0</c:when>
            <c:otherwise>${classroom_pages.totalPage}</c:otherwise>
        </c:choose>页
    </span>
    <span style="align-content: center">&ensp;&ensp;&ensp;当前第${classroom_pages.currPage}页</span>
</div>

<div id="callback_div" class="table-responsive" style="display: none;">
    <div id="paging_callback" class="pagclass"></div>
</div>
<script type="text/javascript">
    // 进入预定教室页面
    function doOrder(classroomId) {
        var data = "classroomId="+classroomId;
        loadhtml("classroom/doOrder.do", data);
    }

    function loadhtml(url, data) {
        var tb = $("#loadhtml");
        tb.html(CommnUtil.loadingImg());
        tb.load(rootPath+"/"+url, data);// 加载页面
    }
</script>