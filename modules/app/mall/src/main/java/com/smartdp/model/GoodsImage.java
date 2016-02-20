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
 * 商品图片
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_goods_image")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GoodsImage extends IdEntity {

	/** 图片路径 */
	@Column(name = "image", nullable = false)
	private String image;

	/** 图片名称 */
	@Column(name = "name")
	private String name;

	/** 图片排序 */
	@Column(name = "sort")
	private Integer sort;

	@Column(name = "version_id")
	private Long versionId;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	private Goods goods;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getVersionId() {
		return this.versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Goods getGoods() {
		return goods;
	}

}
