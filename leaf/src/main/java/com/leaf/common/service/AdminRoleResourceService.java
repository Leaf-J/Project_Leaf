package com.leaf.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.dao.AdminRoleResourceDao;
import com.leaf.common.domain.AdminRole;
import com.leaf.common.domain.AdminRoleResource;

@Service
public class AdminRoleResourceService extends BaseService<AdminRoleResource> {

	private AdminRoleResourceDao adminRoleResourceDao;

	@Resource
	private AdminResourceService adminResService;

	@Resource
	private AdminOperationService adminOprService;
	
	@Resource(name = "adminRoleResourceDao")
	public void setBaseDao(BaseDao<AdminRoleResource> baseDao) {
		this.baseDao = baseDao;
		this.adminRoleResourceDao = (AdminRoleResourceDao) baseDao;
	}

	public List<AdminRoleResource> getByResName(String resName) {
		return adminRoleResourceDao.findByResName(resName);
	}

	/**
	 * 返回导航第一级菜单资源
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getAuthroizedResource(List<AdminRole> roles) {
		if (roles == null || roles.size() == 0) {
			return null;
		}
		Long[] roleIds = new Long[roles.size()];
		int i = 0;
		for (AdminRole role : roles) {
			roleIds[i++] = role.getPid();
		}
		return this.adminRoleResourceDao.findUserResources(roleIds,
				adminResService.getRootResource().getPid());

	}
	
	
	/**
	 * 返回导航子菜单
	 * @param roles
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> getSubMenus(List<AdminRole> roles,Long parentId){
		if (roles == null || roles.size() == 0 || parentId == null) {
			return null;
		}
		Long[] roleIds = new Long[roles.size()];
		int i = 0;
		for (AdminRole role : roles) {
			roleIds[i++] = role.getPid();
		}
		return this.adminRoleResourceDao.findUserResources(roleIds, parentId);
	}
	
	/**
	 * 获取用户某一资源的操作权限
	 */
	public List<String> getResourceOperations(List<AdminRole> roles,String resUrl){
		if (roles == null || roles.size() == 0 || StringUtils.isEmpty(resUrl)) {
			return null;
		}		
		Long[] roleIds = new Long[roles.size()];
		int i = 0;
		for (AdminRole role : roles) {
			roleIds[i++] = role.getPid();
		}
		return this.adminRoleResourceDao.findResourceOperations(roleIds, resUrl);
	}
	
	/**
	 * 重写update
	 */
	@Override
	public void update(AdminRoleResource entity) {
		//更新前先删去原来的权限信息
		this.adminRoleResourceDao.deleteOperationsByRoleResId(entity.getPid());
		super.update(entity);
	}
}
