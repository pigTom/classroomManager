<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html lang="en"
	class="app">
<head>
    <!-- 包含一些css/js等资源文件 -->

<%@include file="/common/common.jspf"%>

<script type="text/javascript">
	$(function() {
		var tb = $("#loadhtml");
		tb.html(CommnUtil.loadingImg());// 显示加载页面的动画
		tb.load(rootPath+"/welcome.jsp"); // rootPath 根目录， 内嵌welcome.jsp页面
		$("[nav-n]").each(function () { // nav-n 是二级菜单中<a>的属性,给每个菜单加一点击事件
				$(this).bind("click",function(){
                    // 获取属性的值，包括key.name(父菜单名）,kc.name(子菜单名),${kc.resUrl}?id=${kc.id}
						var nav = $(this).attr("nav-n");
						var sn = nav.split(",");
						var html = '<li><i class="fa fa-home"></i>';
						html+='<a href="index.shtml">Home</a></li>';
						for(var i=0;i<2;i++){
							html+='<li><a href="javascript:void(0)">'+sn[i]+'</a></li>';
						}
						$("#topli").html(html);
						var tb = $("#loadhtml");
//                    tb.load(rootPath + "/register.jsp", function () {
//                        alert("load welcome success")
//                    });
//                    tb.load(rootPath+"/user/login.do", function () {
//                        alert("load login success")
//                    })
                    	tb.html(CommnUtil.loadingImg());
						tb.load(rootPath+sn[2]);// 加载页面
				});
			});
		});
</script>
</head>
<base href="<%=basePath%>">
<body class="" style="">
	<section class="vbox">
		<!-- 以上是头部 -->
		<header class="bg-dark dk header navbar navbar-fixed-top-xs">
			<div class="navbar-header aside-md" >
				<a class="btn btn-link visible-xs"
					data-toggle="class:nav-off-screen,open" data-target="#nav,html">
					<i class="fa fa-bars"></i>
				</a> <a href="index.shtml#" class="navbar-brand"
					data-toggle="fullscreen"><img
					src="${ctx}/notebook/notebook_files/logo.png" class="m-r-sm">教室管理系统</a>
				<a class="btn btn-link visible-xs" data-toggle="dropdown"
					data-target=".nav-user"> <i class="fa fa-cog"></i>
				</a>
			</div>
			<ul class="nav navbar-nav hidden-xs">
				<li class="dropdown"><a href="index.html#"
					class="dropdown-toggle dker" data-toggle="dropdown"> <i
						class="fa fa-building-o"></i> <span class="font-bold">Activity</span>
				</a>
					<section
						class="dropdown-menu aside-xl on animated fadeInLeft no-borders lt">
						<div class="wrapper lter m-t-n-xs">
							<a href="index.html#" class="thumb pull-left m-r"> <img
								src="${ctx}/notebook/notebook_files/avatar.jpg"
								class="img-circle">
							</a>
							<div class="clear">
								<a  href="javascript:void(0)"><span class="text-white font-bold">@${user.userName}</span></a>
                                <small class="block">Art Director</small> <a
									href="index.html#" class="btn btn-xs btn-success m-t-xs">Upgrade</a>
							</div>
						</div>
						<div class="row m-l-none m-r-none m-b-n-xs text-center">
							<div class="col-xs-4">
								<div class="padder-v">
									<span class="m-b-xs h4 block text-white">245</span> <small
										class="text-muted">Followers</small>
								</div>
							</div>
							<div class="col-xs-4 dk">
								<div class="padder-v">
									<span class="m-b-xs h4 block text-white">55</span> <small
										class="text-muted">Likes</small>
								</div>
							</div>
							<div class="col-xs-4">
								<div class="padder-v">
									<span class="m-b-xs h4 block text-white">2,035</span> <small
										class="text-muted">Photos</small>
								</div>
							</div>
						</div>
					</section></li>
				<li>
					<div class="m-t m-l">
						<a href="price.html"
							class="dropdown-toggle btn btn-xs btn-primary" title="Upgrade"><i
							class="fa fa-long-arrow-up"></i></a>
					</div>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right m-n hidden-xs nav-user">
				<li class="hidden-xs"><a href="index.html#"
					class="dropdown-toggle dk" data-toggle="dropdown"> <i
						class="fa fa-bell"></i> <span
						class="badge badge-sm up bg-danger m-l-n-sm count"
						style="display: inline-block;">3</span>
				</a>
					<section class="dropdown-menu aside-xl">
						<section class="panel bg-white">
							<header class="panel-heading b-light bg-light">
								<strong>You have <span class="count"
									style="display: inline;">3</span> notifications
								</strong>
							</header>

						</section>
					</section></li>
				<li class="dropdown hidden-xs"><a href="index.html#"
					class="dropdown-toggle dker" data-toggle="dropdown"><i
						class="fa fa-fw fa-search"></i></a>
					<section class="dropdown-menu aside-xl animated fadeInUp">
						<section class="panel bg-white">
							<form role="search">
								<div class="form-group wrapper m-b-none">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="Search">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-info btn-icon">
												<i class="fa fa-search"></i>
											</button>
										</span>
									</div>
								</div>
							</form>
						</section>
					</section></li>
				<li class="dropdown"><a href="index.html#"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="thumb-sm avatar pull-left"> <img
							src="${ctx}/notebook/notebook_files/avatar.jpg">
					</span> ${userFormMap.accountName} <b class="caret"></b>
				</a>
					<ul class="dropdown-menu animated fadeInRight">
						<span class="arrow top"></span>
						<li><a href="index.shtml#">Settings</a></li>
						<li><a href="#" onclick="javascript:updatePasswordLayer();">密码修改</a></li>
						<li><a href="index.html#"> <span
								class="badge bg-danger pull-right">3</span> Notifications
						</a></li>
						<li><a href="docs.html">Help</a></li>
						<li class="divider"></li>
						<li><a href="user_login.jsp">Logout</a></li>
					</ul></li>
			</ul>
		</header>
        <!------------------------------------以上是头部---------------------------------------->



		<section>
			<section class="hbox stretch">

                <!------------------------------以下是左边列表部分-------------------------------->
				<!-- .aside -->
				<aside class="bg-dark lter aside-md hidden-print hidden-xs" id="nav">
					<section class="vbox">
						<!-- <header class="header bg-primary lter text-center clearfix">
							<div class="btn-group">
							系统菜单
							</div>
						</header> -->
						<section class="w-f scrollable">
							<div class="slim-scroll" data-height="auto"
								data-disable-fade-out="true" data-distance="0" data-size="5px"
								data-color="#333333">
								<!-- nav -->
								<nav class="nav-primary hidden-xs">
									<ul class="nav">
										<!-- 第一个一级菜单样式-->
										<li class="active"><a href="javascript:void(0)" class="active">
											<i class="fa fa-dashboard icon">
												<b class="bg-danger"></b></i>
											<span class="pull-right">
												<i class="fa fa-angle-down text"></i>
												<i class="fa fa-angle-up text-active"></i></span>
											<span>用户管理</span></a>

											<ul class="nav lt">
												<li class="active">
													<a href="javascript:void(0)" class="active"
													   nav-n="用户管理,基本信息,/user/userInfoPage.do">
														<i class="fa fa-angle-right"></i>
														<span>基本信息</span>
													</a></li>
												<li class="active">
													<a href="javascript:void(0)" class="active"
													   nav-n="用户管理,密码修改,/user/alertPasswordPage.do">
														<i class="fa fa-angle-right"></i>
														<span>密码修改</span>
													</a></li>
											</ul>
										</li>

										<!-- 第二个一级菜单 -->
										<li><a href="javascript:void(0)">
											<i class="fa fa-pencil-square icon">
												<b class="bg-warning"></b></i>
											<span class="pull-right">
												<i class="fa fa-angle-down text"></i>
												<i class="fa fa-angle-up text-active"></i></span>
											<span>教室管理</span></a>
											<!--  二级菜单  -->
											<ul class="nav lt">
												<!-- 每一个<li>包含一列,<span>里的值是这一列的text -->
												<li>
													<a href="javascript:void(0)"
													   nav-n="教室管理,空闲教室查看,/classroom/freeClassLook.do">
														<i class="fa fa-angle-right"></i>
														<span>空闲教室查看</span>
													</a></li>
												<li>
													<a href="javascript:void(0)"
													   nav-n="教室管理,教室排课查看,/classroom/userLookCourse.do">
														<i class="fa fa-angle-right"></i>
														<span>教室排课查看</span>
													</a></li>
											</ul></li>
										<!-- 第三个一级菜单 -->
										<li><a href="javascript:void(0)">
											<i class="fa fa-columns icon">
												<b class="bg-primary"></b></i>
											<span class="pull-right">
												<i class="fa fa-angle-down text"></i>
												<i class="fa fa-angle-up text-active"></i></span>
											<span>活动管理</span></a>
											<ul class="nav lt">
												<!-- 每一个<li>包含一列,<span>里的值是这一列的text -->
												<li class="active">
													<a href="javascript:void(0)"
													   nav-n="活动管理,查看活动,/welcome.jsp">
														<i class="fa fa-angle-right"></i>
														<span>查看活动</span>
													</a></li>
											</ul></li>
										<!-- 第四个一级菜单 -->
										<li><a href="javascript:void(0)">
											<i class="fa fa-th-list icon">
												<b class="bg-success"></b></i>
											<span class="pull-right">
												<i class="fa fa-angle-down text"></i>
												<i class="fa fa-angle-up text-active"></i></span>
											<span>公告</span></a>
											<ul class="nav lt">
												<!-- 每一个<li>包含一列,<span>里的值是这一列的text -->
												<li class="active">
													<a href="javascript:void(0)"
													   nav-n="公告,查看公告,/welcome.jsp">
														<i class="fa fa-angle-right"></i>
														<span>查看公告</span>
													</a></li>
												<li class="active">
													<a href="javascript:void(0)"
													   nav-n="公告,发布公告,/welcome.jsp">
														<i class="fa fa-angle-right"></i>
														<span>发布公告</span>
													</a></li>
											</ul></li>

										<!--  二级菜单  -->

									</ul>
								</nav>
								<!-- / nav -->
							</div>
						</section>

                        <!------------------------左下facebook窗口---------------------->
						<footer class="footer lt hidden-xs b-t b-dark">
							<div id="chat" class="dropup">
								<section class="dropdown-menu on aside-md m-l-n">
									<section class="panel bg-white">
										<header class="panel-heading b-b b-light">Active
											chats</header>
										<div class="panel-body animated fadeInRight">
											<p class="text-sm">No active chats.</p>
											<p>
												<a href="#" class="btn btn-sm btn-default">Start a chat</a>
											</p>
										</div>
									</section>
								</section>
							</div>
							<div id="invite" class="dropup">
								<section class="dropdown-menu on aside-md m-l-n">
									<section class="panel bg-white">
										<header class="panel-heading b-b b-light">
											John <i class="fa fa-circle text-success"></i>
										</header>
										<div class="panel-body animated fadeInRight">
											<p class="text-sm">No contacts in your lists.</p>
											<p>
												<a href="#" class="btn btn-sm btn-facebook"><i
													class="fa fa-fw fa-facebook"></i> Invite from Facebook</a>
											</p>
										</div>
									</section>
								</section>
							</div>
							<a href="#nav" data-toggle="class:nav-xs"
								class="pull-right btn btn-sm btn-dark btn-icon"> <i
								class="fa fa-angle-left text"></i> <i
								class="fa fa-angle-right text-active"></i>
							</a>
							<div class="btn-group hidden-nav-xs">
								<button type="button" title="Chats"
									class="btn btn-icon btn-sm btn-dark" data-toggle="dropdown"
									data-target="#chat">
									<i class="fa fa-comment-o"></i>
								</button>
								<button type="button" title="Contacts"
									class="btn btn-icon btn-sm btn-dark" data-toggle="dropdown"
									data-target="#invite">
									<i class="fa fa-facebook"></i>
								</button>
							</div>
						</footer>
                        <!----------------------以上是左下facebook小窗口---------------->
					</section>
				</aside>
                <!------------------------------以上是左边列表部分-------------------------------->





                <!----------------------------------内容部分---------------------------------->
				<!-- /.aside -->
				<section id="content">
					<section id="id_vbox" class="vbox">
						<ul class="breadcrumb no-border no-radius b-b b-light" id="topli">
						</ul>
						<section class="scrollable" style="margin-top: 35px;">
                            <!-- 加载外部页面 -->
						<div id="loadhtml"></div>
						</section>
					</section>
				</section>


				<aside class="bg-light lter b-l aside-md hide" id="notes">
					<div class="wrapper">Notification</div>
				</aside>
			</section>
		</section>
	</section>


	<!-- Bootstrap -->
	<div id="flotTip" style="display: none; position: absolute;"></div>


</body>
</html>