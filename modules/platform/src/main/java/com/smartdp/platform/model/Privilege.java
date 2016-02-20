package com.smartdp.platform.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PLATFORM_PRIVILEGE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Privilege{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRIVILEGE_ID", nullable = false)
	private Long privilegeId;
	
	@Column(name = "ROLE_ID", nullable = true)
	private Long roleId;
	
	@Column(name = "RESOURCE_ID", nullable = true)
	private String resourceId;
	
	@Column(name = "PRIVILEGE_TYPE", nullable = true)
	private String privilegeType;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID",nullable = false, insertable = false, updatable = false)
	private Role role;

	public Long getPrivilegeId() {
		return this.privilegeId;
	}
	
	public void setPrivilegeId(Long value) {
		this.privilegeId = value;
	}

	public Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Long value) {
		this.roleId = value;
	}

	public String getResourceId() {
		return this.resourceId;
	}
	
	public void setResourceId(String value) {
		this.resourceId = value;
	}

	public String getPrivilegeType() {
		return this.privilegeType;
	}
	
	public void setPrivilegeType(String value) {
		this.privilegeType = value;
	}
	
	public void setRole(Role role){
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}
}
