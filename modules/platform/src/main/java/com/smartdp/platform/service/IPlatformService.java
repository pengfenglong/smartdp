package com.smartdp.platform.service;

import java.util.List;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.dao.entity.PropertyFilter;
import com.smartdp.core.dao.impl.DefaultPage;
import com.smartdp.core.service.IBaseService;
import com.smartdp.platform.model.User;

public interface IPlatformService extends IBaseService{
	/**
	 * 根据group查user
	 * @param groupId
	 * @param page
	 * @param filters
	 * @return
	 */
	public IPage<User> getUsersFromGroupByPage(Long groupId,DefaultPage page,List<PropertyFilter> filters);
}