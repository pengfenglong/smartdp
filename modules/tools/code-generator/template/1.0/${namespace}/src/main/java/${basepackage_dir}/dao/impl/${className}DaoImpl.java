<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.dao.impl;

import org.springframework.stereotype.Component;

import com.smartdp.core.dao.impl.BaseDaoSupport;
import ${basepackage}.model.${className};
import ${basepackage}.dao.I${className}Dao;

@Component("${classNameLower}Dao")
public class ${className}DaoImpl extends BaseDaoSupport<${className},${table.idColumn.javaType}>  implements I${className}Dao{


}
