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
import com.leaf.common.domain.VideoAlbum;
import com.leaf.common.domain.VideoCategory;
import com.leaf.common.domain.VideoLabel;
import com.leaf.common.domain.VideoLocation;
import com.leaf.common.service.VideoCategoryService;
import com.leaf.common.service.VideoLabelService;
import com.leaf.common.service.VideoLocationService;

/**
 * 后台视频管理--视频专辑管理模块Controller
 * 
 * @author admin
 * 
 */

@Controller
@RequestMapping("/videoadmin/videoMgr/albumMgr")
public class VideoAlbumtManagerController extends
		VideoAdminBaseController<VideoAlbum> {

	private static final String KEY_LOCATIONS = "locations";
	private static final String KEY_CATEGORIRES = "categories";
	private static final String KEY_LABELS = "labels";

	@Resource
	private VideoLabelService labelService;

	@Resource
	private VideoLocationService locationService;

	@Resource
	private VideoCategoryService categoryService;

	@Resource(name = "videoAlbumService")
	@Override
	protected void setService(BaseService<VideoAlbum> service) {
		this.baseService = service;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	protected void toDefaultPage(HttpServletRequest request, Model model) {
		super.toDefaultPage(request, model);
		request.setAttribute(KEY_LOCATIONS, this.locationService.getAll());
		request.setAttribute(KEY_CATEGORIRES, this.categoryService.getAll());
		request.setAttribute(KEY_LABELS, this.labelService.getAll());
	}

	/**
	 * bean绑定转换方法
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	}

	@Override
	protected void beforeDoSave(HttpServletRequest request, VideoAlbum entity)
			throws Exception {
		super.beforeDoSave(request, entity);
		String labelPids = request.getParameter("labelPids");
		if (StringUtils.isNotEmpty(labelPids)) {
			List<VideoLabel> labels = new ArrayList<VideoLabel>();
			for (String labelPid : labelPids.split(",")) {
				VideoLabel label = new VideoLabel();
				label.setPid(Long.parseLong(labelPid));
				labels.add(label);
			}
			entity.setLabels(labels);
		}
	}

	@Override
	protected void beforeDoUpdate(HttpServletRequest request, VideoAlbum entity)
			throws Exception {
		super.beforeDoUpdate(request, entity);
		String labelPids = request.getParameter("labelPids");
		if (StringUtils.isNotEmpty(labelPids)) {
			List<VideoLabel> labels = new ArrayList<VideoLabel>();
			for (String labelPid : labelPids.split(",")) {
				VideoLabel label = new VideoLabel();
				label.setPid(Long.parseLong(labelPid));
				labels.add(label);
			}
			entity.setLabels(labels);
		}
	}
}
