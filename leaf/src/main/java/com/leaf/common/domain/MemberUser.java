package com.leaf.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 前台会员
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_video_member_user")
public class MemberUser extends BaseEntity {

	@Column
	private String nickName;
	@Column(unique = true)
	private String email;
	@Column
	private String picUrl;
	@Column
	private String password;

	@Column
	private boolean isEmailVerified = Boolean.FALSE;

	@Column
	private Integer userState = MemberUserState.CREATED.getValue();

	public enum MemberUserState {
		CREATED(0), // 创建，还没邮箱校验
		ENABLED(1), // 有效账号
		DISABLED(2);// 冻结账号
		private final Integer stateValue;

		MemberUserState(Integer stateValue) {
			this.stateValue = stateValue;
		}

		public Integer getValue() {
			return this.stateValue;
		}
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

}
