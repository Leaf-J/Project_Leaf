package com.leaf.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.MemberUser;

@Service
public class MemberUserService extends BaseService<MemberUser> {
	@Resource(name = "memberUserDao")
	public void setBaseDao(BaseDao<MemberUser> baseDao) {
		this.baseDao = baseDao;
	}
	
	public MemberUser findByEmailAndPassword(String email,String password){
		return this.getBaseDao().findByHql("from MemberUser u where u.email=? and u.password=?", new Object[]{email,password}).get(0);
	}
}
