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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 订单
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends IdEntity {

	/** 订单号 */
	@Column(name = "order_code", nullable = false)
	private String orderCode;

	/** 支付帐号 */
	@Column(name = "payment_code", nullable = false)
	private String paymentCode;

	/** 订单状态,completed:已完成;nocompleted:未完成;refused:用户拒收;canceled:已取消 */
	@Column(name = "status", nullable = false)
	private String status;

	/** 价格 */
	@Column(name = "total_amount")
	private Float totalAmount;

	/** 订购数量 */
	@Column(name = "quantity")
	private Long quantity;

	/** 描述 */
	@Column(name = "description")
	private String description;

	/** 接受人 */
	@Column(name = "recipient")
	private String recipient;

	/** 电话 */
	@Column(name = "phone")
	private String phone;

	/** 地址 */
	@Column(name = "address")
	private String address;

	/** 邮编 */
	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "email")
	private String email;

	/** 配送费用 */
	@Column(name = "delivery_fee")
	private Float deliveryFee;

	/** 订单创建时间 */
	@Column(name = "create_time", nullable = false)
	private java.sql.Timestamp createTime;

	/** 订单确认时间 */
	@Column(name = "confirm_time")
	private java.sql.Timestamp confirmTime;

	/** 订单支付时间 */
	@Column(name = "pay_time")
	private java.sql.Timestamp payTime;

	/** 订单配送时间 */
	@Column(name = "delivery_time")
	private java.sql.Timestamp deliveryTime;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderGoods> orderGoodss = new HashSet<OrderGoods>(0);

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getPaymentCode() {
		return this.paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Float getDeliveryFee() {
		return this.deliveryFee;
	}

	public void setDeliveryFee(Float deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	public java.sql.Timestamp getConfirmTime() {
		return this.confirmTime;
	}

	public void setConfirmTime(java.sql.Timestamp confirmTime) {
		this.confirmTime = confirmTime;
	}

	public java.sql.Timestamp getPayTime() {
		return this.payTime;
	}

	public void setPayTime(java.sql.Timestamp payTime) {
		this.payTime = payTime;
	}

	public java.sql.Timestamp getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(java.sql.Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Set<OrderGoods> getOrderGoodss() {
		return orderGoodss;
	}

	public void setOrderGoodss(Set<OrderGoods> orderGoodss) {
		this.orderGoodss = orderGoodss;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getMerchantName() {
		return merchant.getName();
	}

}
