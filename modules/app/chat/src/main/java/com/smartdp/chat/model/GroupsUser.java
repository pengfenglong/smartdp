package com.smartdp.chat.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 群组用户
 * @author pengfenglong
 * 
 */
@Entity
@Table(name = "CHAT_GROUPSUSER")
public class GroupsUser extends IdEntity {

	private String userId;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "groups_id")
	private Groups groups;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

}
