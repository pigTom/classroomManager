<%--
  Created by IntelliJ IDEA.
  User: tangdunhong
  Date: 2018/3/16
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+
            ":"+request.getServerPort()+path+"/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <title>教室管理员登录</title>
    <meta charset="UTF-8"/>
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
<div id="header"><span class="title">教室管理员登录</span></div>
<div class="myFrame">
    <form action="admin/login.do" method="post" onclick="return loginCheck()">
        <!-- 管理员登录名 -->
        <div class="td1"><input type="text" name="adminName" id="adminName"
                                 placeholder="Admin Name" value="${adminName}" ></div>
        <div class="ErrorData" id="adminError">${adminNameError}</div>
        <!-- 管理员登录密码 -->
        <div class="td1"><input type="password" name="password" id="password"
                                 placeholder="password" value="${password}" }></div>
        <div class="ErrorData" id="passwordError">${passwordError}</div>
        <!-- 登录 -->
        <div class="td1"><input type="submit" value="登录"></div>
        <div class="ErrorData" id="errorData">${loginError}</div>
    </form>
    <div class="td1">
        <a href="register.jsp">教室用户注册</a>
    </div>
    <div class="td1">

        <a href="user_login.jsp">教室用户登录</a>
    </div>

</div>
<script type="text/javascript">
    function loginCheck(){
        var result = true;
         $("#adminName").onblur(function(){
             if($(this).val().trim() === ""){
                 $("#adminError").html("&ensp;*&ensp;账号不能为空");
                 result = false;
             }
         });

        $("#password").onblur(function() {
            if ($("#password").val().trim() === "") {
                $("#passwordError").html("&ensp;*&ensp;密码不能为空");
                result = false;
            }
        });

        return result;
    }
</script>
</body>
</html>