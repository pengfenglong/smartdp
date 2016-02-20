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
 * 购物车
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_shoppingcart")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Shoppingcart extends IdEntity{

	/**数量*/
	@Column(name = "quantity", nullable = false)
	private Long quantity;
	
	@Column(name = "attr")
	private String attr;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	private Goods goods;
	
	public Long getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public String getAttr() {
		return this.attr;
	}
	
	public void setAttr(String attr) {
		this.attr = attr;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setGoods(Goods goods){
		this.goods = goods;
	}
	
	public Goods getGoods() {
		return goods;
	}
	
}
