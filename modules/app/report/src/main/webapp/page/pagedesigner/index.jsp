<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<html>
<head>
<link type="text/css" href="../../resources/css/pagedesigner.css" rel="stylesheet" />
<script type="text/javascript" src="../../resources/js/chart.js"></script>
<script type="text/javascript" src="../../resources/js/pagedesigner.js"></script>
<script type="text/javascript" src="../../resources/js/widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/framework/plugin/Highcharts-2.2.5/js/highcharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/framework/plugin/Highcharts-2.2.5/js/themes/grid.js"></script>
<title></title>
</head>
<body>
<sm:panel title="报表设计器" fit="true" border="false">
<sm:layoutcontainer>
	<sm:layout region="west" width="160px" border="false" split="true">
		
		<div id="widget">
			<p class="acp-filedset">资源</p>
			<div id="folder" style="height: 200px;">
				<div id="folderTool">
					<img id="new_catalog_btn" title="新建文件夹" src="../../resources/images/Kombine_toolbar_037.png" />
					<img id="new_page_btn" title="新建页面" src="../../resources/images/Kombine_toolbar_019.png" />
					<img id="edit_btn" title="编辑" src="../../resources/Kombine_toolbar_015.png" />
					<img id="del_btn" title="删除" src="../../resources/delete.gif" />
					<img id="authorize_btn" title="授权" src="../../resources/images/paste.gif" />
					<img id="export_btn" title="导出" src="../../resources/images/Kombine_toolbar_020.png" />
				</div>
				<div id="folderTree"></div>	
			</div>
		</div>	
	</sm:layout> 
	<sm:layout region="center" border="false">
		<div id="tool_bar">
			<form action="" id="pageForm" method="post">
				<input type="hidden" id="pageId" name="pageInfo.pageId"/>
				<input type="hidden" id="pageName" name="pageInfo.pageName" />
				<input type="hidden" id="pageCatalog" name="pageInfo.catalogId" />
				<input type="hidden" id="pageDesc" name="pageInfo.pageDesc" />
				<input type="hidden" id="pageInfoJson" name="pageInfoJson" />
				<div>
					<div id="operation_img">
						<span id="new_btn"><img  title="新建" src="../../resources/images/Kombine_toolbar_013.png" />新建</span>
						<span id="save_btn"><img  title="保存" src="../../resources/images/save_edit.gif" />保存</span>
						<span id="save_other_btn"><img  title="另存为" src="../../resources/images/saveas.gif" />另存为</span>
						<span id="preview_btn"><img  title="预览" src="../../resources/images/Kombine_toolbar_022.png" />预览</span>
						<span id="publish_btn"><img  title="发布" src="../../resources/images/Kombine_toolbar_016.png" />发布</span>
						<span id=""><img  title="撤销" src="../../resources/images/undo.gif" />撤销</span>
						<span id=""><img  title="重做" src="../../resources/images/redo.gif" />重做</span>
						<span id="clean_btn"><img  title="清空页面" src="../../resources/images/delete.gif" />清空</span>
					</div>
				
				</div>
				<div id="save_dialog" style="display:none">
					名称:<input id="pageNameInput" style="width:272px" />
					目录:<input id="catalogName" style="width:272px" disabled="true" />
					描述:<textarea cols="36" rows="8"  id="pageDescInput"></textarea>
				</div>
				<div id="chooseDtaservice_dialog" title="选择数据服务" style="display:none">
				    <iframe id="chooseDtaservice" name="chooseDtaservice" src="chooseDataservice.action" frameBorder="0"  width="99.8%" height="99.8%"></iframe>
				</div>
			</form>
		</div>
		<div id="pageContainer">
		</div>
	</sm:layout>
	<sm:layout region="east" width="220px" border="false" split="true">
		<div id="tools">
			<p id="acp-filedset-ctrl" class="acp-filedset">控件列表</p>
			<ul id="ctrlList"></ul>
			<div id="acp-filedset-ctrl-attr" class="acp-filedset">
			    <span >控件属性</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="appButton"><button onclick='WidgetUtil.attrApp()'>应用</button>&nbsp;&nbsp;<button onclick='WidgetUtil.attrClear()'>重置</button></span>
			</div>
			<div id="widget_attr" class="widget-attr">
				<div id="attrDiv">
					<div class="explain2">请点击左侧元素以显示其属性</div>
				</div>
			</div>
		</div>
	</sm:layout>
</sm:layoutcontainer>
</sm:panel>
</body>
</html>
