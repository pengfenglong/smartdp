/*
 * pengfenglong
 */

package com.smartdp.platform.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PLATFORM_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID", nullable = false)
	private Long roleId;
	
	@Column(name = "ROLE_NAME", nullable = true)
	private String roleName;
	
	@Column(name = "ROLE_LEVEL", nullable = true)
	private Short roleLevel;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	@Column(name = "MEMO", nullable = true)
	private String memo;
	
	@Column(name = "CREATOR", nullable = true)
	private String creator;
	
	@Column(name = "CREATE_DATE", nullable = false)
	private Timestamp createDate;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy="roles")
	private Set<Group> groups = new HashSet<Group>(0);

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy="roles")
	private Set<User> users = new HashSet<User>(0);
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "role")
	private Set<Privilege> privileges = new HashSet<Privilege>(0);

	public Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Long value) {
		this.roleId = value;
	}

	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(String value) {
		this.roleName = value;
	}

	public Short getRoleLevel() {
		return this.roleLevel;
	}
	
	public void setRoleLevel(Short value) {
		this.roleLevel = value;
	}

	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}

	public String getMemo() {
		return this.memo;
	}
	
	public void setMemo(String value) {
		this.memo = value;
	}

	public String getCreator() {
		return this.creator;
	}
	
	public void setCreator(String value) {
		this.creator = value;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(Timestamp value) {
		this.createDate = value;
	}
	
	public void setPrivileges(Set<Privilege> privilege){
		this.privileges = privilege;
	}
	
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	
	public void setGroups(Set<Group> groups){
		this.groups = groups;
	}
	
	public Set<Group> getGroups() {
		return groups;
	}
	
	public void setUsers(Set<User> users){
		this.users = users;
	}
	
	public Set<User> getUsers() {
		return users;
	}
}
