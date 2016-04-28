package com.leaf.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 剧集
 * 
 * @author admin
 *
 */
@Entity
@Table(name="t_espisode")
public class Espisode extends BaseEntity {

	@Column
	private Integer orderNumber;

	@Column
	private String description;

	@OneToOne
	@JoinColumn(name = "video_object_id")
	private VideoObject videoObject;

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VideoObject getVideoObject() {
		return videoObject;
	}

	public void setVideoObject(VideoObject videoObject) {
		this.videoObject = videoObject;
	}

}
