<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<html>
	<head>
		<%@ include file="/WEB-INF/page/common/meta.jsp" %>
		<title>数据服务列表</title>
		<script type="text/javascript">
		function pageRefresh(){
            window.location.reload(true);
        }
		
		function addDataService(){
			top.frames["mainFrame"].addTab("新增数据服务","${ctx}/rae/dataservice_first.action");
		}
		
		function editDataService(id,name)
        {
			top.frames["mainFrame"].addTab("修改数据服务-"+name,"${ctx}/rae/viewDataservice.action?dataService.id="+id);
        }
		
		function delDatasourceConfig(id){
            $( "#del-confirm" ).dialog({
                resizable: false,
                height:140,
                modal: true,
                buttons: {
                    "确认": function() {
                        var me = this;
                        $.ajax({
                            url: '${ctx}/rae/deleteDataService.action',
                            type : 'POST',
                            data:{"dataService.id":id},
                            cache: false,
                            timeout : 30000,
                            success: function(data) 
                            {
                                $( "#del-message" ).dialog({
                                    modal: true,
                                    buttons: {
                                        Ok: function() {
                                            window.location.reload(true);
                                            $( this ).dialog( "close" );
                                            
                                        }
                                    }
                                });
                                $( me ).dialog( "close" );
                            },
                            error : function(jqXHR, textStatus, errorThrown){
                                $( this ).dialog( "close" );
                            }
                        });
                        
                    },
                    "取消": function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        }
		
		$(function(){
	        
	        $(".button").button();
	        
	    });
		</script>
	</head>
	
	<body>
	<div id="del-confirm" style="display:none" title="删除数据数据服务">
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>是否确认删除该数据服务？</p>
</div>
<div id="del-message" style="display:none" title="提示">
    <p>
        <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                        删除数据服务成功！
    </p>
</div>
	<form action="" method="get" style="display: inline;">
		<input type="button"  value="新增" class="button" onclick="addDataService()"/>
		<input type="button"  value="删除" class="button" onclick=""/>
		<input type="button" class="button" value="刷新" onclick="pageRefresh()"/>
	</form>
	
	<ec:table items='page.result' var="item" method="get" imagePath="${ctx}/resources/rae/images/table/*.gif"
		retrieveRowsCallback="limit" sortRowsCallback="limit" filterRowsCallback="limit" 
		action="${ctx}/rae/listDataservice.action" rowsDisplayed="15">
		<ec:row>
			<ec:column width="3%" property="选择" title="<input type='checkbox' onclick=\"setAllCheckboxState('items',this.checked)\" >" sortable="false" viewsAllowed="html" filterable="false">
				<div style="text-align: center;"><input type="checkbox" name="items" value="id=${item.id}"/></div>
			</ec:column>
			<ec:column property="name"  title="名称"/>
			<ec:column property="description"  title="描述"/>
			<ec:column property="操作" title="操作" sortable="false" viewsAllowed="html" filterable="false" width="12%">
				<a href="javascript:void(0)">查看</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="editDataService('${item.id}','${item.name}')">修改</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="delDatasourceConfig('${item.id}')">删除</a>
			</ec:column>
		</ec:row>
	</ec:table>
	</body>
</html>
