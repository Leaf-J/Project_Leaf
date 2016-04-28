package com.leaf.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leaf.base.dao.BaseDao;
import com.leaf.common.domain.AdminResource;

@Repository
public class AdminResourceDao extends BaseDao<AdminResource> {
	
	private String HQL_GET_ROOT_RESOURCE;
	
	@Override
	protected void initHQL() {
		HQL_GET_ROOT_RESOURCE="from "+this.getEntityClassName()+" where parent is null ";
	};
	
	public AdminResource findRootResource(){
		List<AdminResource> list = this.findByHql(HQL_GET_ROOT_RESOURCE);
		if(list.size()>0)
			return list.get(0);
		return null;
	}
}
