<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<html>
<head>
<title><decorator:title default="四叶草" /></title>
<%@ include file="ref/head.jsp"%>
<decorator:head />
</head>
<body> 
	<%@ include file="ref/navgiate_bar.jsp"%>
	<decorator:body />
	<%@ include file="ref/foot.jsp"%>
	
	<script type="text/javascript">
		$(function(){
			var memberLogin = function(){
				var email = $("#loginForm input[id=email]").val();
				var pwd = $("#loginForm input[id=password]").val();
				
				$.ajax( {    
				    url: '/leaf/front/login/doLogin',// 跳转到 action    
				    data:{    
				             "email" : email,    
				             "password" : pwd     
				    },    
				    type:'post',    
				    dataType:'json',    
				    success:function(data) {    
						location.reload();
				     }
				});  

			};
			
			$("#loginForm button[name=submit]").bind("click",memberLogin);
		});
	</script>
</body>
</html>