<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.3
Version: 1.5.5
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>四叶草管理后台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">

<!-- INCLUDE COMMON HEAD -->
<%@include file="/WEB-INF/jsp/videoadmin/common/admin_head.jsp"%>

<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/adminRes/assets/plugins/select2/select2_metro.css" />
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link
	href="${pageContext.request.contextPath}/adminRes/assets/css/pages/login.css"
	rel="stylesheet" type="text/css" />

</head>
<!-- BEGIN BODY -->
<body class="login" background="${pageContext.request.contextPath}/adminRes/assets/img/admin_bg.png">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<img
			src="${pageContext.request.contextPath}/adminRes/assets/img/logo-big.png"
			alt="" />
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="login-form" action="${pageContext.request.contextPath}/videoadmin/authen" method="post">
			<h3 class="form-title">账号登陆</h3>
			<div class="alert alert-danger display-hide" 
				<c:if test="${SPRING_SECURITY_LAST_ERR != null}">
					style="display:block"
				</c:if>
			>
				<button class="close" data-close="alert"></button>
				<span>${SPRING_SECURITY_LAST_ERR}</span>
			</div>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="input-icon">
					<i class="fa fa-user"></i> <input
						class="form-control placeholder-no-fix" type="text"
						autocomplete="off" placeholder="用户名" name="username" value="${SPRING_SECURITY_LAST_USERNAME}"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i> <input
						class="form-control placeholder-no-fix" type="password"
						autocomplete="off" placeholder="密码" name="password" value="${SPRING_SECURITY_LAST_PWD}"/>
				</div>
			</div>
			<div class="form-actions">
<!-- 				<label class="checkbox"> <input type="checkbox"
					name="_spring_security_remember_me" value="true" /> 记住我
				</label> -->
				<button type="submit" class="btn green pull-right">
					登录 <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
			<span>
				<a href="#">忘记密码</a>
			</span>
		</form>
		<!-- END LOGIN FORM -->
	</div>
	<!-- END LOGIN -->
	
	<br/>
	<br/>
	<br/>
	<!-- INCLUDE COMMON FOOT -->
	<%@include file="/WEB-INF/jsp/videoadmin/common/admin_foot.jsp"%>

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script
		src="${pageContext.request.contextPath }/adminRes/assets/plugins/jquery-validation/dist/jquery.validate.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/adminRes/assets/plugins/select2/select2.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script
		src="${pageContext.request.contextPath }/adminRes/assets/scripts/app.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath }/adminRes/assets/scripts/login.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {
			App.init();
			Login.init();
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>