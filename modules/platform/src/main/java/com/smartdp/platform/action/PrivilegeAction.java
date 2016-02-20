package com.smartdp.platform.action;

import java.util.List;

import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.platform.model.Privilege;

@Component("platform-PrivilegeAction")
public class PrivilegeAction extends CrudActionSupport<Privilege> {

	private static final long serialVersionUID = 1L;

	private Privilege model;
	
	@Override
	public void prepareModel() {
		if (id != null) {
			model = (Privilege) baseService.get(id);
		} else {
			model = new Privilege();
		}
	}

	@Override
	public Privilege getModel() {
		return model;
	}
	
	public String listAll() {
		List<Privilege> privileges = baseService.getAll();
		result = privileges;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Privilege> privilegePage = baseService.findPage(getPage(),getFilters());
		savePage(privilegePage);
		return SUCCESS;
	}

	@Override
	public String input() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		baseService.save(model);
		result = resultJson;
		return SUCCESS;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

}
