/*
 * pengfenglong
 */

package com.smartdp.cms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

@Entity
@Table(name = "cms_article_picture")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArticlePicture extends IdEntity{

	@Column(name = "article_id", nullable = false)
	private java.lang.Long articleId;
	
	/**排列顺序*/
	@Column(name = "priority", nullable = false)
	private java.lang.Integer priority;
	
	/**图片地址*/
	@Column(name = "img_path", nullable = false)
	private java.lang.String imgPath;
	
	/**描述*/
	@Column(name = "description", nullable = true)
	private java.lang.String description;
	
	/**状态*/
	@Column(name = "style", nullable = true)
	private java.lang.String style;
	
	@Column(name = "is_thumb", nullable = true)
	private java.lang.Short isThumb;
	

	public java.lang.Long getArticleId() {
		return this.articleId;
	}
	public void setArticleId(java.lang.Long value) {
		this.articleId = value;
	}
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	public java.lang.String getStyle() {
		return this.style;
	}
	public void setStyle(java.lang.String value) {
		this.style = value;
	}
	public java.lang.Short getIsThumb() {
		return this.isThumb;
	}
	public void setIsThumb(java.lang.Short value) {
		this.isThumb = value;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id",nullable = false, insertable = false, updatable = false)
	private Article article;
	
	public void setArticle(Article article){
		this.article = article;
	}
	
	public Article getArticle() {
		return article;
	}
}
