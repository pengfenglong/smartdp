package com.smartdp.weixin.api.pojo;

/**
 * 登录返回信息Json对象
 * 
 * @author pengfenglong
 */

// {"base_resp":{"ret":0,"err_msg":"ok"},"redirect_url":"\/cgi-bin\/home?t=home\/index&lang=zh_CN&token=821975340"}
public class LoginJson {
	private Base_resp base_resp;
	private String redirect_url;

	public Base_resp getBase_resp() {
		return base_resp;
	}

	public void setBase_resp(Base_resp base_resp) {
		this.base_resp = base_resp;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

}
