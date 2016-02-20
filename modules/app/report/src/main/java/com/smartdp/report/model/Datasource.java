package com.smartdp.report.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 数据源
 * @author pengfenglong
 */
@Entity
@Table(name = "resport_datasource")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Datasource extends IdEntity {

	/** 数据源名称 */
	private String name;

	/** 类型 */
	private String dsType;

	/**
	 * jdbc属性 jdbc.driverClassName=com.mysql.jdbc.Driver;jdbc.url=jdbc:mysql://
	 * localhost:3306/bme;jdbc.username=admin;jdbc.password=admin;
	 */
	private String dsProperties;

	/** dsProperties中字符串之间的分隔符 */
	private String propertyRegex;

	/** 描述 */
	private String memo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDsType() {
		return dsType;
	}

	public void setDsType(String dsType) {
		this.dsType = dsType;
	}

	public String getDsProperties() {
		return dsProperties;
	}

	public void setDsProperties(String dsProperties) {
		this.dsProperties = dsProperties;
	}

	public String getPropertyRegex() {
		return propertyRegex;
	}

	public void setPropertyRegex(String propertyRegex) {
		this.propertyRegex = propertyRegex;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
