<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/notice.js"></script>

<div class="myFrame">
    <form action="admin/notice/newNotice.do" onclick="return checkForm1()">
        <div class="data"><label>主题：</label><input name="subject" type="text" required size="40"/> </div>
        <div class="data"><label>内容：</label><textarea name="content" wrap="hard" rows="10" cols="80"></textarea></div>
        <div class="data"><input type="submit" value="提交"></div>
    </form>
</div>

<div id="callback_div" class="table-responsive" style="display: none;">
    <div id="paging_callback" class="pagclass"></div>
</div>
