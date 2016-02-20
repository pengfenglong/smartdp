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
@Table(name = "cms_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Template extends IdEntity{

	/**模板编码*/
	@Column(name = "code")
	private java.lang.String code;
	
	/**板模名称*/
	@Column(name = "name", nullable = false)
	private java.lang.String name;
	
	/**模板路径*/
	@Column(name = "url")
	private java.lang.String url;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "template")
	private Set<Site> sites = new HashSet<Site>(0);

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public Set<Site> getSites() {
		return sites;
	}

	public void setSites(Set<Site> sites) {
		this.sites = sites;
	}
	
}
