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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

@Entity
@Table(name = "cms_article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonAutoDetect
public class Article extends IdEntity{
	
	/**用户ID*/
	@Column(name = "user_id")
	private java.lang.Long userId;
	
	/**审核人员ID*/
	@Column(name = "check_id")
	private java.lang.Integer checkId;
	
	/**步骤*/
	@Column(name = "step")
	private java.lang.Integer step;
	
	/**标题*/
	@Column(name = "title", nullable = false)
	private java.lang.String title;
	
	/**短标题*/
	@Column(name = "short_title")
	private java.lang.String shortTitle;
	
	/**标题颜色*/
	@Column(name = "title_color")
	private java.lang.String titleColor;
	
	/**是否加粗*/
	@Column(name = "is_bold")
	private java.lang.Boolean isBold;
	
	/**是否置顶*/
	@Column(name = "is_top")
	private java.lang.Boolean isTop;
	
	/**是否推荐*/
	@Column(name = "is_recommend")
	private java.lang.Boolean isRecommend;
	
	/**状态(0:草稿;1:审核中;2:已审核;3:回收站)*/
	@Column(name = "status")
	private java.lang.Byte status;
	
	/**类型*/
	@Column(name = "style")
	private java.lang.String style;
	
	/**副标题*/
	@Column(name = "sub_title")
	private java.lang.String subTitle;
	
	/**来源*/
	@Column(name = "origin")
	private java.lang.String origin;
	
	/**来源链接*/
	@Column(name = "origin_url")
	private java.lang.String originUrl;
	
	/**是否显示到首页*/
	@Column(name = "show_index")
	private java.lang.Boolean showIndex;
	
	/**是否红头文件*/
	@Column(name = "is_red_tape")
	private java.lang.Boolean isRedTape;
	
	/**红头文件来源*/
	@Column(name = "red_tape_origin")
	private java.lang.String redTapeOrigin;
	
	/**描述*/
	@Column(name = "description")
	private java.lang.String description;
	
	/**是否允许评论*/
	@Column(name = "comment_control")
	private java.lang.Boolean commentControl;
	
	/**顶踩控制*/
	@Column(name = "updown_control")
	private java.lang.Boolean updownControl;
	
	/**外部链接*/
	@Column(name = "link")
	private java.lang.String link;
	
	/**指定模板*/
	@Column(name = "tpl_content")
	private java.lang.String tplContent;
	
	/**定时日期*/
	@Column(name = "time_day")
	private java.sql.Date timeDay;
	
	/**定时时间*/
	@Column(name = "time_hour")
	private java.sql.Time timeHour;
	
	@Column(name = "tag_str")
	private java.lang.String tagStr;
	
	@Column(name = "CREATOR")
	private String creator;

	@Column(name = "CREATE_DATE")
	private Timestamp createDate;
	
	/**文章内容*/
	@Lob
	@Column(name = "content", nullable = false)
	private java.lang.String content;
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "article")
	private Set<ArticlePicture> articlePictures = new HashSet<ArticlePicture>(0);
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id")
	private Channel channel;

	public java.lang.Long getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	public java.lang.Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(java.lang.Integer checkId) {
		this.checkId = checkId;
	}

	public java.lang.Integer getStep() {
		return step;
	}

	public void setStep(java.lang.Integer step) {
		this.step = step;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(java.lang.String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public java.lang.String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(java.lang.String titleColor) {
		this.titleColor = titleColor;
	}

	public java.lang.Boolean getIsBold() {
		return isBold;
	}

	public void setIsBold(java.lang.Boolean isBold) {
		this.isBold = isBold;
	}

	public java.lang.Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(java.lang.Boolean isTop) {
		this.isTop = isTop;
	}

	public java.lang.Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(java.lang.Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public java.lang.Byte getStatus() {
		return status;
	}

	public void setStatus(java.lang.Byte status) {
		this.status = status;
	}

	public java.lang.String getStyle() {
		return style;
	}

	public void setStyle(java.lang.String style) {
		this.style = style;
	}

	public java.lang.String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(java.lang.String subTitle) {
		this.subTitle = subTitle;
	}

	public java.lang.String getOrigin() {
		return origin;
	}

	public void setOrigin(java.lang.String origin) {
		this.origin = origin;
	}

	public java.lang.String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(java.lang.String originUrl) {
		this.originUrl = originUrl;
	}

	public java.lang.Boolean getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(java.lang.Boolean showIndex) {
		this.showIndex = showIndex;
	}

	public java.lang.Boolean getIsRedTape() {
		return isRedTape;
	}

	public void setIsRedTape(java.lang.Boolean isRedTape) {
		this.isRedTape = isRedTape;
	}

	public java.lang.String getRedTapeOrigin() {
		return redTapeOrigin;
	}

	public void setRedTapeOrigin(java.lang.String redTapeOrigin) {
		this.redTapeOrigin = redTapeOrigin;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
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

	public java.lang.String getLink() {
		return link;
	}

	public void setLink(java.lang.String link) {
		this.link = link;
	}

	public java.lang.String getTplContent() {
		return tplContent;
	}

	public void setTplContent(java.lang.String tplContent) {
		this.tplContent = tplContent;
	}

	public java.sql.Date getTimeDay() {
		return timeDay;
	}

	public void setTimeDay(java.sql.Date timeDay) {
		this.timeDay = timeDay;
	}

	public java.sql.Time getTimeHour() {
		return timeHour;
	}

	public void setTimeHour(java.sql.Time timeHour) {
		this.timeHour = timeHour;
	}

	public java.lang.String getTagStr() {
		return tagStr;
	}

	public void setTagStr(java.lang.String tagStr) {
		this.tagStr = tagStr;
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

	
	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public Set<ArticlePicture> getArticlePictures() {
		return articlePictures;
	}

	public void setArticlePictures(Set<ArticlePicture> articlePictures) {
		this.articlePictures = articlePictures;
	}

	@JsonIgnore
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
