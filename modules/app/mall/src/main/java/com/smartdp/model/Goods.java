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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 商品
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_goods")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Goods extends IdEntity {

	/** 商品名称 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 商品名称拼音首字母 */
	@Column(name = "inital")
	private String inital;

	/** 商品编码 */
	@Column(name = "code")
	private String code;

	/** 商品价格 */
	@Column(name = "price", nullable = false)
	private Float price;

	/** 访问量 */
	@Column(name = "visit")
	private Long visit;

	/** 返现 */
	@Column(name = "cashback")
	private Float cashback;

	/** 商品折扣价 */
	@Column(name = "discount")
	private Float discount;

	/** 积分 */
	@Column(name = "integration")
	private Long integration;

	/** 商品小图 */
	@Column(name = "small_image")
	private String smallImage;

	/** 商品大图 */
	@Column(name = "big_image")
	private String bigImage;

	@Column(name = "create_date")
	@OrderBy
	private java.sql.Timestamp createDate;

	/** 商品描述 */
	@Column(name = "description")
	private String description;

	/** 审核状态,0:未审核，1:已审核 */
	@Column(name = "audit")
	private Integer audit;

	/** 上下架,0:下架;1,上架 */
	@Column(name = "status")
	private Integer status;

	/** 排序值 */
	@Column(name = "sort")
	private Long sort;

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "goods")
	@OrderBy("id asc")
	private Set<GoodsAttributeValue> goodsAttributeValues = new HashSet<GoodsAttributeValue>(
			0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "goods")
	private Set<Comment> comments = new HashSet<Comment>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "goods")
	@OrderBy("sort asc")
	private Set<GoodsImage> goodsImages = new HashSet<GoodsImage>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "goods")
	private Set<LinkGoods> linkGoodss = new HashSet<LinkGoods>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "goods")
	private Set<Shoppingcart> shoppingcarts = new HashSet<Shoppingcart>(0);

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "custom_category_id")
	private CustomCategory customCategory;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInital() {
		return inital;
	}

	public void setInital(String inital) {
		this.inital = inital;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getVisit() {
		return visit;
	}

	public void setVisit(Long visit) {
		this.visit = visit;
	}

	public Float getCashback() {
		return cashback;
	}

	public void setCashback(Float cashback) {
		this.cashback = cashback;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Long getIntegration() {
		return integration;
	}

	public void setIntegration(Long integration) {
		this.integration = integration;
	}

	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getBigImage() {
		return bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Set<GoodsAttributeValue> getGoodsAttributeValues() {
		return goodsAttributeValues;
	}

	public void setGoodsAttributeValues(
			Set<GoodsAttributeValue> goodsAttributeValues) {
		this.goodsAttributeValues = goodsAttributeValues;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<GoodsImage> getGoodsImages() {
		return goodsImages;
	}

	public void setGoodsImages(Set<GoodsImage> goodsImages) {
		this.goodsImages = goodsImages;
	}

	public Set<LinkGoods> getLinkGoodss() {
		return linkGoodss;
	}

	public void setLinkGoodss(Set<LinkGoods> linkGoodss) {
		this.linkGoodss = linkGoodss;
	}

	public Set<Shoppingcart> getShoppingcarts() {
		return shoppingcarts;
	}

	public void setShoppingcarts(Set<Shoppingcart> shoppingcarts) {
		this.shoppingcarts = shoppingcarts;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CustomCategory getCustomCategory() {
		return customCategory;
	}

	public void setCustomCategory(CustomCategory customCategory) {
		this.customCategory = customCategory;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

}
