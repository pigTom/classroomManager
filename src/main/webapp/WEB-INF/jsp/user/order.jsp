<%@ page import="java.util.Date" %><%--
  Created by pigTom.
  User: Administrator
  Date: 2018/3/25
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/user.js"></script>


<div class="table-responsive">
    <form action="/user/order.do" method="post" onsubmit="orderClass()">
        <<input type="hidden" name="classId" value="${classroom.id}">
    <table class="table">
        <tr>
            <td class="row">教学楼</td>
            <td class="row">教室名</td>
            <td class="row">时间</td>
            <td class="row">具体时间</td>
            <td class="row">预定标题</td>
            <td class="row">确定</td>
        </tr>
                <tr class="data">
                    <td class="row">${classroom.buildingName}</td>
                    <td class="row">${classroom.classroomName}</td>
                    <td class="row">
                        <select name="orderDate" id="orderDate" title="${classroom.id}">
                            <c:if test="${user.privilege == 'none'}">
                                <option value="-1">你没有权限预定教室</option>
                            </c:if>
                            <c:if test="${user.privilege == 'normal'}">
                                <option id="tipDate" selected>选择日期</option>
                                <option value="0">今天</option>
                                <option value="1">明天</option>
                            </c:if>
                            <c:if test="${user.privilege == 'senior'}">
                                <option value="0">今天</option>
                                <option value="1"><fmt:formatDate value="<%=new Date(1000*3600*(new Date().getTime()/1000/3600/24+1))%>" pattern="yyyy-MM-dd"/></option>
                                <option value="2"><fmt:formatDate value="<%=new Date(1000*3600*(new Date().getTime()/1000/3600/24+2))%>" pattern="yyyy-MM-dd"/></option><%=new Date()%>
                                <option value="3"><fmt:formatDate value="<%=new Date(1000*3600*(new Date().getTime()/1000/3600/24+3))%>" pattern="yyyy-MM-dd"/> </option>
                                <option value="4"><fmt:formatDate value="<%=new Date(1000*3600*(new Date().getTime()/1000/3600/24+4))%>" pattern="yyyy-MM-dd"/></option>
                                <option value="5"><fmt:formatDate value="<%=new Date(1000*3600*(new Date().getTime()/1000/3600/24+5))%>" pattern="yyyy-MM-dd"/></option>
                                <option value="6"><fmt:formatDate value="<%=new Date(1000*3600*(new Date().getTime()/1000/3600/24+6))%>" pattern="yyyy-MM-dd"/></option>
                            </c:if>
                        </select>
                    </td>
                    <td class="row"><select id="orderTime" name="orderTime">
                        <option selected id="tip">请先选择日期</option>
                        <%--<option value="1" selected>第一大节</option>--%>
                        <%--<option value="2">第二大节</option>--%>
                        <%--<option value="3">第三大节</option>--%>
                        <%--<option value="4">第四大节</option>--%>
                        <%--<option value="5">第五大节</option>--%>
                    </select></td>
                    <td class="row"><input type="text" name="logName" placeholder="请说明预定理由"></td>
                    <td class="row"><input type="submit" value="提交"></td>

                </tr>
    </table>
    </form>
</div>

<div id="callback_div" class="table-responsive" style="display: none;">
    <div id="paging_callback" class="pagclass"></div>
</div>