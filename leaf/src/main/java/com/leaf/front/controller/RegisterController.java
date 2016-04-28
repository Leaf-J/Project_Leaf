package com.leaf.front.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leaf.base.controller.VideoFrontBaseController;
import com.leaf.base.util.RandomValidateCode;
import com.leaf.base.util.SecurityUtil;
import com.leaf.base.util.ValidateUtil;
import com.leaf.common.domain.AdminUser;
import com.leaf.common.domain.MemberUser;
import com.leaf.common.service.MemberUserService;

/**
 * Register控制器
 * @author admin
 *
 */
@Controller
@RequestMapping("/front/register")
public class RegisterController extends VideoFrontBaseController{

	@Resource
	private MemberUserService memberUserService;
	
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
