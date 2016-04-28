package com.leaf.base.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leaf.base.constant.Constant;
import com.leaf.base.domain.BaseEntity;
import com.leaf.base.service.BaseService;
import com.leaf.base.util.GenericUtil;
import com.leaf.base.util.SecurityUtil;
import com.leaf.common.domain.AdminResource;
import com.leaf.common.domain.AdminUser;
import com.leaf.common.service.AdminResourceService;
import com.leaf.common.service.AdminRoleResourceService;
import com.leaf.common.vo.AdminResponseVO;
import com.leaf.common.vo.DataVO;

/**
 * 后台基础控制类
 * 
 * @author admin
 * 
 */
public abstract class VideoAdminBaseController<E extends BaseEntity> extends
		BaseController {

	private final Logger log = Logger.getLogger(this.getClass());

	private int page;
	private int rows;

	private Class<E> entityClass;

	protected BaseService<E> baseService;

	@Resource
	private AdminRoleResourceService adminRoleResService;

	@Resource
	private AdminResourceService adminResourceService;

	protected abstract void setService(BaseService<E> service);

	protected BaseService<E> getBaseService() {
		return this.baseService;
	}

	public VideoAdminBaseController() {
		try {
			entityClass = GenericUtil.getActualClass(this.getClass(), 0);
		} catch (Exception e) {
			log.error("初始化出错", e);
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "doSave", method = RequestMethod.POST)
	@ResponseBody
	protected AdminResponseVO doSave(HttpServletRequest request,E entity) {
		AdminResponseVO rsp = new AdminResponseVO();
		try {
			beforeDoSave(request,entity);
			this.getBaseService().save(entity);
			afterDoSave();
			rsp.setStatus(Constant.RESPONSE_SUCCESS);
			rsp.setMessage("保存成功!");
		} catch (Exception e) {
			log.error("保存失败!", e);
			rsp.setStatus(Constant.RESPONSE_FAILED);
			rsp.setMessage("保存失败：" + e.getMessage());
		}
		return rsp;
	}

	protected void beforeDoSave(HttpServletRequest request,E entity) throws Exception {
	}

	protected void afterDoSave() throws Exception {
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "doDelete", method = RequestMethod.POST)
	@ResponseBody
	protected AdminResponseVO doDelete(HttpServletRequest request)
			throws IOException {
		AdminResponseVO rsp = new AdminResponseVO();
		String pids = request.getParameter("pids");
		try {
			if (StringUtils.isNotEmpty(pids)) {
				String[] strs = pids.split("::");
				Long[] lpids = new Long[strs.length];
				for(int i=0;i<strs.length;i++){
					lpids[i] = Long.parseLong(strs[i]);
				}
				beforeDoDelete(lpids);
				this.getBaseService().deleteByIds(lpids);
				afterDoDelete();
				rsp.setStatus(Constant.RESPONSE_SUCCESS);
				rsp.setMessage(Constant.RESPONSE_MSG_SUCCESS);
			} else {
				rsp.setStatus(Constant.RESPONSE_FAILED);
				rsp.setMessage("没有参数，非法操作！");
			}
		} catch (Exception e) {
			log.error("删除失败", e);
			rsp.setStatus(Constant.RESPONSE_FAILED);
			rsp.setMessage("删除失败!" + e.getMessage());
		}
		return rsp;
	}

	protected void beforeDoDelete(Long[] lpids) throws Exception {
	}

	protected void afterDoDelete() throws Exception {
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@ResponseBody
	protected AdminResponseVO doUpdate(HttpServletRequest request,E entity) {
		AdminResponseVO rsp = new AdminResponseVO();
		try {
			//解决同一pid的多个对象保存冲突问题，以及表单空值覆盖原数据问题 start
			E e = this.getBaseService().get(entity.getPid());
			this.getBaseService().getBaseDao().getHibernateTemplate().evict(e);
			com.leaf.base.util.BeanUtils.copyProperties(entity, e);
			entity = e;
			// end
			beforeDoUpdate(request,entity);
			this.getBaseService().update(entity);
			afterDoUpdate(entity);
			rsp.setStatus(Constant.RESPONSE_SUCCESS);
			rsp.setMessage(Constant.RESPONSE_MSG_SUCCESS);
		} catch (Exception e) {
			log.error("更新失败", e);
			rsp.setStatus(Constant.RESPONSE_FAILED);
			rsp.setMessage("更新失败!" + e.getMessage());
		}
		return rsp;
	}

	protected void beforeDoUpdate(HttpServletRequest request,E entity) throws Exception {
	}

	protected void afterDoUpdate(E entity) throws Exception {
	}

	/**
	 * 分页数据
	 */
	@RequestMapping(value = "getData", method = RequestMethod.POST)
	@ResponseBody
	protected DataVO<E> getData(HttpServletRequest request) {
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		if (StringUtils.isEmpty(page) || StringUtils.isEmpty(rows)) {
			return null;
		}
		DetachedCriteria condition = DetachedCriteria.forClass(this
				.getEntityClass());
		decorateCondition(condition);
		return this.getBaseService().getPageData(condition,
				Integer.valueOf(page), Integer.valueOf(rows));
	}

	protected void decorateCondition(DetachedCriteria condition) {
	}

	@Override
	protected void preProccess(HttpServletRequest request) {
		super.preProccess(request);
		this.verifyBackendUserInfo(request);
	}

	private void verifyBackendUserInfo(HttpServletRequest request) {
		request.setAttribute("adminResContext", Constant.ADMIN_RES_CONTEXT);
		String resId = (String) request.getParameter("navResourceId");
		String subMenuId = (String) request.getParameter("subMenuId");
		if (StringUtils.isNotEmpty(resId)) {
			Long resId_ = null;
			try {
				resId_ = Long.valueOf(resId);
				request.getSession().setAttribute(
						Constant.SESSION_KEY_ACTIVE_RES_ID, resId_);
				// retrieve sub menus
				AdminUser user = (AdminUser) request.getSession().getAttribute(
						Constant.SESSION_KEY_USER);
				if (user != null) {
					request.getSession().setAttribute(
							Constant.SESSION_KEY_SUB_MENUS,
							adminRoleResService.getSubMenus(user.getRoles(),
									resId_));
					AdminResource parentRes = adminResourceService.get(resId_);
					request.getSession().setAttribute(
							Constant.SESSION_KEY_SUB_PAGE_NAME,
							parentRes == null ? "" : parentRes.getLabel());
				}
			} catch (Exception e) {
			}
		} else if (StringUtils.isNotEmpty(subMenuId)) {
			request.getSession().setAttribute(
					Constant.SESSION_KEY_ACTIVE_SUB_MENU_ID, subMenuId);
		}

		AdminUser user = (AdminUser) request.getSession().getAttribute(
				Constant.SESSION_KEY_USER);
		List<Map<String, Object>> resources = (List<Map<String, Object>>) request
				.getSession().getAttribute(Constant.SESSION_KEY_RESOURCES);

		if (user == null || resources == null) {
			this.setUserInfo(request.getSession());
		}

	}

	// 直接数据库查找
	private void setUserInfo(HttpSession session) {
		Authentication authen = SecurityUtil.getUserAuthentication();
		if (authen == null) {
			return;
		}
		AdminUser user = (AdminUser) authen.getPrincipal();
		session.setAttribute(Constant.SESSION_KEY_USER, user);
		session.setAttribute(Constant.SESSION_KEY_RESOURCES,
				adminRoleResService.getAuthroizedResource(user.getRoles()));
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request, Model model) {
		String url = request.getRequestURL().toString();
		String urlGetData = url + "/"
				+ Constant.ACTION_GET_DATA;
		String urlDoDelete = url + "/"
				+ Constant.ACTION_DO_DELETE;
		String urlDoSave = url + "/"
				+ Constant.ACTION_DO_SAVE;
		String urlDoUpdate = url + "/"
				+ Constant.ACTION_DO_UPDATE;
		request.setAttribute(Constant.ACTION_GET_DATA, urlGetData);
		request.setAttribute(Constant.ACTION_DO_DELETE, urlDoDelete);
		request.setAttribute(Constant.ACTION_DO_SAVE, urlDoSave);
		request.setAttribute(Constant.ACTION_DO_UPDATE, urlDoUpdate);

		if(url.indexOf("/videoadmin/")>-1){
			int idx = url.indexOf("/videoadmin/");
			//权限置空
			request.setAttribute(Constant.OPR_LIST, null);
			Authentication authen = SecurityUtil.getUserAuthentication();
			AdminUser user = (AdminUser) authen.getPrincipal();
			List<String> oprs = adminRoleResService.getResourceOperations(user.getRoles(),url.substring(idx));
			request.setAttribute(Constant.OPR_LIST, oprs);			
		}

		
		super.toDefaultPage(request, model);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public AdminRoleResourceService getAdminRoleResService() {
		return adminRoleResService;
	}

	public void setAdminRoleResService(
			AdminRoleResourceService adminRoleResService) {
		this.adminRoleResService = adminRoleResService;
	}

	public AdminResourceService getAdminResourceService() {
		return adminResourceService;
	}

	public void setAdminResourceService(
			AdminResourceService adminResourceService) {
		this.adminResourceService = adminResourceService;
	}

	public Logger getLog() {
		return log;
	}

	public void setBaseService(BaseService<E> baseService) {
		this.baseService = baseService;
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

}
