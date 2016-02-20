<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<html>
	<head>
		<%@ include file="/WEB-INF/page/common/meta.jsp" %>
		<script type="text/javascript">
			function addDatasourceConfig()
			{
				parent.parent.frames["mainFrame"].addTab("新增数据源","${ctx}/rae/editDatasourceConfig.action?date="+new Date().getTime());
			}

			function editDatasourceConfig(id,name)
			{
				parent.parent.frames["mainFrame"].addTab("修改数据源-"+name,"${ctx}/rae/editDatasourceConfig.action?datasourceConfig.id="+id);
			}

			function viewDatasourceConfig(id,name)
			{
				parent.parent.frames["mainFrame"].addTab("查看数据源-"+name,"${ctx}/rae/viewDatasourceConfig.action?datasourceConfig.id="+id);
			}
		</script>
		<title>数据源列表</title>		
	</head>
	<body>
		<div style="display: inline;">
			<input type="button" value="新增" onclick="addDatasourceConfig()"/>
			<input type="button" value="删除" onclick="batchDelete('${ctx}/security/batchDeleteUser.action','items',document.forms.ec)"/>			
		</div>
		
		<ec:table items='page.result' var="item" method="get" 
			retrieveRowsCallback="limit" sortRowsCallback="limit" filterRowsCallback="limit"
			action="${ctx}/rae/datasourceconfiglist.action" autoIncludeParameters="true">
			<ec:exportXls fileName="presidents.xls" tooltip="Export Excel" />
			<ec:exportPdf fileName="presidents.pdf" tooltip="Export PDF"
				headerColor="blue" headerBackgroundColor="red"
				headerTitle="Presidents" />
			<ec:exportCsv fileName="presidents.txt" tooltip="Export CSV"
				delimiter="|" />
			<ec:row>
				<ec:column property="选择" title="<input type='checkbox' onclick=\"setAllCheckboxState('items',this.checked)\" >" sortable="false" width="3%" viewsAllowed="html" filterable="false">
					<input type="checkbox" name="items" value="userId=${item.id}&"/>
				</ec:column>
				<ec:column property="id"  title="ID" />
				<ec:column property="name"  title="名称"/>
				
				<ec:column property="description"  title="描述"/>
				<ec:column property="parentId"  title="父类型"/>
				<ec:column property="操作" title="操作" sortable="false" viewsAllowed="html" filterable="false" width="12%">
					<a href="javascript:void(0)" onclick="viewDatasourceConfig('${item.id}','${item.name}')">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="editDatasourceConfig('${item.id}','${item.name}')">修改</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/rae/deleteDatasourceConfig.action?datasourceConfig.id=${item.id}">删除</a>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
</html>

