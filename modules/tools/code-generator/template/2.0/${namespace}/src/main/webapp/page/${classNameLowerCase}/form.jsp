<#assign className = table.className>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<sm:validform formid="smart-form" layout="div" dialog="false" action="${namespace}-save-${className}.action">
<#list table.columns as column>
	<#if column.remarks?exists && column.remarks != "">
	<#assign label = column.remarks>
	<#else>
	<#assign label = column.columnNameLower>
	</#if>
	<#if !column.pk && column.columnNameLower!="createDate" && column.columnNameLower!="creator">
	<#if column.nullable>
	<sm:div label="${label}">
	<#else>
	<sm:div label="${label}" need="true">
	</#if>		
		<#if column.javaType == "java.lang.String">
		<#if column.nullable>
	    <sm:validinput  datatype="s1-${column.size}" ignore="ignore" name="${column.columnNameLower}"></sm:validinput>
		<#else>
		<sm:validinput  datatype="s1-${column.size}" name="${column.columnNameLower}"></sm:validinput>
		</#if>
		<#elseif  column.javaType == "java.sql.Date">
	    <sm:datebox name="${column.columnNameLower}"></sm:datebox>
		<#elseif  column.javaType == "java.sql.Timestamp">
	    <sm:datetimebox name="${column.columnNameLower}"></sm:datetimebox>						
		<#elseif  column.javaType == "java.lang.Long" || column.javaType == "java.lang.Integer">
		<#if column.nullable>
	    <sm:validinput  datatype="n1-${column.size}" ignore="ignore" name="${column.columnNameLower}"></sm:validinput>
		<#else>
		<sm:validinput  datatype="n1-${column.size}" name="${column.columnNameLower}"></sm:validinput>
		</#if>
		<#else>
		</#if>
	</sm:div>
	</#if>
</#list>
</sm:validform>