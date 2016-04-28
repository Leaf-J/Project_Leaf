package com.leaf.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 基础控制类
 * @author admin
 *
 */
public abstract class BaseController {
	
/*	*//**
	 * 寻找当前路径视图
	 * @param viewName 视图名
	 * @return
	 *//*
	protected String toPackageView(HttpServletRequest request,String viewName){
		String path = (String)request.getAttribute(Constant.PARENT_VIEW_PATH);
		return path+viewName;
	}*/
	
	/**
	 * 重定向视图
	 * @param viewPath 视图路径
	 * @return
	 */
	protected String redirectToView(HttpServletRequest request,String viewPath){
		service(request);
		return "redirect:"+viewPath;
	}
	
	/**
	 * forward视图
	 * @param viewPath 视图路径
	 * @return
	 */
	protected String forwardToView(HttpServletRequest request,String viewPath){
		service(request);
		return "forward:"+viewPath;
	}
	
	/**
	 * 自定义视图路径,用于复杂路径的处理,例如路径里面包含参数
	 * @param viewPath
	 * @return
	 */
	protected String toCustomView(HttpServletRequest request,String viewPath){
		service(request);
		return viewPath;
	}
	
	/**
	 * 跳转到控制器的默认页
	 */
	@RequestMapping(method=RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request,Model model){
		service(request);
	}
	
	protected void preProccess(HttpServletRequest request){}
	
	protected void proccess(HttpServletRequest request){}
	
	protected void postProccess(HttpServletRequest request){}
	
	protected void service(HttpServletRequest request){
		preProccess(request);
		proccess(request);
		postProccess(request);
	}
}
