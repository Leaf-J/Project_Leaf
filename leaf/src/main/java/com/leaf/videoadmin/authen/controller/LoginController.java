package com.leaf.videoadmin.authen.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.domain.BaseEntity;
import com.leaf.base.service.BaseService;

/**
 * 后台登录Controller，具体验证逻辑由spring-security提供
 * @author admin
 *
 */

@Controller
@RequestMapping("/videoadmin/login")
public class LoginController extends VideoAdminBaseController<BaseEntity>{
	
	@RequestMapping(method=RequestMethod.GET)
	public void login(HttpSession session){
		this.handleErrorMsg(session);
	}
	
	/**
	 * 处理spring security 验证失败返回消息
	 * @param session
	 */
	@SuppressWarnings("deprecation")
	private void handleErrorMsg(HttpSession session){	
		Object exception = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");//ss的验证错误异常
		if(exception instanceof BadCredentialsException){
			BadCredentialsException bce = (BadCredentialsException) exception;
			String error="用户名或密码错误。";
			session.setAttribute("SPRING_SECURITY_LAST_USERNAME",bce.getAuthentication().getPrincipal());
			session.setAttribute("SPRING_SECURITY_LAST_PWD",bce.getAuthentication().getCredentials());
			session.setAttribute("SPRING_SECURITY_LAST_ERR",error);
		}else if(exception instanceof SessionAuthenticationException){
			SessionAuthenticationException sae = (SessionAuthenticationException) exception;
			if(sae.getMessage().endsWith("principal exceeded")){//暂时hardcode
				String error="当前账号已登录，如有问题，请联系管理员。";
				session.setAttribute("SPRING_SECURITY_LAST_USERNAME",null);
				session.setAttribute("SPRING_SECURITY_LAST_PWD",null);
				session.setAttribute("SPRING_SECURITY_LAST_ERR",error);
			}
		}
	}

	//权限认证，不与某个具体实体类相关联
	@Override
	protected void setService(BaseService service) {
		
	}
}
