package com.leaf.front.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;
import com.leaf.base.controller.VideoFrontBaseController;
import com.leaf.base.util.RandomValidateCode;
import com.leaf.base.util.SecurityUtil;
import com.leaf.base.util.ValidateUtil;
import com.leaf.common.domain.MemberUser;
import com.leaf.common.service.MemberUserService;
import com.leaf.email.domain.EmailEvent.EmailEventName;
import com.leaf.email.domain.EmailEventRequest;
import com.leaf.email.service.EmailService;

/**
 * Register控制器
 * @author admin
 *
 */
@Controller
@RequestMapping("/front/register")
public class RegisterController extends VideoFrontBaseController{

	private static final Logger logger = Logger.getLogger(RegisterController.class);
	
	@Resource
	private MemberUserService memberUserService;
	
	@Resource
	private EmailService emailService;
	
	//TODO 重构，result Map 耦合
	@RequestMapping(value="doRegister",method=RequestMethod.POST)
	public String doRegister(HttpServletRequest request){
		HttpSession session = request.getSession();
		MemberUser user = this.buildModel(request);
		Map<String,Object> result =ValidateUtil.vialidateMemberUser(user);
		boolean isValid = (Boolean)result.get("isValid");
		String verifyCode = request.getParameter("verifycode");
		String sysVerifyCode = (String)session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
		Map<String,String> errMap = (Map<String,String>)result.get("errMsg");
		if(sysVerifyCode == null || !sysVerifyCode.equals(verifyCode)){
			errMap.put("errVerifyCode", "验证码不一致");
			isValid = false;
			result.put("isValid", isValid);
			session.setAttribute("errMsg", errMap);
		}
		if(!isValid){
			return this.redirectToView(request, "/front/register");			
		}
		//密码编码
		user.setPassword(SecurityUtil.MD5(user.getPassword()+"{"+user.getEmail()+"}"));
		memberUserService.save(user);
		
		Map<String,Object> templateParmDataMap = Maps.newHashMap();
		try {
			templateParmDataMap.put("userName",user.getNickName());
			emailService.sendEmail(EmailEventRequest.
					createRequest(user.getEmail(),EmailEventName.MemberUserAccountCreated),templateParmDataMap);
		} catch (Exception e) {
			 e.printStackTrace();
			logger.error(e);
		}
		return this.redirectToView(request, "/front/index");
	}
	
	private MemberUser buildModel(HttpServletRequest request){
		String email = request.getParameter("email");
		String nickName = request.getParameter("nickname");
		String password = request.getParameter("password");
		MemberUser user = new MemberUser();
		user.setEmail(email);
		user.setNickName(nickName);
		user.setPassword(password);
		return user;
	}
}
