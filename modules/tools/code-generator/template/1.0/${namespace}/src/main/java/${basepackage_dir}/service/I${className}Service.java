<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;

import com.smartdp.core.service.IBaseService;
import ${basepackage}.model.${className};

public interface I${className}Service extends IBaseService<${className},${table.idColumn.javaType}>{

}
