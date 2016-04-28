<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>权限配置</title>
<%@ include file="/WEB-INF/decorators/admin/ref/easyui/commonInclude.jsp"%>
</head>
<body class="easyui-layout">

	<div data-options="region:'center'">
		<table id="dg-1" title="权限管理--->权限配置" class="easyui-datagrid"
			style="height: 500px"
			data-options="toolbar:'#toolbar-1',
			checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,
			fitColumns:true,url:'${requestScope.getData}',pagination:true,onLoadError:loadError">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th
						data-options="field:'role',width:30,formatter:function(value){return value.name;}">角色</th>
					<th
						data-options="field:'resource',width:30,formatter:function(value){return value.name;}">资源</th>
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
		<div class="ftitle">权限信息</div>
		<form method="post">
			<input type="hidden" name="pid" />
			<div class="fitem">
				<label>角色:</label> <select name="role.pid">
					<c:forEach var="role" items="${requestScope.roles}">
						<c:choose>
							<c:when test="${role.pid == 1}">
								<option value="${role.pid}" selected="selected">${role.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${role.pid}">${role.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="fitem">
				<label>资源:</label> <select name="resource.pid">
					<c:forEach var="res" items="${requestScope.resources}">
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
			<div >
				<label>操作权限:</label><br/>
				<input type="hidden" id="oprPids" name="oprPids"/>
				<c:forEach var="opr" items="${requestScope.operations}"
					varStatus="status">
					<input type="checkbox" name="oprsPid" value="${opr.pid }" />${opr.description }
					<c:if test="status%2==0">
						<br />
					</c:if>
				</c:forEach>
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
				validateForm:function(form){
					var oprs = $("input[type=checkbox][name='oprsPid']:checked");
					var oprIds = [];
					if(oprs){
						for(var i=0;i<oprs.length;i++){
							oprIds.push(oprs[i].value);
						}
					}
					$("#oprPids").val(oprIds.join(","));
				},
				formLoadData:function(data){
					DataGridExt.prototype.formLoadData.call(this,data);
					if(data.oprs){
						var oprIds = [];
						$(data.oprs).each(function(idx){
							oprIds.push(this.pid);
						});
						$("input[type=checkbox][name='oprsPid']").each(function(idx){
							if($.inArray(parseInt(this.value), oprIds)>-1 ){
								this.checked = true;
							}
						});
						$("#oprPids").val(oprIds.join(","));
					}
				}
			});
			dg1.init();
		});
	</script>
</body>
</html>



