<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/classroom/classroomCourse.js"></script>
<div class="m-b-md">
    <form class="form-inline" role="form" id="searchForm"
          name="searchForm">
        <div class="form-group">
            <label class="control-label"> <span
                    class="h4 font-thin v-middle">&ensp;&ensp;教室:</span></label> <input
                class="input-medium ui-autocomplete-input" id="className"
                name="userFormMap.accountName" title="log" >
        </div>
        <a href="javascript:void(0)" class="btn btn-default" id="search" onclick="query()">查询</a>
    </form>
</div>
<header class="panel-heading">
    <div class="doc-buttons">
        <!-- buttons -->
        <input type="checkbox" id="checkbox" title="selectAll" onclick="selectAll()"/>
        <button type="button" onclick="deleteAllOrders()">删除预定</button>
        <button type="button" onclick="return1()">返回</button>
    </div>
</header>
<div class="table-responsive">
    <table class="table">
        <tr>
            <td></td>
            <td class="td1">预定人姓名</td>
            <td class="td1">事件</td>
            <td class="td1">时间</td>
            <td class="td1">具体时间</td>
            <td class="td1">预定日期</td>
            <td class="td1">教室号</td>
            <td class="td1">删除预定</td>
        </tr>

        <c:if test="${course_pages != null}">
            <c:forEach items="${course_pages.data}" var="course" varStatus="status">

                <tr class="data">
                    <td><input type="checkbox" title="${course.id}"></td>
                    <td class="row">${course.userName}</td>
                    <td class="row">${course.logName}</td>
                    <td class="row">${course.logDate}</td>
                    <td class="row">
                        <c:if test="${course.logTime == '1'}">
                            第一大节
                        </c:if>
                        <c:if test="${course.logTime == '2'}">
                            第二大节
                        </c:if>
                        <c:if test="${course.logTime == '3'}">
                            第三大节
                        </c:if>
                        <c:if test="${course.logTime == '4'}">
                            第四大节
                        </c:if>
                        <c:if test="${course.logTime == '5'}">
                            第五大节
                        </c:if></td>
                    <td class="row">${course.createTime}</td>
                    <td class="row">${course.classroomName}</td>
                    <td class="row">
                        <button onclick="cancelOrders(${course.id})">取消预定</button>
                    </td>

                </tr>

            </c:forEach>
        </c:if>
    </table>

    <a href="javascript:void(0)" onclick="change(${course_pages.currPage}-1, ${course_pages.totalPage})">上一页</a>
    跳转到第<input type="text" title="babab" maxlength="2" size="2" id="pagenum">页
    <input type="button" value="跳转" onclick="change1()">
    <a href="javascript:void(0)" onclick="change(${course_pages.currPage}+1, ${course_pages.totalPage})">下一页</a>
    <span>共
    <c:choose>
    <c:when test="${course_pages == null || course_pages == ''}">0</c:when>
    <c:otherwise>${course_pages.totalPage}</c:otherwise>
    </c:choose>页
    </span>
    <span style="align-content: center">&ensp;&ensp;&ensp;当前第${course_pages.currPage}页</span>
</div>

<div id="callback_div" class="table-responsive" style="display: none;">
    <div id="paging_callback" class="pagclass"></div>
</div>