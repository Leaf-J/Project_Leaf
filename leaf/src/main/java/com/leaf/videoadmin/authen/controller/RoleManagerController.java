package com.leaf.videoadmin.authen.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.AdminRole;

/**
 * 后台权限管理--角色管理模块Controller
 * @author admin
 *
 */

@Controller
@RequestMapping("/videoadmin/authenMgr/roleMgr")
public class RoleManagerController extends VideoAdminBaseController<AdminRole>{
	
	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request,Model model) {
		super.toDefaultPage(request,model);
	}
	
/*	@RequestMapping(value="listData",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listData(HttpServletRequest request) {
		String sEcho = request.getParameter("sEcho");;
		String start = request.getParameter("iDisplayStart");
		String length = request.getParameter("iDisplayLength");
		String sort_th = request.getParameter("iSortCol_0");
		String sort_type = request.getParameter("sSortDir_0");
		String search = request.getParameter("sSearch");
		Map<String,Object> vo = new LinkedHashMap<String,Object>();
		vo.put("sEcho",String.valueOf(sEcho) );
		vo.put("iTotalRecords", 1);
		vo.put("iTotalDisplayRecords", 1);;
		AdminRole role = new AdminRole();
		role.setName("Admin");
		role.setDescription("管理员");
		AdminRole role1 = new AdminRole();
		role1.setName("Admin1");
		role1.setDescription("管理员1");		
		List<AdminRole> list = new ArrayList<AdminRole>();
		list.add(role);
		list.add(role1);
		vo.put("aaData", list);
		return vo;
	}*/

	@Resource(name="adminRoleService")
	@Override
	protected void setService(BaseService<AdminRole> service) {
		this.baseService = service;
	}
}
