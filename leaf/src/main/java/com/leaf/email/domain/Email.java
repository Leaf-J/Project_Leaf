package com.leaf.email.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * Email实体，对应发送的email
 * 
 * @author admin
 *
 */
@Entity
@Table(name = "t_Email")
public class Email extends BaseEntity {

	@Column(name = "emailFrom")
	private String from;

	@Column(name = "emailTo")
	private String to;

	@Column(name = "emailCC")
	private String cc;

	@Column(name = "emailBCC")
	private String bcc;

	@Column(name = "emailContent")
	private String content;

	@Column(name = "emailSubject")
	private String subject;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
