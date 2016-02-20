package com.smartdp.weixin.api.pojo;
/**

/**
 * 文本消息表单
 * @author pengfenglong
 */
public class TextMsgForm extends MsgForm{
    protected String type = MsgType.TEXT;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}

