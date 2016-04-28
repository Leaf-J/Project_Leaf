<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="${pageContext.request.contextPath}/css/leaf/front/video/watch_video.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/videoPlayer/flowplayer-3.2.13.min.js"></script>


</head>
<body>
	<div class="container">
		<!-- this A tag is where your Flowplayer will be placed. it can be anywhere -->
		<!-- 	<a
		href="${pageContext.request.contextPath }${videoObject.fileUrl}"
		style="display: block; width: 800px; height: 500px" id="player"> </a>
 -->
		<div id="player"></div>
		<div class="navNdesc">
			<div class="navNdesc-banner">
				<ul class="nav nav-tabs">
					<li role="presentation" class="active"><a
						href="javascript:void(0);" tagId="introduction">简介</a></li>
					<li role="presentation"><a href="javascript:void(0);"
						tagId="episode">剧集列表</a></li>
				</ul>
			</div>
			<div class="navNdesc-mainer">
				<div class="show" tagId="introduction">

					<div class="row">
						<div class="col-sm-2 col-md-2">
							<div class="thumbnail">
								<a target="_blank" href="#"><img
									src="${pageContext.request.contextPath}${album.picUrl}"></a>
							</div>
						</div>
						<div class="col-sm-10 col-md-10">
							<div class="abTitle">
								<h4><b>${album.name}</b></h4>
							</div>
							<div class="abDescription"><span style="margin-right:.5em">简介:</span>${album.description}</div>
						</div>
					</div>

				</div>
				<div class="hidden" tagId="episode">
					<c:forEach items="${album.videoObjects}" var="vo">
						<a style="margin-right: 2em" href="${pageContext.request.contextPath}/front/v/ab${album.pid}_${vo.orderNumber}">
						<button type="button" class="btn 
						<c:choose>
							<c:when test="${vo.orderNumber==videoObject.orderNumber}">btn-primary</c:when>
							<c:otherwise>btn-default</c:otherwise>
						</c:choose>
						">第${vo.orderNumber}话</button>
						</a>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>


	<!-- this will install flowplayer inside previous A- tag. -->
	<script>
		$(".navNdesc-banner ul li").click(
				function() {
					var srcObj = $(this).find("a");
					var tagId = $(srcObj).attr("tagId");
					if (tagId) {
						$(srcObj).parent().parent().find("li.active")
								.removeClass("active");
						$(srcObj).parent().addClass("active");
						$(".navNdesc-mainer").find("div.show").attr("class",
								"hidden");
						$(".navNdesc-mainer").find("div[tagId=" + tagId + "]")
								.attr("class", "show");
					}
				});

		flowplayer(
				"player",
				"${pageContext.request.contextPath }/videoPlayer/flowplayer-3.2.18.swf",
				{
					clip : {
						// these two configuration variables does the trick
						autoPlay : false,
						autoBuffering : false, // <- do not place a comma here
						bufferLength : 5
					},
					playlist : [ // playlist is an array of Clips, hence [...]
					"${pageContext.request.contextPath }${videoObject.fileUrl}"// simple playlist entry: video
					]
				});
	</script>
</body>
</html>