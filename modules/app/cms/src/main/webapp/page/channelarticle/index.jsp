<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
	var siteId = '1';
	var channelId = null;
	var channelName = null;
	$(function() {
		$.crud.init({
			beforCreate:function(){
				if(channelId == null){
					$.messager.alert('提示','请选择分类。');
					return false;
				}else{
					return true;
				}
			}
		});
	});
	function preview(){
		window.open('http://localhost:8080/smartdp/template/wap/index.html?site=1','newwindow','height=520,width=320,top=50,left=800,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
	}
	$(function(){
		//$('.panel-tool:last').html('<a href="#" style="width:80;font-weight: bolder;" onclick="preview()">微官网预览</a>');
	});
</script>
<sm:layoutcontainer>
	<sm:layout region="west" width="160px" split="true">
		<sm:panel title="分类"  fit="true" border="false">
			<sm:tree id="_tree" param="siteId:siteId" url="cms-tree-Channel.action" onClick="function(node) {
									channelId = node.id;
									channelName = node.text;
									$('#smart-datagrid').datagrid('load', {
										'channelId' : channelId
									})
									}" onContextMenu="function(e, node) {
									e.preventDefault();
									$(this).tree('select', node.target);
									$('#mm').menu('show', {
										left : e.pageX,
										top : e.pageY
									});
								}">
			</sm:tree>
		</sm:panel>
	</sm:layout>
	<sm:layout region="center">
		<sm:datagrid title="文章" url="cms-list-Article.action" idField="id" autotool="add,edit,delete" queryParams="{siteId:siteId}" border="false">
			<sm:datagridcolumn field="title" width="1"  query="true">标题</sm:datagridcolumn>
			<sm:datagridcolumn field="shortTitle" width="1">短标题</sm:datagridcolumn>
			<sm:datagridcolumn field="status" width="1">状态</sm:datagridcolumn>
			<sm:datagridcolumn field="createDate" width="1">创建时间</sm:datagridcolumn>
			<sm:datagridcolumn field="creator" width="1">创建人</sm:datagridcolumn>
		</sm:datagrid>
	</sm:layout>
</sm:layoutcontainer>