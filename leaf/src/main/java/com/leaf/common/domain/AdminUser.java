package com.leaf.common.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.druid.util.StringUtils;
import com.leaf.base.domain.BaseEntity;
import com.leaf.base.util.SecurityUtil;

/**
 * 后台系统用户
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_admin_user")
public class AdminUser extends BaseEntity implements UserDetails{
	public static final Integer USER_DISABLED = 0;
	public static final Integer USER_ENABLED = 1;

	//后台用户默认密码
	public static final String DEFAULT_PWD_TXT = "123456";
	
	@Column
	private String name;

	@Column(name="user_state")
	private Integer userState = USER_ENABLED;

	@Column(length = 255)
	private String password;

	@ManyToMany(targetEntity = com.leaf.common.domain.AdminRole.class, cascade = {
			CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "t_admin_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<AdminRole> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AdminRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AdminRole> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	@Transient
	private Collection<GrantedAuthority> authorities;
	@Transient
	private boolean isAccountNonExpired;
	@Transient
	private boolean isAccountNonLocked;
	@Transient
	private boolean isCredentialsNonExpired;
	@Transient
	private boolean isEnabled;
	
	public void configAuthUserDetails(Collection<GrantedAuthority> authorities){
		this.configAuthUserDetails(authorities,true,true,true,true);
	}

	public void configAuthUserDetails(Collection<GrantedAuthority> authorities,
			boolean isAccountNonExpired,boolean isAccountNonLocked,
			boolean isCredentialsNonExpired,boolean isEnabled){
		this.authorities = authorities;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}
	
	//以下是SpringSecurity的UserDetails接口方法
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * 重写equals方法，解决spring security重复登录配置失效问题
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AdminUser)){
			return false;
		}
		AdminUser usr = (AdminUser) obj;
		return this.name.equals(usr.getName()) && this.password.equals(usr.getPassword());
	}

	/**
	 * 重写hashCode方法，解决spring security重复登录配置失效问题
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + this.name.hashCode();
		result = 37 * result + this.password.hashCode();
		return result;
	}
}
