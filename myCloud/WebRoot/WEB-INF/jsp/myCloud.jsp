<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <base href="<%=basePath%>">
  <head>
    <title>My Cloud</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="utf-8"> 	
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/cloud.css">
	<meta content="" name="keywords"/>
	<meta content="" name="description"/>
  </head>
  <body>
	<div id="header"><a href="user/logout.do">Logout</a><span class="title">云，享我所想</span></div>
	<div id="body">
		<div class="title">Welcome<span class="TiName"> ${userName }</span></div>

		<div><form action="file/upload.do"  method="post" enctype="multipart/form-data">
				<input type="submit" value="上传文件" onclick="return checkfile()">
				<input type="file" name="file" onchange="checkfile()" id="fileupload">
				${uploadMsg }${downloadMsgError }${deleteMsgError}
			</form>
		</div>
		<div class="table">
			<div class="page">当前是第[${ pageInfo.currPage}]页 <a href="file/refresh.do">刷新列表</a></div>
			<div class="tableHeader">
				<div class="fileName">文件名</div>	
				<div class="fileSize">文件大小</div>
				<div class="fileDate">修改日期</div>
				<div class="fileDownload">下载</div>
				<div class="fileDelete">删除</div>
			</div>
			
			<div class="tableBody">
			<c:forEach items="${pageInfo.getData()}" var="file" varStatus="stat">
			<div class="rowData">
				<div class="fileName">${file.fileName }</div>
				<div class="fileSize">
					<c:if test="${file.fileSize >= 1024*1024*1024}">
					<fmt:formatNumber value="${file.fileSize/(1024*1024*1024) }"
 						 maxFractionDigits="2" minFractionDigits="0"/>GB</c:if>
					<c:if test="${file.fileSize < 1024*1024*1024 && file.fileSize >= 1024*1024}">
					<fmt:formatNumber value="${file.fileSize/(1024*1024) }"
 						 maxFractionDigits="2" minFractionDigits="0"/>MB</c:if>
					<c:if test="${file.fileSize < 1024*1024 && file.fileSize >= 1024}">
						<fmt:formatNumber value="${file.fileSize/1024 }"
 						 maxFractionDigits="2" minFractionDigits="0"/>KB</c:if>
					<c:if test="${file.fileSize < 1024}">
						<fmt:formatNumber value="${file.fileSize}"
 						 maxFractionDigits="2" minFractionDigits="0"/>字节</c:if>
				</div>
				<div class="fileDate"><fmt:formatDate value="${file.alterTime }" pattern="yyyy/MM/dd"/></div>
				<div class="fileDownload"><a href="javascript:void(0)" onclick="downloadFile('${file.filePath}')">下载</a></div>
				<div class="fileDelete"><a href="javascript:void(0)" onclick="deleteFile('${file.fileId}', '${file.filePath}')">删除</a></div>
			</div>
			</c:forEach>
			</div>
			<div class="page"  id="pageBar">
				<a href="javascript:void(0)" onclick="changePage(${pageInfo.currPage}-1)">上一页 </a>
					跳转到第<input type="text" maxlength="2" size="2" id="pagenum">页
				<input type="button" value="跳转" onclick="changePage($('#pagenum').val())">
				<a href="javascript:void(0)" onclick="changePage(${pageInfo.currPage}+1)">下一页</a> 
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="copyright">&copy;Copyright All Rights Deserved</div>
		<div class="author">AUTHOR: <span class="TiName">flyingPig</span></div>
		<div class="date"></div></div>
	<script type="text/javascript">
		function changePage(pagenum){
			if( pagenum == ${pageInfo.currPage})
				return;
			if( pagenum <= 0)
				return;
			if( pagenum > ${pageInfo.totalPage})
				return;
			window.location.href = "file/changePage.do?pageNum="+pagenum;
		}
		
		function deleteFile(fileId, path){
			window.location.href = "file/delete.do?fileId="+fileId+"&path="+path;
		}
		
		function downloadFile(path){
			alert(path);
			window.location.href = "file/download.do?path="+path;
		}
		
		function checkfile(){
			var file = $("#fileupload");
			if( file.val() == ""){
				alert("请选择文件");
				return false;
			}
			if( file.size == 0){
				alert("这是一个空文件,请重新选择文件");
				return false;
			}
			if( file.size > 50*1024*1024){
				alert("文件大小不能超过50M");
				return false;
			}
			alert(file.val);
			return false;
		}
	</script>
  </body>
</html>
