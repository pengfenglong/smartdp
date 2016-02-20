package com.smartdp.dbexportdoc.entity;

import java.util.ArrayList;
import java.util.List;

public class DBTable {

	/**
	 * 表名称
	 */
	private String name;

	/**
	 * 表注释
	 */
	private String remarks;

	/**
	 * 字段集合
	 */
	private List<DBColumn> columns = new ArrayList<DBColumn>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<DBColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DBColumn> columns) {
		this.columns = columns;
	}
	
	

}
