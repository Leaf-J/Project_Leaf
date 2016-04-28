package com.leaf.common.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.leaf.base.domain.BaseEntity;

/**
 * 视频特征标签
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_video_label")
@JsonIgnoreProperties(value = { "videoAlbums" })
public class VideoLabel extends BaseEntity {

	/**
	 * 标签名称
	 */
	@Column
	private String name;

	/**
	 * 包括的视频专辑
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "labels", targetEntity = VideoAlbum.class)
	private List<VideoAlbum> videoAlbums;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VideoAlbum> getVideoAlbums() {
		return videoAlbums;
	}

	public void setVideoAlbums(List<VideoAlbum> videoAlbums) {
		this.videoAlbums = videoAlbums;
	}
}
