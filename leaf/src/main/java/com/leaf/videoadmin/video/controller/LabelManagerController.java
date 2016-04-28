package com.leaf.videoadmin.video.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.VideoLabel;

/**
 * 后台视频管理--标签管理模块Controller
 * @author admin
 *
 */

@Controller
@RequestMapping("/videoadmin/videoMgr/labelMgr")
public class LabelManagerController extends VideoAdminBaseController<VideoLabel>{

	@Resource(name="videoLabelService")
	@Override
	protected void setService(BaseService<VideoLabel> service) {
		this.baseService = service;
	}
}
