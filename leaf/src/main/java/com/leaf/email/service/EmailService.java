package com.leaf.email.service;

import java.io.IOException;
import java.util.Map;

import com.leaf.email.domain.EmailCommonRequest;
import com.leaf.email.domain.EmailEvent;
import com.leaf.email.domain.EmailEventRequest;

import freemarker.template.TemplateException;

public interface EmailService {
	
	public void sendEmail(EmailCommonRequest emailCommonRequest);
	
	public void sendEmail(EmailEvent emailEvent);
	
	public void sendEmail(EmailEventRequest emailEventRequest,Map<String,Object> templateParmDataMap)  throws IOException, TemplateException;
}
