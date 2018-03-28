<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/notice.js"></script>
<div class="m-b-md">
    <form class="form-inline" role="form" id="searchForm"
          name="searchForm">
        <div class="form-group">
            <label class="control-label"> <span
                    class="h4 font-thin v-middle">&ensp;&ensp;教室号:</span></label> <input
                class="input-medium ui-autocomplete-input" id="className"
                placeholder="请输入教室号"
                name="userFormMap.accountName" title="log" >
        </div>
        <a href="javascript:void(0)" class="btn btn-default" id="search" onclick="query()">查询</a>
    </form>
</div>
<header class="panel-heading">
    <div class="doc-buttons">
        <!-- buttons -->
        <input type="checkbox" id="checkbox" title="selectAll" onclick="selectAll()"/>
        <button type="button" onclick="return1()">返回</button>
    </div>
</header>
<div class="table-responsive">
    <table class="table">
        <tr>
            <td></td>
            <td class="td1">发布者</td>
            <td class="td1">主题</td>
            <td class="td1">发布时间</td>
        </tr>

        <c:if test="${notice_pages != null}">
            <c:forEach items="${notice_pages.data}" var="notice" varStatus="status">

                <tr class="data">
                    <td><input type="checkbox" title="${notice.id}"></td>
                    <td class="row">${notice.publisherName}</td>
                    <td class="row"><a href="javascript:void(0)" onclick="goContent('${notice.subject}',
                            '${notice.publisherName}', '${notice.content}')">${notice.subject}</a></td>
                    <td class="row"><fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd"/></td>
                </tr>

            </c:forEach>
        </c:if>
    </table>

    <a href="javascript:void(0)" onclick="change(${notice_pages.currPage}-1, ${notice_pages.totalPage})">上一页</a>
    跳转到第<input type="text" title="babab" maxlength="2" size="2" id="pagenum">页
    <input type="button" value="跳转" onclick="change1()">
    <a href="javascript:void(0)" onclick="change(${notice_pages.currPage}+1, ${notice_pages.totalPage})">下一页</a>
    <span>共
    <c:choose>
        <c:when test="${notice_pages == null || notice_pages == ''}">0</c:when>
        <c:otherwise>${notice_pages.totalPage}</c:otherwise>
    </c:choose>页
    </span>
    <span style="align-content: center">&ensp;&ensp;&ensp;当前第${notice_pages.currPage}页</span>
</div>

<div id="callback_div" class="table-responsive" style="display: none;">
    <div id="paging_callback" class="pagclass"></div>
</div>
