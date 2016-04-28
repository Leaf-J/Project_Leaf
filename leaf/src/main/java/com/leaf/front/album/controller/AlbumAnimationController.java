package com.leaf.front.album.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leaf.common.service.VideoAlbumService;

@Controller
@RequestMapping(AlbumAnimationController.VIEW_CONTEXT+"/animation")
public class AlbumAnimationController extends AlbumController{
	@Autowired
	private VideoAlbumService videoAlbumService;
	
	@Override
	protected void proccess(HttpServletRequest request) {
		super.proccess(request);
		request.setAttribute("albumList",videoAlbumService.getBaseDao().findAll());
	}
}
