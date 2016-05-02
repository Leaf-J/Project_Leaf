package com.leaf.email.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

@Entity
@Table(name = "t_EmailTemplate")
public class EmailTemplate extends BaseEntity {

	@Column
	private String templateSubject;

	@Column
	private String templateName;

	@Column
	private String templateContent;

	@Column
	private Long emailEventId;

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public Long getEmailEventId() {
		return emailEventId;
	}

	public void setEmailEventId(Long emailEventId) {
		this.emailEventId = emailEventId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateSubject() {
		return templateSubject;
	}

	public void setTemplateSubject(String templateSubject) {
		this.templateSubject = templateSubject;
	}

}
