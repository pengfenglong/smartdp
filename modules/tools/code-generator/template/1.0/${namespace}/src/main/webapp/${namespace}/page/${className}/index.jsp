<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<%@ page contentType="text/html;charset=UTF-8"%>
<head>
<title>${table.remarks}</title>
<link rel="stylesheet" type="text/css" href="../../resources/css/main.css" />
<%@ include file="../../../framework/easyui-head.jsp"%>
<script type="text/javascript">
	$(function() {
		$.crud.init({
			title : '${table.remarks}',
			saveUrl : '${namespace}-save-${className}.action',
			delUrl : '${namespace}-delete-${className}.action',
			normalQueryParam : 'filter_LIKES_id_OR_name'
		});
	});
</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false">
			<div class="toolbar">
				<input id="q" class="easyui-searchbox" prompt="按ID或者名称查询" searcher="$.crud.normalQuery" /> 
				<a href="#" class="easyui-linkbutton" iconCls="" plain="true" onclick="$.crud.create()">新增</a> 
				<a href="#" class="easyui-linkbutton" iconCls="" plain="true" onclick="$.crud.edit()">修改</a> 
				<a href="#" class="easyui-linkbutton" iconCls="" plain="true" onclick="$.crud.del()">删除</a>
			</div>
		</div>
		<div region="center" border="false">
			<table id="smart-datagrid" 
				   class="easyui-datagrid"
				   url="<%=request.getContextPath()%>/${namespace}-list-${className}.action" 
				   fit="true" 
				   singleSelect="true"
				   checkOnSelect="true",
				   selectOnCheck="false",
				   fitColumns="true"  
				   loadMsg="请等待..."
				   pagination="true"
		           <#list table.columns as column>
		           <#if column.pk>
		           idField="${column.columnNameLower}"
		           </#if>
				   </#list>		
			>
				<thead>
					<tr>
						<th checkbox="true"></th>
						<#list table.columns as column>
						<#if !column.pk>
						<th field="${column.columnNameLower}" width="1000">
							<#if column.remarks?exists && column.remarks != "">
							${column.remarks}
							<#else>
							${column.columnNameLower}
							</#if> 
						</th>
						</#if>
						</#list>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="smart-dialog" modal="true" class="easyui-dialog" title=" " closed="true"
		style="width: 550px; height: <#if (table.columns?size>13)>550<#else>${table.columns?size * 45}</#if>px;padding:10px; position: relative"
		data-options="buttons:[{
				text:'保存',
				handler:function(){$.crud.save()}
			},{
				text:'取消',
				handler:function(){$.crud.close()}
			}]">
		<%@ include file="form.jsp"%>
	</div>

</body>
