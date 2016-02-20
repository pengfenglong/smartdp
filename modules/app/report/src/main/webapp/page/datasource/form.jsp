<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<sm:validform formid="smart-form" layout="div" dialog="false" action="report-save-Datasource.action">
	<sm:div label="名称" >
		<sm:validinput datatype="s2-8" name="name"></sm:validinput>
	</sm:div>
	<sm:div label="类型" >
		<sm:select dict="datasourceType"  name="dsType"></sm:select>
	</sm:div>
	<sm:div label="jdbc属性" >
		<sm:validinput datatype="*2-200" name="dsProperties"></sm:validinput>
	</sm:div>
	<sm:div label="分隔符" >
		<sm:validinput datatype="*1-2" name="propertyRegex" ignore="ignore"></sm:validinput>
	</sm:div>
	<sm:div label="描述" >
		<sm:validinput datatype="s2-8" name="memo" ignore="ignore"></sm:validinput>
	</sm:div>
</sm:validform>
