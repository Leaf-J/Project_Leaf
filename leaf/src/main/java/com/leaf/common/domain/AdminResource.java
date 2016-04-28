package com.leaf.common.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.leaf.base.constant.Constant;
import com.leaf.base.domain.BaseEntity;

/**
 * 后台系统资源
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "t_admin_resource")
@JsonIgnoreProperties(value={"subResources","adminRoleResources"}) 
public class AdminResource extends BaseEntity {

	@Column
	private String name;

	@Column(length = 200)
	private String url;

	@Column(name = "order_no")
	private Integer orderNo;

	@OneToMany(mappedBy = "parent")
	@OrderBy("orderNo")
	private List<AdminResource> subResources;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private AdminResource parent;

	@OneToMany(mappedBy = "resource")
	private List<AdminRoleResource> adminRoleResources;

	/**
	 * 资源叶子节点标识
	 */
	@Column(length = 1, name = "leaf_flag")
	private Integer leafFlag = Constant.ADMIN_RESOURCE_IS_LEAF_YES;

	/**
	 * 显示在应用中的名称
	 */
	@Column
	private String label;

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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public List<AdminResource> getSubResources() {
		return subResources;
	}

	public void setSubResources(List<AdminResource> subResources) {
		this.subResources = subResources;
	}

	public AdminResource getParent() {
		return parent;
	}

	public void setParent(AdminResource parent) {
		this.parent = parent;
	}

	public List<AdminRoleResource> getAdminRoleResources() {
		return adminRoleResources;
	}

	public void setAdminRoleResources(List<AdminRoleResource> adminRoleResources) {
		this.adminRoleResources = adminRoleResources;
	}

	public void setLeafFlag(Integer leafFlag) {
		this.leafFlag = leafFlag;
	}

	public Integer getLeafFlag() {
		return leafFlag;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
