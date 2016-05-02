package com.leaf.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.hibernate.validator.engine.groups.Group;

import com.alibaba.druid.util.StringUtils;
import com.leaf.base.exception.ValidationException;
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
	
	private static ValidatorFactory validatorFactory;

	public static Validator getValidator(){
		if(validatorFactory==null){
			validatorFactory = Validation.buildDefaultValidatorFactory();
		}
		return validatorFactory.getValidator();
	}
	
	public static void validate(Object object){
		Set<ConstraintViolation<Object>> errSet = getValidator().validate(object);
		if(errSet!= null && errSet.size()>0){
			throw new ValidationException(errSet.iterator().next().getMessage());
		}
	}
	
	public static void validate(Object object,String propertyName,Class<?>... groups){
		Set<ConstraintViolation<Object>> errSet = getValidator().validateProperty(object, propertyName, groups);
		if(errSet!= null && errSet.size()>0){
			throw new ValidationException(errSet.iterator().next().getMessage());
		}
	}
	
	public static void validate(Object object,String propertyName){
		validate(object,propertyName,Default.class);
	}
	
}
