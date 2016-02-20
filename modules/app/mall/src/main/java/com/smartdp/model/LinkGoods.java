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
 * 套餐商品
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_link_goods")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LinkGoods extends IdEntity{

	/**商品id*/
	@Column(name = "goods_id", nullable = false)
	private Long goodsId;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "link_goods_id")
	private Goods goods;
	
	public Long getGoodsId() {
		return this.goodsId;
	}
	
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	public void setGoods(Goods goods){
		this.goods = goods;
	}
	
	public Goods getGoods() {
		return goods;
	}
	
}
