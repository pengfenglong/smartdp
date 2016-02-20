package com.smartdp.weixin.api.pojo;



/**
 * 图片消息表单
 * @author pengfenglong
 *
 */
public class ImgMsgForm extends FileMsgForm{
    protected String type = MsgType.IMG;
    protected String content_type = "image/";
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
