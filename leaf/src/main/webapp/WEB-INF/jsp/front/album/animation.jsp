<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="${pageContext.request.contextPath}/css/leaf/front/album/album.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
		<%@include file="areaFilter.jsp"%>
		<div id="albumPage" class="albumPage">
				<c:forEach items="${albumList}" var="album">
				
				<div class="col-sm-2 col-md-2 albumBlock">
					<div class="thumbnail">
						<a target="_blank" href="${pageContext.request.contextPath}/front/v/ab${album.pid}"><img
							src="${pageContext.request.contextPath}${album.picUrl}"
							></a>
						<div class="caption info">
<a href="${pageContext.request.contextPath}/front/v/ab${album.pid}" target="_blank" title="${album.name}" class="title">${album.name}</a>
<p class="view">播放：<span>10万</span></p>
						</div>
					</div>
				</div>
				</c:forEach>
		</div>


	</div>
	
</body>
</html>