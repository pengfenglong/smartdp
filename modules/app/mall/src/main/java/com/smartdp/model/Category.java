package com.smartdp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 商品分类
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category extends IdEntity {

	/** 分类名称 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 分类编码 */
	@Column(name = "code")
	private String code;

	/** 分类大图 */
	@Column(name = "big_image")
	private String bigImage;

	/** 分类小图 */
	@Column(name = "small_image")
	private String smallImage;


	/** 显示状态,0:不显示,1:显示 */
	@Column(name = "status")
	private Integer status;

	/** 排序值 */
	@Column(name = "sort")
	private Long sort;

	/** 子类 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "upCategory")
	@OrderBy("sort asc")
	private List<Category> subCategories = new ArrayList<Category>();

	/** 父类 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category upCategory;

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Attribute> attributes = new HashSet<Attribute>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Brand> brands = new HashSet<Brand>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Goods> goodss = new HashSet<Goods>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Merchant> merchants = new HashSet<Merchant>(0);

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

	public String getBigImage() {
		return this.bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}

	public String getSmallImage() {
		return this.smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public List<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public Category getUpCategory() {
		return upCategory;
	}

	public void setUpCategory(Category upCategory) {
		this.upCategory = upCategory;
	}

	public void setGoodss(Set<Goods> goods) {
		this.goodss = goods;
	}

	public Set<Goods> getGoodss() {
		return goodss;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<Brand> getBrands() {
		return brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	public Set<Merchant> getMerchants() {
		return merchants;
	}

	public void setMerchants(Set<Merchant> merchants) {
		this.merchants = merchants;
	}

}
