$(function() {
	var dataSourceId = $('#dataService_datasourceConfig_id').val();
	Sqlbuilder.buildTablesByDataresource(dataSourceId);
	Sqlbuilder.buildSqlGeneHTML();
	$('#dataService_datasourceConfig_id').change(function(){
		var result = window.confirm('切换数据源将清空设计器，是否继续?');
		if(result){
			var dataSourceId_ = $('#dataService_datasourceConfig_id').val();
			Sqlbuilder.cleanHTML();
			dataSourceId = dataSourceId_;
			Sqlbuilder.buildTablesByDataresource(dataSourceId_);
			
		}else{
			$('#dataService_datasourceConfig_id').val(dataSourceId);
		}
	});
	$('#_tabs').tabs({
		onSelect:function(title){
			if(title == 'SQL'){
				$('#executeSql').val(Sqlbuilder.buildSQL());
			}
		}
	});
});

Sqlbuilder = {
	
	/**
	 * 下拉选择赋值
	 * @returns {String}
	 */
	buildDropDownList : function(){
		
		//字段
		var fielddataitems = '';
		$('.table_div').each(function(){
			var tableName = $(this).find('.tableName').text();
			$(this).find('.columnName').each(function(){
				var columnName = $(this).text();
				var fieldName = tableName + '.' + columnName;
				fielddataitems += '{text:"';
				fielddataitems += fieldName;
				fielddataitems += '",value:"';
				fielddataitems += fieldName;
				fielddataitems += '"},';
			});

		});
		fielddataitems = fielddataitems.substring(0,fielddataitems.length - 1);
		$('#fieldName_list').attr('dataitems',fielddataitems);
		$('#calcfieldName_list').attr('dataitems',fielddataitems);
		$('#conditionFieldName_list').attr('dataitems',fielddataitems);
		$('#groupFieldName_list').attr('dataitems',fielddataitems);
		$('#sortFieldName_list').attr('dataitems',fielddataitems);
		
		//函数
		var funcdataitems = '';
		var funcArr = Sqlbuilder.getFuncByDatabaseType('mysql');
		for(var i = 0 ; i < funcArr.length ; i ++){
			funcdataitems += '{text:"';
			funcdataitems += funcArr[i];
			funcdataitems += '",value:"';
			funcdataitems += funcArr[i];
			funcdataitems += '"},';
		}
		funcdataitems = funcdataitems.substring(0,funcdataitems.length - 1);
		$('#func_list').attr('dataitems',funcdataitems);
		
		//操作符
		var operatordataitems = '';
		var operatorArr = Sqlbuilder.getOperationByDatabaseType('mysql');
		for(var i = 0 ; i < operatorArr.length ; i ++){
			operatordataitems += '{text:"';
			operatordataitems += operatorArr[i];
			operatordataitems += '",value:"';
			operatordataitems += operatorArr[i];
			operatordataitems += '"},';
		}
		operatordataitems = operatordataitems.substring(0,operatordataitems.length - 1);
		$('#operator_list').attr('dataitems',operatordataitems);
		
		//连接符
		var joindataitems = '';
		var joinArr = Sqlbuilder.getJoinByDatabaseType('mysql');
		for(var i = 0 ; i < joinArr.length ; i ++){
			joindataitems += '{text:"';
			joindataitems += joinArr[i];
			joindataitems += '",value:"';
			joindataitems += joinArr[i];
			joindataitems += '"},';
		}
		joindataitems = joindataitems.substring(0,joindataitems.length - 1);
		$('#join_list').attr('dataitems',joindataitems);
		
		
		
		
	},
	/**
	 * 选择表
	 */
	addTable : function(obj){
		$("#selectDialog").html('<textarea cols="25" rows="10"/><button class="self_input_btn_">确定</button>');
		Sqlbuilder.bindEventWithSelectObj(obj);
		Sqlbuilder.showDialog(obj);
		
		$('.self_input_btn_').click(function(){
			$('#key_word_from').append('<p>' + $(this).prev().val() + ',</p>');
			$('#selectDialog').dialog( "close" );
		});
	},
	/**
	 * 生成sql HTML代码
	 */
	buildSqlGeneHTML : function(){
		var sqlGeneHTML = ''+
		'<p>'+
			'<span class="key_word">select</span> '+
			'<p id="key_word_select" class="sql_statement"></p>'+
			'<p class="operation_btn" id="add_filed_btn"><添加字段></p>'+
		'</p>'+
		'<p>'+
			'<span class="key_word">form</span>'+
			'<p id="key_word_from" class="sql_statement"></p>'+
			'<p class="operation_btn" id="add_table_btn"><添加表></p>'+
		'</p>'+
		'<p>'+
			'<span class="key_word">where</span> '+
			'<p id="key_word_where" class="sql_statement"></p>'+
			'<p class="operation_btn" id="add_condition_btn"><添加条件></p>'+
		'</p>'+
		'<p>'+
			'<span class="key_word">group by</span> '+
			'<p id="key_word_groupby" class="sql_statement"></p>'+
			'<p class="operation_btn" id="add_group_btn"><添加分组></p>'+
		'</p>'+
		'<p>'+
			'<span class="key_word">having</span> '+
			'<p id="key_word_having" class="sql_statement"></p>'+
			'<p class="operation_btn" id="add_having_btn"><添加条件></p>'+
		'</p>'+
		'<p>'+
			'<span class="key_word">order by</span> '+
			'<p id="key_word_orderby" class="sql_statement"></p>'+
			'<p class="operation_btn" id="add_order_btn"><添加排序字段></p>'+
		'</p>'+
		'<div id="selectDialog"></div>	';
		
		$('#sql_gene').html(sqlGeneHTML);
		
		$('#add_filed_btn').click(function(){
		});
		
		$('#add_table_btn').click(function(){
			Sqlbuilder.addTable(this);
		});
		
		$('#add_condition_btn').click(function(){
			Sqlbuilder.addCondition(this,'all');
		});
		
		$('#add_group_btn').click(function(){
			Sqlbuilder.showSelectDialog(this,'group');
		});
		
		$('#add_order_btn').click(function(){
			Sqlbuilder.showSelectDialog(this,'order');
		});
		$('#add_having_btn').click(function(){
			Sqlbuilder.addCondition(this,'having');
		});	
	},
	/**
	 * 通过数据源获得表
	 */
	buildTablesByDataresource : function(dataSourceId){

		$.ajax({
			url : 'table.json',
			dataType : 'text',
			data : {
				dataSourceId : dataSourceId
			},
			error : function(jqXHR, textStatus, errorThrown) {

			},
			success : function(data, textStatus, jqXHR) {
				$('#database_table').html('');
				var tableNames = data.split(',');
				for(var i = 0 ; i < tableNames.length ; i ++){
					$('#database_table').append('<li>' + tableNames[i] + '</li>');
				}
				Sqlbuilder.bindDraggable();
			}

		});
	},
	/**
	 * 表拖拽事件
	 */
	bindDraggable : function(){
		$( "#database_table li" ).draggable({
			proxy:'clone',
			revert:true,
			onStartDrag:function(){
				//$(this).draggable('proxy').addClass('dp');
				$('#table_container').css('border','1px red dashed');
			},
			onStopDrag:function(e){
				$('#table_container').css('border','0px red dashed');
				var me = this;
				var dataSourceId = $('#dataService_datasourceConfig_id').val();
				var tableName = $(me).text();
				if(Sqlbuilder.isExistTableInContainer(tableName)){
//					$.messager.alert('提示','已经存在该表！');
					$.crud.createNote('已存在该表');
					return;
				}
				$.ajax({
					url : 'column.json',
					dataType : 'text',
					type: "post", 
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					data : {
						dataSourceId : dataSourceId,
						tableName : tableName
					},
					error : function(jqXHR, textStatus, errorThrown) {

					},
					success : function(data, textStatus, jqXHR) {
						//var left = $(me).offset.left - $('#table_container').offset().left;
						//var top = $(me).offset.top - $('#table_container').offset().top;
						var left = e.offsetX;
						var top = e.offsetY;
						var tableHtml = '<div class="table_div" '+
							' style="position:absolute;left:' + left +
							'px;top:' + top +
							'px;">'+
							'<table border="1">'+
							'<tr><td class="tableName">'+
							'<input style="cursor:default" value="'+
							tableName+
							'" onclick="Sqlbuilder.bindSelectAllColumnEvent(this)" type="checkbox"/>'+
							tableName+
							'<a class="del_table"></a></td></tr>';
						var columnNames = data.split(',');
						for(var i = 0 ; i < columnNames.length ; i ++){
							if(columnNames[i] != ''){
								tableHtml += '<tr><td class="columnName"><input style="cursor:default" value="' + 
										tableName + 
										'.' + 
										columnNames[i] + 
										'" onchange="Sqlbuilder.bindSelectColumnNameEvent(this)" type="checkbox"/>' +
										columnNames[i] + 
										'</td></tr>';
							}
						}
						'</table></div>';
						$('#table_container').append(tableHtml);
						//$('#table_container table').resizable();
						
						$( "#table_container table" ).css('cursor','move');
						$( "#table_container table" ).draggable({
							containment:'#table_container'
						});
						Sqlbuilder.bindDelTableEvent();
						Sqlbuilder.generateSelectTable(tableName);
					}
				});
			}
			
		});
	},
	/**
	 * 判断时候已经存在拖拽的表
	 */
	isExistTableInContainer : function(tableName){
		var result = false;
		$('#key_word_from').children().each(function(){
			if($(this).text().indexOf(tableName) != -1){
				result = true;
			}
		});
		return result;
	},
	/**
	 * 生成选择表语句
	 * @param tableName
	 */
	generateSelectTable : function(tableName){
		//表的数量
		//var tableCount = $('.table_div').length;
		var selectTableName = '<p>';
		selectTableName += tableName;
		//if(tableCount > 1){
			selectTableName += ',';
		//}
		selectTableName += '</p>';
		$('#key_word_from').append(selectTableName);
		
		Sqlbuilder.buildDropDownList();
	},	
	/**
	 * 绑定删除表事件
	 */
	bindDelTableEvent : function(){
		$('.table_div .del_table').click(function(){
			var tableName = $(this).parent().text();
			$(this).parentsUntil('.table_div').remove();
			//删除选择表语句
			Sqlbuilder.delSelectTable(tableName);
			//删除该表的相关字段
			Sqlbuilder.delColumnInTable(tableName);
			//删除where中该表的相关条件
			Sqlbuilder.delWhereConditionInTable(tableName);
			//删除group by中该表的相关字段
			Sqlbuilder.delGroupColumnInTable(tableName);
			//删除having中该表的相关条件
			Sqlbuilder.delHavingConditionInTable(tableName);
			//删除order by中该表的相关字段
			Sqlbuilder.delOrderColumnInTable(tableName);
		});
	},
	/**
	 * 删除表相关的
	 * @param tableName
	 * @param keyWordId
	 */
	delSomeInTable : function(tableName,keyWordId){
		$('#'+keyWordId).children().each(function(){
			if($(this).text().indexOf(tableName) != -1){
				$(this).remove();
			}
		});
	},
	/**
	 * 删除选择表语句
	 * @param tableName
	 */
	delSelectTable : function(tableName){
		Sqlbuilder.delSomeInTable(tableName,'key_word_from');
	},
	/**
	 * 删除该表的相关字段
	 */
	delColumnInTable : function(tableName){
		Sqlbuilder.delSomeInTable(tableName,'key_word_select');
	},
	/**
	 * 删除where中该表的相关条件
	 * @param tableName
	 */
	delWhereConditionInTable : function(tableName){
		Sqlbuilder.delSomeInTable(tableName,'key_word_where');
	},
	/**
	 * 删除group by中该表的相关字段
	 * @param tableName
	 */
	delGroupColumnInTable : function(tableName){
		Sqlbuilder.delSomeInTable(tableName,'key_word_groupby');
	},
	/**
	 * 删除having中该表的相关条件
	 * @param tableName
	 */
	delHavingConditionInTable : function(tableName){
		Sqlbuilder.delSomeInTable(tableName,'key_word_having');
	},
	/**
	 * 删除order by中该表的相关字段
	 * @param tableName
	 */
	delOrderColumnInTable : function(tableName){
		Sqlbuilder.delSomeInTable(tableName,'key_word_orderby');
	},
	/**
	 * 绑定选择字段名事件
	 */
	bindSelectColumnNameEvent : function(columnCheck){
		var isSelect = $(columnCheck).attr('checked');
		if(isSelect == 'checked'){
			var selectColumnName = '<p>';
			selectColumnName += '<span class="func_btn" onclick="Sqlbuilder.showFuncDialog(this);"><函数></span> ';
			selectColumnName += '<span>';
			selectColumnName += $(columnCheck).val();
			selectColumnName += '</span>';
			selectColumnName += ',';
			selectColumnName += '</p>';
			$('#key_word_select').append(selectColumnName);
		}else{
			$('#key_word_select').children().each(function(){
				if($(this).text().indexOf($(columnCheck).val()) != -1){
					$(this).remove();
				}
			});
		}

	},
	/**
	 * 根据数据库类型获得函数
	 */
	getFuncByDatabaseType : function(databaseType){
		var funcArr = [];
		funcArr.push('sum');
		funcArr.push('max');
		funcArr.push('min');
		funcArr.push('avg');
		funcArr.push('count');
		if('mysql' == databaseType){

		}else if('oracle' == databaseType){
			
		}else if('sqlserver' == databaseType){
			
		}else{
			
		}
		funcArr.push('无');
		return funcArr;
	},
	/**
	 * 根据数据库类型获得操作符
	 */
	getOperationByDatabaseType : function(databaseType){
		var operationArr = [];
		operationArr.push('=');
		operationArr.push('<>');
		operationArr.push('<');
		operationArr.push('<=');
		operationArr.push('>');
		operationArr.push('>=');
		operationArr.push('like');
		operationArr.push('not like');
		operationArr.push('is null');
		operationArr.push('is not null');
		operationArr.push('is between');
		operationArr.push('is not between');
		operationArr.push('is in list');
		operationArr.push('is not in list');
		if('mysql' == databaseType){

		}else if('oracle' == databaseType){
			
		}else if('sqlserver' == databaseType){
			
		}else{
			
		}
		return operationArr;
	},
	/**
	 * 根据数据库类型获得连接符
	 */
	getJoinByDatabaseType : function(databaseType){
		var joinArr = [];
		joinArr.push('and');
		joinArr.push('and not');
		joinArr.push('or');
		joinArr.push('or not');
		if('mysql' == databaseType){

		}else if('oracle' == databaseType){
			
		}else if('sqlserver' == databaseType){
			
		}else{
			
		}
		return joinArr;
	},
	/**
	 * 显示函数窗口
	 */
	showFuncDialog : function(obj){
		var funcArr = Sqlbuilder.getFuncByDatabaseType('mysql');
		var funcHTML = '';
		for(var i = 0 ; i < funcArr.length ; i ++){
			funcHTML += '<p class="select_obj">' + funcArr[i]  + '</p>';
		}
		$("#selectDialog").html(funcHTML);
		Sqlbuilder.bindEventWithSelectObj(obj,'funcSelect');
		Sqlbuilder.showDialog(obj);
	},
	/**
	 * 弹出操作符窗口
	 */
	showOperationDialog : function(obj){
		var operationArr = Sqlbuilder.getOperationByDatabaseType('mysql');
		var operationHTML = '';
		for(var i = 0 ; i < operationArr.length ; i ++){
			operationHTML += '<p class="select_obj">' + operationArr[i]  + '</p>';
		}
		$("#selectDialog").html(operationHTML);
		Sqlbuilder.bindEventWithSelectObj(obj,'operationSelect');
		Sqlbuilder.showDialog(obj);
	},
	/**
	 * 弹出连接符窗口
	 */
	showJoinDialog : function(obj){
		var joinArr = Sqlbuilder.getJoinByDatabaseType('mysql');
		var joinHTML = '';
		for(var i = 0 ; i < joinArr.length ; i ++){
			joinHTML += '<p class="select_obj">' + joinArr[i]  + '</p>';
		}
		$("#selectDialog").html(joinHTML);
		Sqlbuilder.bindEventWithSelectObj(obj,'joinSelect');
		Sqlbuilder.showDialog(obj);
	},
	/**
	 * 绑定选择所有字段名事件
	 */
	bindSelectAllColumnEvent : function(allColumnCheck){
		var isSelect = $(allColumnCheck).attr('checked');
		if(isSelect == 'checked'){
			Sqlbuilder.delColumnInTable($(allColumnCheck).val());
			$(allColumnCheck).parentsUntil('.table_div').find('.columnName input').attr('checked',true);
			$(allColumnCheck).parentsUntil('.table_div').find('.columnName input').change();
		}else{
			Sqlbuilder.delColumnInTable($(allColumnCheck).val());
			$(allColumnCheck).parentsUntil('.table_div').find('.columnName input').attr('checked',false);
			$(allColumnCheck).parentsUntil('.table_div').find('.columnName input').change();
		}
	},
	/**
	 * 增加条件
	 */
	addCondition : function(obj,scope){
		var conditionStr = '';
		conditionStr += '<div><p>';
		conditionStr += '<span class="key_word">(</span>';
		conditionStr += '<span onclick="Sqlbuilder.showConditionDialog(this,\'' + scope + '\')"><span class="operation_btn" style="margin-left: 0;"><表达式></span></span>';
		conditionStr += '<span class="key_word click_obj left_right_space" onclick="Sqlbuilder.showOperationDialog(this)"> = </span>';
		conditionStr += '<span onclick="Sqlbuilder.showConditionDialog(this,\'' + scope + '\')"><span class="operation_btn" style="margin-left: 0;"><表达式></span></span>';
		conditionStr += '<span class="key_word">)</span>';
		conditionStr += '</p>';
		conditionStr += '<p><span class="key_word click_obj left_right_space" onclick="Sqlbuilder.showJoinDialog(this)"> and </span><span class="operation_btn" onclick="Sqlbuilder.delCondition(this)"><删除条件></span></p></div>';
		$(obj).prev().append(conditionStr);
	},
	/**
	 * 删除条件
	 */
	delCondition : function(obj){
		$(obj).parent().prev().remove();
		$(obj).parent().remove();
	},
	/**
	 * 删除字段
	 * @param obj
	 */
	delField : function(obj){
		$(obj).parent().remove();
	},
	/**
	 * 条件窗口
	 */
	showConditionDialog : function(obj,scope){
		var conditionSelect = '';
		conditionSelect += '<div id="conditionSelect" class="easyui-tabs">';
		conditionSelect +=	'<div title="字段列表">';
		conditionSelect +='<div id="filedList" class="filed_list">';
		if('all' == scope){
			conditionSelect +=Sqlbuilder.showAllField();
		}else{
			conditionSelect +=Sqlbuilder.showSelectedField();
		}
		conditionSelect +='</div></div>';
		conditionSelect +=	'<div title="自定义">';

		conditionSelect +='<div id="selfInput" class="self_input">';
		conditionSelect +='<textarea cols="22" rows="8"/>';
		conditionSelect +='<button class="self_input_btn">确定</button>';
		conditionSelect +='</div><div></div>';
		
		$("#selectDialog").html(conditionSelect);
		$( "#conditionSelect" ).tabs();
		Sqlbuilder.bindEventWithSelectObj(obj,'conditionSelect');
		Sqlbuilder.showDialog(obj);
	},
	/**
	 * 显示所有字段
	 */
	showAllField : function(){
		var field = '<div>';
		$('.table_div').each(function(){
			var tableName = $(this).find('.tableName').text();
			$(this).find('.columnName').each(function(){
				var columnName = $(this).text();
				field += '<div class="select_obj">';
				field += tableName;
				field += '.';
				field += columnName;
				field += '</div>';
			});

		});
		field += '</div>';
		return field;
	},
	/**
	 * 显示所选字段
	 */
	showSelectedField : function(){
		var field = '<div>';
		$('#key_word_select').children().each(function(){
			field += '<div class="select_obj">';
			field += $(this).text().replace(',','').replace('<函数>','');
			field += '</div>';
		});
		field += '</div>';
		return field;
	},
	/**
	 * 改变排序方式
	 */
	changeOrderType : function(obj){
		if($(obj).text().indexOf('asc') != -1){
			$(obj).text(' desc');
		}else{
			$(obj).text(' asc');
		}
	},
	/**
	 * 字段绑定事件
	 */
	bindEventWithSelectObj : function(obj,type){

		$('.select_obj').each(function(){
			var filed = this;
			$(this).click(function(){
				if('conditionSelect' == type){
					var tableFiled = $(filed).text();
					$(obj).html(tableFiled);
				}else if('funcSelect' == type){ //选择函数时
					var func = $(filed).text();
					if($(obj).hasClass('func_btn')){
						if('无' != func){
							var field = $(obj).next().text();
							$(obj).next().text('');
							var funcHTML = '<span class="key_word click_obj">' +
											func + 
											'(</span><span>' + 
											field + 
											'</span><span class="key_word">)</span>';
							$(obj).html(funcHTML);
							$(obj).removeClass('func_btn');
						}
					}else{
						if('无' == func){
							var field = $(obj).children().first().next().text();
							$(obj).text('<函数>');
							$(obj).next().text(field);
							$(obj).addClass('func_btn');
						}else{
							//var oldFunc = $(obj).next().text();
							$(obj).children().first().text(func + '(');
						}
					}

				}else if('operationSelect' == type || 'joinSelect' == type){
					$(obj).text(' '+ $(filed).text() + ' ');
				}else if('order' == type){
					var tableFiled = '<p>';
					tableFiled += '<span>';
					tableFiled += $(filed).text();
					tableFiled += '</span>';
					tableFiled += '<span  class="key_word click_obj left_right_space" onclick="Sqlbuilder.changeOrderType(this)">';
					tableFiled += ' asc';
					tableFiled += '</span>';
					tableFiled += ',';
					tableFiled += '<span class="operation_btn" onclick="Sqlbuilder.delField(this)"><删除字段></span>';
					tableFiled += '</p>';
					$(obj).prev().append(tableFiled);
				}else if('group' == type){
					var tableFiled = '<p>';
					tableFiled += $(filed).text();
					tableFiled += ',';
					tableFiled += '<span class="operation_btn" onclick="Sqlbuilder.delField(this)"><删除字段></span>';
					tableFiled += '</p>';
					$(obj).prev().append(tableFiled);
				}else{
					var tableFiled = '<p>';
					tableFiled += $(filed).text();
					tableFiled += ',';
					tableFiled += '</p>';
					$(obj).prev().append(tableFiled);
				}
				$('#selectDialog').dialog( "close" );
			});
			$(this).mouseover(function(){
				$(this).addClass('hight_light');
			});
			$(this).mouseout(function(){
				$(this).removeClass('hight_light');
			});
		});
		
		$('.self_input_btn').click(function(){
			$(obj).html('<span>' + $(this).prev().val() + '</span>');
			$('#selectDialog').dialog( "close" );
		});
	},
	/**
	 * 弹出显示所有字段窗口
	 */
	showSelectDialog: function(obj,scope){
		$("#selectDialog").html(Sqlbuilder.showAllField());
		Sqlbuilder.bindEventWithSelectObj(obj,scope);
		Sqlbuilder.showDialog(obj);
	},
	/**
	 * 弹出已选字段窗口
	 */
	showFieldSelectedDialog : function(obj){
		$("#selectDialog").html(Sqlbuilder.showSelectedField());
		Sqlbuilder.bindEventWithSelectObj(obj);
		Sqlbuilder.showDialog(obj);
	},
	/**
	 * 显示窗口
	 */
	showDialog : function(obj){
		$("#selectDialog").dialog({
			width:'230',
			height:'250',
			left: $(obj).offset().left - 20,
			top:$(obj).offset().top,
			draggable:true,
			resizable:false,
			modal: false
		});
		//$('.ui-dialog-titlebar').remove();
		$('#table_container').click(function(){
			$('#selectDialog').dialog( "close" );
		});
	},
	/**
	 * 清空
	 */
	cleanHTML : function(){
		Sqlbuilder.buildSqlGeneHTML();
		$('#table_container').html('');
		$('#key_word_select').html('');
		$('#key_word_from').html('');
		$('#key_word_where').html('');
		$('#key_word_groupby').html('');
		$('#key_word_having').html('');
		$('#key_word_orderby').html('');
	},
	/**
	 * 生成SQL
	 */
	buildSQL : function(){
		var sql = '';
		
		//select语句
		sql += 'select' + '\r\t';
		var select_statement = $('#key_word_select').text();
		select_statement = select_statement.replace(/<函数>/g,'');
		select_statement = select_statement.substring(0,select_statement.length-1);
		select_statement = select_statement.replace(/,/g,',\r\t');
		sql += select_statement + '\r';
		
		//from语句
		sql += '\r' + 'from' + '\r\t';
		var from_statement = $('#key_word_from').text();
		from_statement = from_statement.substring(0,from_statement.length-1);
		from_statement = from_statement.replace(/,/g,',\r\t');
		sql += from_statement + '\r';
		
		//where语句
		
		var where_statement = $('#key_word_where').text();
		where_statement = where_statement.replace(/<删除条件>/g,'');
		where_statement = where_statement.substring(0,where_statement.lastIndexOf(')')+1);
		where_statement = where_statement.replace(/\(/g,'\r\t(');
		where_statement = where_statement.replace(/\)/g,')\r\t');
		if(where_statement != ''){
			sql += '\r' + 'where' + '\t';
			sql += where_statement;
		}
		
		//group by语句
		var groupby_statement = $('#key_word_groupby').text();
		groupby_statement = groupby_statement.replace(/<删除字段>/g,'');
		groupby_statement = groupby_statement.substring(0,groupby_statement.length-1);
		groupby_statement = groupby_statement.replace(/,/g,',\r\t');
		if(groupby_statement != ''){
			sql += '\r' + 'group by' + '\r\t';
			sql += groupby_statement + '\r';
		}
		
		//having语句
		var having_statement = $('#key_word_having').text();
		having_statement = having_statement.replace(/<删除条件>/g,'');
		having_statement = having_statement.substring(0,having_statement.lastIndexOf(')')+1);
		having_statement = having_statement.replace(/\(/g,'\r\t(');
		having_statement = having_statement.replace(/\)/g,')\r\t');
		if(having_statement != ''){
			sql += '\r' + 'having' + '\t';
			sql += having_statement;
		}
		
		//order by语句
		var orderby_statement = $('#key_word_orderby').text();
		orderby_statement = orderby_statement.replace(/<删除字段>/g,'');
		orderby_statement = orderby_statement.substring(0,orderby_statement.length-1);
		orderby_statement = orderby_statement.replace(/,/g,',\r\t');
		if(orderby_statement != ''){
			sql += '\r' + 'order by' + '\r\t';
			sql += orderby_statement;
		}
		
		return sql;
	}
};