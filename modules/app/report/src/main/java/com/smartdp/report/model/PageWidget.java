package com.smartdp.report.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 页面组件
 * @author pengfenglong
 */
@Entity
@Table(name = "resport_pagewidget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PageWidget extends IdEntity{

	private Integer widgetIndex;
	
	private String widgetWidth;
	
	private String widgetHeight;
	
	private String attrAndValueJson;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "pageInfo_id")
	private PageInfo pageInfo;

	public Integer getWidgetIndex() {
		return widgetIndex;
	}

	public void setWidgetIndex(Integer widgetIndex) {
		this.widgetIndex = widgetIndex;
	}

	public String getWidgetWidth() {
		return widgetWidth;
	}

	public void setWidgetWidth(String widgetWidth) {
		this.widgetWidth = widgetWidth;
	}

	public String getWidgetHeight() {
		return widgetHeight;
	}

	public void setWidgetHeight(String widgetHeight) {
		this.widgetHeight = widgetHeight;
	}

	public String getAttrAndValueJson() {
		return attrAndValueJson;
	}

	public void setAttrAndValueJson(String attrAndValueJson) {
		this.attrAndValueJson = attrAndValueJson;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}


}
