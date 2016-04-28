package com.leaf.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leaf.base.dao.BaseDao;
import com.leaf.common.domain.AdminUser;

@Repository
public class AdminUserDao extends BaseDao<AdminUser> {
	
	private String HQL_GET_USER_BY_NAME;
	
	@Override
	protected void initHQL() {
		HQL_GET_USER_BY_NAME="from "+this.getEntityClassName()+" where name=?";
	};
	
	public AdminUser findUserByName(String username){
		List<AdminUser> list = this.findByHql(HQL_GET_USER_BY_NAME, new Object[]{username});
		if(list.size()>0)
			return list.get(0);
		return null;
	}
}
