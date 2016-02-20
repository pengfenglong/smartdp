/**
 * pengfenglong
 * 模块操作
 */
package com.smartdp.platform.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PLATFORM_MODULEOPERATE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ModuleOperate {

	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OPERATE_ID", nullable = false)
	private Long operateId;

	@Column(name = "OPERATE_NAME", nullable = true)
	private String operateName;

	@Column(name = "OPERATE_IMG_LINK", nullable = true)
	private String optImgLink;

	@Column(name = "OPERATE_ORDER", nullable = true)
	private Integer optOrder;

	@Column(name = "OPERATE_GROUP", nullable = true)
	private Integer optGroup;

	@Column(name = "MEMO", nullable = true)
	private String memo;

	@Column(name = "STATUS", nullable = true)
	private String status;

	@Column(name = "CREATOR", nullable = true)
	private String creator;

	@Column(name = "CREATEDATE", nullable = true)
	private Date createDate;

	@Column(name = "OPERATE_FUN_LINK", nullable = true)
	private String optFunLink;
	
	public Long getOperateId() {
		return operateId;
	}

	public void setOperateId(Long operateId) {
		this.operateId = operateId;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getOptImgLink() {
		return optImgLink;
	}

	public void setOptImgLink(String optImgLink) {
		this.optImgLink = optImgLink;
	}

	public Integer getOptOrder() {
		return optOrder;
	}

	public void setOptOrder(Integer optOrder) {
		this.optOrder = optOrder;
	}

	public Integer getOptGroup() {
		return optGroup;
	}

	public void setOptGroup(Integer optGroup) {
		this.optGroup = optGroup;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOptFunLink() {
		return optFunLink;
	}

	public void setOptFunLink(String optFunLink) {
		this.optFunLink = optFunLink;
	}
}
