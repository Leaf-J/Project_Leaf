package com.leaf.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 视频对象
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_video_object")
public class VideoObject extends BaseEntity {

	/**
	 * 描述
	 */
	@Column
	private String description;

	/**
	 * 点击数
	 */
	@Column
	private long clickCount = 0;

	/**
	 * 视频标题
	 */
	@Column
	private String title;

	/**
	 * 视频截图
	 */
	@Column
	private String picUrl;

	/**
	 * 视频文件url
	 */
	@Column
	private String fileUrl;

	@Column
	private Integer orderNumber;
	
	@ManyToOne
	@JoinColumn(name = "album_id")
	private VideoAlbum album;

	public long getClickCount() {
		return clickCount;
	}

	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VideoAlbum getAlbum() {
		return album;
	}

	public void setAlbum(VideoAlbum album) {
		this.album = album;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
}
