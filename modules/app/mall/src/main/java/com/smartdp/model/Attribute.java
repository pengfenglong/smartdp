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
 * 属性类型(如颜色、尺寸、认证、菜谱)
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_attribute")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Attribute extends IdEntity {

	/** 属性类型名称 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 编码 */
	@Column(name = "code")
	private String code;

	/** 描述 */
	@Column(name = "description")
	private String description;
	
	/** 录入方式，如 输入框:text,列表选择:select，多选:multiselect,文本域:textarea,图片:image,图文:imagetext */
	@Column(name = "entryway")
	private String entryway;

	/** 排序值 */
	@Column(name = "sort")
	private Long sort;

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "attribute")
	private Set<AttributeValue> attributeValues = new HashSet<AttributeValue>(0);

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "attribute")
	private Set<Category> categorys = new HashSet<Category>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "attribute")
	private Set<GoodsAttributeValue> goodsAttributeValues = new HashSet<GoodsAttributeValue>(
			0);

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

	public String getEntryway() {
		return this.entryway;
	}

	public void setEntryway(String entryway) {
		this.entryway = entryway;
	}

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public void setAttributeValues(Set<AttributeValue> attributeValue) {
		this.attributeValues = attributeValue;
	}

	public Set<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public void setCategorys(Set<Category> categorys) {
		this.categorys = categorys;
	}

	public Set<Category> getCategorys() {
		return categorys;
	}

	public Set<GoodsAttributeValue> getGoodsAttributeValues() {
		return goodsAttributeValues;
	}

	public void setGoodsAttributeValues(
			Set<GoodsAttributeValue> goodsAttributeValues) {
		this.goodsAttributeValues = goodsAttributeValues;
	}

}
