package com.leaf.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 页面展示视频
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_page_video")
public class PageVideo extends BaseEntity {

	@Column(length = 25, nullable = false)
	private String pageName;

	@Column(length = 25, nullable = false)
	private String sectionName;

	@Column
	private Integer orderNum;

	@OneToOne
	@JoinColumn(unique=true,name="videoId")
	private VideoObject video;

	@Column(length = 70, nullable = false)
	private String title;

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public VideoObject getVideo() {
		return video;
	}

	public void setVideo(VideoObject video) {
		this.video = video;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
