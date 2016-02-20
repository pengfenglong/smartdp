package com.smartdp.platform.loadpoint;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.smartdp.core.web.loadpoint.ISystemLoadPoint;
import com.smartdp.platform.model.User;
import com.smartdp.platform.service.IPlatformService;

public class PlatformLoadPoint implements ISystemLoadPoint {
	private ServletContextEvent event;
	private IPlatformService platformService;

	public PlatformLoadPoint(ServletContextEvent event) {
		this.event = event;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startup() {
		try {

			platformService = (IPlatformService) WebApplicationContextUtils
					.getRequiredWebApplicationContext(event.getServletContext())
					.getBean("platformService");
			List list = platformService.find("from User where userName = ?",
					"admin");
			if (list == null || list.size() == 0) {
				User user = new User();
				user.setUserName("admin");
				user.setUserPassword("admin");
				user.setCreateDate(new Timestamp(System.currentTimeMillis()));
				user.setStatus("0");
				platformService.save(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setPlatformService(IPlatformService platformService) {
		this.platformService = platformService;
	}

}
