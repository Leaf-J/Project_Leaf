<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="header-inner">
		<!-- BEGIN LOGO -->
		<a class="navbar-brand"
			href="${pageContext.request.contextPath }/videoadmin/main"> <img
			src="${pageContext.request.contextPath}/adminRes/assets/img/logo.png"
			alt="logo" class="img-responsive" />
		</a>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse"> <img
			src="${pageContext.request.contextPath}/adminRes/assets/img/menu-toggler.png"
			alt="" />
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->

		<!-- BEGIN TOP NAVIGATION MENU -->
		<ul class="nav navbar-nav pull-right">
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" data-hover="dropdown"
				data-close-others="true"> <img alt=""
					src="${pageContext.request.contextPath}/adminRes/assets/img/avatar1_small.jpg" />
					<span class="username"> <sec:authentication property="name" /></span>
					<i class="fa fa-angle-down"></i>
			</a>
				<ul class="dropdown-menu">
					<li><a href="extra_profile.html"><i class="fa fa-user"></i>
							My Profile</a></li>
					<!-- 
					<li class="divider"></li> 分割线-->
					<li><a href="javascript:;" id="trigger_fullscreen"><i
							class="fa fa-move"></i>全屏显示</a></li>
					<li><a
						href="${pageContext.request.contextPath }/videoadmin/logout"><i
							class="fa fa-key"></i>注销</a></li>
				</ul></li>
			<!-- END USER LOGIN DROPDOWN -->
		</ul>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<div class="clearfix"></div>