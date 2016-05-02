package com.leaf.email.domain;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import com.leaf.base.util.ValidateUtil;

/**
 * 非实体的email 请求封装
 * @author admin
 *
 */
@Validated
public abstract class EmailRequest {

	@NotBlank
	@Email(message="\"from\" should be a email address")
	private String from;

	@NotBlank
	@Pattern(regexp="(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*;)+"
	,message="\"to\" should contains valid email address,semicolon as spliter.e.g. addr1@test.com;addr2@test.com;")
	private String to;

	@Pattern(regexp="(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*;)+"
	,message="\"cc\" should contains valid email address,semicolon as spliter.e.g. addr1@test.com;addr2@test.com;")
	private String cc;

	@Pattern(regexp="(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*;)+"
	,message="\"bcc\" should contains valid email address,semicolon as spliter.e.g. addr1@test.com;addr2@test.com;")
	private String bcc;

	public String getFrom() {
		return from;
	}

	protected void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	protected void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	protected void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	protected void setBcc(String bcc) {
		this.bcc = bcc;
	}
	
	public EmailRequest cc(String cc){
		this.setCc(cc);
		ValidateUtil.validate(this, "cc");
		return this;
	}
	
	public EmailRequest bcc(String bcc){
		this.setBcc(bcc);
		ValidateUtil.validate(this,"bcc");
		return this;
	}

	/**
	 * hard coded, move to config file in the future
	 * @return
	 */
	protected static String getSystemFromEmail(){
		return "578681777@qq.com";
	}
}
