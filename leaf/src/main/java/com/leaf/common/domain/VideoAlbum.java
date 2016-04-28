package com.leaf.common.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.leaf.base.domain.BaseEntity;

/**
 * 视频专辑
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_video_album")
@JsonIgnoreProperties(value = { "videoObjects" })
public class VideoAlbum extends BaseEntity {

	/**
	 * 名字
	 */
	@Column
	private String name;

	/**
	 * 包含的视频对象
	 */

	@OneToMany(mappedBy = "album")
	private List<VideoObject> videoObjects;

	/**
	 * 描述
	 */
	@Column(length = 4000)
	private String description;

	/**
	 * 语言
	 */
	@Column
	private String language;

	/**
	 * 视频特征标签
	 */
	@ManyToMany(targetEntity = VideoLabel.class, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	@JoinTable(name = "t_video_album_label", joinColumns = { @JoinColumn(name = "album_id") }, inverseJoinColumns = { @JoinColumn(name = "label_id") })
	private List<VideoLabel> labels;

	/**
	 * 所属地区
	 */
	@ManyToOne
	@JoinColumn(name = "location_id")
	private VideoLocation location;

	/**
	 * 发行日期
	 */
	@Temporal(TemporalType.DATE)
	private Date publishDate;

	/**
	 * 所属类别
	 */
	@ManyToOne
	@JoinColumn(name = "category_id")
	private VideoCategory category;

	/**
	 * 专辑图片
	 */
	@Column
	private String picUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<VideoLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<VideoLabel> labels) {
		this.labels = labels;
	}

	public List<VideoObject> getVideoObjects() {
		return videoObjects;
	}

	public void setVideoObjects(List<VideoObject> videoObjects) {
		this.videoObjects = videoObjects;
	}

	public VideoLocation getLocation() {
		return location;
	}

	public void setLocation(VideoLocation location) {
		this.location = location;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public VideoCategory getCategory() {
		return category;
	}

	public void setCategory(VideoCategory category) {
		this.category = category;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
