<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<html>
	<head>
		<%@ include file="/WEB-INF/page/common/meta.jsp" %>
		<script type="text/javascript">
			function addDatasourceConfig()
			{
				parent.parent.frames["mainFrame"].addTab("新增数据源","${ctx}/rae/addDatasourceConfig.action?date="+new Date().getTime());
			}
			function editDatasourceConfig(id,name)
			{
				parent.parent.frames["mainFrame"].addTab("修改数据源-"+name,"${ctx}/rae/editDatasourceConfig.action?datasourceConfig.id="+id);
			}
			function viewDatasourceConfig(id,name)
			{
				parent.parent.frames["mainFrame"].addTab("查看数据源-"+name,"${ctx}/rae/viewDatasourceConfig.action?datasourceConfig.id="+id);
			}
			
			function pageRefresh(){
				window.location.reload(true);
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
                                url: '${ctx}/rae/deleteDatasourceConfig.action',
                                type : 'POST',
                                data:{"datasourceConfig.id":id},
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
			
			
			$(document).ready(function(){
				$(".button").button();
			});
		</script>
		<title>数据源列表</title>		
	</head>
	<body>
	<div id="del-confirm" style="display:none" title="删除数据源">
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>是否确认删除该数据源？</p>
</div>

<div id="del-message" style="display:none" title="提示">
    <p>
        <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                        删除数据源成功！
    </p>
</div>

		<div style="display: inline;">
			<input type="button" class="button" value="新增" onclick="addDatasourceConfig()"/>
			<input type="button" class="button" value="删除" onclick="batchDelete('${ctx}/rae/batchDeleteDatasourceConfig.action','items',document.forms.ec)"/>
			<input type="button" class="button" value="刷新" onclick="pageRefresh()"/>			
		</div>
		
		<ec:table items='page.result' var="item" method="get" imagePath="${ctx}/resources/rae/images/table/*.gif"
			retrieveRowsCallback="limit" sortRowsCallback="limit" filterRowsCallback="limit"
			action="${ctx}/rae/datasourceConfiglist.action" autoIncludeParameters="true" rowsDisplayed="15">
			<ec:row> 
				<ec:column width="5%" property="选择" title="<input type='checkbox' onclick=\"setAllCheckboxState('items',this.checked)\" >" sortable="false" viewsAllowed="html" filterable="false">
					<div style="text-align: center;"><input type="checkbox" name="datasourceConfig.id" value="${item.id}"/></div>
				</ec:column>
				<ec:column width="13%" property="name"  title="名称"/>
				<ec:column width="10%" property="dsType"  title="类型"/>
				<ec:column width="47%" property="dsProperties"  title="配置"/>
				<ec:column width="10%" property="propertyRegex"  title="分隔符"/>
				<ec:column width="15%" property="操作" title="操作" sortable="false" viewsAllowed="html" filterable="false">
					<a href="javascript:void(0)" onclick="viewDatasourceConfig('${item.id}','${item.name}')">查看</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="editDatasourceConfig('${item.id}','${item.name}')">修改</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="delDatasourceConfig('${item.id}')">删除</a>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
</html>

