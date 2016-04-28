<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>视频专辑管理</title>
<%@ include file="/WEB-INF/decorators/admin/ref/easyui/commonInclude.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/adminRes/uploadify/uploadify.css" />
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/uploadify/jquery.uploadify.js"></script>
</head>
<body class="easyui-layout">
	
	<div data-options="region:'center'">
		<table id="dg-1" title="视频管理--->视频专辑管理" class="easyui-datagrid"
			style="height: 500px"
			data-options="toolbar:'#toolbar-1',
			checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,
			fitColumns:true,url:'${requestScope.getData}',pagination:true,onLoadError:loadError,nowrap:false">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'picUrl',width:150, formatter:function(url){ return '<img src=\''+context_+url+'\' />';}">专辑名</th>					
					<th data-options="field:'name',width:30">专辑名</th>
					<th data-options="field:'description',width:30">描述</th>
					<th data-options="field:'language',width:30">语言</th>
					<th
						data-options="field:'category',width:30,
						formatter:function(category){
							return category.name;
						}">类别</th>
					<th
						data-options="field:'labels',width:30,
						formatter:function(labels){
							if(labels){
								var arr = [];
								for(var i=0;i<labels.length;
						i++){
									arr.push(labels[i].name);
								}
								return arr.join(',');
							}
						}">标签</th>
					<th
						data-options="field:'location',width:30,
						formatter:function(location){
							return location.name;
						}">地区</th>
					<th data-options="field:'publishDate',width:30">发行日期</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="toolbar-1">
		<c:forEach var="opr" items="${requestScope.opr_list}">
			<c:if test="${opr == 'add'}">
				<a href="#" class="easyui-linkbutton add" iconCls="icon-add"
					plain="true">新增</a>
			</c:if>
			<c:if test="${opr == 'modify'}">
				<a href="#" class="easyui-linkbutton edit" iconCls="icon-edit"
					plain="true">修改</a>
			</c:if>
			<c:if test="${opr == 'delete'}">
				<a href="#" class="easyui-linkbutton remove" iconCls="icon-remove"
					plain="true">删除</a>
			</c:if>
		</c:forEach>
	</div>

	<div id="dlg-1" class="easyui-dialog"
		style="width: 400px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons-1">
		<div class="ftitle">资源信息</div>
		<form method="post">
			<input type="hidden" name="pid" />
			<div class="fitem">
				<label>专辑名:</label> <input type="text" name="name"
					class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label style="vertical-align: top;">描述:</label>
				<textarea rows="4" cols="16" name="description"></textarea>
			</div>
			<div class="fitem">
				<label>语言:</label> <input type="text" name="language"
					class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>发行日期:</label> <input name="publishDate"
					class="easyui-datebox" required="true" />
			</div>
			<div class="fitem">
				<label>所属地区:</label> <select name="location.pid">
					<c:forEach var="location" items="${requestScope.locations}">
						<c:choose>
							<c:when test="${location.pid == 1}">
								<option value="${location.pid}" selected="selected">${location.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${location.pid}">${location.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="fitem">
				<label>所属类别:</label> <select name="category.pid">
					<c:forEach var="category" items="${requestScope.categories}">
						<c:choose>
							<c:when test="${category.pid == 1}">
								<option value="${category.pid}" selected="selected">${category.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${category.pid}">${category.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div>
				<label>标签:</label> <input type="hidden" id="labels"
					name="labelPids" />
					<label></label>
					<div>
						<c:forEach var="label" items="${requestScope.labels}"
							varStatus="status">
							<input type="checkbox" style="vertical-align: bottom;"
								name="labelPid" value="${label.pid }" />${label.name }
							<c:if test="${status.index >0 and status.index%4==0}">
								<br />
							</c:if>
						</c:forEach>
				</div>
			</div>
			<div class="fitem">
				<label>图片:</label> 
				<input class="easyui-textbox" id="picUrl" name="picUrl" readonly="readonly">
				<label/>
				<input type="file" id="uploadImg"  name="uploadImg" />
				<div id="fileQueue" class="fileQueue"></div>					
			</div>
		</form>
	</div>
	<div id="dlg-buttons-1">
		<a href="#" class="easyui-linkbutton  save" iconCls="icon-ok">保存</a> <a
			href="#" class="easyui-linkbutton cancel" iconCls="icon-cancel">取消</a>
	</div>

	<script type="text/javascript">
		$(function() {
			var dg1 = new DataGridExt(context_, 1);
			$
					.extend(
							dg1,
							{
								validateForm : function(form) {
									var labels = $("input[type=checkbox][name='labelPid']:checked");
									var labelIds = [];
									if (labels) {
										for ( var i = 0; i < labels.length; i++) {
											labelIds.push(labels[i].value);
										}
									}
									$("#labels").val(labelIds.join(","));
								},
								formLoadData : function(data) {
									DataGridExt.prototype.formLoadData.call(
											this, data);
									if (data.labels) {
										var labelIds = [];
										$(data.labels).each(function(idx) {
											labelIds.push(this.pid);
										});
										$(
												"input[type=checkbox][name='labelPid']")
												.each(
														function(idx) {
															if ($
																	.inArray(
																			parseInt(this.value),
																			labelIds) > -1) {
																this.checked = true;
															}
														});
										$("#labels").val(labelIds.join(","));
									}
								},
								init:function(){
									DataGridExt.prototype.init.call(this);
									var this_ = this;
									$("#uploadImg").uploadify({
										'swf'       : context_+'/js/uploadify/uploadify.swf',
										'uploader'         : context_+'/upload/uploadImage.do',
										'cancelImg'      : context_+'/js/uploadify/uploadify-cancel.png',
										'queueID'        : 'fileQueue',
										'auto'           : true,
										'multi'          : false,
										'buttonText'	 : '图片上传',
										'fileObjName':'uploadImg',
										'method':'post',
										'width':70,
										'height':20,
										'formData':{imageWidth:400,imageHeight:400,saveFolder:'/album/image'},
										'fileSizeLimit'   : '1MB',            
										'fileTypeDesc'    : '图片上传',
										'fileTypeExts'    : '*.png; *.jpg; *.bmp;*.jpeg', 
										'onUploadSuccess' : function(file, data, response) {
											var json = jQuery.parseJSON(data);
											$("#picUrl").val(json.data.picUrl);
							        },
							        	'onUploadStart':function(){
							        		this_.dlgBtn.find('.save').linkbutton('disable');
							        	},
							        	'onUploadComplete':function(){
							        		this_.dlgBtn.find('.save').linkbutton('enable');
							        	}
									}
									);   									
								}
							});
			dg1.init();
			
		});
	</script>
</body>
</html>



