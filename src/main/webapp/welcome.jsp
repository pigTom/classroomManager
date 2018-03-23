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
    String basePath = request.getScheme() + "://" + request.getServerName() +
            ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script>
</head>
<body>
    <div class="myFrame">
        你好
    </div>
</body>
</html>