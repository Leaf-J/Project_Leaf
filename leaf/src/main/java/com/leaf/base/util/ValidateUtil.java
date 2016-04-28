package com.leaf.base.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.leaf.common.domain.MemberUser;

public class ValidateUtil {
	public static Map<String,Object> vialidateMemberUser(MemberUser user){
		Map<String,Object> result = new HashMap<String,Object>();
		String email = user.getEmail();
		String nickName = user.getNickName();
		String password = user.getPassword();
		
		Map<String,String> errMap = new HashMap<String,String>();
		if(StringUtils.isEmpty(email)){
			errMap.put("errEmail","邮箱不能为空");
		}
		if(StringUtils.isEmpty(nickName)){
			errMap.put("errNickname","昵称不能为空");
		}
		if(StringUtils.isEmpty(password)){
			errMap.put("errPassword","密码不能为空");
		}
		
		if(errMap.size()>0){
			result.put("isValid", false);
		}else{
			result.put("isValid", true);
		}
		result.put("errMsg", errMap);
		return result;
	}
}
