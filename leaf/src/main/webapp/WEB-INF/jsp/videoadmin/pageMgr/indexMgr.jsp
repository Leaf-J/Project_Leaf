<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>引导页视频管理</title>
<%@ include file="/WEB-INF/decorators/admin/ref/easyui/commonInclude.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/adminRes/uploadify/uploadify.css" />
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/uploadify/jquery.uploadify.js"></script>
</head>
<body class="easyui-layout">

	<div data-options="region:'center'">
		<table id="dg-1" title="页面管理--->引导页视频管理" class="easyui-datagrid"
			style="height: 500px"
			data-options="toolbar:'#toolbar-1',
			checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,
			fitColumns:true,url:'${requestScope.getData}',pagination:true,onLoadError:loadError,nowrap:false">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th
						data-options="field:'video',width:30,formatter:function(obj){ return '<img src=\' '+context_+obj.picUrl+'\' />';}"">截图
					</th>
					<th data-options="field:'title',width:30">标题</th>
					<th data-options="field:'pageName',width:30">页面标识</th>
					<th data-options="field:'sectionName',width:30">区段标识</th>
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
				<label style="vertical-align: top;">页面标识:</label>
				<input type="text" class="easyui-textbox" name="pageName"></textarea>
			</div>
			<div class="fitem">
				<label style="vertical-align: top;">区段标识:</label>
				<input type="text" class="easyui-textbox" name="sectionName"></textarea>
			</div>			
			<div class="fitem">
				<label>引用视频:</label>
				<input name="video.pid" type="hidden"/>
				<input id="indexMgrRefVdo">
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
			$.extend(dg1,{
				formLoadData : function(data) {
					DataGridExt.prototype.formLoadData.call(
							this, data);
					if (data.video) {
						$("input[name='video.pid']").val(data.video.pid);
						$('#indexMgrRefVdo').combogrid('setValue', data.video.title);
					}
				}				
			});
			dg1.init();
			
				$('#indexMgrRefVdo').combogrid({
				    delay: 500,
				    mode: 'remote',
				    url: context_+'/videoadmin/videoMgr/objMgr/getLikeTitle',
				    method:'post',
				    idField: 'pid',
				    textField: 'title',
				    fit: true,
				    panelWidth: 600,
				    fitColumns: true,
				    required:true,
				    columns: [[
				        {field:'title',title:'标题',width:60,sortable:true},
				        {field:'description',title:'描述',width:60},
				        {field:'picUrl',title:'截图',width:30,formatter:function(url){
				        	return '<img style=\'width:100px;height:50px;\'src=\' '+context_+url+'\' />';
				        }}
				    ]],
				    onSelect: function () {//选中处理
				   		$("input[name='video.pid']").val($('#indexMgrRefVdo').combogrid('grid').datagrid('getSelected').pid);
       				 }
				});
			});
	</script>
</body>
</html>



