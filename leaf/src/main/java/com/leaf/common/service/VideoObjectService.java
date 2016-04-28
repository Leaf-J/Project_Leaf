package com.leaf.common.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.VideoObject;
import com.leaf.common.vo.DataVO;

@Service
public class VideoObjectService extends BaseService<VideoObject>{
	
	@Resource(name="videoObjectDao")
	public void setBaseDao(BaseDao<VideoObject> baseDao) {
		this.baseDao = baseDao;
	}

	@Cacheable(value="videoCache",key="#pid")
	@Override
	public VideoObject get(Serializable pid) {
		return super.get(pid);
	}
	
	public List<VideoObject> getVideosLikeTitle(String txt){
		DetachedCriteria conditon = DetachedCriteria.forClass(VideoObject.class);
			conditon.add(Restrictions.like("title","%"+txt+"%"));	
		DataVO<VideoObject> data = getPageData(conditon, 0, 500);
		return data.getRows();
	}
}
