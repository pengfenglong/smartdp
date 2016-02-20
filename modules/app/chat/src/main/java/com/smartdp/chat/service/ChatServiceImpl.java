package com.smartdp.chat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.chat.pojo.ChatGroup;
import com.smartdp.chat.pojo.ChatUser;
import com.smartdp.core.service.impl.BaseServiceImpl;
import com.smartdp.platform.model.Group;
import com.smartdp.platform.model.User;

@Component("chatService")
public class ChatServiceImpl extends BaseServiceImpl implements IChatService {


	@Override
	public List<ChatGroup> getGroups(String parentId) {
		List<ChatGroup> groups = new ArrayList<ChatGroup>();
		List<Group> list = null;
		if (StringUtils.isEmpty(parentId)) {
			list = baseDao.find("from Group where parentGroup.groupId is null");
		} else {
			list = baseDao.find("from Group where parentGroup.groupId = "
					+ parentId);
		}
		for (Group g : list) {
			ChatGroup group = new ChatGroup();
			group.setId(String.valueOf(g.getGroupId()));
			group.setText(g.getGroupSimpleName());
			if (g.getGroups() == null || g.getGroups().size() == 0) {
				group.setState("open");
			} else {
				group.setState("closed");
			}
			groups.add(group);
		}
		return groups;
	}

	@Override
	public List<ChatUser> getUsers(String groupId) {
		List<String> ids = new ArrayList<String>();
		getChildren(ids, groupId);
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < ids.size(); i++) {
			if (i == ids.size() - 1) {
				s.append(ids.get(i));
			} else {
				s.append(ids.get(i)).append(",");
			}
		}
		List<Group> groups = baseDao.find("from Group where groupId in ("
				+ s.toString() + ") ");
		List<ChatUser> chatUsers = new ArrayList<ChatUser>();
		for (Group group : groups) {
			Set<User> users = group.getUsers();
			for (User user : users) {
				ChatUser chatUser = new ChatUser();
				chatUser.setUserId(String.valueOf(user.getUserId()));
				chatUser.setUserCode(user.getUserCode());
				chatUser.setUserName(user.getUserName());
				chatUsers.add(chatUser);
			}
		}
		return chatUsers;
	}

	public void getChildren(List<String> ids, String groupId) {
		if(StringUtils.isEmpty(groupId)){
			groupId = null;
		}
		ids.add(groupId);
		List<Group> list = baseDao
				.find("from Group where parentGroup.groupId = " + groupId);
		if (list != null && list.size() > 0) {
			for (Group g : list) {
				getChildren(ids, String.valueOf(g.getGroupId()));
			}
		}
	}

	@Override
	public List<ChatUser> getAllUsers() {
		List<ChatUser> chatUsers = new ArrayList<ChatUser>();
		List<User> users = baseDao.find("from User");
		for (User user : users) {
			ChatUser chatUser = new ChatUser();
			chatUser.setUserId(String.valueOf(user.getUserId()));
			chatUser.setUserCode(user.getUserCode());
			chatUser.setUserName(user.getUserName());
			chatUsers.add(chatUser);
		}
		return chatUsers;
	}

	@Override
	public ChatUser getUserByUserCode(String userCode) {
		User user = (User) baseDao.findUnique("from User where userCode = ?",
				userCode);
		ChatUser chatUser = new ChatUser();
		chatUser.setUserId(String.valueOf(user.getUserId()));
		chatUser.setUserCode(userCode);
		chatUser.setUserName(user.getUserName());
		return chatUser;
	}

}
