package com.leaf.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.VideoLabel;

@Service
public class VideoLabelService extends BaseService<VideoLabel>{
	
	@Resource(name="videoLabelDao")
	public void setBaseDao(BaseDao<VideoLabel> baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<VideoLabel> getAll(){
		return this.getBaseDao().findByHql("from VideoLabel");
	}
}
