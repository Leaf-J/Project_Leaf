<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>资源管理</title>
<%@ include file="/WEB-INF/decorators/admin/ref/easyui/commonInclude.jsp"%>
</head>
<body class="easyui-layout">

	<div data-options="region:'center'">
		<table id="dg-1" title="权限管理--->资源管理" class="easyui-datagrid"
			style="height: 500px"
			data-options="toolbar:'#toolbar-1',
			checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,
			fitColumns:true,url:'${requestScope.getData}',pagination:true,onLoadError:loadError">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'name',width:30">资源名</th>
					<th data-options="field:'orderNo',width:30">序号</th>
					<th data-options="field:'url',width:40">URL</th>
					<th
						data-options="field:'parent',width:40,formatter:function(value){if(value) return value.name; else return null;}">父资源名</th>
					<th data-options="field:'label',width:40">资源标签名</th>
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
		style="width: 400px; height: 420px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons-1">
		<div class="ftitle">资源信息</div>
		<form method="post">
			<input type="hidden" name="pid"/>
			<input type="hidden" name="orderNo"/>
			<div class="fitem">
				<label>资源名:</label> <input type="text" name="name"
					class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>资源标签名:</label> <input type="text" name="label"
					class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>URL:</label> <input type="text" name="url"
					class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>父资源:</label> <select name="parent.pid">
					<c:forEach var="res" items="${requestScope.parentRez}">
						<c:choose>
							<c:when test="${res.pid == 1}">
								<option value="${res.pid}" selected="selected">${res.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${res.pid}">${res.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
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
			dg1.init();
		});
	</script>
</body>
</html>



