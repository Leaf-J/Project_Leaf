<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>四叶草管理后台</title>
<%@ include file="/WEB-INF/decorators/admin/ref/easyui/commonInclude.jsp"%>
</head>

<body class="easyui-layout" style="text-align: left">
	<div data-options="region:'north',border:false" >

		<div id="inner-header">
			<div style="text-align: right;">
				<sec:authentication property="name" />
				&nbsp;&nbsp;|&nbsp;&nbsp; <a
					href="${pageContext.request.contextPath }/videoadmin/logout">[退出系统]</a>
				&nbsp;&nbsp;|&nbsp;&nbsp; <a id="changePassword" href="#">修改密码</a>
			</div>


		</div>
	
	</div>
	
	<div id ="leftMenu" data-options="region:'west',title:'管理导航',split:true"
		style="width: 150px;">
		<ul id="subMenus" class="easyui-tree" data-options="animate:true">
			
		</ul>  

	</div>	
	
	<div data-options="region:'center'">
		<div id="main-tabs" class="easyui-tabs"
			data-options="fit:true,border:false,plain:true">
			<div title="Welcome" href="${pageContext.request.contextPath}/html/videoadmin/welcome.html"></div>
		</div>
	</div>	
	<script type="text/javascript">
		//页面渲染时加载第一级树目录
		$(document).ready(function(){
			$("#subMenus").tree({
				url:'${pageContext.request.contextPath}/videoadmin/main/getNavTreeNodes',
				onClick: function(node){
					if(!node.state){ //资源节点才有页面
						openTab(node.text,node.attributes.url);
					}
				}
			});
		}); 
		
		var mainTabs = $("#main-tabs");
		function openTab(title,url){
			if(mainTabs.tabs('exists',title)){
				mainTabs.tabs('select',title);
				var iframeContext = mainTabs.tabs('getTab',title).find("iframe");
				if(iframeContext){
					iframeContext[0].src = context_ + url;
				}
			}else{
				mainTabs.tabs('add',{
					title:title,
					content:createFrame(context_+url),
					closable:true
				});
			}
		}
		
		function createFrame(url){
			var s = '<iframe name="mainFrame" scrolling="auto" frameborder="no" border="0" marginwidth="0" marginheight="0"  allowtransparency="yes" src="'
				+ url + '" style="width:100%;height:99%;"></iframe>';
			return s;
		}
		
	</script>
</body>
</html>