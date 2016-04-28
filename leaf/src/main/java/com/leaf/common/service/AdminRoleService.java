package com.leaf.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.AdminRole;

@Service
public class AdminRoleService extends BaseService<AdminRole> {

	private static List<AdminRole> allRoles; // 将集合存在内存中，防止重复查找。以后需要设置过期时间，解决数据同步问题
	
	@Resource(name="adminRoleDao")
	@Override
	public void setBaseDao(BaseDao<AdminRole> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	public List<AdminRole> getAll(){
/*		if(allRoles == null){
			allRoles = this.getBaseDao().findByHql("from AdminRole");
		}
		return allRoles;*/
		return this.getBaseDao().findByHql("from AdminRole");
	}
}
