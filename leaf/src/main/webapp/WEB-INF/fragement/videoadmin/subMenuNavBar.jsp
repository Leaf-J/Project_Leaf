<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>    
		<div class="portlet-body" style="display: block;">
			<div role="navigation" class="navbar navbar-default">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button data-target=".navbar-ex1-collapse" data-toggle="collapse"
						class="navbar-toggle" type="button">
						<span class="sr-only"> Toggle navigation </span> <span
							class="fa fa-bar"> </span> <span class="fa fa-bar"> </span> <span
							class="fa fa-bar"> </span>
					</button>
					<a href="javascript:;" class="navbar-brand">${admin_sub_page_name}</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<ul class="nav navbar-nav">
 						<core:forEach var="resource" items="${admin_sub_menus}">
							<core:choose>
								<core:when test="${activeSubMenuId == resource.res_id }">
									<li class="active">
								</core:when>
								<core:otherwise>
									<li>
								</core:otherwise>
							</core:choose>
							<a href="${pageContext.request.contextPath}${resource.res_url}?subMenuId=${resource.res_id}">${resource.label}</a>
							</li>		
						</core:forEach>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
		</div>