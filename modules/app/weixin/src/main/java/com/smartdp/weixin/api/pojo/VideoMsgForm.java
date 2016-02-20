package com.smartdp.weixin.api.pojo;



/**
 * 声音消息表单
 * @author pengfenglong
 *
 */
public class VideoMsgForm extends FileMsgForm{
    protected String type = MsgType.VIDEO;
    protected String content_type = "video/";
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
