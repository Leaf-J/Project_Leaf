package com.leaf.videoadmin.video.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.Espisode;
import com.leaf.common.domain.VideoAlbum;
import com.leaf.common.domain.VideoCategory;
import com.leaf.common.domain.VideoLabel;
import com.leaf.common.domain.VideoLocation;
import com.leaf.common.service.VideoCategoryService;
import com.leaf.common.service.VideoLabelService;
import com.leaf.common.service.VideoLocationService;

/**
 * 剧集管理模块Controller
 * 
 * @author admin
 * 
 */

@Controller
@RequestMapping("/videoadmin/videoMgr/espisodeMgr")
public class EspisodeController extends
		VideoAdminBaseController<Espisode> {

	private static final String KEY_LOCATIONS = "locations";
	private static final String KEY_CATEGORIRES = "categories";
	private static final String KEY_LABELS = "labels";

	@Resource
	private VideoLabelService labelService;

	@Resource
	private VideoLocationService locationService;

	@Resource
	private VideoCategoryService categoryService;

	@Resource(name = "espisodeService")
	@Override
	protected void setService(BaseService<Espisode> service) {
		this.baseService = service;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request, Model model) {
		super.toDefaultPage(request, model);
	}

}
