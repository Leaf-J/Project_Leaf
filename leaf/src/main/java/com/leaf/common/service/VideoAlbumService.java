package com.leaf.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.VideoAlbum;

@Service
public class VideoAlbumService extends BaseService<VideoAlbum>{
	
	@Resource(name="videoAlbumDao")
	public void setBaseDao(BaseDao<VideoAlbum> baseDao) {
		this.baseDao = baseDao;
	}

}
