<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<sm:validform formid="smart-form" layout="div" dialog="false" action="platform-save-Parameter.action">
	<sm:div label="参数键名" >
		<sm:validinput datatype="s2-8" name="paraKeyName"></sm:validinput>
	</sm:div>
	<sm:div label="参数名" >
		<sm:validinput datatype="s2-8" name="paraName"></sm:validinput>
	</sm:div>
	<sm:div label="参数类型" >
		<sm:validinput datatype="s2-8" name="paraType"></sm:validinput>
	</sm:div>
	<sm:div label="参数值" >
		<sm:validinput datatype="s2-8" name="paraValue"></sm:validinput>
	</sm:div>
	<sm:div label="描述" >
		<sm:validinput datatype="s2-8" name="memo"></sm:validinput>
	</sm:div>
</sm:validform>
