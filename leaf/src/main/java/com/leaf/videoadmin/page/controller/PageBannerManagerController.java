package com.leaf.videoadmin.page.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leaf.base.controller.VideoAdminBaseController;
import com.leaf.base.service.BaseService;
import com.leaf.common.domain.Banner;

/**
 * 后台页面管理Controller
 * @author admin
 *
 */
@Controller
@RequestMapping("/videoadmin/pageMgr/bannerMgr")
public class PageBannerManagerController extends VideoAdminBaseController<Banner>{

	@Resource(name="bannerService")
	@Override
	protected void setService(BaseService<Banner> service) {
		this.baseService = service;
	}

}
