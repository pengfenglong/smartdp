<%@ page contentType="text/html;charset=UTF-8"%>
<form id="smart-form" method="post" >
	<table>
		<#list table.columns as column>
		<#if !column.pk>
		<tr>
			<td align="right">			
				<#if column.remarks?exists && column.remarks != "">
				${column.remarks}:
				<#else>
				${column.columnNameLower}:
				</#if>
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="${column.columnNameLower}"
					<#if column.javaType == "java.lang.String">
					   class="easyui-validatebox"
					<#elseif  column.javaType == "java.sql.Date">
					   class="easyui-datebox"
					<#elseif  column.javaType == "java.sql.Timestamp">
					   class="easyui-datetimebox"						
					<#elseif  column.javaType == "java.lang.Long">
					   class="easyui-numberbox"
					<#elseif  column.javaType == "java.lang.Integer">
					   class="easyui-numberbox"
					<#else>
					</#if>
					<#if !column.nullable>
					   required="true"
					</#if>
				/>
			 </td>
		</tr>
		</#if>
		</#list>
	</table>
</form>

