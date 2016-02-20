package com.smartdp.webos.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "WEBOS_APPLICATION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Application {

	/**
	 * 应用ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APPLICATION_ID", nullable = false)
	private Long applicationId;
	
	/**
	 * 应用编码
	 */
	@Column(name = "ID")
	private String id;
	/**
	 * 应用名称
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 应用分类
	 */
	@Column(name = "CATALOG")
	private String catalog;
	/**
	 * 应用图片
	 */
	@Column(name = "ICO")
	private String ico;
	/**
	 * 应用创建时间
	 */
	@Column(name = "EXPLAIN_TIME")
	private Timestamp explain;
	/**
	 * 是否需要登录
	 */
	@Column(name = "VERIFY")
	private String verify;
	/**
	 * 打开模式(window新窗口 dialog弹出框)
	 */
	@Column(name = "MODE")
	private String mode;
	/**
	 * 应用地址
	 */
	@Column(name = "HREF")
	private String href;
	/**
	 * 应用窗口是否全屏 1为全屏
	 */
	@Column(name = "FULLSCREEN")
	private String fullScreen;
	/**
	 * 应用大小
	 */
	@Column(name = "SIZE")
	private String size;
	/**
	 * 应用窗口宽度
	 */
	@Column(name = "WIDTH")
	private String width;
	/**
	 * 应用窗口高度
	 */
	@Column(name = "HEIGHT")
	private String height;
	/**
	 * 应用窗口最小宽度
	 */
	@Column(name = "MINWIDTH")
	private String minWidth;
	/**
	 * 应用窗口最小高度
	 */
	@Column(name = "MINHEIGHT")
	private String minHeight;
	/**
	 * 应用窗口背景
	 */
	@Column(name = "BODYBG")
	private String bodyBG;
	/**
	 * margin
	 */
	@Column(name = "MARGIN")
	private String margin;
	/**
	 * 应用提供方
	 */
	@Column(name = "PROVIDER")
	private String provider;


	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getApplicationId() {
		return applicationId;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public Timestamp getExplain() {
		return explain;
	}

	public void setExplain(Timestamp explain) {
		this.explain = explain;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(String fullScreen) {
		this.fullScreen = fullScreen;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	public String getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}

	public String getBodyBG() {
		return bodyBG;
	}

	public void setBodyBG(String bodyBG) {
		this.bodyBG = bodyBG;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProvider() {
		return provider;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

}
