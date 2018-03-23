<%--
  Created by IntelliJ IDEA.
  User: tangdunhong
  Date: 2018/3/17
  Time: 10:29
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
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script>
</head>
<body>
    <div id="header">
        <span class="title">用户注册</span>
    </div>
    <div class="body">
        <div class="myFrame">
            <form action="user/register.do" method="post" onclick="formCheck()">
                <!-- 工号 -->
                <div class="data">
                    <input type="text" name="userId" id="userId" placeholder="学号或教号"/>
                </div>
                <div class="ErrorData" id="userIdError">${userIdError}</div>
                <!-- 用户姓名 -->
                <div class="data">
                    <input type="text" name="userName" id="userName" placeholder="用户姓名"/>
                </div>
                <div class="ErrorData" id="userNameError">${userNameError}</div>
                <!-- 登录密码 -->
                <div class="data">
                    <input type="password" name="password" id="password" placeholder="密码"/>
                </div>
                <div class="ErrorData" id="passwordError">${staffPasswordError}</div>

                <!-- 职称 -->
                <div class="data">
                    <input type="text" name="userTile" id="userTile" placeholder="职称"/>
                </div>
                <div class="ErrorData" id="userTitleError">${userTitleError}</div>

                <!-- 验证码 -->
                <div class="data">
                    <input type="text" id="validate" placeholder="请输入验证码"/>
                </div>
                <div class="ErrorData" id="validateError">${validateError}</div>
                <div>
                    <img id="img1" class="verification" alt="找不到图片" src="pic/0.jpg">
                    <img id="img2" class="verification" alt="找不到图片" src="pic/0.jpg">
                    <img id="img3" class="verification" alt="找不到图片" src="pic/0.jpg">
                    <img id="img4" class="verification" alt="找不到图片" src="pic/0.jpg">
                </div>
                <div class="data">
                    <input id="flush" type="button" class="btn" value="刷新">
                </div>
                <div class="data">
                    <input type="submit" class="btn" value="提交">
                </div>
            </form>
            <div class="message">用户注册</div>
            <div class="link">
                已有账户？<a href="user_login.jsp">登录</a>
            </div>
        </div>
    </div>

<script type="text/javascript">
    var img1 = $('#img1');
    var img2 = $('#img2');
    var img3 = $('#img3');
    var img4 = $("#img4");
    var flush = $('#flush');

    // to recode the verification code
    var verificationCode = "";
    // dynamic generate image path
    function generatePath() {
        // set verification code to ""
        verificationCode = "";
        img1.setAttribute("src", setUrl());
        img2.setAttribute("src", setUrl());
        img3.setAttribute("src", setUrl());
        img4.setAttribute("src", setUrl());
        function setUrl() {
            // random generate a number from 0 to 9
            var num = Math.floor(Math.random() * 10);
            // concat every number to form a verification code
            verificationCode += num;
            return 'pic/' + num + '.jpg';

        }

    }
    // generate code when this page loaded
    generatePath();

    // add onclick listener to flush button
    flush.onclick = function () {
        generatePath();
    };

    //from check
    function formCheck(){
        if($("#userName").val().trim() === ""){
            $("#usernameError").html("&ensp;*&ensp;用户名不能为空");
            return false;
        }

        if($("#password").val().trim() === "") {
            $("#passwordError").html("&ensp;*&ensp;密码不能为空");
            return false;
        }

        if($("#validate").val().trim() !== verificationCode){
            $("#validateError").html("&ensp;*&ensp;验证码错误");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
