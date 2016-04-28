<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>角色管理</title>
<%@ include file="/WEB-INF/decorators/admin/ref/easyui/commonInclude.jsp"%>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="dg-1" title="权限管理--->角色管理" class="easyui-datagrid"
			style="height: 500px"
			data-options="toolbar:'#toolbar-1',
			checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,
			fitColumns:true,url:'${requestScope.getData}',pagination:true,onLoadError:loadError">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'name',width:30">角色名</th>
					<th data-options="field:'description',width:30">角色描述</th>
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
		style="width: 400px; height: 420px; padding: 0" closed="true"
		buttons="#dlg-buttons-1">
		<form method="post">
			<div class="ftitle">角色信息</div>
			<input type="hidden" name="pid" />
			<table class="table-info-form">
				<tr>
					<td class="info-label">角色名：</td>
					<td class="info-controller"><input name="name" required="true" /></td>
				</tr>
				<tr>
					<td class="info-label">角色描述：</td>
					<td class="info-controller"><textarea rows="4" cols="30"
							name="description"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons-1">
		<a href="#" class="easyui-linkbutton  save" iconCls="icon-ok">保存</a> <a
			href="#" class="easyui-linkbutton cancel" iconCls="icon-cancel">取消</a>
	</div>
	<script type="text/javascript">
		$(function() {
			var dg1 = new DataGridExt(context_, 1);
			dg1.init();
		});
	</script>
</body>
</html>



