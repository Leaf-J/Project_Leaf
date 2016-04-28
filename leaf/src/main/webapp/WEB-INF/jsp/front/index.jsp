<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
	.recommend{
		float:left;
		margin-right: 5px;
	}
	
	.recommend img{
		width:122px;
	}
	
	.recommend .title{
		font-size:12px;
		display: block;
		width:122px;
	}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/plugins/carousel.js"></script>
</head>

<body>

	<div class="container mainer-inner" style="margin-top:10px;margin-bottom:10px;">

		<div id="carousel-example-generic" class="carousel slide"
			data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<c:forEach items="${banners}" var="banner" varStatus="status">
				<c:choose>
					<c:when test="${status.index==0}">
						<div class="item active">
					</c:when>
					<c:when test="${status.index!=0}">
						<div class="item">
					</c:when>				
				</c:choose>
					<a href="${banner.link }" target="blank">
						<img
							src="${pageContext.request.contextPath}${banner.picUrl}"
							alt="" style="height:500px;width:1139px;"/>					
					</a>
						<div class="carousel-caption"></div>
					</div>				
				</c:forEach>
			</div>

			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-example-generic"
				role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>

		<div style="margin: 20px 5px 120px 0px;"> 
			<div >
				<div><span style="font-size: 17px;font-weight: bold;color:#428bca;font-family: microsoft yahei;cursor: default;">猜你喜欢</span><hr style="margin-top:5px;"/>		
				</div>
							<c:choose>
								<c:when test="${recommendList != null }">
									<c:forEach items="${recommendList}" var="item" varStatus="status">
										<c:if test="${status.index < 7}">
											<div class="recommend" >
												<a class="thumb" target="_blank" href="${pageContext.request.contextPath}/front/video/${item.video.pid}"><img
													class="preview preview-delay"
													src=" ${pageContext.request.contextPath}${item.video.picUrl}">
				
													<div class="superscript recom"></div> </a><a class="title"
													title="${item.video.title}"
													target="_blank" href="${pageContext.request.contextPath}/front/video/${item.video.pid}">${item.video.title}</a>
											</div>													
										</c:if>
									</c:forEach>									
								</c:when>
								<c:otherwise>
									<c:forEach items="${animations}" var="item" varStatus="status">
										<c:if test="${status.index < 7}">
											<div class="recommend" >
												<a class="thumb" target="_blank" href="${pageContext.request.contextPath}/front/video/${item.video.pid}"><img
													class="preview preview-delay"
													src=" ${pageContext.request.contextPath}${item.video.picUrl}">
				
													<div class="superscript recom"></div> </a><a class="title"
													title="${item.title}"
													target="_blank" href="${pageContext.request.contextPath}/front/video/${item.video.pid}">${item.title}</a>
											</div>													
										</c:if>
									</c:forEach>							
								</c:otherwise>
									
							</c:choose>

			</div>
		</div>
		
		<div class="area">
			<div class="area-inner">
				<div class="area-left">
					<div class="block block-unit">
						<div class="banner">
							<a class="title-main" title="动画" target="_blank"
								href="/v/list1/index.htm">动画</a> / <a class="title-sub"
								title="更多动画视频" target="_blank" href="/v/list1/index.htm"> 更多</a>

							<div class="area-right" style="padding-left: 60px;">
								<a class="tab" title="千斗五十铃Lamb" target="_blank"
									href="/v/ac1546437">千斗五十铃Lamb</a> <a class="tab" title="豆知识"
									target="_blank" href="/v/ac1546493">豆知识</a>
							</div>
						</div>
						<div class="mainer">
						<c:forEach items="${animations}" var="item">
							<div class="unit unit-0">
								<a class="thumb" target="_blank" href="${pageContext.request.contextPath}/front/video/${item.video.pid}"><img
									class="preview preview-delay"
									src=" ${pageContext.request.contextPath}${item.video.picUrl}">

									<p class="time">24:17</p>

									<div class="superscript recom"></div> </a><a class="title"
									title="${item.title}"
									target="_blank" href="${pageContext.request.contextPath}/front/video/${item.video.pid}">${item.title}</a>
							</div>						
						</c:forEach>

						</div>
					</div>
				</div>
				<div class="area-right">
					<div class="block">
						<div class="banner">
							<p class="tab fixed" title="新番更新列表">新番更新列表</p>
						</div>
						<div class="mainer">
							<ul>
								<c:forEach begin="1" end="8"  >
									<li style="border-bottom: 1px dashed #eee;"><b
										style="font-family: 'Microsoft YaHei'; color: #ccc; padding-right: 3px;">4</b>
										<a class="title" title="钻石王牌 第二季" target="_blank"
										href="http://www.acfun.tv/v/ab1465812_0">钻石王牌 第二季</a></li>								
								</c:forEach>
							</ul>
						</div>
					</div>

				</div>
			</div>

		</div>


		<div class="area">
			<div class="area-inner">
				<div class="area-left">
					<div class="block block-unit">
						<div class="banner">
							<a class="title-main" title="音乐" target="_blank"
								href="/v/list1/index.htm">音乐</a> / <a class="title-sub"
								title="更多音乐视频" target="_blank" href="/v/list1/index.htm"> 更多</a>

							<div class="area-right" style="padding-left: 60px;">
								<a class="tab" title="千斗五十铃Lamb" target="_blank"
									href="/v/ac1546437">千斗五十铃Lamb</a> <a class="tab" title="豆知识"
									target="_blank" href="/v/ac1546493">豆知识</a>
							</div>
						</div>
						<div class="mainer">
						<c:forEach items="${music}" var="item">
							<div class="unit unit-0">
								<a class="thumb" target="_blank" href="${pageContext.request.contextPath}/front/video/${item.video.pid}"><img
									class="preview preview-delay"
									src=" ${pageContext.request.contextPath}${item.video.picUrl}">

									<p class="time">24:17</p>

									<div class="superscript recom"></div> </a><a class="title"
									title="${item.title}"
									target="_blank" href="${pageContext.request.contextPath}/front/video/${item.video.pid}">${item.title}</a>
							</div>						
						</c:forEach>

						</div>
					</div>
				</div>
				<div class="area-right">
					<div class="block">
						<div class="banner">
							<p class="tab fixed" title="新番更新列表">新番更新列表</p>
						</div>
						<div class="mainer">
							<ul>
								<c:forEach begin="1" end="8"  >
									<li style="border-bottom: 1px dashed #eee;"><b
										style="font-family: 'Microsoft YaHei'; color: #ccc; padding-right: 3px;">4</b>
										<a class="title" title="钻石王牌 第二季" target="_blank"
										href="http://www.acfun.tv/v/ab1465812_0">钻石王牌 第二季</a></li>								
								</c:forEach>
							</ul>
						</div>
					</div>

				</div>
			</div>

		</div>
		
	</div>


	<script type="text/javascript">
		$('.carousel').carousel();
	</script>
</body>
</html>
