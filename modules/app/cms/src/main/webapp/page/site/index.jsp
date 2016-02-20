<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
	function gotoChannelarticlePage(){
		var rows = $('#smart-datagrid').datagrid('getChecked');
		if(rows.length==0){
			$.messager.alert('提示','请选择站点。');
		}else if(rows.length>1){
			$.messager.alert('提示','请选择一个站点。');
		}else{
			window.location.href='../channelarticle/index.jsp?siteId='+rows[0].id+'&siteName='+rows[0].siteName;
		}
	}
	function release(){
		$.messager.progress({ 
			title:'请稍后', 
			msg:'站点发布中...' 
		}); 
		var rows = $('#smart-datagrid').datagrid('getChecked');
		var ids = [];
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		$.ajax({
			url:contextPath+'/cms-release-Site.action?siteIds='+ids.join(','),
			success:function(data){
				if(data.success){
					$.messager.progress('close');
					$.messager.alert('提示','站点发布成功。');
				}else{
					$.messager.alert('提示','站点发布失败。');
				}
			}
		});
	}
</script>
<sm:linkbutton onclick="gotoChannelarticlePage();">栏目文章维护</sm:linkbutton>
<sm:linkbutton onclick="release();">发布</sm:linkbutton>
<sm:datagrid title="站点管理" url="cms-list-Site.action" idField="id" autotool="add,edit,delete">
	<sm:datagridcolumn field="siteName" width="1" query="true">名称</sm:datagridcolumn>
	<sm:datagridcolumn field="shortName" width="1" query="true">简短名称</sm:datagridcolumn>
	<sm:datagridcolumn field="template" width="1">模板</sm:datagridcolumn>
	<sm:datagridcolumn field="createDate" width="1">创建时间</sm:datagridcolumn>
	<sm:datagridcolumn field="creator" width="1">创建人</sm:datagridcolumn>
</sm:datagrid>