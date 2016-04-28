package com.leaf.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 基本拦截器
 * 
 * @author admin
 * 
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger log=Logger.getLogger(BaseInterceptor.class);
	
	private static final String clsName = BaseInterceptor.class.getSimpleName();
	
/*	@Resource
	private AdminRoleResourceService adminRoleResService;*/
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
/*		if(request.getRequestURL().toString().contains(Constant.ADMIN_CONTEXT_PATH)){
			verifyBackendUserInfo(request,response);
		}*/

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
/*	private void setViewPath(HttpServletRequest request){
		request.setAttribute(Constant.PARENT_VIEW_PATH,request.getRequestURI()+"/");		
	}*/
	

}
