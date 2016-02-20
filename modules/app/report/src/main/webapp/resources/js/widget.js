var drawChartMap = [];
var currentWidgetContentId = null;
var WidgetUtil = {
		
		/**
		 * 创建部件
		 * @param defaultSize 初始化大小 格式{widthScale:'',heightScale:''} 或者{width:'',height:''} 
		 * @returns {String}
		 */
		createWidget : function(defaultSize, flag, paramModel){
			var defaultWidth = '32.5%';
			var defaultHeight = '38%';
			if(defaultSize.width){
				//编辑时的宽大小defaultHeight
				defaultWidth = Number(defaultSize.width)*100 + '%';
				defaultHeight = Number(defaultSize.height)*100 + '%';
			}else{
				//创建时默认的大小
				
				if('2' == defaultSize.widthScale){
					defaultWidth = '65.5%';
				}else if('3' == defaultSize.widthScale){
					defaultWidth = '98.5%';
				}
				
				if('2' == defaultSize.heightScale){
					defaultHeight = '60%';
				}else if('3' == defaultSize.heightScale){
					defaultHeight = '98.5%';
				}
			}

			var generateId =  this.createUUID();
			
			var widgetHTML = ''+
			'<div class="widget-layout" id="w-' +generateId+ '"style="width:' + defaultWidth + ';height:'+defaultHeight+'">'+
			'	<div class="widget-layout-toolbar">'+
			'		<button class="widget-del">x</button>'+
			'	</div>'+
			'	<div class="widget-layout-content" id="'+ generateId +'">'+
			'	</div>'+
			'	<div style="clear: both;"></div>'+
			'</div>';
			$(widgetHTML).appendTo($('#pageContainer'));
			
			$('#' + generateId).bind("mousedown",(function(event){
				WidgetUtil.loadAttr($(this).parent());
				//切换到属性页签
				$('#tools').find('a[href=#widget]').click();
				return false;
			}));
			
			//绑定删除部件事件
			$('.widget-layout-toolbar > .widget-del').click(function(){
				$(this).parent().parent().remove();
			});
			
			//绑定改变大小的能力
			$('#w-' + generateId).resizable();
			
			//渲染图形
			var drawChartObj = this.drawChart(flag, generateId, paramModel);
			
			//将图形信息保存在map中
			drawChartMap[generateId] = drawChartObj;
		},
		/**
		 * 加载属性
		 */
		loadAttr : function(o){
			var widgetContentId = $(o).find(".widget-layout-content").attr("id");
			if (currentWidgetContentId == widgetContentId)
			{
				return;
			}
			currentWidgetContentId = widgetContentId;
			$(o).parent().children(".widget-layout").css("border-color", "#ccc").end().end().css("border-color", "red");
			var drawChart = drawChartMap[currentWidgetContentId];
			drawChart.loadProperties(function(data){
				var $attrdiv = $(data);
				$attrdiv.find("legend").bind("click", function(){
					var $hide = $(this).attr("hide");
					if (!$hide || $hide==0){
						$(this).attr("hide",1);
						$(this).parent("fieldSet").children().not("legend").hide();
					}
					else{
						$(this).attr("hide",0);
						$(this).parent("fieldSet").children().show();
					}
					
				});
				$("#attrDiv").html($attrdiv);
				$attrdiv.find("input.color-picker").parent("td").css("text-align", "left").end().css("width","50%").miniColors({
					letterCase: 'uppercase'
				});
				
				$attrdiv.find("select[multiple='true']")
				.multiselect({minWidth:140})
				.end()
				.find("select[multiple='false']")
				.multiselect({
					multiple: false,
					minWidth:140,
					header: "请选择",
					noneSelectedText: "请选择",
					selectedList: 1});
				if ($("#widget_attr").css("display") == 'none')
				{
					$("#ctrlList").css("height", "30%").animate({height : 'show'});
					$("#widget_attr").css("height", "54%").show();
				}
				var paramModel = drawChart.paramObject;
				var drawChartAttrs = paramModel.getCtrlAttr();
				if (drawChartAttrs){
					var drawChartAttr = drawChartAttrs.split("&");
					for (var i = 0; i < drawChartAttr.length; i++)
					{
						var map = drawChartAttr[i].split("=");
						$("#" + map[0]).val(map[1]);
					}
				}
				var dataService = paramModel.getDataService();
				if (dataService)
				{
					var drawChartDataService = dataService.split("&");
					var dataServiceJson = [];
					for (var i = 0; i < drawChartDataService.length; i++)
					{
						var map = drawChartDataService[i].split("=");
						var key = map[0];
						var value = map[1];
						dataServiceJson[key] = value;
					}
					var dataserviceId = dataServiceJson["dataserviceName"];
					$("#dataserviceName").val(dataserviceId);
					WidgetUtil._getFieldMappingByDataserviceId(dataserviceId, function(){
						for (var i = 0; i < drawChartDataService.length; i++)
						{
							var map = drawChartDataService[i].split("=");
							var key = map[0];
							var value = map[1];
							$("#"+key+" option[value="+value+"]").attr("selected", "selected");
						}
						$("#attrDiv").find("select").multiselect("refresh");
					});
				}
				
			});
		},
		/**
		 * 生成图表
		 * @param flag 为edit时查询模拟数据，为view时查询真实数据
		 * @param divId 要渲染的DIV的ID
		 * @param paramModel 参数模型
		 * @see jquery.acp.rae.render.js#Acp.render.paramModel
		 */
		drawChart:function(flag, divId, param){
			param.chart.renderTo = divId;
			var drawChart = new Highcharts.Chart(param);
			return drawChart;
		},	
		
		/**
		 * 创建部件布局位置
		 * @param defaultSize 初始化大小
		 * @returns {String}
		 */		
		createWidgetLayout:function(defaultSize){
			var defaultWidth = '32.5%';
			var defaultHeight = '38%';
				
			if('2' == defaultSize.widthScale){
				defaultWidth = '65.5%';
			}else if('3' == defaultSize.widthScale){
				defaultWidth = '98.5%';
			}
			
			if('2' == defaultSize.heightScale){
				defaultHeight = '60%';
			}else if('3' == defaultSize.heightScale){
				defaultHeight = '98.5%';
			}
			
			var widgetHTML = ''+
			'<div class="widget-position" style="width:' + defaultWidth + ';height:'+defaultHeight+'">'+
			'</div>';
			return widgetHTML;
		},

		/**
		 * 加载部件信息
		 * @param pageInfo
		 */
		loadWidget:function(pageInfo){
			$('#pageContainer').html('');
			for(var i = 0 ; i < pageInfo.length ; i++){
				var defaultSize = {};
				defaultSize.width = pageInfo[i].widgetWidth;
				defaultSize.height = pageInfo[i].widgetHeight;
				
				var attrAndValueJson;
				if(typeof(pageInfo[i].attrAndValueJson) == 'string'){
					attrAndValueJson = eval('('+pageInfo[i].attrAndValueJson+')');
				}else{
					attrAndValueJson = pageInfo[i].attrAndValueJson;
				}
				var paramModel = Acp.render.paramModel.toSelf(attrAndValueJson);
				this.createWidget(defaultSize, 'view', paramModel);
			}
		},
		/**
		 * 显示部件
		 * @param pageInfo 部件信息，格式为[{"widgetIndex":"",'"widgetWidth":"","widgetHeight":"","attrAndValueJson":""},...];
		 */
		showWidgetToBody:function(pageInfo){
			if (!pageInfo || !pageInfo.length > 0) return; 
			$('#pageContainer').html('');
			for(var i = 0 ; i < pageInfo.length ; i++){
				var generateId = this.createUUID();
				$('<div>').attr('index', 'D_PAGE_WIDGET_'+pageInfo[i].widgetIndex)
				.attr('id',generateId)
		        .width(pageInfo[i].widgetWidth * $('body').width())
		        .height(pageInfo[i].widgetHeight * $('body').height())
		        .addClass('widget-layout-preview')
		        .appendTo($('#pageContainer'));
				var attrAndValueJson;
				if(typeof(pageInfo[i].attrAndValueJson) == 'string'){
					attrAndValueJson = eval('('+pageInfo[i].attrAndValueJson+')');
				}else{
					attrAndValueJson = pageInfo[i].attrAndValueJson;
				}
				var paramModel = Acp.render.paramModel.toSelf(attrAndValueJson);
				this.drawChart('view', generateId, paramModel);
			}
		},
		
		/**
		 * @returns {String}
		 */
		getPageInfoJson:function(){
			var pageInfoJson = '[';
			$('#pageContainer .widget-layout').each(function(index){
				var divID = $(this).attr('id').substring(2,$(this).attr('id').length);
				var attrAndValueJson = drawChartMap[divID].paramObject.toString();
				pageInfoJson += '{"widgetIndex":"'+index+'",'+
								  '"widgetWidth":"'+($(this).width())/($('#pageContainer').width())+'",'+
								  '"widgetHeight":"'+($(this).height())/($('#pageContainer').height())+'",'+
								  '"attrAndValueJson":'+attrAndValueJson+'},';
			});
			
			if($('#pageContainer .widget-layout').length > 0){
				pageInfoJson = pageInfoJson.substring(0,pageInfoJson.length-1);
			}
			pageInfoJson += ']';
			return pageInfoJson;
		},
		/**
		 * 图表属性清除
		 */
		attrClear : function(){
			$("#attrDiv form").each(function(){
				$(this)[0].reset();
			});
		},
		/**
		 * 图表属性应用
		 */
		attrApp:function(){
			if (currentWidgetContentId == null)
			{
				alert("请点击左侧元素区域以显示其属性，修改相应属性后应用。");
			}
			var drawChart = drawChartMap[currentWidgetContentId];
			var paramModel = drawChart.paramObject;
			paramModel.setCtrlAttr(decodeURIComponent($("#attrDiv #attrs").serialize()));
			paramModel.setDataService(decodeURIComponent($("#attrDiv #dataservice").serialize()));
			drawChart.update(paramModel.toJson());
		},
		/**
		 * 选择数据服务
		 */
		chooseDataservice:function(){
			$("#chooseDtaservice_dialog").dialog({
				width:'600',
				height:'430',
				position: 'center',
				draggable:true,
				resizable:false,
				modal: true,
				buttons: {
					"确定": function() {
						var me = this;
						var $radio = $("#chooseDtaservice").contents().find("input:radio:checked");
						var size = $radio.size();
						if (size == 0){
							alert('请选择数据服务。');
							return;
						}
						var dataserviceId = $radio.val();
						$("#dataserviceName").val(dataserviceId);
						WidgetUtil._getFieldMappingByDataserviceId(dataserviceId, function(){
							$( me ).dialog( "close" );
						});
						
					},
					"取消": function() {
						$( this ).dialog( "close" );
					}
				}
			});
		},
		
		_getFieldMappingByDataserviceId:function(dataserviceId, callback){
			$.ajax({
				 url:'getFieldMappingsByDataservice.action',
				 data:{"dataServiceId" : dataserviceId},
				 dataType:'json',
				 type:'POST',
				 error:function(jqXHR, textStatus, errorThrown){
					 
				 },
				 success:function(data, textStatus, jqXHR){
					 if (data)
					 {
						 var $selects = $("#attrDiv").find("select");
						 for (var key in data){
							 var opt = $("<option />", {
								 value : key,
								 text : data[key]
							 });
							 $selects.append(opt);
						 }
						 $selects.multiselect("refresh").multiselect("enable");
					 }
					 if (callback && $.isFunction(callback))
					 {
						 callback();
					 }
					 
				 }
			 });
		},
		
		/**
		 * 生成UUID
		 */
		createUUID:function(){
		    d   =   new   Date(); 
		    s= ""; 
		    s   +=   d.getHours().toString(); 
		    s   +=   d.getMinutes().toString(); 
		    s   +=   d.getSeconds().toString(); 
		    s   +=   d.getMilliseconds().toString(); 
		    s   +=   Math.floor(Math.random()*1000).toString(); 
		    return s;
		}
}