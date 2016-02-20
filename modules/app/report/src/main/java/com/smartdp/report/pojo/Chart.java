package com.smartdp.report.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述报表文档基本信息
 * 
 * @author pengfenglong
 */
@XmlRootElement(name="chart")
public class Chart {
	/**
	 * id
	 */
	private String id;

	/**
	 * 类型
	 */
	private String catalog;
	/**
	 * 类型
	 */
	private String type;

	/**
	 * 子类型
	 */
	private String subType;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 宽度
	 */
	private int widthScale;

	/**
	 * 高度
	 */
	private int heightScale;

	/**
	 * 图标路径
	 */
	private String icon;

	/**
	 * 配置信息
	 */
	private String configuration;

	/**
	 * 属性信息定义(html)
	 */
	private String attributes;

	@XmlElement
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement
	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	@XmlElement
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public int getWidthScale() {
		return widthScale;
	}

	public void setWidthScale(int widthScale) {
		this.widthScale = widthScale;
	}

	@XmlElement
	public int getHeightScale() {
		return heightScale;
	}

	public void setHeightScale(int heightScale) {
		this.heightScale = heightScale;
	}

	@XmlElement
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@XmlElement
	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	@XmlElement
	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

}
