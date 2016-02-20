package com.smartdp.platform.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.platform.model.Privilege;
import com.smartdp.platform.model.Role;

@Component("platform-RoleAction")
public class RoleAction extends CrudActionSupport<Role> {

	private static final long serialVersionUID = 1L;

	private Role model;

	public void prepareModel() {
		if (id != null) {
			model = (Role) baseService.get(id);
		} else {
			model = new Role();
		}
	}

	@Override
	public Role getModel() {
		return model;
	}

	public String listAll() {
		List<Role> roles = baseService.getAll();
		result = roles;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Role> rolePage = baseService.findPage(getPage(), getFilters());
		savePage(rolePage);
		return SUCCESS;
	}

	@Override
	public String input() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		if (!StringUtils.isEmpty(getParameter("id"))) {
			long id = getParameterLong("id");
			model.setRoleId(id);
		}
		model.setStatus("1");
		baseService.save(model);

		if (!StringUtils.isEmpty(getParameter("privis"))) {
			String[] privis = getParameter("privis").split(";");
			String menus = privis[0];
			String operates = privis[1];
			if (!StringUtils.isEmpty(menus)) {
				String[] ms = menus.split("-");
				for (String m : ms) {
					Privilege p = new Privilege();
					p.setPrivilegeType("menu");
					p.setResourceId(m);
					p.setRoleId(model.getRoleId());
					baseService.save(p);
				}
			}
			if (!StringUtils.isEmpty(operates)) {
				String[] os = operates.split("-");
				for (String o : os) {
					Privilege p = new Privilege();
					p.setPrivilegeType("operate");
					p.setResourceId(o);
					p.setRoleId(model.getRoleId());
					baseService.save(p);
				}
			}

		}

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
