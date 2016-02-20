package com.smartdp.report.model;

import java.util.ArrayList;
import java.util.List;

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
 * 数据服务
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "resport_dataservice")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataService extends IdEntity {

	private String name;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "datasource_id")
	private Datasource datasource;

	private String description;

	private String executeSql;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "dataService")
	private List<FieldMapping> fieldMappings = new ArrayList<FieldMapping>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Datasource getDatasource() {
		return datasource;
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExecuteSql() {
		return executeSql;
	}

	public void setExecuteSql(String executeSql) {
		this.executeSql = executeSql;
	}

	public List<FieldMapping> getFieldMappings() {
		return fieldMappings;
	}

	public void setFieldMappings(List<FieldMapping> fieldMappings) {
		this.fieldMappings = fieldMappings;
	}
	
	

}
