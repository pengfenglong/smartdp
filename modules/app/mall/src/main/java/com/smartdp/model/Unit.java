package com.smartdp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 单位
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_unit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Unit extends IdEntity {

	/** 单位名称 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 单位编码 */
	@Column(name = "code")
	private String code;

	/** 单位描述 */
	@Column(name = "description")
	private String description;

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "unit")
	private Set<Goods> goodss = new HashSet<Goods>(0);

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGoodss(Set<Goods> goods) {
		this.goodss = goods;
	}

	public Set<Goods> getGoodss() {
		return goodss;
	}

}
