package com.leaf.front.video.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Preconditions;
import com.leaf.base.controller.VideoFrontBaseController;
import com.leaf.base.exception.ServiceException;
import com.leaf.common.domain.VideoAlbum;
import com.leaf.common.domain.VideoObject;
import com.leaf.common.service.VideoAlbumService;
import com.leaf.common.service.VideoObjectService;

@Controller
@RequestMapping(value={"/front/v","/front/video"})
public class WatchVideoController extends VideoFrontBaseController {

	private static final String VIEW_WATCH_VIDEO = "/front/video/watchVideo";

	@Autowired
	private VideoObjectService videoObjectService;
	
	@Autowired
	private VideoAlbumService videoAlbumService;

	// @RequestMapping("{objectPid}")
	// public ModelAndView getVideoObject(HttpServletRequest
	// request,@PathVariable String objectPid,ModelAndView mav){
	//
	// VideoObject video = videoObjectService.get(Long.parseLong(objectPid));
	// mav.setViewName(VIEW_WATCH_VIDEO);
	// mav.addObject("videoObject", video);
	// return mav;
	// }

	@RequestMapping(method = RequestMethod.GET, value = { "ab{albumId:\\d+}"})
	public ModelAndView watchVideo(HttpServletRequest request,ModelAndView mav,@PathVariable Long albumId) {
		mav.setViewName(VIEW_WATCH_VIDEO);
		setData4WatchVideo(mav, albumId, null);
		return mav;
	}	

	@RequestMapping(method = RequestMethod.GET, value = { "ab{albumId:\\d+}", "ab{albumId:\\d+}_{orderNumber:\\d+}" })
	public ModelAndView watchVideo(HttpServletRequest request, ModelAndView mav,
			@PathVariable Long albumId,
			@PathVariable Integer orderNumber) {
		mav.setViewName(VIEW_WATCH_VIDEO);
		setData4WatchVideo(mav,albumId,orderNumber);

		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = {"{videoId:\\d+}"})
	public String watchVideoByVideoID(HttpServletRequest request,@PathVariable Long videoId){
		Preconditions.checkNotNull(videoId);
		VideoObject videoObject = videoObjectService.get(videoId);
		Preconditions.checkNotNull(videoObject);
		
		return "redirect:/front/v/ab"+videoObject.getAlbum().getPid()+"_"+videoObject.getOrderNumber();
	}
	
	private void setData4WatchVideo(ModelAndView mav,Long albumId,Integer orderNumber){
		Assert.notNull(albumId);
		VideoAlbum album = videoAlbumService.get(albumId);
		if(album==null){
			throw new ServiceException("No album found with id specified");
		}else{
			if(album.getVideoObjects()==null || album.getVideoObjects().size()==0){
				throw new ServiceException("There is no video in this album");
			}else{
				
					List<VideoObject> videos = album.getVideoObjects();
					Collections.sort(videos,new Comparator<VideoObject>() {
						@Override
						public int compare(VideoObject o1, VideoObject o2) {
							return o1.getOrderNumber()-o2.getOrderNumber();
						}
					});
					VideoObject vo = videos.get(0);
				if(orderNumber!=null){	
					for(int i=0;i<videos.size();i++){
						if(videos.get(i).getOrderNumber()==orderNumber){
							vo = videos.get(i);
						}
					}
				}
				mav.addObject("videoObject",vo);
				mav.addObject("album",album);
			}
		}
	}
}
