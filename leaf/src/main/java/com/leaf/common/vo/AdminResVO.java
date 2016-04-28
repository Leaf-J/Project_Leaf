package com.leaf.common.vo;

import java.util.List;

import com.leaf.base.vo.ValueObject;

public class AdminResVO extends ValueObject{
	private Long id;
	private String name;
	private String url;
	private Integer leafFlag;
	private String label;
	private List<String> operations;
	private AdminResVO parent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLeafFlag() {
		return leafFlag;
	}

	public void setLeafFlag(Integer leafFlag) {
		this.leafFlag = leafFlag;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getOperations() {
		return operations;
	}

	public void setOperations(List<String> operations) {
		this.operations = operations;
	}

	public AdminResVO getParent() {
		return parent;
	}

	public void setParent(AdminResVO parent) {
		this.parent = parent;
	}

}
