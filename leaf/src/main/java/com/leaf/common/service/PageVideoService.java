package com.leaf.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.PageVideo;

@Service
public class PageVideoService extends BaseService<PageVideo>{
	
	@Resource(name="pageVideoDao")
	public void setBaseDao(BaseDao<PageVideo> baseDao){
		this.baseDao = baseDao;
	}
	
	public List<PageVideo> getByPageId(String pageId){
		DetachedCriteria condition = DetachedCriteria.forClass(PageVideo.class);
		condition.add(Restrictions.eq("pageName", pageId));
		return this.getBaseDao().findByDetachedCriteria(condition);
	}
}
