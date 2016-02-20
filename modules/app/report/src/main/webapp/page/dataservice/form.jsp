<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<link type="text/css" href="../../resources/css/sqlbuilder.css" rel="stylesheet" />
<script type="text/javascript" src="../../resources/js/sqlbuilder.js"></script>
<script type="text/javascript" src="../../resources/js/editTables.js"></script>
<sm:panel title="SQL设计器" fit="true" border="false">
	<sm:layoutcontainer>
		<sm:layout region="west" width="180px" border="false" split="true">
			<ul id="database_table"></ul>
		</sm:layout>
		<sm:layout region="center" border="false">
			<sm:layoutcontainer>
				<sm:layout region="center" border="false">
					<div id="table_container" style="width: 99%;height: 99%"></div>
				</sm:layout>
				<sm:layout region="south" border="false" split="true" height="500px">
					<sm:tabs id="_tabs">
						<sm:tab title="SQL视图">
							<div id="sql_gene"></div>
						</sm:tab>
						<sm:tab title="SQL">
							<div id="sql_resource">
								<textarea style="width: 95%; height: 80%" id="executeSql"></textarea>
							</div>
						</sm:tab>
						<sm:tab title="查询结果"></sm:tab>
					</sm:tabs>
				</sm:layout>
			</sm:layoutcontainer>
		</sm:layout>
	</sm:layoutcontainer>
</sm:panel>