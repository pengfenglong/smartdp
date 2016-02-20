package com.smartdp.platform.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.platform.model.ModuleOperate;

@Component("platform-ModuleOperateAction")
public class ModuleOperateAction extends CrudActionSupport<ModuleOperate> {

	private static final long serialVersionUID = 1L;

	private ModuleOperate model;
	
	private Long id;
	
	public void prepareModel(){
		if (id != null) {
			model = (ModuleOperate) baseService.get(id);
		} else {
			model = new ModuleOperate();
		}
	}

	@Override
	public ModuleOperate getModel() {
		return model;
	}
	
	
	public String listAll() {
		List<ModuleOperate> moduleOperates = baseService.getAll();
		result = moduleOperates;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<ModuleOperate> moduleOperatePage = baseService.findPage(getPage(),getFilters());
		savePage(moduleOperatePage);
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
			model.setOperateId(id);
		}
		model.setStatus("1");
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
