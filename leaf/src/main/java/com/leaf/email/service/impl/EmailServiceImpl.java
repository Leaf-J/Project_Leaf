package com.leaf.email.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.base.util.FreeMarkerUtil;
import com.leaf.email.dao.EmailDao;
import com.leaf.email.dao.EmailEventDao;
import com.leaf.email.dao.EmailTemplateDao;
import com.leaf.email.domain.Email;
import com.leaf.email.domain.EmailCommonRequest;
import com.leaf.email.domain.EmailEvent;
import com.leaf.email.domain.EmailEvent.EmailEventName;
import com.leaf.email.domain.EmailEventRequest;
import com.leaf.email.domain.EmailTemplate;
import com.leaf.email.service.EmailService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * spring email + freemarker 实现
 * @author admin
 *
 */
@Service
public class EmailServiceImpl extends BaseService<Email> implements EmailService{

	private static final Logger log = Logger.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private EmailEventDao emailEventDao;
	
	@Autowired
	private EmailTemplateDao emailTemplateDao;
	
	@Resource(name="emailDao")
	@Override
	public void setBaseDao(BaseDao<Email> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	
	
	@Autowired
	private JavaMailSender mailSender;//spring配置中定义
	
	@Override
	public void sendEmail(EmailCommonRequest emailCommonRequest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmail(EmailEvent emailEvent) {
		Preconditions.checkNotNull(emailEvent);
		// TODO Auto-generated method stub		
	}
	
	@PostConstruct
	private void initEmailTemplate(){
		List<EmailTemplate> emailTemplateList = emailTemplateDao.findAll();
		for(EmailTemplate template : emailTemplateList){
			FreeMarkerUtil.putStringTemplate(template.getTemplateName(), template.getTemplateContent());
		}
	}
	
	@Transactional 
	@Override
	public void sendEmail(EmailEventRequest emailEventRequest,Map<String,Object> templateParmDataMap) throws IOException, TemplateException {
		Preconditions.checkNotNull(emailEventRequest);
		EmailEventName emailEventName = emailEventRequest.getEmailEventName();
		Preconditions.checkNotNull(emailEventName);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmailEvent.class);
		detachedCriteria.add(Restrictions.eq("eventName",emailEventName.getEventName()));
		List<EmailEvent> list = emailEventDao.findByDetachedCriteria(detachedCriteria);
		log.info("Event size:"+list.size());
		if(!CollectionUtils.isEmpty(list)){
			Set<EmailTemplate> templateSet = list.get(0).getEventTemplateSet();
			if(!CollectionUtils.isEmpty(templateSet)){
				log.info("Template set size:"+templateSet.size());
				for(EmailTemplate template : templateSet){
					Template freeMarkerTemplate = null;
					try {
						  freeMarkerTemplate = FreeMarkerUtil.getStringLoaderConfiguration().getTemplate(template.getTemplateName(),"utf-8");
					} catch (IOException e) {
						e.printStackTrace();
						log.error(e);
						//exception might be caused by not setting template before
//						FreeMarkerUtil.putStringTemplate(template.getTemplateName(), template.getTemplateContent());
//						freeMarkerTemplate = FreeMarkerUtil.getStringLoaderConfiguration().getTemplate(template.getTemplateName(),"utf-8");
					}
					
					String emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateParmDataMap);
					log.info("send email,email template name:"+template.getTemplateName());
					
					try {
						EmailCommonRequest request = EmailCommonRequest.createRequest(emailEventRequest.getTo(),emailContent,template.getTemplateSubject());
						this.sendHtml(request);
						Email email = new Email();
						email.setFrom(request.getFrom());
						email.setTo(request.getTo());
						email.setContent(emailContent);
						email.setSubject(template.getTemplateSubject());
						((EmailDao)this.getBaseDao()).save(email);;
					} catch (MessagingException e) {
						log.error(e);
					}
				}
			}
		}
	}
	
    /** 
     * 发送普通文本邮件 
     * 
     */  
/*    public void sendText(){  
        mailSender = this.getMailSender();  
        simpleMailMessage.setTo(this.getTo()); //接收人    
        simpleMailMessage.setFrom(simpleMailMessage.getFrom()); //发送人,从配置文件中取得  
        simpleMailMessage.setSubject(this.getSubject());  
        simpleMailMessage.setText(this.getContent());  
        mailSender.send(simpleMailMessage);  
    }  */

	/** 
     * 发送普通Html邮件 
     * @throws javax.mail.MessagingException 
     * 
     */  
    public void sendHtml(EmailCommonRequest request) throws javax.mail.MessagingException{  
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");  
        

            messageHelper.setTo(getEmailAddressList(request.getTo()).toArray(new String[0]));  
            messageHelper.setFrom(request.getFrom());  
            messageHelper.setSubject(request.getSubject());  
            messageHelper.setText(request.getContent(),true);        

            if(StringUtils.isNotBlank(request.getCc())){
	            List<String> ccList = getEmailAddressList(request.getCc());
	            if(!CollectionUtils.isEmpty(ccList)){
	            	messageHelper.setCc(ccList.toArray(new String[0]));
	            }
            }
            if(StringUtils.isNotBlank(request.getBcc())){
	            List<String> bccList = getEmailAddressList(request.getBcc());
	            if(!CollectionUtils.isEmpty(bccList)){
	            	messageHelper.setBcc(bccList.toArray(new String[0]));
	            }
            }
        mailSender.send(mimeMessage);  
    }
    
    private List<String> getEmailAddressList(String addressString){
    	Preconditions.checkNotNull(addressString);
    	List<String> list = Lists.newArrayList();
        StringTokenizer token = new StringTokenizer(addressString, ";");
        while(token.hasMoreElements()){
        	list.add((String)token.nextElement());
        }
    	return list;
    }
    /** 
     * 发送普通带一张图片的Html邮件 
     * 
     */  
/*    public void sendHtmlWithImage(String imagePath){  
        mailSender = this.getMailSender();  
        MimeMessage mimeMessage = mailSender.createMimeMessage();  
        try {  
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);  
            messageHelper.setTo(this.getTo());  
            messageHelper.setFrom(simpleMailMessage.getFrom());  
            messageHelper.setSubject(this.getSubject());  
            messageHelper.setText(this.getContent(),true);  
//          Content="<html><head></head><body><img src=\"cid:image\"/></body></html>";  
            //图片必须这样子：<img src='cid:image'/>  
            FileSystemResource img = new FileSystemResource(new File(imagePath));  
            messageHelper.addInline("image",img);  
        } catch (MessagingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        mailSender.send(mimeMessage);  
    }  */
    /** 
     * 发送普通带附件的Html邮件 
     * 
     */  
/*    public void sendHtmlWithAttachment(String filePath){  
        mailSender = this.getMailSender();  
        MimeMessage mimeMessage = mailSender.createMimeMessage();  
        try {  
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);  
            messageHelper.setTo(this.getTo());  
            messageHelper.setFrom(simpleMailMessage.getFrom());  
            messageHelper.setSubject(this.getSubject());  
            messageHelper.setText(this.getContent(),true);  
            FileSystemResource file = new FileSystemResource(new File(filePath));  
//          System.out.println("file.getFilename==="+file.getFilename());  
            messageHelper.addAttachment(file.getFilename(),file);  
        } catch (MessagingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        mailSender.send(mimeMessage);  
    }  */

//    public static void main(String[] args) {  
//        try {  
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"/applicationContext.xml","/applicationContext-mail.xml"});  
//        JavaMailSender sender = (JavaMailSender)ctx.getBean("mailSender");  
//        MimeMessage msg = sender.createMimeMessage();  
//            MimeMessageHelper helper = new MimeMessageHelper(msg,true,"utf-8");  
//            helper.setFrom("578681777@qq.com");  
//            helper.setTo("578681777@qq.com");  
//            helper.setText("tets this is a spring mvc email");  
//            helper.setSubject("test");  
//            sender.send(msg);  
//            System.out.println("email send ok");  
//              
//        } catch (MessagingException e) {  
//            System.out.println("send fail");  
//            e.printStackTrace();  
//        }  
//          
//    }  
}
