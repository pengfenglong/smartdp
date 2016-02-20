package com.smartdp.chat.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartdp.chat.model.Message;
import com.smartdp.chat.pojo.ChatUser;


/**
 * 公共属性
 * 
 * @author pengfenglong
 * 
 */
public class ChatContainer {
	
	/**
	 * Session集合
	 */
	public static List<ChatUser> users = new ArrayList<ChatUser>();

	/**
	 * 消息集合
	 */
	public static List<Message> messages = new ArrayList<Message>();

	/**
	 * 会话线程映射 Key:SessionId Value:Thread
	 */
	public static Map<String, Thread> sessionThreadMapping = new HashMap<String, Thread>();

}

