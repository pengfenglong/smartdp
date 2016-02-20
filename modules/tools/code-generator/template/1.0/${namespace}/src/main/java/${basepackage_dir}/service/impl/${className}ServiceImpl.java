<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IBaseDao;
import com.smartdp.core.service.impl.BaseServiceImpl;
import ${basepackage}.model.${className};
import ${basepackage}.service.I${className}Service;

@Component("${classNameLower}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${className},${table.idColumn.javaType}> implements I${className}Service{
	@Autowired
	public void set${className}Dao(IBaseDao ${classNameLower}Dao) {
		this.baseDao = ${classNameLower}Dao;
	}
	
}
