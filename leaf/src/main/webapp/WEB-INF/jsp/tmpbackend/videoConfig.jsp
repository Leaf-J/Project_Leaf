<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>视频配置</title>
<link href="/leaf/css/bootstrap/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/leaf/css/bootstrap/date_picker/datepicker.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<h3>视频配置</h3>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="tabbable" id="tabs-689208">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-736464" data-toggle="tab">第一部分</a>
						</li>
						<li><a href="#panel-346575" data-toggle="tab">第二部分</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-736464">
							<form:form method="post" cssClass="form-horizontal"
								modelAttribute="videoObject" action="./videoConfig/create">

								<div class="control-group">
									<label class="control-label" for="name">视频名称</label>
									<div class="controls">
										<form:input path="name" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="locaiton">所属地区</label>
									<div class="controls">
										<form:select path="locaiton" items="${videoLocations}"
											itemValue="pid" itemLabel="name"></form:select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="category">所属类别</label>
									<div class="controls">
										<form:select path="category" items="${videoCategories}"
											itemValue="pid" itemLabel="name"></form:select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="publishDate">发行日期</label>
									<div class="date pbshDate controls" id="pbshDateDiv">
										<form:input path="publishDate" readonly="true" />
										<span class="add-on"> <i class="icon-calendar"></i>
										</span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="lables">特征标签</label>
									<div class="controls">
										<c:forEach items="${videoLabels}" var="label"
											varStatus="status">
											<label for="lables${status.index+1}" class="checkbox"><input
												id="lables${status.index+1}" type="checkbox"
												value="${label.pid}" name="lables"> ${label.name}</label>
										</c:forEach>
										<p>
											<br/>
											<button type="submit" class="btn">创建视频</button>
										</p>
									</div>
								</div>

							</form:form>

							<%-- 							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label" for="inputEmail">邮箱</label>
									<div class="controls">
										<input id="inputEmail" type="text" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputPassword">密码</label>
									<div class="controls">
										<input id="inputPassword" type="password" />
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="checkbox"><input type="checkbox" />
											Remember me</label>
										<button type="submit" class="btn">登陆</button>
									</div>
								</div>
							</form> --%>
						</div>
						<div class="tab-pane" id="panel-346575"></div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script type="text/javascript" src="/leaf/js/jquery/jquery.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="/leaf/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="/leaf/js/bootstrap/bootstrap.js"></script>
	<script type="text/javascript"
		src="/leaf/js/bootstrap/date_picker/datepicker.js" charset="UTF-8"></script>

	<script type="text/javascript">
		$('.pbshDate').datepicker({
			format : 'yyyy-mm-dd',
			language : 'zh-CN',
			todayBtn : 'linked',
			autoclose : true
		});
	</script>
</body>
</html>