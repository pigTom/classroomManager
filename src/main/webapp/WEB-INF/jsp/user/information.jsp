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
<div class="myFrame">

        <form action="user/login.do" method="post" onsubmit="return alertUserInfo()">
            <table>
        <tr class="data">
            <td >姓名：</td>
            <td><input type="text" id="userName" name="userName" value="${user.userName}" disabled="disabled"></td>
        </tr>
        <tr class="data">
            <td>职员ID：</td>
            <td>${user.userId}</td>
        </tr>
        <tr class="data">
            <td>性别：</td>
            <td><input  id="male" type="radio" name="userSex" value="male"
                       <c:if test="${user.userSex == 'male'}">checked</c:if>/>男
                <input  id="female" type="radio" name="userSex" value="female"
                       <c:if test="${user.userSex == 'female'}">checked</c:if>/>女</td>
        </tr>
        <tr class="data">
            <td>QQ:</td>
            <td><input type="text" id="qq" name="userQq" value="${user.userQq}" disabled></td>
        </tr>
        <tr class="data">
            <td>邮箱：</td>
            <td><input type="email" name="userMail" id="userMail" value="${user.userMail}" disabled="disabled"></td>
        </tr>
        <tr class="data">
            <td>电话：</td>
            <td><input type="tel" name="userTelephone" id="UserTel" value="${user.userTelephone}" disabled="disabled"> </td>
        </tr>
    </table>
            <div style="text-align: center;margin-top: 5px"><input type="button" value="修改" onclick="alertBtn()">
                &ensp;<input type="submit"   value="确定"></div>
        </form>

</div>