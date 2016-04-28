package com.leaf.videoadmin.authen.interceptor.component;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.leaf.videoadmin.authen.exception.AdminAccessDeniedException;

public class AdminAccessDecisionManager implements
		org.springframework.security.access.AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		// 资源没有配置权限
		if (configAttributes == null) {
			return;
		}

		// 管理员特权，免验证，待扩展
		// TODO

		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();

			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.trim().equals(ga.getAuthority().trim())) {// 有角色权限
					return;
				}
			}
		}
		
		throw new AdminAccessDeniedException("Access Denied.");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
