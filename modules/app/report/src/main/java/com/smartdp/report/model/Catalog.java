package com.smartdp.report.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 分类
 * @author pengfenglong
 */
@Entity
@Table(name = "resport_catalog")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Catalog extends IdEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Catalog parent;
	private String name;
	private String memo;

	public Catalog getParent() {
		return parent;
	}

	public void setParent(Catalog parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
