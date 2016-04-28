package com.leaf.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.Banner;

@Service
public class BannerService extends BaseService<Banner>{

	@Resource(name="bannerDao")
	public void setBaseDao(BaseDao<Banner> baseDao){
		this.baseDao = baseDao;
	}
	
	public List<Banner> getBanners(String groupName){
		List<Banner> result = null;
		
		result = this.getBaseDao().findByHql("from Banner b where b.name =? order by b.orderNum ",new Object[]{groupName});
		
		if(result==null){
			result = new ArrayList<Banner>();
		}
		return result;
	}
}
