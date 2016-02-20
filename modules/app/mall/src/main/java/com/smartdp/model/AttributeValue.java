package com.smartdp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 属性
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_attribute_value")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttributeValue extends IdEntity {

	/** 属性名称 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 编码 */
	@Column(name = "code")
	private String code;

	/** 图片 */
	@Column(name = "image")
	private String image;

	/** 描述 */
	@Column(name = "description")
	private String description;

	/** 排序值 */
	@Column(name = "sort")
	private Long sort;

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "attributeValue")
	private Set<GoodsAttributeValue> goodsAttributeValues = new HashSet<GoodsAttributeValue>(
			0);

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute_id")
	private Attribute attribute;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public void setGoodsAttributeValues(
			Set<GoodsAttributeValue> goodsAttributeValue) {
		this.goodsAttributeValues = goodsAttributeValue;
	}

	public Set<GoodsAttributeValue> getGoodsAttributeValues() {
		return goodsAttributeValues;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Attribute getAttribute() {
		return attribute;
	}

}
