package com.smartdp.webservice;

import com.smartdp.core.utils.SpringContextHolder;
import com.smartdp.im.model.Message;
import com.smartdp.im.utils.IMContainer;
import com.smartdp.im.utils.IMUtil;
import com.smartdp.platform.model.User;
import com.smartdp.platform.service.IUserService;
import com.smartdp.webos.model.Application;
import com.smartdp.webos.service.IApplicationService;

public class SmartdpWebservice {

	private static IApplicationService applicationService;

	private static IUserService userService;

	static {
		applicationService = (IApplicationService) SpringContextHolder
				.getBean("applicationService");
		userService = (IUserService) SpringContextHolder.getBean("userService");
	}

	/**
	 * 发布应用
	 * 
	 * @param application
	 * @return
	 */
	public String publishApplication(Application application) {
		try {
			applicationService.save(application);
		} catch (Exception e) {
			return e.getMessage();

		}
		return "操作成功";
	}

	/**
	 * 推送消息
	 * 
	 * @return
	 */
	public String pushMessage(Message message) {

		User fromUser = userService.findUniqueBy("userName", message.getFrom());
		User toUser = userService.findUniqueBy("userName", message.getTo());

		if (toUser == null) {
			return "没有找到接受消息的对象";
		} else {
			if (fromUser != null) {
				message.setFrom(Long.toString(fromUser.getUserId()));
			}
			message.setTo(Long.toString(toUser.getUserId()));
			IMContainer.messages.add(message);

			// 唤醒全部更新列表
			IMUtil.wakeUpAllThread(Long.toString(toUser.getUserId()));
			return "操作成功";
		}
	}

}
