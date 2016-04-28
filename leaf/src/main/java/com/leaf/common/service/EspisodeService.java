package com.leaf.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.Espisode;

@Service
public class EspisodeService extends BaseService<Espisode>{

	@Resource(name="espisodeDao")
	@Override
	public void setBaseDao(BaseDao<Espisode> baseDao) {
		super.setBaseDao(baseDao);
	}
}
