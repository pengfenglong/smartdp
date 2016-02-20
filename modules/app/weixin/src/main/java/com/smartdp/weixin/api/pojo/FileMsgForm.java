package com.smartdp.weixin.api.pojo;

import java.io.File;

/**
 * 文件消息表单
 * @author pengfenglong
 *
 */
public class FileMsgForm extends MsgForm{
    protected String cgi = "uploadmaterial";
    protected String type = "";
    protected String token = "";
    protected String t = "iframe-uploadfile";
    protected String lang = "zh_CN";
    protected String formId = "1";
    protected File uploadfile = null;
    
    public String getCgi() {
        return cgi;
    }

    public void setCgi(String cgi) {
        this.cgi = cgi;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
