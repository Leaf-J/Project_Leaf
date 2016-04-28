package com.leaf.common.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.leaf.base.domain.BaseEntity;

/**
 * 后台系统角色
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_admin_role")
@JsonIgnoreProperties(value={"uers","adminRoleResources"})
public class AdminRole extends BaseEntity {
	@Column
	private String name;

	@Column
	private String description;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles", targetEntity = com.leaf.common.domain.AdminUser.class)
	private List<AdminUser> uers;

	@OneToMany(mappedBy = "role")
	private List<AdminRoleResource> adminRoleResources;

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

	public List<AdminUser> getUers() {
		return uers;
	}

	public void setUers(List<AdminUser> uers) {
		this.uers = uers;
	}

	public List<AdminRoleResource> getAdminRoleResources() {
		return adminRoleResources;
	}

	public void setAdminRoleResources(List<AdminRoleResource> adminRoleResources) {
		this.adminRoleResources = adminRoleResources;
	}

	@Override
	public String toString() {
		return "AdminRole:"+this.getName()+":"+this.getDescription();
	}
}
