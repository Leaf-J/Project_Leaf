package com.leaf.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.VideoCategory;

@Service
public class VideoCategoryService extends BaseService<VideoCategory>{
	
	@Resource(name="videoCategoryDao")
	public void setBaseDao(BaseDao<VideoCategory> baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<VideoCategory> getAll(){
		return this.getBaseDao().findByHql("from VideoCategory");
	}
}
