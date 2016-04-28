package com.leaf.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 视频分类
 * @author admin
 *
 */
@Entity
@Table(name="t_video_category")
public class VideoCategory extends BaseEntity{

	/**
	 * 类别名称
	 */
	@Column
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
