package com.smartdp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 推荐类型
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_recommed_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RecommedType extends IdEntity {

	/** 类型名称 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 编码 */
	@Column(name = "code")
	private String code;

	/** 描述 */
	@Column(name = "description")
	private String description;

	/** 排序值 */
	@Column(name = "sort")
	private Long sort;

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "recommedType")
	@OrderBy("sort asc")
	private Set<Recommed> recommeds = new HashSet<Recommed>(0);

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

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public void setRecommeds(Set<Recommed> recommed) {
		this.recommeds = recommed;
	}

	public Set<Recommed> getRecommeds() {
		return recommeds;
	}

}
