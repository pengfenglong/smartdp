<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<sm:validform formid="smart-form" layout="div" dialog="false" action="cms-save-Template.action">
	<sm:div label="模板编码">
	    <sm:validinput  datatype="s1-255" ignore="ignore" name="code"></sm:validinput>
	 </sm:div>
	<sm:div label="板模名称" need="true">
		<sm:validinput  datatype="s1-255" name="name"></sm:validinput>
	 </sm:div>
	<sm:div label="模板路径">
	    <sm:validinput  datatype="s1-255" ignore="ignore" name="url"></sm:validinput>
	 </sm:div>
</sm:validform>