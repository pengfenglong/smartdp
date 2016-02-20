package com.smartdp.chat.service;

import java.util.List;

import com.smartdp.chat.pojo.ChatGroup;
import com.smartdp.chat.pojo.ChatUser;

/**
 * @author pengfenglong
 */
public interface IChatService {
	public List<ChatGroup> getGroups(String parentGroupId);
	public List<ChatUser> getUsers(String groupId);
	public List<ChatUser> getAllUsers();
	public ChatUser getUserByUserCode(String userCode);
}
