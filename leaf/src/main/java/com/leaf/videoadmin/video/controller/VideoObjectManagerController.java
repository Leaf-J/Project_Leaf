package com.leaf.videoadmin.video.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.VideoAlbum;
import com.leaf.common.domain.VideoObject;
import com.leaf.common.service.VideoAlbumService;
import com.leaf.common.service.VideoObjectService;
import com.leaf.common.vo.DataVO;

/**
 * 后台视频管理--视频对象管理模块Controller
 * @author admin
 *
 */

@Controller
@RequestMapping("/videoadmin/videoMgr/objMgr")
public class VideoObjectManagerController extends VideoAdminBaseController<VideoObject>{

	@Resource
	private VideoAlbumService albumService;
	
	@Resource(name="videoObjectService")
	@Override
	protected void setService(BaseService<VideoObject> service) {
		this.baseService = service;
	}
	
	@RequestMapping(value={"getAlbums"},method=RequestMethod.POST)
	@ResponseBody
	public List<VideoAlbum> getAlbums(HttpServletRequest request){
		String queryTxt = request.getParameter("q");
		DetachedCriteria condition = DetachedCriteria.forClass(VideoAlbum.class);
		if(StringUtils.isNotEmpty(queryTxt)){
			condition.add(Restrictions.like("name", "%"+queryTxt+"%"));	
		}
		DataVO<VideoAlbum> data = albumService.getPageData(condition, 0, 500);
		return data.getRows();
	}
	
	@RequestMapping(value="getLikeTitle",method=RequestMethod.POST)
	@ResponseBody
	public List<VideoObject> getByCondition(HttpServletRequest request){
		String queryTxt = request.getParameter("q");
		if(queryTxt==null){
			queryTxt = "";
		}
		return ((VideoObjectService)this.getBaseService()).getVideosLikeTitle(queryTxt); 
	}
}
