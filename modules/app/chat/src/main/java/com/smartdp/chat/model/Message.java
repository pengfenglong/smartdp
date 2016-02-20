package com.smartdp.chat.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 消息
 * @author pengfenglong
 * 
 */
@Entity
@Table(name = "CHAT_MESSAGE")
public class Message extends IdEntity{

	@Index(name = "chat_message_sender_index")
	private String sender;//发送者
	@Index(name = "chat_message_receiver_index")
	private String receiver;//接受者
	@Lob
	private String content;
	private String type;//群组、个人
	private String msgType;//文本、文件
	@Index(name = "chat_message_flag_index")
	private String flag;//1已读、0未读
	@Index(name = "chat_message_createDate_index")
	private Timestamp createDate;
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}


}
