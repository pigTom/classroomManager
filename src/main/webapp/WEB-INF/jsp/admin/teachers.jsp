<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/teachers.js"></script>
<div class="m-b-md">
    <form class="form-inline" role="form" id="searchForm"
          name="searchForm">
        <div class="form-group">
            <label class="control-label"> <span
                    class="h4 font-thin v-middle">&ensp;&ensp;学号:</span></label> <input
                class="input-medium ui-autocomplete-input" id="userId"
                placeholder="工号或教室名"
                name="userFormMap.accountName">
        </div>
        <a href="javascript:void(0)" class="btn btn-default" id="search" onclick="query()">查询</a>
    </form>
</div>
<header class="panel-heading">
    <div class="doc-buttons">
        <!-- buttons -->
        <input type="checkbox" id="checkbox" onclick="selectAll()"/>
        <button type="button" onclick="deleteAll()">删除</button>
        <button type="button" onclick="freezeAll()">冻结</button>
    </div>
</header>
<div class="table-responsive">
    <table class="table">
        <tr>
            <td></td>
            <td class="td1">教号</td>
            <td class="td1">教师姓名</td>
            <td class="td1">权限</td>
            <td class="td1">注册时间</td>
            <td class="td1">删除</td>
            <td class="td1">更新</td>
            <td class="td1">冻结</td>
        </tr>

        <c:if test="${teacher_pages != null}">
            <c:forEach items="${teacher_pages.data}" var="teacher" varStatus="status">

                <tr class="data">
                    <td><input type="checkbox" title="${teacher.id}"></td>
                    <td class="row">${teacher.userId}</td>
                    <td class="row">${teacher.userName}</td>
                    <td class="row">${teacher.privilege}</td>
                    <td class="row"><fmt:formatDate value="${teacher.createTime}" pattern="yyyy-MM-dd"/></td>
                    <td class="row">
                        <button onclick="deleteUser(${teacher.id})">删除</button>
                    </td>
                    <td class="row">
                        <button onclick="update1(${teacher.id})">重置密码</button>
                    </td>
                    <td class="row">
                        <button onclick="freeze(${teacher.id}, '${teacher.privilege}')">
                            <c:choose>
                                <c:when test="${teacher.privilege == 'none'}">解冻</c:when>
                                <c:otherwise>冻结</c:otherwise>
                            </c:choose></button>
                    </td>
                </tr>

            </c:forEach>
        </c:if>
    </table>

    <a href="javascript:void(0)" onclick="change(${teacher_pages.currPage}-1, ${teacher_pages.totalPage})">上一页</a>
    跳转到第<input type="text" maxlength="2" size="2" id="pagenum">页
    <input type="button" value="跳转" onclick="change1()">
    <a href="javascript:void(0)" onclick="change(${teacher_pages.currPage}+1, ${teacher_pages.totalPage})">下一页</a>
    <span>共
        <c:choose>
            <c:when test="${teacher_pages == null || teacher_pages == ''}">0</c:when>
            <c:otherwise>${teacher_pages.totalPage}</c:otherwise>
        </c:choose>页
    </span>
    <span style="align-content: center">&ensp;&ensp;&ensp;&ensp;&ensp;第${teacher_pages.currPage}页</span>
</div>

<div id="callback_div" class="table-responsive" style="display: none;">
    <div id="paging_callback" class="pagclass"></div>
</div>

