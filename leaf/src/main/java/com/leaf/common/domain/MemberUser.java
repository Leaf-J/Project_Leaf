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
	@Column(unique=true)
	private String email;
	@Column
	private String picUrl;
	@Column
	private String password;

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

}
