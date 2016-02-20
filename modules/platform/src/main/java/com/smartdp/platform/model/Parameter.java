/*
 * pengfenglong
 */

package com.smartdp.platform.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PLATFORM_PARAMETER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Parameter{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PARA_ID", nullable = false)
	private Long paraId;
	
	@Column(name = "CREATE_DATE", nullable = true)
	private Timestamp createDate;
	
	@Column(name = "CREATOR", nullable = true)
	private String creator;
	
	@Column(name = "MEMO", nullable = true)
	private String memo;
	
	@Column(name = "PARA_KEY_NAME", nullable = true)
	private String paraKeyName;
	
	@Column(name = "PARA_NAME", nullable = true)
	private String paraName;
	
	@Column(name = "PARA_TYPE", nullable = true)
	private String paraType;
	
	@Column(name = "PARA_VALUE", nullable = true)
	private String paraValue;
	
	@Column(name = "STATUS", nullable = true)
	private String status;

	public Long getParaId() {
		return this.paraId;
	}
	
	public void setParaId(Long value) {
		this.paraId = value;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(Timestamp value) {
		this.createDate = value;
	}

	public String getCreator() {
		return this.creator;
	}
	
	public void setCreator(String value) {
		this.creator = value;
	}

	public String getMemo() {
		return this.memo;
	}
	
	public void setMemo(String value) {
		this.memo = value;
	}

	public String getParaKeyName() {
		return this.paraKeyName;
	}
	
	public void setParaKeyName(String value) {
		this.paraKeyName = value;
	}

	public String getParaName() {
		return this.paraName;
	}
	
	public void setParaName(String value) {
		this.paraName = value;
	}

	public String getParaType() {
		return this.paraType;
	}
	
	public void setParaType(String value) {
		this.paraType = value;
	}

	public String getParaValue() {
		return this.paraValue;
	}
	
	public void setParaValue(String value) {
		this.paraValue = value;
	}

	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
}
