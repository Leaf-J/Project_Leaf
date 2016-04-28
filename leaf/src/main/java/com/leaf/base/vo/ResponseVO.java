package com.leaf.base.vo;

import java.util.Map;

import com.leaf.base.constant.Constant;

/**
 * 响应的值对象
 * 
 * @author admin
 * 
 */
public abstract class ResponseVO {
	protected int status = Constant.RESPONSE_FAILED;
	protected String message = Constant.RESPONSE_MSG_FAILED;

	protected Map<String, Object> data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
