<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:datagrid title="管理" url="cms-list-ArticlePicture.action" idField="id" autotool="add,edit,delete">
	<sm:datagridcolumn field="articleId" width="1">articleId</sm:datagridcolumn>
	<sm:datagridcolumn field="priority" width="1">排列顺序</sm:datagridcolumn>
	<sm:datagridcolumn field="imgPath" width="1">图片地址</sm:datagridcolumn>
	<sm:datagridcolumn field="description" width="1">描述</sm:datagridcolumn>
	<sm:datagridcolumn field="style" width="1">状态</sm:datagridcolumn>
	<sm:datagridcolumn field="isThumb" width="1">isThumb</sm:datagridcolumn>
</sm:datagrid>