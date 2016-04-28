<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>视频对象管理</title>
<%@ include file="/WEB-INF/decorators/admin/ref/easyui/commonInclude.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/adminRes/uploadify/uploadify.css" />
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/uploadify/jquery.uploadify.js"></script>
</head>
<body class="easyui-layout">

	<div data-options="region:'center'">
		<table id="dg-1" title="视频管理--->视频对象管理" class="easyui-datagrid"
			style="height: 500px"
			data-options="toolbar:'#toolbar-1',
			checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,
			fitColumns:true,url:'${requestScope.getData}',pagination:true,onLoadError:loadError,nowrap:false">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th
						data-options="field:'picUrl',width:30,formatter:function(url){ return '<img src=\' '+context_+url+'\' />';}"">截图
					</th>
					<th data-options="field:'title',width:30">标题</th>
					<th data-options="field:'clickCount',width:30">点击量</th>
					<th data-options="field:'description',width:30">描述</th>
					<th data-options="field:'fileUrl',width:30">视频文件</th>
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
		style="width: 400px; height: 450px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons-1">
		<div class="ftitle">视频信息</div>
		<form method="post">
			<input type="hidden" name="pid" />
			<div class="fitem">
				<label>标题:</label> <input type="text" name="title"
					class="easyui-textbox" />
			</div>
			<div class="fitem">
				<label>序号:</label> <input type="text" name="orderNumber"
					class="easyui-textbox" />
			</div>			
			<div class="fitem">
				<label style="vertical-align: top;">描述:</label>
				<textarea rows="4" cols="16" name="description"></textarea>
			</div>
			<div class="fitem">
				<label>所属专辑:</label>
				<input name="album.pid" type="hidden"/>
				<input id="cc">
			</div>
			<div class="fitem">
				<label>图片:</label> 
				<input class="easyui-textbox" id="picUrl" name="picUrl" readonly="readonly">
				<label/>
				<input type="file" id="uploadImg"  name="uploadImg" />					
			</div>
			<div class="fitem">
				<label>视频文件:</label> 
				<input class="easyui-textbox" id="fileUrl" name="fileUrl" readonly="readonly">
				<label/>
				<input type="file" id="uploadFile"  name="uploadFile" />					
			</div>
			<div id="fileQueue"></div>			
		</form>
	</div>
	<div id="dlg-buttons-1">
		<a href="#" class="easyui-linkbutton  save" iconCls="icon-ok">保存</a> <a
			href="#" class="easyui-linkbutton cancel" iconCls="icon-cancel">取消</a>
	</div>

	<script type="text/javascript">
		$(function() {
			var dg1 = new DataGridExt(context_, 1);
			$.extend(dg1,{
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
						'formData':{imageWidth:208,imageHeight:116,saveFolder:'/video/image'},
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
					$("#uploadFile").uploadify({
						'swf'       : context_+'/js/uploadify/uploadify.swf',
						'uploader'         : context_+'/upload/uploadFile.do',
						'cancelImg'      : context_+'/js/uploadify/uploadify-cancel.png',
						'queueID'        : 'fileQueue',
						'auto'           : true,
						'multi'          : false,
						'buttonText'	 : '视频 上传',
						'fileObjName':'uploadFile',
						'method':'post',
						'width':70,
						'height':20,
						'formData':{saveFolder:'/video/file'},
						'fileSizeLimit'   : '100MB',            
						'fileTypeDesc'    : '视频上传',
						'fileTypeExts'    : '*.rmvb; *.avi; *.flv;', 
						'onUploadSuccess' : function(file, data, response) {
							var json = jQuery.parseJSON(data);
							$("#fileUrl").val(json.data.fileUrl);
			        },
			        	'onUploadStart':function(){
			        		this_.dlgBtn.find('.save').linkbutton('disable');
			        	},
			        	'onUploadComplete':function(){
			        		this_.dlgBtn.find('.save').linkbutton('enable');
			        	}
					}
					);					
				},
				formLoadData : function(data) {
					DataGridExt.prototype.formLoadData.call(
							this, data);
					if (data.album) {
						$("input[name='album.pid']").val(data.album.pid);
						$('#cc').combogrid('setValue', data.album.name);
					}
				}				
			});
			dg1.init();
			
				$('#cc').combogrid({
				    delay: 500,
				    mode: 'remote',
				    url: context_+'/videoadmin/videoMgr/objMgr/getAlbums',
				    method:'post',
				    idField: 'pid',
				    textField: 'name',
				    fit: true,
				    panelWidth: 400,
				    fitColumns: true,
				    required:true,
				    columns: [[
				        {field:'name',title:'名称',width:60,sortable:true},
				        {field:'description',title:'描述',width:60},
				        {field:'location',title:'地区',width:30,formatter:function(obj){
				        	return obj.name;
				        }},
				        {field:'publishDate',title:'发行日期',width:30}
				    ]],
				    onSelect: function () {//选中处理
				   		$("input[name='album.pid']").val($('#cc').combogrid('grid').datagrid('getSelected').pid);
       				 }
				});
			});
	</script>
</body>
</html>



