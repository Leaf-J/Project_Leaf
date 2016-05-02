package com.leaf.email.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.leaf.base.domain.BaseEntity;

@Entity
@Table(name = "t_EmailEvent")
public class EmailEvent extends BaseEntity {

	@Column
	private String eventName;

	@OneToMany(mappedBy = "emailEventId")
	private Set<EmailTemplate> eventTemplateSet;

	public enum EmailEventName {

		MemberUserAccountCreated("MemberUserAccountCreated");

		private final String eventName;

		EmailEventName(String name) {
			this.eventName = name;
		}

		public String getEventName() {
			return this.eventName;
		}
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Set<EmailTemplate> getEventTemplateSet() {
		return eventTemplateSet;
	}

	public void setEventTemplateSet(Set<EmailTemplate> eventTemplateSet) {
		this.eventTemplateSet = eventTemplateSet;
	}

}
