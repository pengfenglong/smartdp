package com.smartdp.lifequery;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String uri = "http://gd.ums86.com:8899/sms/Api/Send.do";
		StringBuilder sBuff = new StringBuilder();
		try {
			URL url = new URL(uri);
			HttpURLConnection connectionData = (HttpURLConnection)url.openConnection();
			connectionData.setConnectTimeout(10000);
			 // http正文内，因此需要设为true
			connectionData.setDoOutput(true);
	        // Read from the connection. Default is true.
			connectionData.setDoInput(true);
	        // 默认是 GET方式
			connectionData.setRequestMethod("POST");
	       
	        // Post 请求不能使用缓存
			connectionData.setUseCaches(false);
	       
			connectionData.setInstanceFollowRedirects(true);
	       
	        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
	        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
	        // 进行编码
			connectionData.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
	        // 要注意的是connection.getOutputStream会隐含的进行connect。
			connectionData.connect();
			 DataOutputStream out = new DataOutputStream(connectionData
		                .getOutputStream());
			 // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
		        String content = "SpCode=" + URLEncoder.encode("210557", "GBK");
		        content +="&LoginName="+URLEncoder.encode("admin", "GBK");
		        content +="&Password="+URLEncoder.encode("p89%k72#", "GBK");
		        content +="&MessageContent="+URLEncoder.encode("您本次使用的验证码为123456,30秒以内有效.", "GBK");
		        content +="&UserNumber="+URLEncoder.encode("18617016265", "GBK");
		        content +="&SerialNumber="+URLEncoder.encode("", "GBK");
		        content +="&ScheduleTime="+URLEncoder.encode("", "GBK");
		        content +="&f="+URLEncoder.encode("", "GBK");
		        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
		        out.writeBytes(content);

		        out.flush();
		        out.close(); 
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connectionData.getInputStream(), "GBK"));
			String line = null;
			while ((line = br.readLine()) != null)
				sBuff.append(line);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sBuff);

	}

}
