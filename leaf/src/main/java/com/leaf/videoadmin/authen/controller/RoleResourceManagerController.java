package com.leaf.videoadmin.authen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leaf.base.constant.Constant;
import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.AdminOperation;
import com.leaf.common.domain.AdminRoleResource;
import com.leaf.common.service.AdminOperationService;
import com.leaf.common.service.AdminResourceService;
import com.leaf.common.service.AdminRoleService;
import com.leaf.common.vo.AdminResponseVO;

/**
 * 后台权限管理--角色资源配置模块Controller
 * 
 * @author admin
 * 
 */

@Controller
@RequestMapping("/videoadmin/authenMgr/roleResMgr")
public class RoleResourceManagerController extends
		VideoAdminBaseController<AdminRoleResource> {

	private final Logger log = Logger.getLogger(this.getClass());

	private static final String KEY_RESOURCES = "resources";
	private static final String KEY_ROLES = "roles";
	private static final String KEY_OPERATIONS = "operations";

	@Resource
	private AdminResourceService resourceService;

	@Resource
	private AdminRoleService roleService;

	@Resource
	private AdminOperationService oprService;

	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request, Model model) {
		super.toDefaultPage(request, model);
		request.setAttribute(KEY_RESOURCES, this.resourceService.getAll());
		request.setAttribute(KEY_ROLES, this.roleService.getAll());
		request.setAttribute(KEY_OPERATIONS, this.oprService.getAll());
	}

	@Resource(name = "adminRoleResourceService")
	@Override
	protected void setService(BaseService<AdminRoleResource> service) {
		this.baseService = service;
	}

	@Override
	protected void decorateCondition(DetachedCriteria condition) {
		condition.createAlias("role", "role");// 按对象属性中的属性排序时要先设置别名
		condition.addOrder(Order.asc("role.name"));
		super.decorateCondition(condition);
	}

	@Override
	protected void beforeDoSave(HttpServletRequest request,
			AdminRoleResource entity) throws Exception {
		super.beforeDoSave(request, entity);
		String oprPids = request.getParameter("oprPids");
		if (StringUtils.isNotEmpty(oprPids)) {
			List<AdminOperation> oprs = new ArrayList<AdminOperation>();
			for (String oprid : oprPids.split(",")) {
				AdminOperation opr = new AdminOperation();
				opr.setPid(Long.parseLong(oprid));
				oprs.add(opr);
			}
			entity.setOprs(oprs);
		}
	}
	
	@Override
	protected void beforeDoUpdate(HttpServletRequest request,
			AdminRoleResource entity) throws Exception {
		super.beforeDoUpdate(request, entity);
		String oprPids = request.getParameter("oprPids");
		if (StringUtils.isNotEmpty(oprPids)) {
			List<AdminOperation> oprs = new ArrayList<AdminOperation>();
			for (String oprid : oprPids.split(",")) {
				AdminOperation opr = new AdminOperation();
				opr.setPid(Long.parseLong(oprid));
				oprs.add(opr);
			}
			entity.setOprs(oprs);
		}
	}

}