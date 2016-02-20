/*
* jPushTemplate.js
* name:xiaojia 小嘉
* email:iatt@qq.com
* qq:273142650
* time:2012.4.2 16:44
*/

jPushTemplate = {

	debug: false,

	data: {

		templateConfig: null,

		serviceList: [],

		defaultPage: 'default'

	},

	refresh: function () {

		this.data.templateConfig = null;

	},

	init: function () {

		this.getTemplate();

		this.getStyle();

		this.getServiceList();

		this.loadFileNum();

		this.getServiceFile(0);

	},

	loadFileNum: function () {

		jPush.loadingData.template.all = this.data.serviceList.length;

	},

	load: function (key) {

		if (typeof key == 'undefined') {

			key = 'default';

		}

		this.defaultPage = key;

		this.init();

	},

	getStyle: function () {


		var url = "resources/css/default.css";

		if (document.createStyleSheet) {

			document.createStyleSheet(url);

		} else {

			$('<link href="' + url + '" rel="stylesheet" type="text/css" />').appendTo('head');

		}

	},

	getServiceList: function () {

		var _this = this;
		
		_this.data.serviceList.push("resources/js/jQuery.jPlayer.0.0.1.js");
		_this.data.serviceList.push("resources/js/jquery.easing.1.3.js");
		_this.data.serviceList.push("resources/js/jPushDefault.js");
		_this.data.serviceList.push("resources/js/jPushInit.js");
		_this.data.serviceList.push("resources/js/jPushApplication.js");
		_this.data.serviceList.push("resources/js/jPushLanguage.js");
		_this.data.serviceList.push("resources/js/jPushDocumentReady.js");
		_this.data.serviceList.push("resources/js/jPushPlugin.js");

	},

	getServiceFile: function (num) {

		if (num >= this.data.serviceList.length) {

			return;

		}

		var script = jPush.appendScript(this.data.serviceList[num]);

		jPush.ready(script, function () {

			jPush.loadingSuccess('template');

			jPushTemplate.getServiceFile(++num);

		});

	},

	getTemplate: function () {

		var _this = this;
		
		_this.getTemploatePages("page/head.html", "head");
		
		_this.getTemploatePages("page/body.html", "body");

	},

	getTemploatePages: function (url, node) {

		$.ajax({

			type: 'GET',

			url: url,

			async: true,

			cache: !this.debug,

			dataType: 'html',

			success: function (xmlHttp) {

				$(xmlHttp).appendTo(node);

			}

		});

	}
}