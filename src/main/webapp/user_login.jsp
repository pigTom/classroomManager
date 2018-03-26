<%--
  Created by IntelliJ IDEA.
  User: tangdunhong
  Date: 2018/3/16
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+
            ":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>教室用户登录</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script>
</head>
<body>
<div id="header"><span class="title">教室用户登录</span></div>
<div class="myFrame">
    <form action="user/login.do" method="post" onclick="return loginCheck()">
        <div class="data"><input type="text" name="userName" id="userName" placeholder="User Name" value="${userName}" ></div>
        <div class="ErrorData" id="userNameError">${userNameError}</div>
        <div class="data"><input type="password" name="password" id="password" placeholder="password" value="${password}" }></div>
        <div class="ErrorData" id="passwordError">${passwordError}</div>
        <div class="data"><input type="submit" value="登录"></div>
        <div class="ErrorData" id="loginError">${loginError}</div>
    </form>
    <a href="register.jsp">教室用户注册</a>
    <a href="adminLogin.jsp">教室管理员登录</a>

</div>
<script type="text/javascript">
    function loginCheck(){
        if(  $("#userName").val().trim() == ""){
            $("#userNameError").html("&ensp;*&ensp;用户名不能为空");
            //alert("hello");
            return false;
        }

        if( $("#password").val().trim() == ""){
            $("#passwordError").html("&ensp;*&ensp;密码不能为空");
            return false;
        }

        //alert("hello");
        return true;
    }
</script>
</body>
</html>