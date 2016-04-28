<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>幻灯片管理</title>
<%@ include file="/WEB-INF/decorators/admin/ref/easyui/commonInclude.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/adminRes/uploadify/uploadify.css" />
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/uploadify/jquery.uploadify.js"></script>
</head>
<body class="easyui-layout">
	
	<div data-options="region:'center'">
		<table id="dg-1" title="页面管理--->幻灯片管理" class="easyui-datagrid"
			style="height: 500px"
			data-options="toolbar:'#toolbar-1',
			checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,
			fitColumns:true,url:'${requestScope.getData}',pagination:true,onLoadError:loadError,nowrap:false">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'picUrl',width:100, formatter:function(url){ return '<img style=\'height:250px;width:500px;\' src=\''+context_+url+'\' />';}">图片预览</th>					
					<th data-options="field:'link',width:30">连接</th>
					<th data-options="field:'name',width:30">幻灯片组名</th>
					<th data-options="field:'orderNum',width:30">序号</th>
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
		<div class="ftitle">幻灯片信息</div>
		<form method="post">
			<input type="hidden" name="pid" />
			<div class="fitem">
				<label>幻灯片组名:</label> <input type="text" name="name"
					class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label style="vertical-align: top;">连接:</label>
				<input type="text" name="link"
					class="easyui-textbox" required="true" />
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
										'formData':{imageWidth:1139,imageHeight:500,saveFolder:'/banner/image'},
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



