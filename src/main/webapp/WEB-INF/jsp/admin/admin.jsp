<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tangdunhong
  Date: 2018/3/16
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>管理员界面</title>
    <meta charset="UTF-8">
</head>
<body>
你好管理员 ${admin.adminName}
<div id="main">
    <div id="top">
        <span class="bankLogo">教室管理系统</span><span id="admin">管理员：<span id="admName">${admin.adminName}</span></span>
    </div>
    <div id="left">

        <div class="menu">用户管理</div>
        <div class="menu">教室管理</div>
        <div class="menu">日志查询</div>
        <div class="menu">盈利结算</div>
    </div>
    <div id="right">
        <div id="showUser">
            <div class="row">用户名</div>
            <div class="row">用户职称</div>
            <div class="row">用户权限</div>
            <div class="row">注册日期</div>
            <div class="row">冻结用户</div>
            <div class="row">删除用户</div>
            <div class="row">查看预定</div>
            <c:forEach items="${list}" var="user">
                <div class="data1">${user.userName }</div>
                <div class="data1">${user.staffTitle}</div>
                <div class="data1">${user.privilege }</div>
                <div class="data1">${user.createTime }</div>
                <div class="data1"><li onclick="freezeUser(user.id)"></li></div>
                <div class="data1"><li onclick="deleteUser(user.id)"></li></div>
            </c:forEach>
            <div class="data" id="pageBottom" >
                <form action="servlet/AdminServlet" method="get" id="form1">
                    <input type="hidden" name="type" value="rankFront">
                </form>
                <form action="servlet/AdminServlet" method="get" id="form2">
                    <input type="hidden" name="type" value="rankNext">
                </form>
                <div class="bottom">
                    <input type="submit" value="上一页" name="text" id="front">
                    <span id="page">${sessionScope.rankPage}</span>
                    <input type="submit" value="下一页" name="next" id="next">
                </div>
            </div>
        </div>
        <div id="">
            <form action="servlet/AdminServlet" method="post"
                  onSubmit=" return checkAdd">
                <div class="input" id="userName">
                    姓&emsp;&emsp;名：&ensp;<input type="text" name="userName"><span
                        class="warn"></span>
                </div>
                <div class="input" id="pid">
                    身份证号：&ensp;<input type="text" name="pid"><span
                        class="warn"></span>
                </div>
                <div class="input" id="openMoney">
                    开户金额：&ensp;<input type="number" name="openMoney" min="1" value=10><span
                        class="warn"></span>
                </div>
                <div class="input" id="tel">
                    电&emsp;&emsp;话：&ensp;<input type="tel" name="tel"><span
                        class="warn"></span>
                </div>
                <div class="input">
                    地&emsp;&emsp;址：&ensp;<input type="text" name="address"><span
                        class="warn"></span>
                </div>
                <div class="input" id="button">
                    <input type="submit" value="添加"><input type="reset"
                                                           value="重置">
                </div>
                <div>
                    <span id="addInfo" class="ok"></span>
                </div>
                <input type="hidden" name="type" value="addCust">
            </form>
        </div>

        <div id="totalMoney">
            <div class="data">
                总金额为：<span id="spTotal">${totalMoney }￥</span>
            </div>
        </div>
        <div id="profit">
            <div class="data">总盈利：${profit}￥</div>
            <input type="hidden" name="type" value="profit">
        </div>
    </div>
</div>

<script type="text/javascript">
    function showClassroom(classrooms) {

    }
    function freezeUser(id){
        $.ajax({
            type: 'post',
            url: 'admin/freezeUser.do',
            data: 'id='+id
        })
    }
    function deleteUser(id) {

    }
</script>
</body>
</html>
