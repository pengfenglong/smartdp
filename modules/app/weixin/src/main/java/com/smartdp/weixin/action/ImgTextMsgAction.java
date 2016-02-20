package com.smartdp.weixin.action;

import org.springframework.stereotype.Component;

import com.smartdp.core.web.struts.BaseActionSupport;
import com.smartdp.weixin.api.Weixin;

@Component("weixin-ImgTextMsgAction")
public class ImgTextMsgAction extends BaseActionSupport {

	public void list() throws Exception {
		String begin = getRequest().getParameter("begin");
		String count = getRequest().getParameter("count");
		String LOGIN_USER = "150521330@qq.com";
		String LOGIN_PWD = "pfl@123";
		Weixin wx = new Weixin(LOGIN_USER, LOGIN_PWD);
		wx.login();
		// // wx.getMsgTextList();
		wx.getCookiestr();
		getResponse().getWriter().write(wx.getImgTextMsgsJson(begin,count));
		//result = wx.getImgTextMsgsJson();
	}
	
	
	public static void main(String[] args) {
		String begin = "0";
		String count = "10";
		String LOGIN_USER = "150521330@qq.com";
		String LOGIN_PWD = "pfl@123";
		Weixin wx = new Weixin(LOGIN_USER, LOGIN_PWD);
		wx.login();
		System.out.println(wx.getImgTextMsgsJson(begin,count));
	}

}
