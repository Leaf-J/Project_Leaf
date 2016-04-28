package com.leaf.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 视频对象相关文件
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_video_object_file")
public class VideoObjectFile extends BaseEntity {

	/**
	 * 相关的视频对象
	 */
	@ManyToOne
	@JoinColumn(name = "video_object_id")
	private VideoObject videoObject;

	/**
	 * 文件url
	 */
	@Column(nullable = false)
	private String url;

	/**
	 * 文件序号，多个文件相关一个视频对象，文件的先后顺序
	 */
	@Column(name = "order_number", nullable = false)
	private Integer orderNumber;

	public VideoObject getVideoObject() {
		return videoObject;
	}

	public void setVideoObject(VideoObject videoObject) {
		this.videoObject = videoObject;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
}
