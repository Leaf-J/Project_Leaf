package com.leaf.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.MemberUser;
import com.leaf.common.domain.RecommendedVideo;

@Service
public class RecommendedVideoService extends BaseService<RecommendedVideo>{

	@Resource(name="recommendedVideoDao")
	@Override
	public void setBaseDao(BaseDao<RecommendedVideo> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	public List<RecommendedVideo> getRecommendedListByMemberUser(MemberUser user){
		return this.getBaseDao().findByHql("from RecommendedVideo obj where obj.memberUser=?", new Object[]{user});
	}
}
