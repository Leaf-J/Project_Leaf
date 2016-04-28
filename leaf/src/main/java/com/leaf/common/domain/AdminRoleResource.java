package com.leaf.common.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

/**
 * 用户资源关联表
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_admin_role_res") 
public class AdminRoleResource extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "role_id")
	private AdminRole role;

	@ManyToOne
	@JoinColumn(name = "resource_id")
	private AdminResource resource;

	@ManyToMany
	@JoinTable(name = "t_admin_role_res_opr", joinColumns = { @JoinColumn(name = "role_res_id") }, inverseJoinColumns = { @JoinColumn(name = "opration_id") })
	private List<AdminOperation> oprs;

	public AdminRole getRole() {
		return role;
	}

	public void setRole(AdminRole role) {
		this.role = role;
	}

	public AdminResource getResource() {
		return resource;
	}

	public void setResource(AdminResource resource) {
		this.resource = resource;
	}

	public List<AdminOperation> getOprs() {
		return oprs;
	}

	public void setOprs(List<AdminOperation> oprs) {
		this.oprs = oprs;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AdminRoleResource)) {
			return false;
		}
		AdminRoleResource roleRes = (AdminRoleResource) obj;
		return this.getPid() == roleRes.getPid();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + new Long(this.getPid()).hashCode();
		return result;
	}
}
