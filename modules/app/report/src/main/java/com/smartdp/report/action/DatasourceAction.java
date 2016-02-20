/*
 * pengfenglong
 */

package com.smartdp.report.action;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.report.model.Datasource;

@Component("report-DatasourceAction")
public class DatasourceAction extends CrudActionSupport<Datasource>{
	
	private static final long serialVersionUID = -5779218995720922696L;
	
	private Datasource model;
	
	public void prepareModel(){
		if (id != null) {
			model = (Datasource)baseService.get(id);
		} else {
			model = new Datasource();
		}
	}

	@Override
	public Datasource getModel() {
		return model;
	}
	
	
	public String listAll() {
		List<Datasource> models = baseService.getAll();
		result = models;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Datasource> modelPage = baseService.findPage(getPage(),getFilters());
		savePage(modelPage);
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
			model.setId(id);
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
