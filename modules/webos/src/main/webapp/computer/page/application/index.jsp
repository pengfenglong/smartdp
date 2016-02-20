<%@ page contentType="text/html;charset=UTF-8"%>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="../../resources/css/main.css" />
<%@ include file="../../../framework/easyui-head.jsp"%>
<script type="text/javascript">
	$(function() {
		$('.toptitle').html('系统管理 —— 管理');
		$.crud.init({
			title : '',
			datagrid : 'smart-datagrid',
			dialog : 'smart-dialog',
			form : 'smart-form',
			saveUrl : '/smartdp/webos-save-Application.action',
			delUrl : '/smartdp/webos-delete-Application.action',
			normalQueryParam : 'filter_LIKES_name'
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
				   url="/smartdp/webos-list-Application.action" 
				   fit="true" 
				   singleSelect="true"
				   checkOnSelect="true",
				   selectOnCheck="false",
				   fitColumns="true"  
				   loadMsg="请等待..."
				   pagination="false"
		           idField="applicationId"
			>
				<thead>
					<tr>
						<th checkbox="true"></th>
						<th field="id" width="1000">
							编码
						</th>
						<th field="name" width="1000">
							应用名
						</th>
						<th field="catalog" width="1000">
							分类
						</th>
						<th field="provider" width="1000">
							提供者
						</th>
						<th field="explainTime" width="1000">
							创建时间
						</th>
						<th field="href" width="1000">
							地址
						</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="smart-dialog" modal="true" class="easyui-dialog" title=" " closed="true"
		style="width: 450px; height: 510px;padding:10px; position: relative"
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
