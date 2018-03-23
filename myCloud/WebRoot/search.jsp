<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <base href="<%=basePath%>">
  <head>
    <title>My JSP</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!--<script type="text/javascript" src="js/jquery-2.1.4.js"></script>-->
	<!--<link rel="stylesheet" type="text/css" href="css/bank.css">-->
	<meta content="" name="keywords"/>
	<meta content="" name="description"/>
  </head>
  
  <body>
	<div id="header"><a href="user/logout.do">Logout</a><span class="title">云，享我所想</span></div>
	<div id="body">
		<div class="search"><form action="file/search.do">
			<input id="search" type="text" name="search">
			<input type="submit" value="Search">
			</form>
			</div>
	</div>
	<div id="footer">
		<div class="copyright">&copy;Copyright All Rights Deserved</div>
		<div class="author">AUTHOR: <span class="TiName">flyingPig</span></div>
		<div class="date"></div></div>
	<script type="text/javascript">
  </body>
</html>
