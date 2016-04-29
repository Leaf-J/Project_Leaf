<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>成为会员</title>
<link
	href="${pageContext.request.contextPath}/css/leaf/front/common/register.css"
	rel="stylesheet">
</head>
<body>
<div class="container regArea" style="margin-top:-10px;">

<div class="imgBlock">
	<img alt="" src="${pageContext.request.contextPath }/images/leaf/common/register_img_left.jpg">
</div>
    <div class="regBlock blockRight">
    <form class="form-horizontal register-form" role="form" method="post" action="${pageContext.request.contextPath }/front/register/doRegister">
        <div class="form-group" style="text-align: center;">
            <h3 class="register-title" >会员注册</h3>            
        </div>    
        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">邮箱</label>
            <div class="col-sm-5">
                <input type="text" class="form-control required email" id="email" name="email"
                       placeholder="请输入邮箱">
            </div>
            <div class="col-sm-4"></div>
        </div>
        <div class="form-group">
            <label for="nickname" class="col-sm-3 control-label">昵称</label>
            <div class="col-sm-5">
                <input type="text" class="form-control required" id="nickname" name="nickname"
                       placeholder="请输入昵称">
            </div>
            <div class="col-sm-4"></div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-3 control-label">密码</label>
            <div class="col-sm-5">
            	<!-- id=password 或为保留字-->
                <input type="password" class="form-control {required:true,minlength:6}" id="passwordl" name="password"
                       placeholder="请输入密码">
            </div>
            <div class="col-sm-4"></div>
        </div>
        <div class="form-group">
            <label for="confirmpwd" class="col-sm-3 control-label">确认密码</label>
            <div class="col-sm-5">
                <input type="password" class="form-control {required:true,minlength:6,equalTo:'#passwordl'}" id="confirmpwd" name="confirmpwd"
                       placeholder="确认密码">
            </div>
            <div class="col-sm-4"></div>
        </div>
        <div class="form-group">
            <label for="verifycode" class="col-sm-3 control-label">验证码</label>
            <div class="col-sm-5">
                <input type="text" class="form-control required" id="verifycode" name="verifycode"
                       placeholder="请输入验证码">
            </div>
            <div class="col-sm-4" ><img class="verifyCode" title="点击更换" onclick="javascript:refreshVerifyCode(this);" src="${pageContext.request.contextPath }/verifyCode.do"></div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-8">
                <button type="submit" class="btn btn-success">注册</button>
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
		   $(".register-form").validate({
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
				   nickname:{
					   required:"昵称必填"
				   },
				   password:{
					   required:"密码必填"
				   },
				   confirmpwd:{
					   required:"确认密码必填"
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