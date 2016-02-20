package com.smartdp.weixin.api.pojo;



/**
 * 视频消息表单
 * @author pengfenglong
 *
 */
public class VoiceMsgForm extends FileMsgForm{
    protected String type = MsgType.VOICE;
    protected String content_type = "audio/";
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
    
}
