/**
 * 微信公众平台开发模式(JAVA) SDK
 * @author pengfenglong
 * 
 */
package com.smartdp.wechat.inf;

/**
 * 消息类型
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 *
 */
public enum MsgTypes {
	TEXT("text"), 
	LOCATION("location"), 
	IMAGE("image"),
	LINK("link"),
	VOICE("voice"),
	EVENT("event"),
	VIDEO("video"),
	NEWS("news"),
	MUSIC("music");
	
	private String type;
	
	MsgTypes(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}