<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

import com.smartdp.core.dao.IBaseDao;
import ${basepackage}.model.${className};

public interface I${className}Dao extends IBaseDao<${className},${table.idColumn.javaType}>{

}
