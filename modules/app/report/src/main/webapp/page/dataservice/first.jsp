<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<%@ include file="/WEB-INF/page/common/meta.jsp" %>
		<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/thrid-party/jquery/plugin/DataTables-1.8.1/media/css/demo_page.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/thrid-party/jquery/plugin/DataTables-1.8.1/media/css/demo_table_jui.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/framework/thrid-party/jquery/plugin/DataTables-1.8.1/media/js/jquery.dataTables.min.js"></script>
		<link type="text/css" href="<%=request.getContextPath()%>/resources/rae/css/sqlbuilder.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/rae/js/sqlbuilder.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/rae/js/editTables.js"></script>
		<script type="text/javascript">
			var mode = '<%=request.getParameter("mode")%>';
		</script>
	</head>
	<body>
	<s:set name="mode" value="#parameters.mode[0]" />
	<s:if test="#request.mode == 'edit'">
	   <form method="post" action="dataservice_second_edit.action">
	   <s:hidden name="dataService.id" value="%{dataService.id}"></s:hidden>
	</s:if>
	<s:else>
	    <form method="post" action="dataservice_second.action">
	</s:else>
		<div>
			<s:textfield label="名称"  name="dataService.name" ></s:textfield>
			<s:select list="datasourceConfigs" listKey="id" listValue="name" name="dataService.datasourceConfig.id" label="数据源" cssStyle="width:155px"></s:select>
			<s:textfield label="描述" name="dataService.description"  cssStyle="width:500px"></s:textfield>
		</div>
		<div id="sql_container">
			<ul>
				<li><a href="#sql_designer">SQL设计器</a></li>
				<li><a href="#sql_resource">SQL</a></li>
			</ul>

			<div id="sql_designer">
			
				<div class="ui-layout-west">
					<div class="tables_list">
						<ul id="database_table">
						</ul>
					</div>
				</div>
				<div class="ui-layout-center">
					<div id="container">
						<div class="ui-layout-center">
							<div class="table_container" id="table_container"></div>
						</div>
						<div class="ui-layout-south">
							<div class="query_result" id="query_result_div">
								<table cellpadding="0" cellspacing="0" border="0" class="display" id="result_table">
									<tr><td>无查询结果</td></tr>
								</table>
							</div>
						</div>
					</div>
					
				</div>
				<div class="ui-layout-east">
					<div id="operation_area">
						<ul>
							<li><a href="#designer_columns">字段</a></li>
							<li><a href="#designer_calccolumns">计算字段</a></li>
							<li><a href="#designer_condition">条件</a></li>
							<li><a href="#designer_group">分组</a></li>
							<li><a href="#designer_filter">统计过滤</a></li>
							<li><a href="#designer_sort">排序</a></li>
							<li><a href="#sql_gene">SQL视图</a></li>
						</ul>

						<div id="designer_columns"  class="operation_area_div">
							<table width="345" border="0" cellpadding="0" cellspacing="0" id="designer_columns_table">
							    <tr>
							      <td id="fieldName_list" width="110" bgcolor="#EFEFEF" Name="fieldName" EditType="DropDownList" DataItems="">字段</td>
							      <td width="110" bgcolor="#EFEFEF" Name="alias" EditType="TextBox">别名</td>
							      <td width="30" bgcolor="#EFEFEF" Name="opration">
							      </td>
							    </tr>
							</table>
						</div>
						<div id="designer_calccolumns"  class="operation_area_div">
							<table width="345" border="0" cellpadding="0" cellspacing="0" id="designer_calccolumns_table">
							    <tr>
							      <td id="func_list" width="90" bgcolor="#EFEFEF" Name="func" EditType="DropDownList" DataItems="">函数</td>
							      <td id="calcfieldName_list" width="110" bgcolor="#EFEFEF" Name="calcfieldName" EditType="DropDownList" DataItems="">字段</td>
							      <td width="110" bgcolor="#EFEFEF" Name="alias" EditType="TextBox">别名</td>
							      <td width="30" bgcolor="#EFEFEF" Name="opration">
							      	 <span style="cursor:pointer;color:red;" onclick="AddRow(document.getElementById('designer_calccolumns_table'),1)" />+<span>
							      </td>
							    </tr>
							    <tr>
							      <td bgcolor="#FFFFFF" Value=""></td>
							      <td bgcolor="#FFFFFF" Value=""></td>
							      <td bgcolor="#FFFFFF"></td>
							      <td bgcolor="#FFFFFF"><span style="cursor:pointer;color:red;" onclick="" />-<span></td>
							    </tr>
							</table>
						</div>
							
						<div id="designer_condition" class="operation_area_div">
							<table width="345" border="0" cellpadding="0" cellspacing="0" id="designer_condition_table">
							    <tr>
							      <td id="conditionFieldName_list" width="80" bgcolor="#EFEFEF" Name="conditionFieldName" EditType="DropDownList" DataItems="">字段</td>
							      <td id="operator_list" width="55" bgcolor="#EFEFEF" Name="operator" EditType="DropDownList" DataItems="">操作符</td>
							      <td width="100" bgcolor="#EFEFEF" Name="Amount" EditType="TextBox">值</td>
							      <td id="join_list" width="50" bgcolor="#EFEFEF" Name="Join" EditType="DropDownList" DataItems="">连接符</td>
							   	  <td width="30" bgcolor="#EFEFEF" Name="opration">
							      	 <span style="cursor:pointer;color:red;" onclick="AddRow(document.getElementById('designer_condition_table'),1)" />+<span>
							      </td>
							    </tr>
							    <tr>
							      <td bgcolor="#FFFFFF" Value=""></td>
							      <td bgcolor="#FFFFFF" Value=""></td>
							      <td bgcolor="#FFFFFF"></td>
							      <td bgcolor="#FFFFFF" Value=""></td>
							      <td bgcolor="#FFFFFF"><span style="cursor:pointer;color:red;" onclick="" />-<span></td>
							    </tr>
							</table>
						</div>
						<div id="designer_group" class="operation_area_div">
							<table width="345" border="0" cellpadding="0" cellspacing="0" id="designer_group_table">
							    <tr>
							      <td id="groupFieldName_list" width="110" bgcolor="#EFEFEF" Name="groupFieldName" EditType="DropDownList" DataItems="">字段</td>
								  <td width="30" bgcolor="#EFEFEF" Name="opration">
							      	 <span style="cursor:pointer;color:red;" onclick="AddRow(document.getElementById('designer_group_table'),1)" />+<span>
							      </td>
							    </tr>
							   	<tr>
							      <td bgcolor="#FFFFFF" Value=""></td>
							      <td bgcolor="#FFFFFF"><span style="cursor:pointer;color:red;" onclick="" />-<span></td>
							    </tr>
							</table>
						</div>
						<div id="designer_filter" class="operation_area_div">
							统计过滤
						</div>
						<div id="designer_sort" class="operation_area_div">
							<table width="345" border="0" cellpadding="0" cellspacing="0" id="designer_sort_table">
							    <tr>
							      <td id="sortFieldName_list" width="110" bgcolor="#EFEFEF" Name="sortFieldName" EditType="DropDownList" DataItems="">字段</td>
							      <td width="30" bgcolor="#EFEFEF" Name="opration">
							      	 <span style="cursor:pointer;color:red;" onclick="AddRow(document.getElementById('designer_sort_table'),1)" />+<span>
							      </td>
							    </tr>
							   	<tr>
							      <td bgcolor="#FFFFFF" Value=""></td>
							      <td bgcolor="#FFFFFF"><span style="cursor:pointer;color:red;" onclick="" />-<span></td>
							    </tr>
							</table>						
						</div>
						<div class="sql_gene" id="sql_gene">
							<p>
								<span class="key_word">select</span> 
								<p id="key_word_select" class="sql_statement"></p>
								<p class="operation_btn"><添加字段></p>
							</p>
							<p>
								<span class="key_word">form</span>
								<p id="key_word_from" class="sql_statement"></p>
								<p class="operation_btn" id="add_table_btn"><添加表></p>
							</p>
							<p>
								<span class="key_word">where</span> 
								<p id="key_word_where" class="sql_statement"></p>
								<p class="operation_btn" id="add_condition_btn"><添加条件></p>
							</p>
							<p>
								<span class="key_word">group by</span> 
								<p id="key_word_groupby" class="sql_statement"></p>
								<p class="operation_btn" id="add_group_btn"><添加分组></p>
							</p>
							<p>
								<span class="key_word">having</span> 
								<p id="key_word_having" class="sql_statement"></p>
								<p class="operation_btn" id="add_having_btn"><添加条件></p>
							</p>
							<p>
								<span class="key_word">order by</span> 
								<p id="key_word_orderby" class="sql_statement"></p>
								<p class="operation_btn" id="add_order_btn"><添加排序字段></p>
							</p>
							<div id="selectDialog"></div>	
						</div>
					</div>
				</div>
			</div>
			<div id="sql_resource">
				<s:textarea cols="185" rows="31" id="executeSql" name="dataService.executeSql" cssClass="ui-widget-content">
				</s:textarea>
			</div>
		</div>
		<s:submit label="下一步" id="nex_btn" type="button" cssClass="button"></s:submit>	
	</form>	

	</body>
	<script type="text/javascript">
		var designer_columns_table = document.getElementById("designer_columns_table");
		var designer_calccolumns_table = document.getElementById("designer_calccolumns_table");
		var designer_condition_table = document.getElementById("designer_condition_table");
		var designer_group_table = document.getElementById("designer_group_table");
		var designer_sort_table = document.getElementById("designer_sort_table");
	
		// 设置表格可编辑
		// 可一次设置多个，例如：EditTables(tb1,tb2,tb2,......)
		EditTables(designer_columns_table,designer_calccolumns_table,designer_condition_table,designer_group_table,designer_sort_table);
	</script>
</html>