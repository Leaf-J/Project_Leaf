package com.leaf.videoadmin.authen.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leaf.base.constant.Constant;
import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.AdminRoleResource;
import com.leaf.common.domain.AdminUser;
import com.leaf.common.service.AdminRoleResourceService;

/**
 * 后台权限管理模块Controller
 * @author admin
 *
 */

@Controller
@RequestMapping("/videoadmin/authenMgr")
public class AuthenManagerController extends VideoAdminBaseController<AdminRoleResource>{
	
	@Resource
	private AdminRoleResourceService adminRoleResourceService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getResourceData(
			@RequestParam("resourceId") String resId,HttpServletRequest request) {
		
		Map<String,Object> vo =null;
		AdminUser user = (AdminUser) request.getSession().getAttribute(
				Constant.SESSION_KEY_USER);
		if(user!=null && StringUtils.isNotEmpty(resId)){
			vo = new LinkedHashMap<String,Object>();
			Long parentId = Long.parseLong(resId);
			vo.put("subMenus", adminRoleResourceService.getSubMenus(user.getRoles(), parentId));
		}
		return vo;
	}
	
	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request,Model model) {
		super.toDefaultPage(request,model);
	}

	@Resource(name="adminRoleResourceService")
	@Override
	protected void setService(BaseService<AdminRoleResource> service) {
		this.baseService = service;
	}
}
