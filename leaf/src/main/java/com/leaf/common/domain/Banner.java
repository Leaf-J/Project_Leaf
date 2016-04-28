package com.leaf.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 页面幻灯片
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_page_banner")
public class Banner extends BaseEntity {

	/**
	 * 图片地址
	 */
	@Column(nullable = false)
	private String picUrl;

	/**
	 * 图片链接
	 */
	@Column(nullable = false)
	private String link;

	/**
	 * 所属页面的名字
	 */
	@Column(nullable = false, length = 70)
	private String name;

	@Column(nullable=false)
	private Integer orderNum;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
