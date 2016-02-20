package com.smartdp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_recommed")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Recommed extends IdEntity {

	/** 推荐图片路径 */
	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "sort")
	@OrderBy
	private Integer sort;

	@Column(name = "image_width")
	private Integer imageWidth;

	@Column(name = "image_height")
	private Integer imageHeight;

	@Column(name = "image_position_x")
	private Integer imagePositionX;

	@Column(name = "image_position_y")
	private Integer imagePositionY;

	@Column(name = "description")
	private String description;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "recommed_type_id")
	private RecommedType recommedType;

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getImageWidth() {
		return this.imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}

	public Integer getImageHeight() {
		return this.imageHeight;
	}

	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}

	public Integer getImagePositionX() {
		return this.imagePositionX;
	}

	public void setImagePositionX(Integer imagePositionX) {
		this.imagePositionX = imagePositionX;
	}

	public Integer getImagePositionY() {
		return this.imagePositionY;
	}

	public void setImagePositionY(Integer imagePositionY) {
		this.imagePositionY = imagePositionY;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRecommedType(RecommedType recommedType) {
		this.recommedType = recommedType;
	}

	public RecommedType getRecommedType() {
		return recommedType;
	}

}
