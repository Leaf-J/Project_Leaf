package com.leaf.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.VideoLocation;

@Service
public class VideoLocationService extends BaseService<VideoLocation>{
	
	@Resource(name="videoLocationDao")
	public void setBaseDao(BaseDao<VideoLocation> baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<VideoLocation> getAll(){
		return this.getBaseDao().findByHql("from VideoLocation");
	}
}
