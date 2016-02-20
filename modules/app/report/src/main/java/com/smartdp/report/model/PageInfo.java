package com.smartdp.report.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
 * 页面信息
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "resport_pageinfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PageInfo extends IdEntity {

	private String pageName;

	private String pageDesc;

	private String status;

	private String creator;

	private Timestamp createDate;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "catalog_id")
	private Catalog catalog;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "pageInfo")
	private Set<PageWidget> pageWidgets = new HashSet<PageWidget>(0);

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageDesc() {
		return pageDesc;
	}

	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Set<PageWidget> getPageWidgets() {
		return pageWidgets;
	}

	public void setPageWidgets(Set<PageWidget> pageWidgets) {
		this.pageWidgets = pageWidgets;
	}

}
