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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 商家自定义分类
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_custom_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomCategory extends IdEntity{

	/**分类名称*/
	@Column(name = "name", nullable = false)
	private String name;
	
	/**分类编码*/
	@Column(name = "code")
	private String code;
	
	/**父栏目id*/
	@Column(name = "parent_id")
	private Long parentId;
	
	/**分类大图*/
	@Column(name = "big_image")
	private String bigImage;
	
	/**分类小图*/
	@Column(name = "small_image")
	private String smallImage;
	
	/**显示状态,0:不显示,1:显示*/
	@Column(name = "status")
	private Integer status;
	
	/**排序值*/
	@Column(name = "sort")
	private Long sort;
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "customCategory")
	private Set<Goods> goodss = new HashSet<Goods>(0);
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;
	
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
	
	public Long getParentId() {
		return this.parentId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public String getBigImage() {
		return this.bigImage;
	}
	
	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}
	
	public String getSmallImage() {
		return this.smallImage;
	}
	
	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getSort() {
		return this.sort;
	}
	
	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	public void setGoodss(Set<Goods> goods){
		this.goodss = goods;
	}
	
	public Set<Goods> getGoodss() {
		return goodss;
	}
	
	public void setMerchant(Merchant merchant){
		this.merchant = merchant;
	}
	
	public Merchant getMerchant() {
		return merchant;
	}
	
}
