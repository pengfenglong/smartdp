/**
 * 微信公众平台开发模式(JAVA) SDK
 * @author pengfenglong
 * 
 */
package com.smartdp.wechat.bean;

import java.io.Serializable;

/**
 * 多图文消息
 * @author pengfenglong
 *
 */
public class Articles implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
}
