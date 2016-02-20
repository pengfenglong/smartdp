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
 * 用户收藏
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_favorite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Favorite extends IdEntity{

	/**收藏对象id*/
	@Column(name = "target_id", nullable = false)
	private Long targetId;
	
	/**类型（商品、商家）*/
	@Column(name = "type", nullable = false)
	private String type;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Long getTargetId() {
		return this.targetId;
	}
	
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
}
