package com.leaf.videoadmin.authen.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.druid.util.StringUtils;
import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.base.util.SecurityUtil;
import com.leaf.common.domain.AdminUser;

/**
 * 后台权限管理--用户管理模块Controller
 * @author admin
 *
 */

@Controller
@RequestMapping("/videoadmin/authenMgr/usrMgr")
public class UserManagerController extends VideoAdminBaseController<AdminUser>{
	
	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request,Model model) {
		super.toDefaultPage(request,model);
	}

	@Resource(name="adminUserService")
	@Override
	protected void setService(BaseService<AdminUser> service) {
		this.baseService = service;
	}
	
	@Override
	protected void beforeDoSave(HttpServletRequest request,AdminUser entity) throws Exception {
		super.beforeDoSave(request,entity);
		if(StringUtils.isEmpty(entity.getPassword())){//设置初始密码
			entity.setPassword(SecurityUtil.MD5(AdminUser.DEFAULT_PWD_TXT+"{"+entity.getName()+"}"));
		}
	}
}
