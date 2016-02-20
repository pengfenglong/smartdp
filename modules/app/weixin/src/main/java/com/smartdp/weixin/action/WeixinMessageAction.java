package com.smartdp.weixin.action;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.smartdp.core.web.struts.BaseActionSupport;
import com.smartdp.weixin.utils.WeixinConstant;
import com.smartdp.weixin.utils.WeixinUtils;

@Component("weixin-WeixinMessageAction")
public class WeixinMessageAction extends BaseActionSupport {
	
	public void accept() throws Exception {
		String method = getRequest().getMethod().toUpperCase();
		if("GET".equals(method)){
			excuteGetMethod();
		}else if("POST".equals(method)){
			excutePostMethod();
		}else{
			
		}
	}
	
	public void excuteGetMethod() throws IOException{
		String signature = getRequest().getParameter("signature");
		String timestamp = getRequest().getParameter("timestamp");
		String nonce = getRequest().getParameter("nonce");
		String[] ArrTmp = { WeixinConstant.WEIXIN_API_TOKEN, timestamp, nonce };
		Arrays.sort(ArrTmp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ArrTmp.length; i++) {
			sb.append(ArrTmp[i]);
		}
		String pwd = WeixinUtils.Encrypt(sb.toString());
		String echostr = getRequest().getParameter("echostr");
		System.out.println("pwd==" + pwd);
		System.out.println("echostr==" + echostr);
		if (pwd.equals(signature)) {
			if (!"".equals(echostr) && echostr != null) {
				getResponse().getWriter().print(echostr);
			}
		}
	}
	
	public void excutePostMethod(){
		
	}

}
