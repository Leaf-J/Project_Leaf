package com.leaf.email.domain;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.leaf.base.util.ValidateUtil;

public class EmailCommonRequest extends EmailRequest{

	@NotBlank
	private String content;
	
	@NotBlank
	private String subject;
	
	public String getContent() {
		return content;
	}

	protected void setContent(String content) {
		this.content = content;
	}
	
	public String getSubject() {
		return subject;
	}
	
	protected void setSubject(String subject) {
		this.subject = subject;
	}
	
	public static EmailCommonRequest createRequest(String from,String to,
			String content,String subject){
		EmailCommonRequest request = new EmailCommonRequest();
		request.setFrom(from);
		if(StringUtils.isNotBlank(to) && !to.endsWith(";")){
			to+=";";
		}
		request.setTo(to);
		request.setContent(content);
		request.setSubject(subject);
		ValidateUtil.validate(request);
		return request;
	}
	
	public static EmailCommonRequest createRequest(String to,
			String content,String subject){
		return createRequest(getSystemFromEmail(),to,content,subject);
	}
}
