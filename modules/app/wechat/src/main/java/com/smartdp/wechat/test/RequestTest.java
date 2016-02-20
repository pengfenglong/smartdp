package com.smartdp.wechat.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestTest {

	public static String testServletUrl(String path, String xml)
			throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");// 提交模式
		conn.setConnectTimeout(10000);// 连接超时 单位毫秒
		conn.setReadTimeout(5000);// 读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		// 通过conn.getOutputStream().write 将XML信息写入，在另一端系统，get出来再解析
		conn.getOutputStream().write(xml.getBytes("UTF-8"));
		InputStream inStream = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
		String s = null;
		StringBuffer sBuff = new StringBuffer();
		while((s = br.readLine())!=null){
			sBuff.append(s);
		}
		
		inStream.close();
		return sBuff.toString();
	}

	public static String getCampaginXmlInfo() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<xml>");
		sb.append("	<ToUserName><![CDATA[gh_bd94e5ec0a65]]></ToUserName>");
		sb.append("	<FromUserName><![CDATA[oAdCqjj5U6u-35m1rUwJjfCnsuz0]]></FromUserName>");
		sb.append("	<CreateTime>1399115260</CreateTime>");
		sb.append("	<MsgType><![CDATA[text]]></MsgType>");
		sb.append("	<Content><![CDATA[身份证36220319821026681X]]></Content>");
		sb.append("	<MsgId>6009154285234728419</MsgId>");
		sb.append("</xml>");
		//位置消息
		//return "<xml><ToUserName><![CDATA[gh_bd94e5ec0a65]]></ToUserName><FromUserName><![CDATA[oAdCqjj5U6u-35m1rUwJjfCnsuz0]]></FromUserName><CreateTime>1402669370</CreateTime><MsgType><![CDATA[location]]></MsgType><Location_X>22.579702</Location_X><Location_Y>114.140030</Location_Y><Scale>16</Scale><Label><![CDATA[深圳市罗湖区太白路1002号]]></Label><MsgId>6024419071451362186</MsgId></xml>";
		//图片消息
		//return "<xml><ToUserName><![CDATA[gh_bd94e5ec0a65]]></ToUserName><FromUserName><![CDATA[oAdCqjj5U6u-35m1rUwJjfCnsuz0]]></FromUserName><CreateTime>1402670042</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/FM3loL4GKpYLQQevibnwu2yL5BYPbLkJqZcxuiaiaANeQPrfRDAFt3034jaHIO43TDKIsX7xwYwGcGAFia8Y0CogWQ/0]]></PicUrl><MsgId>6024421957669385132</MsgId><MediaId><![CDATA[ieDs_3pyGHaEyZbjaaes58lThv9IheJ2gZK30CdBZH4zdBpWcEJBZjCsZ43_w9i4]]></MediaId></xml>";
		return sb.toString();
	}

	public static void main(String[] args) {
		try {// testPostServlet
			String result = null;
			result = RequestTest.testServletUrl(
					"http://localhost:8080/smartdp/api/wechat/abcdefg",
					getCampaginXmlInfo());
			System.out.println("result:" + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}