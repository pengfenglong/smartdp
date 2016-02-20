/*
 * pengfenglong
 */

package com.smartdp.platform.action;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.platform.model.Parameter;

@Component("platform-ParameterAction")
public class ParameterAction extends CrudActionSupport<Parameter>{
	
	private static final long serialVersionUID = -5779218995720922696L;
	
	private Parameter model;
	
	public void prepareModel(){
		if (id != null) {
			model = (Parameter)baseService.get(id);
		} else {
			model = new Parameter();
		}
	}

	@Override
	public Parameter getModel() {
		return model;
	}
	
	
	public String listAll() {
		List<Parameter> parameters = baseService.getAll();
		result = parameters;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Parameter> parameterPage = baseService.findPage(getPage(),getFilters());
		savePage(parameterPage);
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
			model.setParaId(id);
		}
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
