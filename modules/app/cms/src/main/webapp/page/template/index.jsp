<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:linkbutton onclick="$.crud.createWindow('导入模板','import.jsp',670,265)">导入模板</sm:linkbutton>
<sm:linkbutton onclick="alert('test');">导出模板</sm:linkbutton>
<sm:linkbutton onclick="alert('test');">预览模板</sm:linkbutton>
<!--<sm:linkbutton onclick="window.location.href='../file/index.jsp'">模板文件维护</sm:linkbutton>  -->
<sm:datagrid title="管理" url="cms-list-Template.action" idField="id" autotool="delete">
	<sm:datagridcolumn field="code" width="1" query="true">模板编码</sm:datagridcolumn>
	<sm:datagridcolumn field="name" width="1" query="true">板模名称</sm:datagridcolumn>
	<sm:datagridcolumn field="url" width="1">模板路径</sm:datagridcolumn>
</sm:datagrid>