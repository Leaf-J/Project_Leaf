package com.leaf.common.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 视频分类相关地区表
 * @author admin
 *
 */
@Entity
@Table(name="t_video_location")
public class VideoLocation extends BaseEntity{
	/**
	 * 地区名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
