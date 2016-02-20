package com.smartdp.dbexportdoc.entity;

/**
 * 
 * @author pengfenglong
 */
public class DBColumn {

	/**
	 * 字段名字
	 */
	private String name;

	/**
	 * 字段注释
	 */
	private String remarks;

	/**
	 * 字段类型
	 */
	private String type;

	/**
	 * 是否为空
	 */
	private boolean isNullable;

	/**
	 * 默认值
	 */
	private String defaultValue;
	
	/**
	 * 数据长度
	 */
	private int size;

	/**
	 * 是否为主键
	 */
	private boolean isPk;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isPk() {
		return isPk;
	}

	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}

}
