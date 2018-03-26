<%--
  Created by pigTom.
  User: Administrator
  Date: 2018/3/25
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/classroom/classroomCourse.js"></script>


<div class="table-responsive">
    <form action="/user/order.do" method="post">
    <table class="table">
        <tr>
            <td class="row">教学楼</td>
            <td class="row">教室名</td>
            <td class="row">时间</td>
            <td class="row">具体时间</td>
            <td class="row">确定</td>
        </tr>
                <tr class="data">
                    <td class="row">${classroom.buildingName}</td>
                    <td class="row">${classroom.classroomName}</td>
                    <td class="row"><input name="orderDate" type="date"></td>
                    <td class="row"><select name="orderTime">
                        <option value="1" selected>第一大节</option>
                        <option value="2">第二大节</option>
                        <option value="3">第三大节</option>
                        <option value="4">第四大节</option>
                        <option value="5">第五大节</option>
                    </select></td>
                    <td class="row"><input type="submit" value="提交"></td>

                </tr>
    </table>
    </form>
</div>

<div id="callback_div" class="table-responsive" style="display: none;">
    <div id="paging_callback" class="pagclass"></div>
</div>