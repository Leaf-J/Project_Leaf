package com.leaf.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.AdminOperation;

@Service
public class AdminOperationService extends BaseService<AdminOperation> {

	private static List<AdminOperation> allOprs; // 将集合存在内存中，防止重复查找。以后需要设置过期时间，解决数据同步问题
	
	@Resource(name="adminOperationDao")
	@Override
	public void setBaseDao(BaseDao<AdminOperation> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	public List<AdminOperation> getAll(){
		if(allOprs == null){
			allOprs = this.getBaseDao().findByHql("from AdminOperation");
		}
		return allOprs;
	}
}
