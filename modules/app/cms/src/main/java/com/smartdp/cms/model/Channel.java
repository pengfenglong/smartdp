/*
 * pengfenglong
 */

package com.smartdp.cms.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "cms_channel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonAutoDetect
public class Channel extends IdEntity{

	/**栏目名称*/
	@Column(name = "name", nullable = false)
	private java.lang.String name;
	
	/**栏目路径*/
	@Column(name = "path")
	private java.lang.String path;
	
	/**栏目编号*/
	@Column(name = "code")
	private java.lang.String code;
	
	/**栏目图标*/
	@Column(name = "icon")
	private java.lang.String icon;
	
	/**排列顺序*/
	@Column(name = "priority")
	private java.lang.Integer priority;
	
	/**是否单页*/
	@Column(name = "is_alone")
	private java.lang.Boolean isAlone;
	
	/**是否显示*/
	@Column(name = "is_show")
	private java.lang.Boolean isShow;
	
	/**外部链接*/
	@Column(name = "link")
	private java.lang.String link;
	
	/**栏目页模板*/
	@Column(name = "tpl_channel")
	private java.lang.String tplChannel;
	
	/**是否允许评论*/
	@Column(name = "comment_control")
	private java.lang.Boolean commentControl;
	
	/**顶踩控制*/
	@Column(name = "updown_control")
	private java.lang.Boolean updownControl;
	
	/**是否新窗口打开*/
	@Column(name = "is_blank")
	private java.lang.Boolean isBlank;
	
	/**TITLE*/
	@Column(name = "title")
	private java.lang.String title;
	
	/**KEYWORDS*/
	@Column(name = "keywords")
	private java.lang.String keywords;
	
	/**DESCRIPTION*/
	@Column(name = "description")
	private java.lang.String description;
	
	/**栏目内容*/
	@Lob
	@Column(name = "content")
	private java.lang.String content;
	
	/**所属站点*/
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	private Site site;
	
	/**文章集合*/
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "channel")
	@OrderBy("createDate desc")
	private Set<Article> articles = new HashSet<Article>(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Channel parent;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	@OrderBy("priority asc")
	private List<Channel> children = new ArrayList<Channel>();
	
	private String state;

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getPath() {
		return path;
	}

	public void setPath(java.lang.String path) {
		this.path = path;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}
	
	public java.lang.String getIcon() {
		return icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	public java.lang.Integer getPriority() {
		return priority;
	}

	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}

	public java.lang.Boolean getIsAlone() {
		return isAlone;
	}

	public void setIsAlone(java.lang.Boolean isAlone) {
		this.isAlone = isAlone;
	}

	public java.lang.Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(java.lang.Boolean isShow) {
		this.isShow = isShow;
	}

	public java.lang.String getLink() {
		return link;
	}

	public void setLink(java.lang.String link) {
		this.link = link;
	}

	public java.lang.String getTplChannel() {
		return tplChannel;
	}

	public void setTplChannel(java.lang.String tplChannel) {
		this.tplChannel = tplChannel;
	}

	public java.lang.Boolean getCommentControl() {
		return commentControl;
	}

	public void setCommentControl(java.lang.Boolean commentControl) {
		this.commentControl = commentControl;
	}

	public java.lang.Boolean getUpdownControl() {
		return updownControl;
	}

	public void setUpdownControl(java.lang.Boolean updownControl) {
		this.updownControl = updownControl;
	}

	public java.lang.Boolean getIsBlank() {
		return isBlank;
	}

	public void setIsBlank(java.lang.Boolean isBlank) {
		this.isBlank = isBlank;
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

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	@JsonIgnore
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@JsonIgnore
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@JsonIgnore
	public Channel getParent() {
		return parent;
	}

	public void setParent(Channel parent) {
		this.parent = parent;
	}

	public List<Channel> getChildren() {
		return children;
	}

	public void setChildren(List<Channel> children) {
		this.children = children;
	}
	
	public String getState() {
//		if(children.size() > 0){
//			return "closed";
//		}else{
//			return "open";
//		}
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
