package com.smartdp.weixin.api.pojo;

import java.io.File;

/**
 * 消息表单
 * @author pengfenglong
 */
public class MsgForm {
	protected String type = "";
	protected String content = "";
	protected String error = "false";
	protected String needcomment = "0";
	protected String groupid = "-1";
	protected String sex = "0";
	protected String country = "";
	protected String province = "";
	protected String city = "";
	protected String token = "";
	protected String ajax = "1";
	protected String fid;
	protected String appmsgid;
	protected String content_type = "";
	protected String cgi = "uploadmaterial";
    protected String t = "iframe-uploadfile";
    protected String lang = "zh_CN";
    protected String formId = "1";
    protected File uploadfile = null;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getAppmsgid() {
		return appmsgid;
	}

	public void setAppmsgid(String appmsgid) {
		this.appmsgid = appmsgid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getNeedcomment() {
		return needcomment;
	}

	public void setNeedcomment(String needcomment) {
		this.needcomment = needcomment;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getCgi() {
		return cgi;
	}

	public void setCgi(String cgi) {
		this.cgi = cgi;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

}
