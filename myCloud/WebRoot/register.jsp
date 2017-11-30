<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>注册云盘</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<!--<link rel="stylesheet" type="text/css" href="css/bank.css">-->
<meta content="yun,baiduyun," name="keywords" />
<meta content="" name="description" />
<link rel="stylesheet" type="text/css" href="css/common.css">


</head>

<body>
	<div id="header">
		<span class="title">云，享我所想</span>
	</div>
	<div id="body">
		<div class="myFrame">
			<div class="message">用户注册</div>
			<form action="user/register.do" onclick="return loginCk()">
				<div class="data">
					<input type="text" name="userName" id="userName" placeholder="用户名" />
				</div>
				<div class="ErrorData">${usernameErrorMsg}</div>
				<div class="data">
					<input type="password" name="password" id="password" placeholder="密码">
				</div>
				<div class="ErrorData">${passwordErrorMsg}</div>
				<div class="data">
					<input type="text" id="validate" placeholder="请输入验证码">
				</div>
				<div class="ErrorData" id="validateWarn"></div>
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
					<input type="submit" class="btn" value="提交 ">
				</div>
			</form>
			<div class="link">
				已有账户？<a href="index.jsp">登录</a>
			</div>
		</div>
	</div>
	<div id="footer"></div>
	<script type="text/javascript">
		var img1 = document.getElementById("img1");
		var img2 = document.getElementById("img2");
		var img3 = document.getElementById("img3");
		var img4 = document.getElementById("img4");
		var commit = document.getElementById("commit");
		var flush = document.getElementById("flush");

		// use rv to note real velification 
		var rv = "";
		// function to dynynamic generate verification
		function geneVe() {
			rv = "";
			img1.setAttribute("src", setUrl());
			img2.setAttribute("src", setUrl());
			img3.setAttribute("src", setUrl());
			img4.setAttribute("src", setUrl());

			function setUrl() {
				var num = Math.floor(Math.random() * 10);

				rv += num;
				return "pic/" + num + ".jpg";
			}
		}
		// call generate velification function
		geneVe();
		// add onclick listener to flush
		flush.onclick = function() {
			geneVe();
		};

		// 表单验证
		function loginCk() {

			if ($("#userName").val().trim() == "") {
				$("#idWarn").html("&ensp;*&ensp;账号不能为空");
				$("#idWarn>.start").text("*");
				//alert("hello");
				return false;
			}

			if ($("#password").val().trim() == "") {
				$("#pwdWarn").html("&ensp;*&ensp;密码不能为空");
				$("#pwdWarn>.start").text("*");
				return false;
			}

			else {
				var veli = $("#validate").val();
				//alert(veli);
				if (veli != rv) {
					$("#veriWarn").html("&ensp;*&ensp;验证码输入错误");
					return false;
				}
			}
			//alert("hello");
			return true;
		};
	</script>
</body>
</html>
