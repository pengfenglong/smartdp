$(function() {
	$('body')
			.append(
					'<div id="smartdp-window" class="easyui-dialog" closed="true"></div>');
	$('body')
			.append(
					'<div id="smartdp-note" style="position:absolute;width:70px;padding:3px;background:#6FB7B7;border:1px solid #6FB7B7;left:40%;z-index:9999;display:none;"></div>');
	$.crud.init();
	$('a[type=button]').appendTo($('#operate_btn'));
});

(function($) {
	$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null){
			return unescape(r[2]);
		}
		return null;
	}
})(jQuery);

(function($){
	$.extend({
		crud:{
			opts:{},
			defaults:{
				title:'',
				datagrid:'smart-datagrid',
				treegrid:'smart-treegrid',
				dialog:'smart-dialog',
				form:'smart-form',
				saveUrl:'',
				updateUrl:'',
				delUrl:'',
				model:'create',
				normalQueryParam:'',
				beforCreate:function(){return true},
				beforSave:function(){return true},
				onLoadCreate:function(){},
				onLaod:function(data){}
				//onSuccess:function(){}
			},
			columnFormat:function(val,row,index){ 
				var valR = val.split('-');
				var type = valR[0];
				var key = valR[1];
				for(var i=0,n=dictJson.length;i<n;i++){
					if(dictJson[i].nameEn == type){
						var dicts = dictJson[i].dicts;
						for(var j=0,m=dicts.length;j<m;j++){
							if(dicts[j].nameEn == key){
								return dicts[j].name;
							}
						}
					}
				}
				return val;
			},
			normalQuery:function(value){
				var param = {};
				param[$.crud.opts.normalQueryParam]=value;
				$('#'+$.crud.opts.datagrid).datagrid('load', param);
			},
			query:function(){
				var param = {};
				var filters = $("[name^=filter_]");
				for(var i=0,n=filters.length;i<n;i++){
					param[$(filters[i]).attr('name')] = $(filters[i]).val();
				}
				if($('#'+this.opts.datagrid).length > 0){
					$('#'+$.crud.opts.datagrid).datagrid('load', param);
				}else if($('#'+this.opts.treegrid).length > 0){
					//alert(JSON.stringify($('#'+this.opts.treegrid).treegrid('getData')));
				}
				
			},
			init:function(options){
				if(options){
					this.opts=$.extend(this.defaults,options);
				}else{
					this.opts=this.defaults;
				}
			},
			create:function(title,href){
				if(!this.opts.beforCreate()){
					return;
				}
				this.createWindow(title,href);
				this.opts.model='create';
				//$('#'+this.opts.form).form('clear');
				//$('#'+this.opts.dialog).dialog('setTitle', '新增'+this.opts.title).dialog('open');
			},
			onLaodEdit:function(){
				var me=this;
				var row;
				if($('#'+me.opts.datagrid).length > 0){
					row = $('#'+me.opts.datagrid).datagrid('getSelected');
					$('#'+me.opts.form).form('load', row);
					$('input[name=pids]', '#'+me.opts.form).attr('checked', false);
					var idField = $('#'+me.opts.datagrid).attr('idField');
					$('#'+me.opts.form).attr('action',$('#'+me.opts.form).attr('action')+'?id='+row[idField]);
					me.opts.onLaodEdit(row);
				}else{
					row = $('#'+me.opts.treegrid).treegrid('getSelected');
					$('#'+me.opts.form).form('load', row);
					var idField = $('#'+me.opts.treegrid).attr('idField');
					$('#'+me.opts.form).attr('action',$('#'+me.opts.form).attr('action')+'?id='+row[idField]);
					me.opts.onLaodEdit(row);
				}
			   
			},
			edit:function(title,href){
				var me=this;
				this.opts.model='update';
				var rows ;
				if($('#'+me.opts.datagrid).length > 0){
					rows = $('#'+me.opts.datagrid).datagrid('getSelections');
				}else{
					rows = $('#'+me.opts.treegrid).treegrid('getSelections');
				}
				if (rows.length==0) {
					$.messager.alert('提示','请选择要修改的对象。');
				}else if(rows.length>1){
					$.messager.alert('提示','请选择一个要修改的对象。');
				}else if(rows.length==1){
					this.createWindow(title,href);
				}
			},
			del:function(url){
				var me=this;
				if($('#'+this.opts.datagrid).length > 0){
					var rows = $('#'+me.opts.datagrid).datagrid('getChecked');
					var idField = $('#'+me.opts.datagrid).attr('idField');
					if (rows.length>0) {
						$.messager.confirm('警告','是否要删除所选的对象？',function(r){
							if (r){
								var ids='';
								for(var i=0;i<rows.length;i++){
									ids += rows[i][idField]+',';
								}
								ids=ids.substring(0,ids.length-1);
								$.post(contextPath+'/'+url, {ids:ids}, function(result){
									if (result.success){
										$('#'+me.opts.datagrid).datagrid('load');
										$('#'+me.opts.datagrid).datagrid('clearSelections');
										$('#'+me.opts.datagrid).datagrid('uncheckAll');
										$('.datagrid-header-check input').attr('checked',false);
										$.crud.createNote('删除成功');
									} else {
										if(typeof(result) == 'string'){
											var n = result.indexOf('}');
											result = result.substring(0,n+1);
											result = eval('(' + result + ')');
										}
										$.messager.alert('提示',result.msg);
									}
								});
							}
						});
					}else {
						$.messager.alert('提示','请选择要删除的对象。');
					}
				}else if($('#'+me.opts.treegrid).length > 0){
					var node = $('#'+me.opts.treegrid).treegrid('getSelected');
					var idField = $('#'+me.opts.treegrid).attr('idField');
					if(node){
						$.messager.confirm('警告','是否要删除所选的对象？',function(r){
							$.post(contextPath+'/'+url, {id:node[idField]}, function(result){
								if (result.success){
									$('#'+me.opts.treegrid).treegrid('remove',node[idField]);
									$.crud.createNote('删除成功');
								} else {
									$.messager.alert('提示',result.msg);
								}
							});
						});
						
					}else{
						$.messager.alert('提示','请选择要删除的对象。');
					}
					
				}
			},
			saveSuccessCallback:function(data){
				if(data.success){
					$.crud.close();
					if(this.opts.onSuccess){
						this.opts.onSuccess();
					}else{
						$.crud.createNote('保存成功');
						if($('#'+this.opts.datagrid).length > 0){
							$('#'+this.opts.datagrid).datagrid('load');
							$('#'+this.opts.datagrid).datagrid('clearSelections');
							$('#'+this.opts.datagrid).datagrid('uncheckAll');
							$('.datagrid-header-check input').attr('checked',false);
						}else if($('#'+this.opts.treegrid).length > 0){
							var node = $('#'+this.opts.treegrid).treegrid('getSelected');
							if(node){
								//$('#'+this.opts.treegrid).treegrid('expandAll', node.target);
								$('#'+this.opts.treegrid).treegrid('reload', node.target);
							}else{
								$('#'+this.opts.treegrid).treegrid('reload');
							}
							
						}
					}
					
				}else{
					$.messager.alert('提示',data.msg);
					
				}
				
			},
			save:function(){
				var me=this;
				var url;
				var flag = me.opts.beforSave();
				if(!flag){
					return;
				}
				if(me.opts.model=='create'){
					url=me.opts.saveUrl;
				}else{
					url=me.opts.updateUrl;
				}
				$('#btn_sub').click();
			},
			close:function(){
				//$('.validatebox-tip').remove(); 
				$("#smartdp-window").dialog("close");
			},
			createWindow:function(title, href, width, height, modal, minimizable, maximizable){
				var me=this;
				var win = $('#smartdp-window').dialog({
					title : title,
					height: height === undefined ? $(window).height() : height,
					width: width === undefined ? $(window).width() : width,
					href : href === undefined ? 'form.jsp' : href,
					modal : modal === undefined ? true : modal,
					minimizable : minimizable === undefined ? false : minimizable,
					maximizable : maximizable === undefined ? false : maximizable,
					shadow : true,
					cache : false,
					closed : false,
					closable : false,
					collapsible : false,
					draggable : false,
					resizable : false,
					loadingMessage : '正在加载数据，请稍等片刻......',
					onLoad:function(){
						var n = $('.form').length;
						var _width =  width;
				        var _height = height;
						if(width === undefined){
							_width =  $('#'+me.opts.form).width()+50;
						}
						if(height === undefined){
							_height = ($('.form').height()+3)*n+220;
						}
						var _top = ($(window).height()-_height)*0.5;
						var _left = ($(window).width()-_width)*0.5;
						//$('#smartdp-window').dialog('resize',{width:_width,left:_left});
						if(me.opts.model=='create'){
							me.opts.onLoadCreate();
						}else if(me.opts.model=='update'){
							$.crud.onLaodEdit();
						}
					},
					buttons : [{
						id:'btn_sub',
						text:'保存',
						handler:function(){
							$.crud.save();
						}
					},{
						text:'取消',
						handler:function(){
							$.crud.close();
						}
					}]
				});
				return win;
			},
			createNote : function(msg) {
				if (!$('#smartdp-note').is(':visible')) {
					$('#smartdp-note').css({
						display : 'block',
						top : '-33px'
					}).animate({
						top : '+33'
					}, 200, function() {
						$('#smartdp-note').text(msg);
						setTimeout(function() {
							$('#smartdp-note').animate({
								top : '0'
							}, 200, function() {
								$(this).css({
									display : 'none',
									top : '-100px'
								});
							});
						}, 2000);
					});
				}
			}
		}
	});
})(jQuery);