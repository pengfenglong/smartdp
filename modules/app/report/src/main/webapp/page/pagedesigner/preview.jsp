<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jslog.jsp"%>
<title></title> 
<link type="text/css" href="<%=request.getContextPath()%>/resources/rae/css/preview.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/thrid-party/jquery/plugin/DataTables-1.8.1/media/css/demo_page.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/framework/thrid-party/jquery/plugin/DataTables-1.8.1/media/css/demo_table.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/framework/thrid-party/jquery/plugin/DataTables-1.8.1/media/js/jquery.dataTables.min.js"></script>
	<!--后面要改为加工后的hightcharts
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/rae/js/chart/module/Highcharts-2.1.9/js/highcharts.js"></script>-->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/rae/js/chart/module/Highcharts-2.1.9/js/highcharts.src.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/framework/common/js/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/rae/js/jquery.acp.rae.tool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/rae/js/uirender/jquery.acp.rae.chart.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/rae/js/uirender/jquery.acp.rae.render.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/rae/js/preview.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/rae/js/widget.js"></script>
<script type="text/javascript">
	var pageInfo = eval('${pageInfoJson}');

</script>

</head>
<body>
	<div id="pageContainer"></div>
</body>

</html>
