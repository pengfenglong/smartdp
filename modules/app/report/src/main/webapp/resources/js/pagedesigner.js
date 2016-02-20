	
	var currentCatalogId = '';
	var currentCatalogName = '';
	var selectNodeObject = null;
	 /**
	  * 加载页面
	  */
	 function loadPage(pageId){
		 $('#pageContainer').html('<p style="font-size: 16px;margin-top: 200px" align="center" >加载中....</p>');
		 //页面id
		 $.ajax({
			 url:'pageLoad.action',
			 dataType:'json',
			 data:{pageInfoId:pageId},
			 error:function(jqXHR, textStatus, errorThrown){
				 
			 },
			 success:function(data, textStatus, jqXHR){
				 //alert(JSON.stringify(data));
				 WidgetUtil.loadWidget(data);
			 }
			 
		 });
	 }
	$(function() {

		 $.ajaxSetup({cache:false});
		 /**
		  * 加载文件夹树
		  */

		$("#acp-filedset-ctrl").click(function(event){
			if ($(event.target).is("P"))
			{
				$("#ctrlList").css("height", "30%").animate({height : 'show'});
				$("#widget_attr").css("height", "54%").show();
			}
			return false;
		});
		$("#acp-filedset-ctrl-attr").click(function(event){
			if ($(event.target).is("DIV"))
			{
				$("#ctrlList").animate({height : 'hide'});
				$("#widget_attr").css("height", "84%").animate({height : 'show'});
			}
	        return false;
		});
		//加载组件
		 $.ajax({
			 url:contextPath+'/report-list-Template.action',
			 dataType:'json',
			 success:function(data, textStatus, jqXHR){
				 for(var i=0;i<data.length;i++){
						var $ctrl = $('<li ></li>');
						for (var key in data[i])
						{
							$ctrl.attr(key, data[i][key]);
						}
						var $img = $('<img style="cursor:hand;"/>');
						$img.attr('src', contextPath + '/component/report/resources/images/' + data[i].icon);
						$img.attr('title', data[i].name);
						$img.css('width', '48px');
						$img.css('height', '48px');
						$ctrl.append($img);
						$("#ctrlList").append($ctrl);
						
				 }
				 
				//增加拖拽事件
					$("#ctrlList li").draggable({
						proxy:'clone',
						revert:true,
						onStartDrag:function(e){
							//初始化大小
							var defaultSize = {};
							var widthScale = $(this).attr('widthscale');
							var heightScale = $(this).attr('heightscale');
							defaultSize.widthScale = ''+widthScale;
							defaultSize.heightScale = ''+heightScale;
							$('#pageContainer').append(WidgetUtil.createWidgetLayout(defaultSize));
						},
						onStopDrag:function(e){
							$('.widget-position').remove();
							
							//初始化大小
							var defaultSize = {};
							var widthScale = $(this).attr('widthscale');
							var heightScale = $(this).attr('heightscale');
							defaultSize.widthScale = ''+widthScale;
							defaultSize.heightScale = ''+heightScale;
							//图表类型
							//var charType = $(ui.draggable).attr('type');
							var ctrlId = $(this).attr("id");
							var ctrlType = $(this).attr("type");
							var paramModel = {};
							paramModel.ctrlId = ctrlId;
							paramModel.ctrlType = ctrlType;
							var param = eval('('+$(this).attr('configuration')+')');
							WidgetUtil.createWidget(defaultSize, "new", param);
						}
						
					});
			 }
			 
		 });

		/**
		 * 预览
		 */
		$('#preview_btn').click(function(){
			var pageInfoJson = WidgetUtil.getPageInfoJson();
			$('#pageForm').attr('action','pagePreview.action');
			$('#pageForm').attr('method','POST');
			$('#pageInfoJson').val(pageInfoJson);
			$('#pageForm').attr('target','_blank');
			$('#pageForm').submit();
		});
		
		/**
		 * 保存
		 */
		$('#save_btn').click(function(){
			if(currentCatalogId == ''){
				alert('请先选择一个文件夹。');
				return;
			}else{
				$('#catalogName').val(currentCatalogName);
			}
			$("#save_dialog").dialog({
				width:'345',
				height:'280',
				position: 'center',
				draggable:true,
				resizable:false,
				modal: true,
				buttons: {
					"确定": function() {
						var pageInfoJson = WidgetUtil.getPageInfoJson();
//						$('#pageForm').attr('action','pageSave.action');
//						$('#pageName').val($('#pageNameInput').val());
//						$('#pageCatalog').val($('#pageCatalogSelect').val());
//						$('#pageDesc').val($('#pageDescInput').val());
//						$('#pageInfoJson').val(pageInfoJson);
//						$('#pageForm').submit();
						 $.ajax({
							 url:'pageSave.action',
							 dataType:'text',
							 data:{
								 'pageInfo.pageId':$('#pageId').val(),
								 'pageInfo.pageName':$('#pageNameInput').val(),
								 'pageInfo.catalogId':currentCatalogId,
								 'pageInfo.pageDesc':$('#pageDescInput').val(),
								 'pageInfoJson':pageInfoJson
							 },
							 type:'POST',
							 error:function(jqXHR, textStatus, errorThrown){
								 
							 },
							 success:function(data, textStatus, jqXHR){
								
								 alert("保存成功");
								 //切换到页面列表页面								 
								 toolTabs.tabs('select',1);
								 $jstree = $.jstree._focused();
								 $jstree.refresh();
							 }
							 
						 });
						
						$( this ).dialog( "close" );
					},
					"取消": function() {
						$( this ).dialog( "close" );
					}
				}
			});
//			var pageInfoJson = WidgetUtil.getPageInfoJson();
//			$('#pageForm').attr('action','save.action');
//			$('#pageInfoJson').val(pageInfoJson);
//			$('#pageForm').submit();
			
		});
		/**
		 * 新建
		 */
		$('#new_btn').click(function(){
			window.location.href = "pageDesignerHome.action";
		});
		
		$('#new_page_btn').click(function(){
			window.location.href = "pageDesignerHome.action";
		});
		
		/**
		 * 发布
		 */
		$('#save_other_btn').click(function(){
			
			alert("开发中.....");
			
		});
		
		/**
		 * 发布
		 */
		$('#publish_btn').click(function(){
			alert("开发中.....");
			
		});
		
		$('#clean_btn').click(function(){
			var result = window.confirm("要清空页面吗？");
			if(result){
				 $('#pageContainer').html('');
				 drawChartMap = [];
			}

		});
		
		$('#refresh_btn').click(function(){
			$('#folderTree').jstree('refresh',-1);
		});
		
		
		$('#new_catalog_btn').click(function(){
			if(selectNodeObject.attr('rel') == 'page'){
				alert('页面下不能新建文件夹。');
			}else{
				$.jstree._focused().create(selectNodeObject);
			}
		});
		$('#edit_btn').click(function(){
			if(selectNodeObject.attr('rel') == 'page'){
				alert('只能编辑文件夹。');
			}else{
				$.jstree._focused().rename(selectNodeObject);
			}
		});
		$('#del_btn').click(function(){
			var isSure = false;
			if(selectNodeObject.attr('rel') == 'catalog'){
				isSure = window.confirm("确定要删除该文件夹吗?");
			}else if(selectNodeObject.attr('rel') == 'page'){
				isSure = window.confirm("确定要删除该页面吗?");
			}
			if(isSure){
				$.jstree._focused().remove(selectNodeObject);
			}
		});
		$('#authorize_btn').click(function(){
			alert('开发中......');
		});
		$('#export_btn').click(function(){
			alert('开发中......');
		});
	});
	
	