package com.smartdp.chat.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.smartdp.chat.model.Message;
import com.smartdp.chat.pojo.ChatGroup;
import com.smartdp.chat.pojo.ChatUser;
import com.smartdp.chat.pojo.ChatUser.ChatUserStatus;
import com.smartdp.chat.service.IChatService;
import com.smartdp.chat.utils.ChatContainer;
import com.smartdp.chat.utils.ChatUtil;
import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.BaseActionSupport;

@Component("chat-ChatAction")
@Scope("prototype")
public class ChatAction extends BaseActionSupport {
	
	private static final long serialVersionUID = -5318694477354162098L;
	
	private String userCode;
	
	@Autowired
	private IChatService chatService;
	
	/**
	 * 部门树
	 * @return
	 */
	public String groupTree(){
		List<ChatGroup> chatGroups = chatService.getGroups(getParameter("parentId"));
		if(StringUtils.isEmpty(getParameter("groupId"))){
			List<Map> groups = new ArrayList<Map>();
			Map group =  new HashMap();
			group.put("id", null);
			group.put("text", "全部");
			group.put("state", "open");
			group.put("children", chatGroups);
			groups.add(group);
			result = groups;
			
		}else{
			result = chatGroups;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 获得部门下的用户
	 * @return
	 */
	public String listUser(){
		result = chatService.getUsers(getParameter("groupId"));
		return SUCCESS;
	}
	
	/**
	 * 保存群组
	 * @return
	 */
	public String saveGroups(){
		System.out.println(getRequest().getParameter("name"));
		System.out.println(getRequest().getParameter("user"));
		return null;
	}

	/**
	 * 登陆
	 * @return
	 */
	public String login() {
		try {

			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			ChatUser user = chatService.getUserByUserCode(userDetails.getUsername());
			
			if (user != null) {
				user.setStatus(ChatUserStatus.online);
				List<ChatUser> users = chatService.getAllUsers();
				String userJson = JSONUtil.serialize(user);
				ChatContainer.users = users;
				getSession().getServletContext().setAttribute("users", users);
				ChatUtil.wakeUpAllThread();
				getResponse().getWriter().write(
						"{code:'0',msg:'登录成功',user:'" + userJson + "'}");
			} else {
				getResponse().getWriter().write("{code:'1',msg:'登录失败'}");
			}
			getResponse().getWriter().flush();
			getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 登出
	 * @return
	 */
	public String logout() {
		for (ChatUser user : ChatContainer.users) {
			if (userCode.equals(user.getUserCode())) {
				user.setStatus(ChatUserStatus.offline);;
			}
		}
		// 唤醒全部更新列表
		ChatUtil.wakeUpAllThread();
		return null;

	}

	/**
	 * 初始化
	 * @return
	 */
	public String initialization() {
		// String userCode = (String)getSession().getAttribute("userCode");
		try {
			ChatUtil.out(getRequest(), getResponse(), userCode,baseService);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新消息状态
	 * @return
	 */
	public String updateMsgStatus() {
		baseService.batchExecute("update Message set flag='1' where id=?",
				Long.parseLong(getRequest().getParameter("msgId")));
		result = "{success:true}";
		return SUCCESS;
	}
	
	/**
	 * 获得未读消息
	 * @return
	 */
	public String getNoReadMsg() {
		List<Message> msgs = baseService.find(
				"from Message where receiver=? and sender=? and flag='0' order by createDate",
				userCode, getParameter("sender"));
		StringBuffer ids = new StringBuffer();
		for(int i=0,n=msgs.size();i<n;i++){
			if(i == n-1){
				ids.append(msgs.get(i).getId());
			}else{
				ids.append(msgs.get(i).getId()).append(",");
			}
		}
		baseService.batchExecute("update Message set flag='1' where id in ("+ids+")");
		result = msgs;
		return SUCCESS;
	}
	
	/**
	 * 查询消息历史记录
	 * @return
	 */
	public String queryHistoryMsg() {
		String loginUser = getParameter("loginUser");
		String chatUser = getParameter("chatUser");
		String date = getParameter("date");
		String hql = "from Message where ((sender='" + loginUser
				+ "' and receiver='" + chatUser + "') or (sender='" + chatUser
				+ "' and receiver='" + loginUser
				+ "'))";
		if(StringUtils.isNotEmpty(date)){
			//hql +=  " and to_date(createDate,'yyyy-mm-dd')  = '" + date + "'";
			hql +=  " and createDate like '" + date + "%'";
		}
		hql += " order by createDate";
		IPage<Message> messagePage = baseService.findPage(getPage(), hql);
		savePage(messagePage);
		return SUCCESS;
	}

	/**
	 * 发送消息
	 * @return
	 */
	public String sendMsg() {
		String sender = getRequest().getParameter("sender");
		String receiver = getRequest().getParameter("receiver");
		String content = getRequest().getParameter("content");
		Message message = new Message();
		message.setSender(sender);
		message.setReceiver(receiver);;
		message.setContent(content);
		message.setFlag("0");
		baseService.getBaseDao().setEntityClass(Message.class);
		baseService.save(message);
//		ChatContainer.messages.add(message);

		// 唤醒全部更新列表
		ChatUtil.wakeUpAllThread(receiver);
		return null;

	}

	/**
	 * 刷新
	 * @return
	 */
	public String refresh() {

		// String userCode = (String) getSession().getAttribute("userCode");

		// 加入映射
		ChatContainer.sessionThreadMapping.put(userCode, Thread.currentThread());

		// 等待
		try {
			synchronized (Thread.currentThread()) {
				//System.out.println(userCode+ "------等待中--------");
				Thread.currentThread().wait();
				//System.out.println("-------" + userCode + "被唤醒--------");
			}
			ChatUtil.out(getRequest(), getResponse(), userCode,baseService);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	/**
	 * 上传文件
	 * @return
	 */
	public String uploadFile() {
		MultiPartRequestWrapper requestWrapper = (MultiPartRequestWrapper) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		// 文件名字符串数组
		String[] fileNames = requestWrapper.getFileNames("imgFile");
		// 文件数组
		File[] files = requestWrapper.getFiles("imgFile");
		return null;
	}
	
	public void setChatService(IChatService chatService) {
		this.chatService = chatService;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
