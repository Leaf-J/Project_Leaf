package com.leaf.videoadmin.authen.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leaf.base.constant.Constant;
import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.AdminResource;
import com.leaf.common.service.AdminResourceService;
import com.leaf.common.vo.AdminResponseVO;

/**
 * 后台权限管理--资源管理模块Controller
 * 
 * @author admin
 * 
 */

@Controller
@RequestMapping("/videoadmin/authenMgr/resMgr")
public class ResourceManagerController extends
		VideoAdminBaseController<AdminResource> {

	private static final String KEY_PARENT_RESOURCES = "parentRez";

	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request, Model model) {
		super.toDefaultPage(request, model);
		request.setAttribute(KEY_PARENT_RESOURCES, this.getResourceService()
				.getAll());
	}

	@Resource(name = "adminResourceService")
	@Override
	protected void setService(BaseService<AdminResource> service) {
		this.baseService = service;
	}

	@Override
	protected void decorateCondition(DetachedCriteria condition) {
		condition.add(Expression.gt("pid", 1L)); // 不显示根资源
		super.decorateCondition(condition);
	}

	@Override
	protected void beforeDoSave(HttpServletRequest request,AdminResource entity) throws Exception {
		super.beforeDoSave(request,entity);
		entity.getParent().setLeafFlag(Constant.ADMIN_RESOURCE_IS_LEAF_NO);
	}

	@Override
	protected void beforeDoDelete(Long[] lpids) throws Exception {
		super.beforeDoDelete(lpids);
		for(Long lpid : lpids){
			this.getResourceService().updateLeafStatus(lpid,null);	
		}
	}
	@Override
	protected void beforeDoUpdate(HttpServletRequest request,AdminResource entity) throws Exception {
		super.beforeDoUpdate(request,entity);
		this.getResourceService().updateLeafStatus(entity.getPid(), entity.getParent().getPid());
	}
	

	private AdminResourceService getResourceService() {
		return (AdminResourceService) this.getBaseService();
	}

}
