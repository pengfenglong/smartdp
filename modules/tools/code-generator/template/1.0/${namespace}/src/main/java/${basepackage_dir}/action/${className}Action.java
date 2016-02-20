<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.action;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;

import ${basepackage}.service.I${className}Service;
import ${basepackage}.model.${className};

public class ${className}Action extends CrudActionSupport<${className}>{
	
	@Autowired
	private I${className}Service ${classNameLower}Service;	
	private ${className} model;
	private ${table.idColumn.javaType} id;
	
	public void prepareModel(){
		if (id != null) {
			model = ${classNameLower}Service.get(id);
		} else {
			model = new ${className}();
		}
	}

	@Override
	public ${className} getModel() {
		return model;
	}
	
	
	public String listAll() {
		List<${className}> ${classNameLower}s = ${classNameLower}Service.getAll();
		result = ${classNameLower}s;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<${className}> ${classNameLower}Page = ${classNameLower}Service.findPage(getPage(),getFilters());
		savePage(${classNameLower}Page);
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
			model.set${table.idColumn.columnName}(id);
		}
		${classNameLower}Service.save(model);
		result = "SUCCESS";
		return SUCCESS;
	}

	@Override
	public String delete() {
		String ids = getParameter("ids");
		${classNameLower}Service.deleteByIds(ids);
		result = "SUCCESS";
		return SUCCESS;
	}

	public void set${className}Service(I${className}Service ${classNameLower}Service) {
		this.${classNameLower}Service = ${classNameLower}Service;
	}
}
