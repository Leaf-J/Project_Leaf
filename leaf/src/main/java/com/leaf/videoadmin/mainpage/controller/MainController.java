package com.leaf.videoadmin.mainpage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leaf.base.constant.Constant;
import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.domain.BaseEntity;
import com.leaf.base.service.BaseService;
import com.leaf.base.util.SecurityUtil;
import com.leaf.common.domain.AdminUser;
import com.leaf.common.service.AdminRoleResourceService;
import com.leaf.common.util.VOUtil;
import com.leaf.common.vo.NavTreeVO;

/**
 * 后台主页Controller
 * 
 * @author admin
 * 
 */

@Controller
@RequestMapping("/videoadmin/main")
public class MainController extends VideoAdminBaseController<BaseEntity> {

	@Resource
	private AdminRoleResourceService adminRoleResService;

	/*
	 * @Override
	 * 
	 * @RequestMapping(method = RequestMethod.GET) protected void
	 * toDefaultPage(HttpServletRequest request, Model model) {
	 * 
	 * AdminUser user = (AdminUser) request.getSession().getAttribute(
	 * Constant.SESSION_KEY_USER); List<Map<String, Object>> resources =
	 * (List<Map<String, Object>>) request
	 * .getSession().getAttribute(Constant.SESSION_KEY_RESOURCES);
	 * 
	 * if (user == null || resources == null) {
	 * this.setUserInfo(request.getSession()); } }
	 * 
	 * // 直接数据库查找 private void setUserInfo(HttpSession session) { AdminUser user
	 * = (AdminUser) SecurityUtil.getUserAuthentication() .getPrincipal();
	 * session.setAttribute(Constant.SESSION_KEY_USER, user);
	 * session.setAttribute(Constant.SESSION_KEY_RESOURCES,
	 * adminRoleResService.getAuthroizedResource(user.getRoles())); }
	 */

	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request, Model model) {
		super.toDefaultPage(request, model);
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "getNavTreeNodes", method = RequestMethod.POST)
	public List<NavTreeVO> getNavTreeNodes(HttpServletRequest request) {
		String id = request.getParameter("id");
		List<NavTreeVO> nodes = new ArrayList<NavTreeVO>();
		List<Map<String, Object>> resources = null;
		if (StringUtils.isNotEmpty(id)) {
			Authentication authen =  SecurityUtil.getUserAuthentication();
			if(authen!=null){
				AdminUser user = (AdminUser) authen.getPrincipal();
				resources = adminRoleResService.getSubMenus(user.getRoles(), Long.valueOf(id));
			}
		} else {// 父节点id为空，取第一层
			resources = (List<Map<String, Object>>) request
					.getSession().getAttribute(Constant.SESSION_KEY_RESOURCES);

		}
		if(resources != null){
			for(Map<String,Object> res : resources){
				NavTreeVO vo = new NavTreeVO();
				VOUtil.convertToNavTree(res, vo);
				nodes.add(vo);
			}
		}
		return nodes;
	}

	//主控制类，不与任何实体相关联，BaseService设为空
	@Override
	protected void setService(BaseService service) {
		
	}
}
