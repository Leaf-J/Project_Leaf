package com.leaf.common.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.dao.AdminUserDao;
import com.leaf.common.domain.AdminRole;
import com.leaf.common.domain.AdminUser;

/**
 * 实现了SpringSecurity接口，提供用户验证资源查询服务
 * 
 * @author admin
 * 
 */
@Service
public class AdminUserService extends BaseService<AdminUser> implements
		UserDetailsService {

	private AdminUserDao adminUserDao;

	@Resource(name = "adminUserDao")
	public void setBaseDao(BaseDao<AdminUser> baseDao) {
		this.baseDao = baseDao;
		adminUserDao = (AdminUserDao) baseDao;
	}

	public AdminUser getUserByName(String username) {
		return adminUserDao.findUserByName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		AdminUser user = null;

		user = this.getUserByName(username);

		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		List<AdminRole> roles = user.getRoles();
		if (roles == null) {
			return null;
		}
		for (AdminRole role : roles) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
					role.getName());
			auths.add(authority);
		}
		user.configAuthUserDetails(auths);
		return user;
	}
}
