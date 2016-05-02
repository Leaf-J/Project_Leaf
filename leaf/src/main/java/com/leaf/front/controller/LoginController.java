package com.leaf.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leaf.base.controller.VideoFrontBaseController;
import com.leaf.base.util.SecurityUtil;
import com.leaf.common.domain.MemberUser;
import com.leaf.common.domain.RecommendedVideo;
import com.leaf.common.service.MemberUserService;
import com.leaf.common.service.RecommendedVideoService;

/**
 * Login控制器
 * @author admin
 *
 */
@Controller("frontLoginController")
@RequestMapping("/front/login")
public class LoginController extends VideoFrontBaseController{
	
	@Autowired
	private MemberUserService memberUserService;
	
	@Autowired
	private RecommendedVideoService recommendedVideoService;
	
	@RequestMapping(value="doLogin",method=RequestMethod.POST)
	@ResponseBody
	public String doLogin(HttpServletRequest request,HttpServletResponse response){
		String email = (String)request.getParameter("email");
		String password = (String)request.getParameter("password");
		String encryptPwd = SecurityUtil.MD5(password+"{"+email+"}");
		MemberUser user = memberUserService.findByEmailAndPassword(email, encryptPwd);
		request.getSession().setAttribute("memberUser", user);
		
		List<RecommendedVideo> remdList = recommendedVideoService.getRecommendedListByMemberUser(user);
		request.getSession().setAttribute("recommendList", remdList);
		return "success";
	}
}
