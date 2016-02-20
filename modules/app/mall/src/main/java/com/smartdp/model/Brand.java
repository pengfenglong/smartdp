package com.smartdp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 品牌
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_brand")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Brand extends IdEntity {

	/** 名称 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 编码 */
	@Column(name = "code")
	private String code;

	/** 描述 */
	@Column(name = "description")
	private String description;

	/** logo图片 */
	@Column(name = "logo")
	private String logo;

	/** 网址 */
	@Column(name = "website")
	private String website;

	/** 排序值 */
	@Column(name = "sort")
	private Long sort;

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "brand")
	private Set<Goods> goodss = new HashSet<Goods>(0);

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "brand")
	private Set<Category> categorys = new HashSet<Category>(0);

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public void setGoodss(Set<Goods> goods) {
		this.goodss = goods;
	}

	public Set<Goods> getGoodss() {
		return goodss;
	}

	public Set<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(Set<Category> categorys) {
		this.categorys = categorys;
	}

}
