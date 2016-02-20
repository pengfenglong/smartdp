
(function($){
	$.extend({
		crud:{
			opts:{},
			defaults:{
				title:'',
				datagrid:'smart-datagrid',
				dialog:'smart-dialog',
				form:'smart-form',
				saveUrl:'/smartdp/save.action',
				updateUrl:'',
				delUrl:'/smartdp/delete.action',
				model:'create',
				normalQueryParam:''
			},
			normalQuery:function(value){
				var param = {};
				param[$.crud.opts.normalQueryParam]=value;
				$('#'+$.crud.opts.datagrid).datagrid('load', param);
			},
			init:function(options){
				if(options){
					this.opts=$.extend(this.defaults,options);
				}else{
					this.opts=this.defaults;
				}
			},
			create:function(){
				this.opts.model='create';
				$('#'+this.opts.form).form('clear');
				$('#'+this.opts.dialog).dialog('setTitle', '新增'+this.opts.title).dialog('open');
			},
			edit:function(){
				this.opts.model='update';
				var me=this;
				var rows = $('#'+me.opts.datagrid).datagrid('getSelections');
				if (rows.length==0) {
					$.messager.alert('提示','请选择要修改的对象。');
				}else if(rows.length>1){
					$.messager.alert('提示','请选择一个要修改的对象。');
				}else if(rows.length==1){
					var row = rows[0];
					var newRow={};
					for(var a in row){
						newRow['entity.'+a]=row[a];
					}
					$('#'+me.opts.form).form('load', newRow);
					$('input[name=pids]', '#'+me.opts.form).attr('checked', false);
					$('#'+this.opts.dialog).dialog('setTitle', '修改'+this.opts.title).dialog('open');
					var idField = $('#'+me.opts.datagrid).attr('idField');
					me.opts.updateUrl=me.opts.saveUrl+'?id='+row[idField];
				}
			},
			del:function(){
				var me=this;
				var rows = $('#'+me.opts.datagrid).datagrid('getSelections');
				var idField = $('#'+me.opts.datagrid).attr('idField');
				if (rows.length>0) {
					$.messager.confirm('警告','是否要删除所选的对象？',function(r){
						if (r){
							var ids='';
							for(var i=0;i<rows.length;i++){
								ids += rows[i][idField]+',';
							}
							ids=ids.substring(0,ids.length-1);
							$.post(me.opts.delUrl, {ids:ids}, function(result){
								if (result){
									$('#'+me.opts.datagrid).datagrid('load');
									$('#'+me.opts.datagrid).datagrid('clearSelections');
									$('.datagrid-header-check input').attr('checked',false);
								} else {
									$.messager.show({
										title:'警告',
										msg:result.msg
									});
								}
							});
						}
					});
				}else {
					$.messager.alert('提示','请选择要删除的对象。');
				}
			},
			save:function(){
				var me=this;
				var url;
				if(me.opts.model=='create'){
					url=me.opts.saveUrl;
				}else{
					url=me.opts.updateUrl;
				}
				$('#'+me.opts.form).form('submit', {
					url : url,
					onSubmit : function() {
						return $('#'+me.opts.form).form('validate');
					},
					success : function(data) {
						$('#'+me.opts.dialog).dialog('close');
						$('#'+me.opts.datagrid).datagrid('load');
						$('#'+me.opts.datagrid).datagrid('clearSelections');
						$('.datagrid-header-check input').attr('checked',false);
					}
				});
			},
			close:function(){
				$('#'+this.opts.dialog).dialog('close')
			}
		}
	});
	
	$.fn.extend({
		test:function(options, param){
		}
	}) ;
	
})(jQuery);