package com.leaf.base.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -9063401315848788150L;
	
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "identity")
	private long pid;
	
	public long getPid() {
		return pid;
	}
	
	public void setPid(long pid) {
		this.pid = pid;
	}
}
