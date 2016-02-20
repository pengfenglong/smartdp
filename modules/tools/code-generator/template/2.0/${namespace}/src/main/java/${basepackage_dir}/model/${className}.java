<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.*;
import com.smartdp.core.dao.entity.IdEntity;

/**
 <#if table.remarks?exists && table.remarks != "">
 * ${table.remarks}
 </#if> 
 * @author pengfenglong
 */
@Entity
@Table(name = "${table.sqlName}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${className} extends IdEntity{

<#-- 普通属性:开始 -->
	<#list table.columns as column>
	<#if !column.pk && !column.fk>
	<#if column.remarks?exists && column.remarks != "">
	/**${column.remarks}*/
	</#if> 
	<#if column.nullable>
	@Column(name = "${column.sqlName}")
	<#else>
	@Column(name = "${column.sqlName}", nullable = false)
	</#if>
	private ${column.javaType} ${column.columnNameLower};
	
	</#if>
	</#list>
<#-- 普通属性:结束 -->
<#-- 一对多属性:开始 -->
	<#list table.oneToMany as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "${classNameLower}")
	private Set<${fkPojoClass}> ${fkPojoClassVar}s = new HashSet<${fkPojoClass}>(0);
	
	</#list>
<#-- 一对多属性:结束 -->
<#-- 多对一属性:开始 -->
	<#list table.manyToOne as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	<#list foreignKey.parentColumns?values as fkColumn>
	@JoinColumn(name = "${fkColumn}")
    </#list>
	private ${fkPojoClass} ${fkPojoClassVar};
	
	</#list>
<#-- 多对一属性:结束 -->
<#-- 多对多属性:开始 -->
	<#list table.manyToMany as foreignKeys>
	<#assign fkPojoClass = foreignKeys[1].parentTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	<#if true>
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch = FetchType.LAZY)
    @JoinTable(
            name = "${foreignKeys[0].tableName}",
        	<#list foreignKeys[0].parentColumns?values as fkColumn>
       		joinColumns = { @JoinColumn(name = "${fkColumn}") },
	       	</#list>
	       	<#list foreignKeys[1].parentColumns?values as fkColumn>
	       	inverseJoinColumns = @JoinColumn(name = "${fkColumn}"))
	       	</#list>
	<#else>
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy="${_fkPojoClassVar}s")
	</#if>
	private Set<${fkPojoClass}> ${fkPojoClassVar}s = new HashSet<${fkPojoClass}>(0);
	
	</#list>
<#-- 多对多属性:结束 -->
<#-- 一般属性get,set方法:开始 -->
	<#list table.columns as column>
	<#if !column.pk && !column.fk>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	
	</#if>
	</#list>
<#-- 一般属性get,set方法:结束 -->
<#-- 一对多属性get,set方法:开始 -->
	<#list table.oneToMany as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	
	</#list>
<#-- 一对多属性get,set方法:结束 -->
<#-- 多对一属性get,set方法:开始 -->
	<#list table.manyToOne as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	
	</#list>
<#-- 多对一属性get,set方法:结束 -->
<#-- 多对多属性get,set方法:开始 -->
	<#list table.manyToMany as foreignKeys>
	<#assign fkPojoClass = foreignKeys[1].parentTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>	
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}s){
		this.${fkPojoClassVar}s = ${fkPojoClassVar}s;
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	
	</#list>
<#-- 多对多属性get,set方法:结束-->
}
