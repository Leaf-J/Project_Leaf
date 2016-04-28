<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>会员登录</title>
<link
	href="${pageContext.request.contextPath}/css/leaf/front/common/register.css"
	rel="stylesheet">
	<style type="text/css">
		.login-form label,h3 {
			color: #888;
		}
		.login-form label.error {
			margin-left: 10px;
			width: auto;
			display: inline;
			color: #FF0000;
		}
		.img-verify-code {
			padding-top:2px;
			width:92px;
			height: 25px;		
		}		
	</style>
</head>
<body>
<div class="container regArea" style="margin-top:-10px;">

    <div class="regBlock">
    <form class="form-horizontal login-form"  method="post" action="${pageContext.request.contextPath}/front/login/doLogin">
        <div class="form-group" style="text-align: center;">
            <h3 class="login-title" >会员登录</h3>            
        </div>      
        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">邮箱</label>
            <div class="col-sm-6">
                <input type="text" class="form-control required email" id="email" name="email"
                       placeholder="请输入邮箱">
            </div>
            <div class="col-sm-3"></div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-3 control-label">密码</label>
            <div class="col-sm-6">
                <input type="password" class="form-control required" id="password" name="password"
                       placeholder="请输入密码">
            </div>
            <div class="col-sm-3"></div>
        </div>
        <div class="form-group">
            <label for="verifycode" class="col-sm-3 control-label">验证码</label>
            <div class="col-sm-4">
                <input type="text" class="form-control required" id="verifycode" name="verifycode"
                       placeholder="请输入验证码">
            </div>
            <div class="col-sm-6" style="width:90px;"><img class="img-verify-code" title="点击更换" onclick="javascript:refreshVerifyCode(this);" src="${pageContext.request.contextPath }/verifyCode.do"></div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-6">
                <button type="submit" class="btn btn-success" style="width:100%">登录</button>
            </div>
        </div>
        <hr/>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-6">
                <a href="#">忘记密码?</a><a href="${pageContext.request.contextPath }/front/register" style="float: right">注册会员>></a><span style="clear: both"/>
            </div>
        </div>        
    </form>
    </div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/leaf/common/leaf.jquery.validate.js"></script>
<script type="text/javascript">
	var refreshVerifyCode = function(img){
		img.src = context_+'/verifyCode.do?'+new Date().getTime();//防止图片缓存
	}
	$(function(){
		   $(".login-form").validate({
			   //debug:true, //调试模式取消submit的默认
			   //errorClass: "label.error", //默认为错误的样式类为：error
			   onkeyup: false, 
			   rules:{
				   verifycode:{
					   remote:{
	                		url: '${pageContext.request.contextPath}/checkVerifyCode',
	                		type: 'post'
	                	
	                }
				   }
			   },
			   messages:{//自定义错误消息
				   email:{
					  required:"邮箱地址必填" 
				   },
				   password:{
					   required:"密码必填"
				   },
				   verifycode:{
					   required:"校验码必填",
					   remote: "输入验证码不正确"
				   }
			   },
		        submitHandler:function(form){//表单提交句柄，为一回调函数，带一个参数：form
		            form.submit(); //提交表单
		        }    
		    });
		});
</script>
</body>
</html>