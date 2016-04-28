package com.leaf.videoadmin.page.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.PageVideo;

@Controller
@RequestMapping("/videoadmin/pageMgr/indexMgr")
public class PageVideoManagerController extends VideoAdminBaseController<PageVideo>{

	@Resource(name="pageVideoService")
	@Override
	protected void setService(BaseService<PageVideo> service) {
		this.baseService = service;
	}

}
