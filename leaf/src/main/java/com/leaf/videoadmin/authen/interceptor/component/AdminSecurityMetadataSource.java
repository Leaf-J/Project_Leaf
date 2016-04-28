package com.leaf.videoadmin.authen.interceptor.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.leaf.common.domain.AdminRoleResource;
import com.leaf.common.service.AdminRoleResourceService;

public class AdminSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private AdminRoleResourceService adminRoleResourceService;

	/**
	 * 后台默认需要的权限
	 */
	private static final String DEFAULT_ROLE_REQUIRED = "ROLE_ADMIN_USER";

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");

		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		
		logger.debug("admin platform: request resource " + url);
		List<ConfigAttribute> result = new ArrayList<ConfigAttribute>();
		ConfigAttribute attribute = new SecurityConfig(DEFAULT_ROLE_REQUIRED);
		result.add(attribute);

		List<AdminRoleResource> permissionList = adminRoleResourceService
				.getByResName(url);
		for (AdminRoleResource perm : permissionList) {
			result.add(new SecurityConfig(perm.getRole().getName()));
		}

		return result;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
