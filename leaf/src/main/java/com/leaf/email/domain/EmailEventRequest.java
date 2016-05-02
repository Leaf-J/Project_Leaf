package com.leaf.email.domain;

import javax.validation.constraints.NotNull;

import com.leaf.base.util.ValidateUtil;
import com.leaf.email.domain.EmailEvent.EmailEventName;

public class EmailEventRequest extends EmailRequest{

	@NotNull
	private EmailEventName emailEventName;
	
	
	public EmailEventName getEmailEventName() {
		return emailEventName;
	}

	protected void setEmailEventName(EmailEventName emailEventName) {
		this.emailEventName = emailEventName;
	}



	public static EmailEventRequest createRequest(String from,String to,
			EmailEventName emailEventName){
		EmailEventRequest request = new EmailEventRequest();
		request.setFrom(from);
		request.setTo(to);
		request.setEmailEventName(emailEventName);
		ValidateUtil.validate(request);
		return request;
	}
	
	public static EmailEventRequest createRequest(String to,
			EmailEventName emailEventName){
		EmailEventRequest request = new EmailEventRequest();
		request.setFrom(getSystemFromEmail());
		request.setTo(to);
		request.setEmailEventName(emailEventName);
		ValidateUtil.validate(request);
		return request;
	}
}
