<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "${table.sqlName}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${className}{
	<@generateFields/>
	<@generateProperties/>
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>
	<@generateJavaManyToMany/>
<#macro generateFields>

	<#list table.columns as column>
	<#if column.pk>
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	</#if>
	@Column(name = "${column.sqlName}", nullable = ${column.nullable?string})
	private ${column.javaType} ${column.columnNameLower};
	
	</#list>

</#macro>


<#macro generateProperties>
	<#list table.columns as column>

	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.oneToMany as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "${classNameLower}")
	private Set<${fkPojoClass}> ${fkPojoClassVar}s = new HashSet<${fkPojoClass}>(0);
	
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.manyToOne as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	<#list foreignKey.parentColumns?values as fkColumn>
	@JoinColumn(name = "${fkColumn}",nullable = false, insertable = false, updatable = false)
    </#list>
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>

<#macro generateJavaManyToMany>
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
	
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}s){
		this.${fkPojoClassVar}s = ${fkPojoClassVar}s;
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>
}
