package com.leaf.common.controller.updownload;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leaf.base.util.RandomValidateCode;

/**
 * 验证码Controller
 * 
 * @author admin
 * 
 */
@Controller
public class VerifyCodeController {

	@RequestMapping("/verifyCode.do")
	public void getVerifyImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		RandomValidateCode code = new RandomValidateCode();
//		code.getRandCode(request, response);
		code.getRandCodeWithPatchca(request, response);
	}
	
	@ResponseBody
	@RequestMapping(value="/checkVerifyCode",method=RequestMethod.POST)
	public boolean checkVerifyCode(HttpServletRequest request,HttpServletResponse response){
		String reqVerifyCode = (String)request.getParameter("verifycode");
		String secVerifyCode = (String)request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
		return 
				StringUtils.isNotBlank(reqVerifyCode) 
				&& StringUtils.isNotBlank(secVerifyCode)
				&& reqVerifyCode.equals(secVerifyCode);
	}
}