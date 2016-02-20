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
 * 商品评论
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_goods_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment extends IdEntity {

	/** 评论内容 */
	@Column(name = "content")
	private String content;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	private Goods goods;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
