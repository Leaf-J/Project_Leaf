package com.leaf.common.vo;

import java.util.Map;

import com.leaf.base.vo.ValueObject;

/**
 * 后台导航栏树值对象 for easyui
 * 
 * @author admin
 * 
 */
public class NavTreeVO extends ValueObject{
	public static final String STATE_OPEN = "open";
	public static final String STATE_CLOSED = "closed";
	// 节点id
	private String id;
	// 节点名称
	private String text;
	// 节点状态，open表示叶子节点，close表示非叶子节点
	private String state;
	// 是否选中 ,默认false
	private boolean checked = false;

	// 附加属性
	private Map<String, Object> attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
