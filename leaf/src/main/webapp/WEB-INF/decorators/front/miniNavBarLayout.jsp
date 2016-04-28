<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<html>
<head>
<title><decorator:title default="四叶草" /></title>
<%@ include file="ref/head.jsp"%>
<decorator:head />
</head>
<body>
	<%@ include file="ref/miniNavgiate_bar.jsp"%>
	<decorator:body />
	<%@ include file="ref/foot.jsp"%>
</body>
</html>