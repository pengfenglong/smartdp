package com.smartdp.weixin.api.pojo;

/**
 * 微信公众账号粉丝
 * 
 * @author pengfenglong
 * 
 */
public class Fan {

	private String id;//ID,发送消息时需要这个ID

	private String nick_name;//名字

	private String remark_name;//备注

	private String group_id;//群组ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getRemark_name() {
		return remark_name;
	}

	public void setRemark_name(String remark_name) {
		this.remark_name = remark_name;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

}
