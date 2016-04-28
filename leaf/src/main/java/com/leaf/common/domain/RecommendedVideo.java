package com.leaf.common.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

@Entity
@Table(name = "t_recommend_video")
public class RecommendedVideo extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "memberUserId")
	private MemberUser memberUser;

	@ManyToOne
	@JoinColumn(name = "video_id")
	private VideoObject video;

	public MemberUser getMemberUser() {
		return memberUser;
	}

	public void setMemberUser(MemberUser memberUser) {
		this.memberUser = memberUser;
	}

	public VideoObject getVideo() {
		return video;
	}

	public void setVideo(VideoObject video) {
		this.video = video;
	}

}
