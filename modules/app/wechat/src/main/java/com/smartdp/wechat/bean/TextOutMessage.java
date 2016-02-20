/* 
 * jeasyPro
 * @author pengfenglong
 * http://www.jeasyuicn.com/
 * 2013-8-11 下午3:31:50
 */
package com.smartdp.wechat.bean;

/**
 * 输出文字消息
 * 
 * @author pengfenglong
 * 
 */
public class TextOutMessage extends OutMessage {

	private String	MsgType	= "text";
	// 文本消息
	private String	Content;
	
	public TextOutMessage() {
	}
	
	public TextOutMessage(String content) {
		Content = content;
	}

	public String getMsgType() {
		return MsgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
