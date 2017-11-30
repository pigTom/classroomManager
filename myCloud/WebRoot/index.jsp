<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <base href="<%=basePath%>">
  <head>
    <title>我的云盘</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!--<script type="text/javascript" src="js/jquery-2.1.4.js"></script>-->
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<meta content="" name="keywords"/>
	<meta content="" name="description"/>
  </head>
  
  <body>
  	  <div id="header"><span class="title">云，享我所想</span></div>
  	<div id="body">
		<div class="myFrame">
		<div class="message">Login</div>
		<form action="user/login.do" method="post" onclick="return loginCk()">
			<div class="data"><input type="text" name="userName" id="userName" placeholder="userName" value="${userName}" ></div>
			<div class="ErrorData">${userNameErrorMsg}</div>
			<div class="data"><input type="password" name="password" id="password" placeholder="password" value="${password}" }></div>
			<div class="ErrorData">${passwordErrorMsg}</div>
			<div class="data"><input type="submit" value="登录"></div>
			<div class="ErrorData">${loginErrorMsg}</div>
		</form>
		<div>没有账号<a href="register.jsp">注册</a></div>
		</div>
	</div>
	<div id="footer"></div>
	<script type="text/javascript">
	// 表单验证
		function loginCk() {
		
			if(  $("#userName").val().trim() == ""){
				$("#idWarn").html("&ensp;*&ensp;账号不能为空");
				$("#idWarn>.start").text("*");
				//alert("hello");
				return false;
			}
			
			 if( $("#password").val().trim() == ""){
				$("#pwdWarn").html("&ensp;*&ensp;密码不能为空");
				$("#pwdWarn>.start").text("*");
				return false;
			}
			
			
	
			//alert("hello");
			return true;
		};
	</script>
  </body>
</html>
