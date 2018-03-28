<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/classrooms.js"></script>
<div class="m-b-md">
    <form class="form-inline" role="form" id="searchForm"
          name="searchForm">
        <div class="form-group">
            <label class="control-label"> <span
                    class="h4 font-thin v-middle">&ensp;&ensp;教室:</span></label> <input
                class="input-medium ui-autocomplete-input" title="11" id="className"
                placeholder="教室号或教学楼名"
                name="userFormMap.accountName">
        </div>
        <a href="javascript:void(0)" class="btn btn-default" id="search" onclick="query()">查询</a>
    </form>
</div>
<header class="panel-heading">
    <div class="doc-buttons">
        <!-- buttons -->
        <input type="checkbox" title="sel" id="checkbox" onclick="selectAll()"/>
        <button type="button" onclick="deleteAll()">删除所有</button>
        <button type="button" onclick="freezeAll()">冻结所有教室</button>
    </div>
</header>
<div class="table-responsive">
    <table class="table">
        <tr>
            <td></td>
            <td class="td1">教学楼</td>
            <td class="td1">教室号</td>
            <td class="td1">是否可预定</td>
            <td class="td1">教室大小</td>
            <td class="td1">删除</td>
            <td class="td1">操作</td>
        </tr>

        <c:if test="${classroom_pages != null}">
            <c:forEach items="${classroom_pages.data}" var="classroom" varStatus="status">

                <tr class="data">
                    <td><input type="checkbox" title="${classroom.id}"></td>
                    <td class="row">${classroom.buildingName}</td>
                    <td class="row"><a href="javascript:void(0)" onclick="intoOrder(${classroom.id})">${classroom.classroomName}</a></td>
                    <td class="row">${classroom.available}</td>
                    <td class="row">${classroom.classroomSeats}</td>
                    <td class="row">
                        <button onclick="deleteClassroom(${classroom.id})">删除</button>
                    </td>
                    <td class="row">
                        <button onclick="freeze(${classroom.id}, '${classroom.available}')">
                            <c:choose>
                                <c:when test="${classroom.available == 'yes' }">冻结</c:when>
                                <c:otherwise>取消冻结</c:otherwise>
                            </c:choose>
                        </button>
                    </td>
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
