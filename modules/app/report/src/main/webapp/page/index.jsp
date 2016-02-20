<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<html>
<head>
	<title></title>
	<script type="text/javascript">
	
		function gotoDataSource(){
			top.frames["mainFrame"].addTab("数据源管理","<%=request.getContextPath()%>/rae/datasourceConfiglist.action");
		}
		function gotoTableDefined(){
			
		}
		function gotoDataService(){
			top.frames["mainFrame"].addTab("数据服务管理","<%=request.getContextPath()%>/rae/listDataservice.action");
		}
		function gotoPageDesigner(){
			top.frames["mainFrame"].addTab("报表设计","<%=request.getContextPath()%>/rae/pageDesignerHome.action");
		}
	</script>
</head>
<body>
	<table style="height: 100%;width: 100%">
		<tr height="20%"><td colspan="9">&nbsp;</td></tr>
		<tr height="33%">
			<td align="center" width="180px">&nbsp;</td>
			<td align="center"   onclick="gotoDataSource();" style="cursor: pointer;">
				<p><img src="../resources/images/ds.jpg" width="120px" height="150px"/></p>
				<p style="font-weight: bold;font-size: 15px">数据源管理</p>
			</td>
			<td align="center" width="150px">
				<p><img src="../resources/images/misto_090.png" width="120px" height="60px"/></p>
			</td>
			<td align="center" onclick="gotoTableDefined();" style="">
				<p><img src="../resources/images/table.jpg" width="120px" height="150px"/></p>
				<p style="font-weight: bold;font-size: 15px">表定义</p>
			</td>
			<td align="center" width="150px">
				<p><img src="../resources/images/misto_090.png" width="120px" height="60px"/></p>
			</td>
			<td align="center" onclick="gotoDataService();" style="cursor: pointer;">
				<p><img src="../resources/images/cube.png" width="150px" height="150px"/></p>
				<p style="font-weight: bold;font-size: 15px">数据服务管理</p>
			</td>
			<td align="center" width="150px">
				<p><img src="../resources/images/misto_090.png" width="120px" height="60px"/></p>
			</td>
			<td align="center" onclick="gotoPageDesigner();" style="cursor: pointer;">
				<p><img src="../resources/images/pie_chart.png" width="150px" height="150px"/></p>
				<p style="font-weight: bold;font-size: 15px">报表设计</p>
			</td>
			<td align="center" width="180px">&nbsp;</td>
		</tr>
		<tr height="46%"><td colspan="9">&nbsp;</td></tr>
	</table>
</body>

</html>
