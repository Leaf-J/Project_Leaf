package com.leaf.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 后台系统操作数据字典
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_admin_operation")
public class AdminOperation extends BaseEntity {
	/**
	 * 操作名称
	 */
	@Column
	private String name;

	/**
	 * 描述
	 */
	@Column
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
