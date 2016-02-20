package com.smartdp.chat.pojo;

/**
 * @author pengfenglong
 */
public class ChatUser {

	private String userId;

	private String userCode;

	private String userName;

	private ChatGroup group;

	private ChatUserStatus status = ChatUserStatus.offline;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ChatGroup getGroup() {
		return group;
	}

	public void setGroup(ChatGroup group) {
		this.group = group;
	}

	public ChatUserStatus getStatus() {
		return status;
	}

	public void setStatus(ChatUserStatus status) {
		this.status = status;
	}

	public enum ChatUserStatus {
		online, offline
	}

}
