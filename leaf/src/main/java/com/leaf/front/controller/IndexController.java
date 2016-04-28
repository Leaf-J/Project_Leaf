package com.leaf.front.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leaf.base.controller.VideoFrontBaseController;
import com.leaf.common.domain.PageVideo;
import com.leaf.common.service.BannerService;
import com.leaf.common.service.PageVideoService;

/**
 * Index控制器
 * @author admin
 *
 */
@Controller
@RequestMapping("/front/index")
public class IndexController extends VideoFrontBaseController{

	private static final String BANNER_GROUP_NAME = "indexPage";
	
	private static final String VIDEOS_GROUP_NAME = "index";
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private PageVideoService pageVideoService;
	
	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request, Model model) {
		request.setAttribute("username", "leaf");
		request.setAttribute("banners", bannerService.getBanners(BANNER_GROUP_NAME));
		this.groupBySectionName(request,pageVideoService.getByPageId(VIDEOS_GROUP_NAME));
		super.toDefaultPage(request, model);
	}
	
	private void groupBySectionName(HttpServletRequest request,List<PageVideo> rawList){
		Map<String,List<PageVideo>> map = new HashMap<String,List<PageVideo>>();
		for(PageVideo pv : rawList){
			String sectionName = pv.getSectionName();
			if(!map.containsKey(sectionName)){
				map.put(sectionName, new ArrayList<PageVideo>());
			}
			map.get(sectionName).add(pv);
		}
		
		for(String key:map.keySet()){
			request.setAttribute(key, map.get(key));
		}
	}
}
