<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<html>
<head>
<title><decorator:title default="四叶草管理后台" /></title>
<%@ include file="ref/adminHead.jsp"%>
<decorator:head />
</head>
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<%@ include file="ref/adminHeadBar.jsp" %>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
	<%@ include file="ref/adminJs.jsp"%>
	<decorator:body />
	</div>
	<!-- END CONTAINER -->
	<%@ include file="ref/adminFoot.jsp"%>
</body>
<!-- END BODY -->
</html>