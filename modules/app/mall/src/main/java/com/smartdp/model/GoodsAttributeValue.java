package com.smartdp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 商品属性
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_goods_attribute_value")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GoodsAttributeValue extends IdEntity {

	/** 图片 */
	@Column(name = "image")
	private String image;

	/** 描述 */
	@Column(name = "description")
	private String description;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	private Goods goods;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute_value_id")
	private AttributeValue attributeValue;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute_id")
	private Attribute attribute;

	/** 手工输入属性值 */
	@Column(name = "attribute_value")
	private String attrValues;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public AttributeValue getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(AttributeValue attributeValue) {
		this.attributeValue = attributeValue;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public String getAttrValues() {
		return attrValues;
	}

	public void setAttrValues(String attrValues) {
		this.attrValues = attrValues;
	}

}
