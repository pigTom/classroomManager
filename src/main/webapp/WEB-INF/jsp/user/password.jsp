<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tangdunhong
  Date: 2018/3/16
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/user.js"></script>
<div class="myFrame"><header class="panel-heading">

</header>
    <form id="maniForm" action="###" method="post" onclick="return false">
        <table>
            <tr class="oneRow">
                <td>姓名：</td>
                <td><input type="text" id="userName" name="userName" value="${user.userName}" disabled="disabled"></td>
            </tr>
            <tr class="oneRow">
                <td>原始密码：</td>
                <td><input type="password" id="password" name="password" ></td>
            </tr>
            <tr class="oneRow">
                <td>新密码：</td>
                <td><input type="password" id="newPassword" name="newPassword"> </td>
            </tr>
            <tr class="oneRow">
                <td>新密码：</td>
                <td><input type="password" id="newPassword1" name="newPassword1"> </td>
            </tr>
            <tr><td colspan="2"><input type="submit" onclick="alertPassword()" value="提交"></td> </tr>
        </table>
    </form>
</div>
