package com.smartdp.tag.easyui;

/**
 * 消息窗口标签
 * 
 * @author pengfenglong
 * 
 */
public class MessagerTag extends AbstractUITag {
	protected String ok;// 确认
	protected String cancel;// 取消

	public String getCss() {
		return "easyui-messager";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("ok", "'" + ok + "'");
		parameters.put("cancel", "'" + cancel + "'");
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
}