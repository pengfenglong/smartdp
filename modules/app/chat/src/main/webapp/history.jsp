<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<style type="text/css">
    .c-label{
        display:inline-block;
        width:100px;
        font-weight:bold;
    }
</style>  
<script type="text/javascript">
	function query(){
		var param = {};
		param['chatUser'] = chat_user_id;
		param['loginUser'] = login_user_id;
		param['date'] = $("#history_date").datebox("getValue");
		$('#history-datagrid').datagrid('load', param);
	}

    var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
        renderRow: function(target, fields, frozen, rowIndex, rowData){
            var cc = [];
            cc.push('<td colspan="' + fields.length + '" style="padding:2px;border:0;font-size:12px;">');
            if (!frozen){    
                cc.push('<div style=\"margin:5px\"><font color=\"#0000FF\">');
                cc.push(getUserByuserCode(rowData.sender).userName+'&nbsp;&nbsp;'+datetime(rowData.createDate));
                cc.push('</font><p style=\"padding:5px 10px\"><font color=\"#333333\">');
                cc.push(rowData.content);
                cc.push('</font></p></div>');           
            }
            cc.push('</td>');
            return cc.join('');
        }
    });
    $(function(){
    	var param = {};
    	param['chatUser'] = chat_user_id;
		param['loginUser'] = login_user_id;
		param['date'] = '';
        $('#history-datagrid').datagrid({
            view: cardview,
            queryParams:param
        });
        $('.datagrid-header').hide()
        //$('#history-datagrid').datagrid('getPanel').panel('header').hide();
    });
</script>
<div style="padding: 2px;"><sm:datebox id="history_date" onSelect="query"></sm:datebox></div>
<table id="history-datagrid" style="width:653px;height:435px" singleSelect="true" fitColumns="false" pageSize="20"
        url="/smartdp/chat-queryHistoryMsg-Chat.action" pagination="true" sortOrder="asc" sortName="createDate" idField="id">
    <thead>
        <tr>
            <th field="sender" width="650"></th>
            <th field="receiver" width="0"></th>
            <th field="content" width="0"></th>
            <th field="createDate" width="0"></th>
        </tr>
    </thead>
</table>
