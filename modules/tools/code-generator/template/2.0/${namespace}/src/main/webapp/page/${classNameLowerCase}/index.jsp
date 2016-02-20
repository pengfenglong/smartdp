<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#list table.columns as column>
 <#if column.pk>
 <#assign idField = column.columnNameLower>  
 </#if>
</#list> 
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:datagrid title="${table.remarks}管理" url="${namespace}-list-${className}.action" idField="${idField}" autotool="add,edit,delete">
<#list table.columns as column>
	<#if column.remarks?exists && column.remarks != "">
	<#assign _columnName = column.remarks>
	<#else>
	<#assign _columnName = column.columnNameLower>
	</#if> 
	<#if !column.pk>
	<#if column.columnNameLower?lower_case?contains("name") || column.columnNameLower?lower_case?contains("code") || column.columnNameLower?lower_case?contains("status")>
	<sm:datagridcolumn field="${column.columnNameLower}" width="1" query="true">${_columnName}</sm:datagridcolumn>
	<#else>
	<sm:datagridcolumn field="${column.columnNameLower}" width="1">${_columnName}</sm:datagridcolumn>
	</#if>
	</#if>
</#list>
</sm:datagrid>