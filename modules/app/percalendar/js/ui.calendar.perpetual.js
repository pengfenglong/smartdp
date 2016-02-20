/*!
 * jQuery UI lineDiagram0_1 1.9.2
 * http://jqueryui.com
 *
 * Copyright 2012 jQuery Foundation and other contributors
 * Released under the MIT license.
 * http://jquery.org/license
 *
 * http://api.jqueryui.com/
 *
 * Depends:
 *	jquery.ui.core.js
 *	jquery.ui.mouse.js
 *	jquery.ui.widget.js
 */
(function( $, undefined ) {
$.widget( "ui.cal_perpetual0_1", $.ui.mouse, {
	version: "1.9.2",
	options: {
		fullPage: false
	},
	_create: function() {
		var o = this.options;
		this._fullPage = o.fullPage;
		this.global = $.ui.cal_perpetual0_1.global;
		
		this._view = $('<div></div>').appendTo(this.element)
					.attr('class', 'cal_0_4_folder_div');
		$('<div></div>').appendTo(this._view)
		.attr('class', 'cal_0_4_info_div');
		this.createOuterDiv().appendTo(this._view);
		this.cld = {};
		this.Today = new Date();
		this.tY = this.Today.getFullYear();
		this.tM = this.Today.getMonth();
		this.tD = this.Today.getDate();
		this.initial();
		
		this._mouseInit();
		if(this._fullPage){
			$(window).bind('resize', this, this.onChangeHeight);
		}
	},
	_destroy: function() {
		if(this._fullPage){
			$(window).unbind('resize', this.onChangeHeight);
		}
		this._view.remove();
		this.cld.remove();
		this.Today.remove();
	},
	getEleByName: function(argName){
		var ele = null;
		switch(argName){
			case '_selMonth':
				ele = this._view[0] // div
				.children[1] // div outter
				.children[0] // div outter left
				.children[0] // div inner
				.children[0] // div row0
				.children[0] // span
				.children[3]; // select month
				break;
			case '_selYear':
				ele = this._view[0] // div
				.children[1] // div outter
				.children[0] // div outter left
				.children[0] // div inner
				.children[0] // div row0
				.children[0] // span
				.children[1]; // select year
				break;
			case '_calHeader':
				ele = this._view[0] // div
				.children[1] // div outter
				.children[0] // div outter left
				.children[0] // div inner
				.children[0] // div row0
				.children[1]; // span
				break;
			case '_calBody':
				ele = this._view[0] // div
				.children[1] // div outter
				.children[0] // div outter left
				.children[0]; // div inner
				break;
		}
		return ele;
	},
	createOuterDiv: function(){
		if(this._fullPage){
			this.element.height($(window).height() - 20);
		}
		var _fontSize = this._view.height() * 0.07;
		var oOuterTable = $('<div></div>')
					.attr('class', 'cal_0_4_outter_div')
					.attr('style', 'font-size: ' + _fontSize + 'px;');
		$('<div></div>').appendTo(oOuterTable[0])
		.attr('class', 'cal_0_4_outter_left_div')
		.append(this.createInnerDiv());
		$('<div></div>').appendTo(oOuterTable[0])
		.attr('class', 'cal_0_4_outter_right_div')
		.append($('<br />'))
		.append($('<br />'))
		.append($('<br />'))
		.append($('<br />'))
		.append($('<button></button>').attr('class', 'cal_0_4_button')
				.append('年↑').bind('click', {curObj: this, key: 'YU'}, this.onClickBtn))
		.append($('<button></button>').attr('class', 'cal_0_4_button')
				.append('年↓').bind('click', {curObj: this, key: 'YD'}, this.onClickBtn))
		.append($('<br />'))
		.append($('<button></button>').attr('class', 'cal_0_4_button')
				.append('月↑').bind('click', {curObj: this, key: 'MU'}, this.onClickBtn))
		.append($('<button></button>').attr('class', 'cal_0_4_button')
				.append('月↓').bind('click', {curObj: this, key: 'MD'}, this.onClickBtn))
		.append($('<br />'))
		.append($('<button></button>').attr('class', 'cal_0_4_button')
				.append('当月').bind('click', {curObj: this, key: ''}, this.onClickBtn));
		return oOuterTable;
	},
	createInnerDiv: function(){
		var oInnerTable = $('<div></div>')
						.attr('class', 'cal_0_4_inner_div');
		var oTr0 = $('<div></div>').appendTo(oInnerTable[0])
					.attr('class', 'cal_0_4_inner_div_row0');
		$('<span></span>').appendTo(oTr0)
		.attr('class', 'cal_0_4_inner_div_row0_span1');
		$('<span></span>').appendTo(oTr0)
		.attr('class', 'cal_0_4_inner_div_row0_span2');
		$('<div></div>').appendTo(oTr0)
		.attr('class', 'cal_0_4_inner_div_row0_spliter');
		$('<span></span>').appendTo(oTr0[0].children[0]).text('公历');
		var oSelYear = $('<select></select>').appendTo(oTr0[0].children[0])
						.attr('class', 'cal_0_4_inner_select')
						.bind('change', this, this.onChangeCld);
		for(var i = 1900; i < 2101; i++){
			$('<option></option>').appendTo(oSelYear)
			.attr('value', i)
			.append(i);;
		}
		$('<span></span>').appendTo(oTr0[0].children[0]).text('年');
		var oSelMonth = $('<select></select>').appendTo(oTr0[0].children[0])
						.attr('class', 'cal_0_4_inner_select')
						.bind('change', this, this.onChangeCld);
		for(var i = 1; i < 13; i++){
			$('<option></option>').appendTo(oSelMonth)
			.attr('value', i)
			.append(i);
		}
		$('<span></span>').appendTo(oTr0[0].children[0]).text('月');
		
		var oTr1 = $('<div></div>').appendTo(oInnerTable[0])
					.attr('class', 'cal_0_4_inner_div_row1');
		$('<div></div>').appendTo(oTr1).append('日')
		.attr('class', 'cal_0_4_inner_inner_div_row1_cell_0_6').attr('style', 'left:1%;')
		.append($('<div></div>').attr('class', 'cal_0_4_inner_inner_div_row1_cell_spliter'));
		$('<div></div>').appendTo(oTr1).append('一')
		.attr('class', 'cal_0_4_inner_inner_div_row1_cell').attr('style', 'left:15%;')
		.append($('<div></div>').attr('class', 'cal_0_4_inner_inner_div_row1_cell_spliter'));
		$('<div></div>').appendTo(oTr1).append('二')
		.attr('class', 'cal_0_4_inner_inner_div_row1_cell').attr('style', 'left:29%;')
		.append($('<div></div>').attr('class', 'cal_0_4_inner_inner_div_row1_cell_spliter'));
		$('<div></div>').appendTo(oTr1).append('三')
		.attr('class', 'cal_0_4_inner_inner_div_row1_cell').attr('style', 'left:43%;')
		.append($('<div></div>').attr('class', 'cal_0_4_inner_inner_div_row1_cell_spliter'));
		$('<div></div>').appendTo(oTr1).append('四')
		.attr('class', 'cal_0_4_inner_inner_div_row1_cell').attr('style', 'left:57%;')
		.append($('<div></div>').attr('class', 'cal_0_4_inner_inner_div_row1_cell_spliter'));
		$('<div></div>').appendTo(oTr1).append('五')
		.attr('class', 'cal_0_4_inner_inner_div_row1_cell').attr('style', 'left:71%;')
		.append($('<div></div>').attr('class', 'cal_0_4_inner_inner_div_row1_cell_spliter'));
		$('<div></div>').appendTo(oTr1).append('六')
		.attr('class', 'cal_0_4_inner_inner_div_row1_cell_0_6').attr('style', 'left:85%;');
		$('<div></div>').appendTo(oTr1)
		.attr('class', 'cal_0_4_inner_div_row0_spliter');
		for(var i = 0; i < 6; i++){
			var oTr = $('<div></div>').appendTo(oInnerTable[0])
						.attr('class', 'cal_0_4_inner_div_row_n')
						.attr('style', 'top:' + (16 + 14 * i) + '%;');
			for(var j = 0; j < 7; j++){
				$('<div></div>').appendTo(oTr)
				.attr('class', 'cal_0_4_inner_div_row_n_cell')
				.attr('style', 'left:' + (1 + 14 * j) + '%;')
				.append($('<span></span>').attr('class', 'cal_0_4_inner_upper_span'))
				.append($('<br />'))
				.append($('<div></div>').attr('class', 'cal_0_4_inner_lowwer_div cal_0_4_div_nowrap'))
				.bind('mouseover', this, this.onMouseOverCell)
				.bind('mouseout', this, this.onMouseOutCell);
			}
		}
		return oInnerTable;
	},
	drawCld: function(SY, SM) {
        var i, sD, s;
        this.cld = new this.global.calendar(SY, SM, this.tY, this.tM, this.tD);

        if (SY > 1874 && SY < 1909) yDisplay = '光绪' + (((SY - 1874) == 1) ? '元' : SY - 1874);
        if (SY > 1908 && SY < 1912) yDisplay = '宣统' + (((SY - 1908) == 1) ? '元' : SY - 1908);
        if (SY > 1911 && SY < 1950) yDisplay = '民国' + (((SY - 1911) == 1) ? '元' : SY - 1911);
        if (SY > 1949) yDisplay = '共和国' + (((SY - 1949) == 1) ? '元' : SY - 1949);

        $(this.getEleByName('_calHeader')).text(yDisplay + '年  农历' + this.global.cyclical(SY - 1900 + 36) + '年  【' + this.global.Animals[(SY - 4) % 12] + '】');
        
		for(var i0 = 0; i0 < 6; i0++){
			for(var i1 = 0; i1 < 7; i1++){
				var oEle = this.getEleByName('_calBody').children[i0 + 2].children[i1];
				i = i0 * 7 + i1;
				$(oEle).removeClass('cal_0_4_inner_td_today');
				sObj = $(oEle.children[0]);
				lObj = $(oEle.children[2]);

				sD = i - this.cld.firstWeek;
				if (sD > -1 && sD < this.cld.length) {  //日期内
					sObj.html(sD + 1);
					if (this.cld[sD].isToday) {
						$(oEle).addClass('cal_0_4_inner_td_today');//今日颜色
					}
					sObj.attr('style', 'color:' + this.cld[sD].color + ';');

					if (this.cld[sD].lDay == 1)  //显示农历月
						lObj.html('<b>' + (this.cld[sD].isLeap ? '闰' : '') + this.cld[sD].lMonth + '月' + (this.global.monthDays(this.cld[sD].lYear, this.cld[sD].lMonth) == 29 ? '小' : '大') + '</b>');
					else  //显示农历日
						lObj.html(this.global.cDay(this.cld[sD].lDay));

					s = this.cld[sD].lunarFestival;
					if (s.length > 0) {  //农历节日
						s = '<span style="color:red;">' + s + '</span>';//s.fontcolor('red');
					}else {  //国历节日
						s = this.cld[sD].solarFestival;
						if (s.length > 0) {
							s = (s == '黑色星期五') ? '<span style="color:black;">' + s + '</span>' : '<span style="color:#0066FF;">' + s + '</span>';
						}else {  //廿四节气
							s = this.cld[sD].solarTerms;
							if (s.length > 0) s = '<span style="color:limegreen;">' + s + '</span>';
						}
					}
					if (this.cld[sD].solarTerms == '清明') s = '<span style="color:red;">清明节</span>';
					if (this.cld[sD].solarTerms == '芒种') s = '<span style="color:red;">芒种</span>';
					if (this.cld[sD].solarTerms == '夏至') s = '<span style="color:red;">夏至</span>';
					if (this.cld[sD].solarTerms == '冬至') s = '<span style="color:red;">冬至</span>';

					if (s.length > 0) lObj.html(s);

				}
				else {  //非日期
					sObj.html('');
					lObj.html('');
				}
			}
		}
    },
	onChangeHeight: function(arg){
		if(arg.data._fullPage){
			arg.data.element.height($(window).height() - 20);
		}
		var _fontSize = arg.data._view.height() * 0.07;
		
		$(arg.data._view[0].children[1]).attr('style', 'font-size: ' + _fontSize + 'px;');
	},
	onChangeCld: function(arg){
        var y, m;
        y = Number($(arg.data.getEleByName('_selYear')).val());
        m = Number($(arg.data.getEleByName('_selMonth')).val());
        arg.data.drawCld(y, m - 1);
    },
    onMouseOverCell: function(arg){
		$(arg.currentTarget).addClass('cal_0_4_inner_td_msover');
		var s, festival;
		var d = Number($(arg.currentTarget.children[0]).text());
		var curObj = arg.data;
		if(d && d > 0 && d <= curObj.cld.length){
			d--;
			var myxz = curObj.global.getAstro(curObj.cld[d].sMonth, curObj.cld[d].sDay);
			if (curObj.cld[d].solarTerms == '' && curObj.cld[d].solarFestival == '' && curObj.cld[d].lunarFestival == '')
                festival = '';
			else
				festival = '<table style="width:100%;border:0px;cell-padding:2px;cell-spacing:0px;background-color:#CCFFCC;"><tr><td>' +
				'<span style="font-size:9pt;color:#000000;">' + curObj.cld[d].solarTerms + '  ' + curObj.cld[d].solarFestival + '  ' + curObj.cld[d].lunarFestival + '</span></td>' + '</tr></table>';

            s = '<table style="width:10em;border:0px;cell-padding:2px;cell-spacing:0px;background-color:#0099FF;" ><tr><td>' +
			'<table style="width:100%;border:0px;cell-padding:0px;cell-spacing:0px;"><tr><td  align="right"><span style="font-size:9pt;color:#ffffff;">' +
			curObj.cld[d].sYear + '  年  ' + curObj.cld[d].sMonth + '  月  ' + curObj.cld[d].sDay + '  日<br>星期' + curObj.cld[d].week + ' ' + myxz + '座<br>' +
			'<span style="color:#FFCCCC;">农历' + (curObj.cld[d].isLeap ? '闰  ' : '  ') + curObj.global.FormatLunarMonth(curObj.cld[d].lMonth) + curObj.global.FormatLunarDay(curObj.cld[d].lDay) + '</span><br>' +
			'<span style="color:yellow;">' + curObj.cld[d].cYear + '年  ' + curObj.cld[d].cMonth + '月  ' + curObj.cld[d].cDay + '日</span>' +
			'</span></td></tr></table>' + festival + '</td></tr></table>';
			$(arg.data._view[0].children[0]).html(s).show();
		}
	},
	onMouseOutCell: function(arg){
		$(arg.data._view[0].children[0]).hide();
		$(arg.currentTarget).removeClass('cal_0_4_inner_td_msover');
	},
	onClickBtn: function(arg){
		var y, m;
		y = Number($(arg.data.curObj.getEleByName('_selYear')).val());
        m = Number($(arg.data.curObj.getEleByName('_selMonth')).val());
		switch (arg.data.key){
			case 'YU':
				if(y > 1900) y--;
				break;
			case 'YD':
				if(y < 2100) y++;
				break;
			case 'MU':
				if(m > 1) m--;
				break;
			case 'MD':
				if(m < 12) m++;
				break;
			default:
				y = arg.data.curObj.tY;
				m = arg.data.curObj.tM + 1;
		}
		$(arg.data.curObj.getEleByName('_selYear')).val(y);
		$(arg.data.curObj.getEleByName('_selMonth')).val(m);
		arg.data.curObj.drawCld(y, m - 1);
	},
	initial: function() {
		$(this.getEleByName('_selYear')).val(this.tY);
		$(this.getEleByName('_selMonth')).val(this.tM + 1);
        this.drawCld(this.tY, this.tM);
    },
	_mouseCapture: function( event ) {
		return false;
	},
	_mouseStart: function() {
		return false;
	},
	_mouseDrag: function( event ) {
		return false;
	},
	_mouseStop: function( event ) {
		return false;
	}
});
$.ui.cal_perpetual0_1.global = {
	lunarInfo: [
		0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af, 0x9ad0, 0x55d2,
		0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd255, 0xb54f, 0xd6a0, 0xada2, 0x95b0, 0x4977,
		0x497f, 0xa4b0, 0xb4b5, 0x6a50, 0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970,
		0x6566, 0xd4a0, 0xea50, 0x6a95, 0x5adf, 0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f,
		0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2, 0xa950, 0xb557,
		0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573, 0x52bf, 0xa9a8, 0xe950, 0x6aa0,
		0xaea6, 0xab50, 0x4b60, 0xaae4, 0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0,
		0x96d0, 0x4dd5, 0x4ad0, 0xa4d0, 0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6,
		0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40, 0xaf46, 0xab60, 0x9570,
		0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58, 0x5ac0, 0xab60, 0x96d5, 0x92e0,
		0xc960, 0xd954, 0xd4a0, 0xda50, 0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5,
		0xa950, 0xb4a0, 0xbaa4, 0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930,
		0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6, 0xa4e0, 0xd260, 0xea65, 0xd530,
		0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0, 0xd0b6, 0xd25f, 0xd520, 0xdd45,
		0xb5a0, 0x56d0, 0x55b2, 0x49b0, 0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0,
		0x4b63, 0x937f, 0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef,
		0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0, 0xda50, 0x5d55, 0x56a0, 0xa6d0, 0x55d4,
		0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50, 0x55a0, 0xaba4, 0xa5b0, 0x52b0,
		0xb273, 0x6930, 0x7337, 0x6aa0, 0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260,
		0xe968, 0xd520, 0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252,
		0xd520
	],
	solarMonth: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
	Gan: ["甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"],
	Zhi: ["子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"],
	Animals: ["鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"],
	solarTerm: ["小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"],
	sTermInfo: [0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758],
	nStr1: ['日', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十'],
	nStr2: ['初', '十', '廿', '卅', ' '],
	sFtv: [ // 国历节日  *表示放假日
		"0101*元旦",
		"0106  中国13亿人口日",
		"0110  中国110宣传日",

		"0202  世界湿地日",
		"0204  世界抗癌症日",
		"0210  世界气象日",
		"0214  情人节",
		"0221  国际母语日",
		"0207  国际声援南非日",

		"0303  全国爱耳日",
		"0308  妇女节",
		"0312  植树节 孙中山逝世纪念日",
		"0315  消费者权益保护日",
		"0321  世界森林日",
		"0322  世界水日",
		"0323  世界气象日",
		"0324  世界防治结核病日",

		"0401  愚人节",
		"0407  世界卫生日",
		"0422  世界地球日",

		"0501*国际劳动节",
		"0504  中国青年节",
		"0505  全国碘缺乏病日",
		"0508  世界红十字日",
		"0512  国际护士节",
		"0515  国际家庭日",
		"0517  世界电信日",
		"0518  国际博物馆日",
		"0519  中国汶川地震哀悼日 全国助残日",
		"0520  全国学生营养日",
		"0522  国际生物多样性日",
		"0523  国际牛奶日",
		"0531  世界无烟日",

		"0601  国际儿童节",
		"0605  世界环境日",
		"0606  全国爱眼日",
		"0617  防治荒漠化和干旱日",
		"0623  国际奥林匹克日",
		"0625  全国土地日",
		"0626  国际反毒品日",

		"0701  建党节 香港回归纪念日",
		"0707  抗日战争纪念日",
		"0711  世界人口日",

		"0801  八一建军节",
		"0815  日本正式宣布无条件投降日",

		"0908  国际扫盲日",
		"0909  毛泽东逝世纪念日",
		"0910  教师节",
		"0916  国际臭氧层保护日",
		"0917  国际和平日",
		"0918  九·一八事变纪念日",
		"0920  国际爱牙日",
		"0927  世界旅游日",
		"0928  孔子诞辰",

		"1001*国庆节 国际音乐节 国际老人节",
		"1002  国际减轻自然灾害日",
		"1004  世界动物日",
		"1007  国际住房日",
		"1008  世界视觉日 全国高血压日",
		"1009  世界邮政日",
		"1010  辛亥革命纪念日 世界精神卫生日",
		"1015  国际盲人节",
		"1016  世界粮食节",
		"1017  世界消除贫困日",
		"1022  世界传统医药日",
		"1024  联合国日",
		"1025  人类天花绝迹日",
		"1026  足球诞生日",
		"1031  万圣节",

		"1107  十月社会主义革命纪念日",
		"1108  中国记者日",
		"1109  消防宣传日",
		"1110  世界青年节",
		"1112  孙中山诞辰",
		"1114  世界糖尿病日",
		"1117  国际大学生节",

		"1201  世界艾滋病日",
		"1203  世界残疾人日",
		"1209  世界足球日",
		"1210  世界人权日",
		"1212  西安事变纪念日",
		"1213  南京大屠杀(1937年)纪念日，血泪的历史，中华儿女不可忘",
		"1220  澳门回归纪念日",
		"1221  国际篮球日",
		"1224  平安夜",
		"1225  圣诞节 世界强化免疫日",
		"1226  毛泽东诞辰"
	],
	lFtv: [ // 农历节日  *表示放假日
		"0101*春节",
		"0102*大年初二",
		"0103*大年初三",
		"0105  路神生日",
		"0115  元宵节",
		"0202  龙抬头",
		"0219  观世音圣诞",
		"0404  寒食节",
		"0408  佛诞节 ",
		"0505*端午节",
		"0606  天贶节 姑姑节",
		"0624  彝族火把节",
		"0707  七夕情人节",
		"0714  鬼节(南方)",
		"0715  盂兰节",
		"0730  地藏节",
		"0815*中秋节",
		"0909  重阳节",
		"1001  祭祖节",
		"1117  阿弥陀佛圣诞",
		"1208  腊八节 释迦如来成道日",
		"1223  过小年",
		"0100*除夕"
	],
	wFtv: [ // 某月的第几个星期几; 5,6,7,8 表示到数第 1,2,3,4 个星期几
		"0110  黑人节",
		"0150  世界麻风日",
		"0121  日本成人节",
		"0520  母亲节",
		"0530  全国助残日",
		"0630  父亲节",
		"0716  合作节",
		"0730  被奴役国家周",
		"0932  国际和平日",
		"0940  国际聋人节 世界儿童日",
		"1011  国际住房日",
		"1144  感恩节"
	],
	lYearDays: function(y){ // 返回农历 y年的总天数
		var me = $.ui.cal_perpetual0_1.global;
		var i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) sum += (me.lunarInfo[y - 1900] & i) ? 1 : 0;
        return (sum + me.leapDays(y));
	},
	leapDays: function(y){ // 返回农历 y年闰月的天数
		var me = $.ui.cal_perpetual0_1.global;
		if (me.leapMonth(y)) return ((me.lunarInfo[y - 1899] & 0xf) == 0xf ? 30 : 29);
        else return (0);
	},
	leapMonth: function(y){ // 返回农历 y年闰哪个月 1-12 , 没闰返回 0
		var me = $.ui.cal_perpetual0_1.global;
        var lm = me.lunarInfo[y - 1900] & 0xf;
        return (lm == 0xf ? 0 : lm);
    },
	monthDays: function(y, m){ // 返回农历 y年m月的总天数
		var me = $.ui.cal_perpetual0_1.global;
        return ((me.lunarInfo[y - 1900] & (0x10000 >> m)) ? 30 : 29);
    },
	Lunar: function(objDate){ // 算出农历, 传入日期控件, 返回农历日期控件 该控件属性有 .year .month .day .isLeap
		var me = $.ui.cal_perpetual0_1.global;
        var i, leap = 0, temp = 0;
        var offset = (Date.UTC(objDate.getFullYear(), objDate.getMonth(), objDate.getDate()) - Date.UTC(1900, 0, 31)) / 86400000;

        for (i = 1900; i < 2100 && offset > 0; i++) { temp = me.lYearDays(i); offset -= temp; }

        if (offset < 0) { offset += temp; i--; }

        this.year = i;

        leap = me.leapMonth(i); //闰哪个月
        this.isLeap = false;

        for (i = 1; i < 13 && offset > 0; i++) {
            //闰月
            if (leap > 0 && i == (leap + 1) && this.isLeap == false)
            { --i; this.isLeap = true; temp = me.leapDays(this.year); }
            else
            { temp = me.monthDays(this.year, i); }

            //解除闰月
            if (this.isLeap == true && i == (leap + 1)) this.isLeap = false;

            offset -= temp;
        }

        if (offset == 0 && leap > 0 && i == leap + 1)
            if (this.isLeap)
            { this.isLeap = false; }
            else
            { this.isLeap = true; --i; }

        if (offset < 0) { offset += temp; --i; }

        this.month = i;
        this.day = offset + 1;
    },
	solarDays: function(y, m){ // 返回公历 y年某m+1月的天数
		var me = $.ui.cal_perpetual0_1.global;
        if (m == 1)
            return (((y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0)) ? 29 : 28);
        else
            return (me.solarMonth[m]);
    },
	cyclical: function(num){ // 传入 offset 返回干支, 0=甲子
		var me = $.ui.cal_perpetual0_1.global;
        return (me.Gan[num % 10] + me.Zhi[num % 12]);
    },
	calElement: function (sYear, sMonth, sDay, week, lYear, lMonth, lDay, isLeap, cYear, cMonth, cDay) { // 阴历属性

        this.isToday = false;
        //瓣句
        this.sYear = sYear;   //公元年4位数字
        this.sMonth = sMonth;  //公元月数字
        this.sDay = sDay;    //公元日数字
        this.week = week;    //星期, 1个中文
        //农历
        this.lYear = lYear;   //公元年4位数字
        this.lMonth = lMonth;  //农历月数字
        this.lDay = lDay;    //农历日数字
        this.isLeap = isLeap;  //是否为农历闰月?
        //八字
        this.cYear = cYear;   //年柱, 2个中文
        this.cMonth = cMonth;  //月柱, 2个中文
        this.cDay = cDay;    //日柱, 2个中文

        this.color = '';

        this.lunarFestival = ''; //农历节日
        this.solarFestival = ''; //公历节日
        this.solarTerms = ''; //节气
    },
	sTerm: function(y, n) { // 某年的第n个节气为几日(从0小寒起算)
		var me = $.ui.cal_perpetual0_1.global;
        var offDate = new Date((31556925974.7 * (y - 1900) + me.sTermInfo[n] * 60000) + Date.UTC(1900, 0, 6, 2, 5));
        return (offDate.getUTCDate());
    },
	calendar: function(y, m, tY, tM, tD) { // 返回阴历 (y年,m+1月)
		var me = $.ui.cal_perpetual0_1.global;
        var sDObj, lDObj, lY = 1900, lM = 0, lD = 1, lL = 0, lX = 0, tmp1, tmp2;
        var cY, cM, cD; //年柱,月柱,日柱
        var lDPOS = new Array(3);
        var n = 0;
        var firstLM = 0;

        sDObj = new Date(y, m, 1, 0, 0, 0, 0);    //当月一日日期

        this.length = me.solarDays(y, m);    //公历当月天数
        this.firstWeek = sDObj.getDay();    //公历当月1日星期几

        ////////年柱 1900年立春后为庚子年(60进制36)
        if (m < 2) cY = me.cyclical(y - 1900 + 36 - 1);
        else cY = me.cyclical(y - 1900 + 36);
        var term2 = me.sTerm(y, 2); //立春日期

        ////////月柱 1900年1月小寒以前为 丙子月(60进制12)
        var firstNode = me.sTerm(y, m * 2); //返回当月「节」为几日开始
        cM = me.cyclical((y - 1900) * 12 + m + 12);

        //当月一日与 1900/1/1 相差天数
        //1900/1/1与 1970/1/1 相差25567日, 1900/1/1 日柱为甲戌日(60进制10)
        var dayCyclical = Date.UTC(y, m, 1, 0, 0, 0, 0) / 86400000 + 25567 + 10;

        for (var i = 0; i < this.length; i++) {

            if (lD > lX) {
                sDObj = new Date(y, m, i + 1);    //当月一日日期
                lDObj = new me.Lunar(sDObj);     //农历
                lY = lDObj.year;           //农历年
                lM = lDObj.month;          //农历月
                lD = lDObj.day;            //农历日
                lL = lDObj.isLeap;         //农历是否闰月
                lX = lL ? me.leapDays(lY) : me.monthDays(lY, lM); //农历当月最后一天

                if (n == 0) firstLM = lM;
                lDPOS[n++] = i - lD + 1;
            }

            //依节气调整二月分的年柱, 以立春为界
            if (m == 1 && (i + 1) == term2) cY = me.cyclical(y - 1900 + 36);
            //依节气月柱, 以「节」为界
            if ((i + 1) == firstNode) cM = me.cyclical((y - 1900) * 12 + m + 13);
            //日柱
            cD = me.cyclical(dayCyclical + i);

            //sYear,sMonth,sDay,week,
            //lYear,lMonth,lDay,isLeap,
            //cYear,cMonth,cDay
            this[i] = new me.calElement(y, m + 1, i + 1, me.nStr1[(i + this.firstWeek) % 7],
                               lY, lM, lD++, lL,
                               cY, cM, cD);
        }

        //节气
        tmp1 = me.sTerm(y, m * 2) - 1;
        tmp2 = me.sTerm(y, m * 2 + 1) - 1;
        this[tmp1].solarTerms = me.solarTerm[m * 2];
        this[tmp2].solarTerms = me.solarTerm[m * 2 + 1];
        if (m == 3) this[tmp1].color = 'red'; //清明颜色

        //国历节日
        for (i in me.sFtv)
            if (me.sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
                if (Number(RegExp.$1) == (m + 1)) {
                    this[Number(RegExp.$2) - 1].solarFestival += RegExp.$4 + '  ';
                    if (RegExp.$3 == '*') this[Number(RegExp.$2) - 1].color = 'red';
                }

        //月周节日
        var iIndex;
        for (i in me.wFtv)
            if (me.wFtv[i].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))
                if (Number(RegExp.$1) == (m + 1)) {
                    tmp1 = Number(RegExp.$2);
                    tmp2 = Number(RegExp.$3);
                    iIndex = ((this.firstWeek > tmp2) ? 7 : 0) + 7 * (tmp1 - 1) + tmp2 - this.firstWeek;
                    if (this[iIndex] && this[iIndex].solarFestival) {
                        this[iIndex].solarFestival += RegExp.$5 + '  ';
                    }
                }

        //农历节日
        for (i in me.lFtv)
            if (me.lFtv[i].match(/^(\d{2})(.{2})([\s\*])(.+)$/)) {
                tmp1 = Number(RegExp.$1) - firstLM;
                if (tmp1 == -11) tmp1 = 1;
                if (tmp1 >= 0 && tmp1 < n) {
                    tmp2 = lDPOS[tmp1] + Number(RegExp.$2) - 1;
                    if (tmp2 >= 0 && tmp2 < this.length) {
                        this[tmp2].lunarFestival += RegExp.$4 + '  ';
                        if (RegExp.$3 == '*') this[tmp2].color = 'red';
                    }
                }
            }

        //复活节只出现在3或4月
        if (m == 2 || m == 3) {
            var estDay = new me.easter(y);
            if (m == estDay.m)
                this[estDay.d - 1].solarFestival = this[estDay.d - 1].solarFestival + ' 复活节(Easter Sunday)';
        }

        if (m == 2) {
            //this[30].solarFestival += unescape('%u300A%u6781%u54C1%u65E5%u5386%u300B%u6B63%u5F0F%u53D1%u5E03');
        }
        //黑色星期五
        if ((this.firstWeek + 12) % 7 == 5)
            this[12].solarFestival += '黑色星期五';

        //今日
        if (y == tY && m == tM) this[tD - 1].isToday = true;
    },
	easter: function(y){ // 返回该年的复活节(春分后第一次满月周后的第一主日)
		var me = $.ui.cal_perpetual0_1.global;
		
		var lMlen;
        var term2 = me.sTerm(y, 5); //取得春分日期
        var dayTerm2 = new Date(Date.UTC(y, 2, term2, 0, 0, 0, 0)); //取得春分的公历日期控件(春分一定出现在3月)
        var lDayTerm2 = new me.Lunar(dayTerm2); //取得取得春分农历

        if (lDayTerm2.day < 15) //取得下个月圆的相差天数
            lMlen = 15 - lDayTerm2.day;
        else
            lMlen = (lDayTerm2.isLeap ? me.leapDays(y) : me.monthDays(y, lDayTerm2.month)) - lDayTerm2.day + 15;

        //一天等于 1000*60*60*24 = 86400000 毫秒
        var l15 = new Date(dayTerm2.getTime() + 86400000 * lMlen); //求出第一次月圆为公历几日
        var dayEaster = new Date(l15.getTime() + 86400000 * (7 - l15.getUTCDay())); //求出下个周日

        this.m = dayEaster.getUTCMonth();
        this.d = dayEaster.getUTCDate();

    },
	cDay: function(d){ // 中文日期
		var me = $.ui.cal_perpetual0_1.global;
        var s;

        switch (d) {
            case 10:
                s = '初十'; break;
            case 20:
                s = '二十'; break;
                break;
            case 30:
                s = '三十'; break;
                break;
            default:
                s = me.nStr2[Math.floor(d / 10)];
                s += me.nStr1[d % 10];
        }
        return (s);
    },
	getAstro: function(month, day){ // 计算星座
        var s = "魔羯水瓶双鱼白羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
        var arr = [20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22];
        return s.substr(month * 2 - (day < arr[month - 1] ? 2 : 0), 2);
    },
	FormatLunarMonth: function(iLunarMonth){ // 将农历iLunarMonth月格式化成农历表示的字符串
        var szText = new String("正二三四五六七八九十");
        var strMonth;
        if (iLunarMonth <= 10) {
            strMonth = szText.substr(iLunarMonth - 1, 1);
        }
        else if (iLunarMonth == 11) strMonth = "十一";
        else strMonth = "十二";
        return strMonth + "月";
    },
	FormatLunarDay: function(iLunarDay){ // 将农历iLunarDay日格式化成农历表示的字符串
        var szText1 = new String("初十廿三");
        var szText2 = new String("一二三四五六七八九十");
        var strDay;
        if ((iLunarDay != 20) && (iLunarDay != 30)) {
            strDay = szText1.substr((iLunarDay - 1) / 10, 1) + szText2.substr((iLunarDay - 1) % 10, 1);
        }
        else if (iLunarDay != 20) {
            strDay = szText1.substr(iLunarDay / 10, 1) + "十";
        }
        else {
            strDay = "二十";
        }
        return strDay;
    }
};
}(jQuery));
