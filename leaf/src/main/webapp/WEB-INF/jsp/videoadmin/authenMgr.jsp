<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
	<div class="page-content">
		<%@ include file="/WEB-INF/fragement/videoadmin/subMenuNavBar.jsp" %>
	</div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script
	src="${pageContext.request.contextPath}/adminRes/assets/scripts/app.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script>
	jQuery(document).ready(function() {
		App.init(); // initlayout and core plugins
	});
</script>