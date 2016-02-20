package com.smartdp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 商家
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_merchant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Merchant extends IdEntity {

	/** 商家名称 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 商家编码 */
	@Column(name = "code")
	private String code;

	/** 模板编码 */
	@Column(name = "template_code")
	private String templateCode;

	/** 商家描述 */
	@Column(name = "description")
	private String description;

	/** logo图片 */
	@Column(name = "big_image")
	private String bigImage;

	/** 二维码 */
	@Column(name = "small_image")
	private String smallImage;

	/** 联系人 */
	@Column(name = "contact")
	private String contact;

	/** 地址 */
	@Column(name = "address")
	private String address;

	/** 电话 */
	@Column(name = "phone")
	private String phone;

	/** 商家状态 */
	@Column(name = "status")
	private Integer status;

	/** 排序值 */
	@Column(name = "sort")
	private Long sort;

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "merchant")
	private Set<Goods> goodss = new HashSet<Goods>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "merchant")
	@OrderBy("sort asc")
	private Set<CustomCategory> customCategorys = new HashSet<CustomCategory>(0);

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "merchant")
	private Set<Category> categorys = new HashSet<Category>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "merchant")
	private Set<Order> orders = new HashSet<Order>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBigImage() {
		return bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Set<Goods> getGoodss() {
		return goodss;
	}

	public void setGoodss(Set<Goods> goodss) {
		this.goodss = goodss;
	}

	public Set<CustomCategory> getCustomCategorys() {
		return customCategorys;
	}

	public void setCustomCategorys(Set<CustomCategory> customCategorys) {
		this.customCategorys = customCategorys;
	}

	public Set<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(Set<Category> categorys) {
		this.categorys = categorys;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
