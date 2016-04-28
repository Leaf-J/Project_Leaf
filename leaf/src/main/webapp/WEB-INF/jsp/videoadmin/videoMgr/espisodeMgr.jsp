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
	<script type="text/javascript">
$(function() {
	var dg1 = new DataGridExt(context_, 1);
	dg1.init();
	});
</script>
</head>
<body class="easyui-layout">


	<div data-options="region:'center'">
		<table id="dg-1" title="视频管理--->剧集管理" class="easyui-datagrid"
			style="height: 500px"
			data-options="toolbar:'#toolbar-1',
			checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,
			fitColumns:true,url:'${requestScope.getData}',pagination:true,onLoadError:loadError,nowrap:false">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'orderNumber',width:30">剧集序号</th>
					<th data-options="field:'description',width:30">描述</th>
					<th data-options="field:'videoObject',width:30,formatter:function(obj){return obj.title;}">视频标题</th>
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
		buttons="#dlg-buttons-1" >
		<div class="ftitle">剧集信息</div>
		<form method="post">
			<input type="hidden" name="pid" />
			<div class="fitem">
				<label>剧集序号:</label> <input type="text" name="orderNumber"
					class="easyui-textbox" />
			</div>
			<div class="fitem">
				<label style="vertical-align: top;">描述:</label>
				<textarea rows="4" cols="16" name="description"></textarea>
			</div>
			<div class="fitem">
				<label>视频Id:</label>
				<input name="videoObject.pid" type="text"/>
			</div>			
		</form>
	</div>
	<div id="dlg-buttons-1">
		<a href="#" class="easyui-linkbutton  save" iconCls="icon-ok">保存</a> <a
			href="#" class="easyui-linkbutton cancel" iconCls="icon-cancel">取消</a>
	</div>
</body>
</html>



