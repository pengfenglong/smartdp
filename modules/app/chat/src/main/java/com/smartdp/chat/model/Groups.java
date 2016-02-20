package com.smartdp.chat.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 群组
 * @author pengfenglong
 * 
 */
@Entity
@Table(name = "CHAT_GROUPS")
public class Groups extends IdEntity {

	private String name;

	private Timestamp createDate;

	private String creator;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "groups")
	private Set<GroupsUser> groupsUsers = new HashSet<GroupsUser>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Set<GroupsUser> getGroupsUsers() {
		return groupsUsers;
	}

	public void setGroupsUsers(Set<GroupsUser> groupsUsers) {
		this.groupsUsers = groupsUsers;
	}

}
