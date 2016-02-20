/*
 * pengfenglong
 */

package com.smartdp.cms.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

@Entity
@Table(name = "cms_site")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonAutoDetect
public class Site extends IdEntity {

	/** 域名 */
	@Column(name = "domain")
	private java.lang.String domain;

	/** 路径 */
	@Column(name = "site_path")
	private java.lang.String sitePath;

	/** 网站名称 */
	@Column(name = "site_name", nullable = false)
	private java.lang.String siteName;

	/** 简短名称 */
	@Column(name = "short_name")
	private java.lang.String shortName;
	
	/** 编码*/
	@Column(name = "code")
	private java.lang.String code;

	/** 上下文 */
	@Column(name = "context_path")
	private java.lang.String contextPath;

	/** 端口 */
	@Column(name = "port")
	private java.lang.Integer port;

	/** 网站风格 */
	@Column(name = "tpl_style")
	private java.lang.String tplStyle;

	/** title */
	@Column(name = "title")
	private java.lang.String title;

	/** 关键字 */
	@Column(name = "keywords")
	private java.lang.String keywords;

	/** 描述 */
	@Column(name = "description")
	private java.lang.String description;

	/** 开启回收站 */
	@Column(name = "is_recover")
	private java.lang.Boolean isRecover;

	@Column(name = "CREATOR")
	private String creator;

	@Column(name = "CREATE_DATE", nullable = false)
	private Timestamp createDate;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id")
	private Template template;
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "site")
	@OrderBy("priority asc")
	private Set<Channel> channels = new HashSet<Channel>(0);

	public java.lang.String getDomain() {
		return domain;
	}

	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}

	public java.lang.String getSitePath() {
		return sitePath;
	}

	public void setSitePath(java.lang.String sitePath) {
		this.sitePath = sitePath;
	}

	public java.lang.String getSiteName() {
		return siteName;
	}

	public void setSiteName(java.lang.String siteName) {
		this.siteName = siteName;
	}

	public java.lang.String getShortName() {
		return shortName;
	}

	public void setShortName(java.lang.String shortName) {
		this.shortName = shortName;
	}
	
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getContextPath() {
		return contextPath;
	}

	public void setContextPath(java.lang.String contextPath) {
		this.contextPath = contextPath;
	}

	public java.lang.Integer getPort() {
		return port;
	}

	public void setPort(java.lang.Integer port) {
		this.port = port;
	}

	public java.lang.String getTplStyle() {
		return tplStyle;
	}

	public void setTplStyle(java.lang.String tplStyle) {
		this.tplStyle = tplStyle;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getKeywords() {
		return keywords;
	}

	public void setKeywords(java.lang.String keywords) {
		this.keywords = keywords;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Boolean getIsRecover() {
		return isRecover;
	}

	public void setIsRecover(java.lang.Boolean isRecover) {
		this.isRecover = isRecover;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	@JsonIgnore
	public Set<Channel> getChannels() {
		return channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}
	
}
