<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
	function queryFans(){
		var param = {};
		param.nick_name = $('#nick_name_input').val();
		param.wxId = $('#roleCombogrid').combobox('getValue');
		$('#fans-datagrid').datagrid('load', param);
	}
	var fanId = '';
	function sendMsg(){
		if(fanId == ''){
			alert('请选择发消息的对象');
		}else if($('textarea').val() == ''){
			alert('发送的内容不能为空');
		}else{
			$.ajax({
				url:contextPath + '/weixin-sendMsg-Member.action',
				data:{
					fanId:fanId,
					wxId:$('#roleCombogrid').combobox('getValue'),
					content:$('textarea').val()
					
				},
				success:function(data){
					alert('success');
				}
			});
		}
	}
</script>
<sm:layoutcontainer>
	<sm:layout region="west" width="235px" border="false" split="true">
		<div id="tb">
			<div>
				微信账号：<sm:combobox id="roleCombogrid" url="weixin-listAll-Member.action"
					valueField="id" textField="weixinloginAccount" onSelect="function(){
						$('#nick_name_input').val('');
						queryFans();
					}" 
					onLoadSuccess="function () {
	                            var data = $(this).combobox('getData');
	                            if (data.length > 0) {
	                                $(this).combobox('select', data[0].id);
	                            }
	                        }">
				</sm:combobox>
			</div>
			<div>
			粉丝名称：<input id="nick_name_input" style="width: 85px; height: 23px;" /><a
				class="easyui-linkbutton" style="margin: 5px;" href="#" onclick="queryFans();">查询</a>
			</div>
		</div>
		<sm:datagrid id="fans-datagrid" title="微信粉丝列表(0)" url="weixin-listFans-Member.action" selectOnCheck="true"
			idField="id" pagination="false" query="false" toolbar="#tb" onSelect="function(index,data){
				$('#dialogue_panel').panel('setTitle','与   '+data.nick_name+'  聊天中...');
				fanId = data.id;
			}" onLoadSuccess="function(){
				$(this).datagrid('getPanel').panel('setTitle','微信粉丝列表('+$('.datagrid-row').length+')');
			}">
			<sm:datagridcolumn field="nick_name" width="1">粉丝名称</sm:datagridcolumn>
		</sm:datagrid>
	</sm:layout>
	<sm:layout region="center" border="false">
		<sm:panel id="dialogue_panel" title="聊天">
			<sm:tabs>
				<sm:tab title="文字">
					<textarea style="width:100%;height: 180px;"></textarea>
				</sm:tab>
				<sm:tab title="图片">
				bbbbb
				</sm:tab>
				<sm:tab title="语音">
				ccccc
				</sm:tab>
				<sm:tab title="视频">
				ddddd
				</sm:tab>
				<sm:tab title="图文消息">
				eeeee
				</sm:tab>
			</sm:tabs>
		</sm:panel>
		<a class="easyui-linkbutton" style="margin: 10px 5px;" href="#" onclick="sendMsg();">发送</a>
	</sm:layout>
</sm:layoutcontainer>
