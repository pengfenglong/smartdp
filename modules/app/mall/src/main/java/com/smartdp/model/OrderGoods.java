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
 * 订单
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_order_goods")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderGoods extends IdEntity{

	/**商品id*/
	@Column(name = "goods_id", nullable = false)
	private Long goodsId;
	
	/**商品名称*/
	@Column(name = "goods_name", nullable = false)
	private String goodsName;
	
	/**商品名称*/
	@Column(name = "small_image")
	private String smallImage;
	
	/**属性*/
	@Column(name = "attr")
	private String attr;
	
	/**商品数量*/
	@Column(name = "quantity", nullable = false)
	private Long quantity;
	
	/**商品单价*/
	@Column(name = "unit_price", nullable = false)
	private Float unitPrice;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	public Long getGoodsId() {
		return this.goodsId;
	}
	
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getGoodsName() {
		return this.goodsName;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getAttr() {
		return this.attr;
	}
	
	public void setAttr(String attr) {
		this.attr = attr;
	}
	
	public Long getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public Float getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public void setOrder(Order order){
		this.order = order;
	}
	
	public Order getOrder() {
		return order;
	}
	
}
