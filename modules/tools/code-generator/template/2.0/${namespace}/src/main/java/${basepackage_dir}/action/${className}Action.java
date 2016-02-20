<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.action;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import ${basepackage}.model.${className};

@Component("${namespace}-${className}Action")
public class ${className}Action extends CrudActionSupport<${className}>{
	
	private ${className} model;
	
	public void prepareModel(){
		if (id != null) {
			model = (${className})baseService.get(id);
		} else {
			model = new ${className}();
		}
	}

	@Override
	public ${className} getModel() {
		return model;
	}
	
	
	public String listAll() {
		List<${className}> models = baseService.getAll();
		result = models;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<${className}> modelPage = baseService.findPage(getPage(),getFilters());
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
