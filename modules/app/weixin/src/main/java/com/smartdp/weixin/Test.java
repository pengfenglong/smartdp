package com.smartdp.weixin;

import java.util.List;

import com.smartdp.weixin.api.Weixin;
import com.smartdp.weixin.api.pojo.Fan;
import com.smartdp.weixin.api.pojo.MsgForm;
import com.smartdp.weixin.api.pojo.TextMsgForm;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String LOGIN_USER = "150521330@qq.com";
		String LOGIN_PWD = "pfl@123";
		Weixin wx = new Weixin(LOGIN_USER, LOGIN_PWD);
		wx.login();
		// // wx.getMsgTextList();
		wx.getCookiestr();
//		wx.configInterfaceParam("","");
		
		//获得粉丝列表
//		List<Fan> fans = wx.getFans();
//
//		MsgForm form = null;
//		String fanId = "1103331500";
//
//		// 单发文本消息
//		form = new TextMsgForm();
//		form.setContent("测试");
//		wx.sendSingleMsg(form, fanId);
		
		//System.out.println(wx.getImgTextMsgsJson());

	}

}
