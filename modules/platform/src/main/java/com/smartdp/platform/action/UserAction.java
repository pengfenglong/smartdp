package com.smartdp.platform.action;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.platform.model.Group;
import com.smartdp.platform.model.Role;
import com.smartdp.platform.model.User;
import com.smartdp.platform.service.IPlatformService;

@Component("platform-UserAction")
public class UserAction extends CrudActionSupport<User>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IPlatformService platformService;
	
	private User model;
	
	@Override
	public void prepareModel() {
		if (id != null) {
			model = (User) baseService.get(id);
		} else {
			model = new User();
		}
	}
	
	@Override
	public User getModel() {
		return model;
	}
	

	@Override
	public String list() {
		IPage<User> userPage = null;
		String groupId = getParameter("groupId");
		if(StringUtils.isEmpty(groupId)){
			userPage = platformService.findPage(getPage(),getFilters());
		}else{
			userPage = platformService.getUsersFromGroupByPage(Long.parseLong(groupId), getPage(),getFilters());
		}
		savePage(userPage);
		return SUCCESS;
	}

	@Override
	public String input() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		if(!StringUtils.isEmpty(getParameter("id"))){
			long id = getParameterLong("id");
			model.setUserId(id);
		}
		Group g = new Group();
		g.setGroupId(getParameterLong("groupId"));
		//设置部门
		model.getGroups().add(g);
		//设置角色
		Set<Role> roles = new HashSet<Role>();
		String[] roleIds = getParameter("roleIds").split(",");
		for(int i = 0 ; i < roleIds.length ; i ++){
			if(!StringUtils.isEmpty(roleIds[i])){
				Role role = (Role) baseService.get(Long.parseLong(roleIds[i]),Role.class);
				roles.add(role);
			}
		}
		model.setRoles(roles);
		baseService.save(model);
		result = resultJson;
		return SUCCESS;
	}

	@Override
	public String delete() {
		String ids = getParameter("ids");
		baseService.deleteByIds(ids);
		result = resultJson;
		return SUCCESS;
	}

}
