<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>视频标签管理</title>
<%@ include file="/WEB-INF/decorators/admin/ref/easyui/commonInclude.jsp"%>
</head>
<body class="easyui-layout">

	<div data-options="region:'center'">
		<table id="dg-1" title="视频管理--->视频标签管理" class="easyui-datagrid"
			style="height: 500px"
			data-options="toolbar:'#toolbar-1',
			checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,
			fitColumns:true,url:'${requestScope.getData}',pagination:true,onLoadError:loadError">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'name',width:30">标签名</th>
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
			<div class="fitem">
				<label>标签名:</label> <input type="text" name="name"
					class="easyui-textbox" required="true" />
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



