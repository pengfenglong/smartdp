package com.smartdp.chat.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.smartdp.chat.model.Message;
import com.smartdp.core.service.IBaseService;

/**
 * 
 * @author pengfenglong
 * 
 */
public class ChatUtil {
	/**
	 * 唤醒全部
	 */
	public static void wakeUpAllThread() {
		Iterator<Map.Entry<String, Thread>> iterator = ChatContainer.sessionThreadMapping
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Thread> entry = iterator.next();
			Thread thread = entry.getValue();
			synchronized (thread) {
				thread.notify();
			}
		}
	}

	/**
	 * 唤醒指定
	 * 
	 * @param userCode
	 */
	public static void wakeUpAllThread(String userCode) {
		Iterator<Map.Entry<String, Thread>> iterator = ChatContainer.sessionThreadMapping
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Thread> entry = iterator.next();
			if (userCode.equals(entry.getKey())) {
				Thread thread = entry.getValue();
				synchronized (thread) {
					//System.out.println(thread.getName()+"------被唤醒--------");
					thread.notify();
				}
			}
		}
	}

	/**
	 * 输出用户列表
	 * 
	 * @param response
	 * @throws IOException
	 */
	public static void out(HttpServletRequest request,HttpServletResponse response,String userCode,IBaseService baseService) throws IOException {
		Map map = new HashMap();
		
		map.put("users", ChatContainer.users);

		
		//发给当前登录用户的消息
//		List<Message> msgs = new ArrayList<Message>();
//		for(Message message : ChatContainer.messages){
//			if(message.getReceiver().equals(userCode)){
//				msgs.add(message);
//			}
//		}
//		ChatContainer.messages.removeAll(msgs);
		List<Message> msgs = baseService.find("from Message where receiver=? and flag='0'", userCode);
		map.put("messages", msgs);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			Set<Pattern> excludeProperties = new HashSet<Pattern>();
			excludeProperties.add(Pattern.compile("users\\[\\d+\\].groups"));
			excludeProperties.add(Pattern.compile("users\\[\\d+\\].roles"));
			String json = JSONUtil.serialize(map, excludeProperties, null, true, true);
			//System.out.println(json);
			out.print(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(new JSONSerializer().serialize(map));
		//out.print(new JSONSerializer().serialize(map));
//		List<Message> msgs = (List<Message>)map.get("messages");
//		msgs.clear();
		out.flush();
		out.close();
	}

}
