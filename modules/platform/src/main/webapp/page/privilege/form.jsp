<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<sm:validform formid="smart-form" layout="div" dialog="false" action="platform-save-ModuleOperate.action">
	<sm:div label="模块操作名称" >
		<sm:validinput datatype="s2-8" name="operateName"></sm:validinput>
	</sm:div>
	<sm:div label="模块操作功能链接" >
		<sm:validinput datatype="s2-8" name="optFunLink"></sm:validinput>
	</sm:div>
	<sm:div label="模块操作顺序" >
		<sm:validinput datatype="n1-3" ignore="ignore" name="optOrder"></sm:validinput>
	</sm:div>
	<sm:div label="模块操作组" >
		<sm:validinput datatype="n1-3" ignore="ignore" name="optGroup"></sm:validinput>
	</sm:div>
	<sm:div label="模块操作状态" >
		<sm:select dict="validState"  name="status"></sm:select>
	</sm:div>
	<sm:div label="描述" >
		<sm:validinput datatype="s1-100" ignore="ignore" name="memo"></sm:validinput>
	</sm:div>
</sm:validform>
