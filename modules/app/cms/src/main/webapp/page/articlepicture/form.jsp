<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<sm:validform formid="smart-form" layout="div" dialog="false" action="cms-save-ArticlePicture.action">
	<sm:div label="articleId" need="true">
		<sm:validinput  datatype="n1-19" name="articleId"></sm:validinput>
	 </sm:div>
	<sm:div label="排列顺序" need="true">
		<sm:validinput  datatype="n1-10" name="priority"></sm:validinput>
	 </sm:div>
	<sm:div label="图片地址" need="true">
		<sm:validinput  datatype="s1-100" name="imgPath"></sm:validinput>
	 </sm:div>
	<sm:div label="描述">
	    <sm:validinput  datatype="s1-255" ignore="ignore" name="description"></sm:validinput>
	 </sm:div>
	<sm:div label="状态">
	    <sm:validinput  datatype="s1-50" ignore="ignore" name="style"></sm:validinput>
	 </sm:div>
	<sm:div label="isThumb">
	 </sm:div>
</sm:validform>