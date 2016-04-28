package com.leaf.front.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.leaf.base.constant.Constant;

/**
 * 前台登录拦截器
 * 
 * @author admin
 * 
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger log=Logger.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURL().toString().contains(Constant.FRONT_MEMBER_PATH)){//会员中心，需要登录
			log.info("check if user account exists before accessing user section");
			if(request.getSession().getAttribute(Constant.SES_KEY_MBM_USR)==null){
				log.info("user account is null , redirect to login page");
				response.sendRedirect(request.getContextPath()+Constant.FRONT_LOGIN_URL);
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	

}
