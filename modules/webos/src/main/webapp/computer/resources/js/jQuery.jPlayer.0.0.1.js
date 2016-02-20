/*
* jQuery.jPlayer.0.0.1.js
* name:xiaojia 小嘉
* email:iatt@qq.com
* qq:273142650
* time:2012.10.9 10:06
*/

"use strict";

jQuery.extend({

	jPlayer: {
		
		debug: true,
	
		data: {
			
			defaultTemplate: 'default',
			
			normal: null,
			
			mini: null,

			config: null,

			images: null,
			
			state: {

				objectReady: new jQuery.Hashtable(),

				template: false

			}
			
		},
	
		init: function (container, json) {
			
			this.getTemplate();
			
			var boxID = this.showTemplate(container, json);
			
			var ObjectID = this.loadDecoding(json, boxID);
			
			this.data.state.objectReady.add(ObjectID, 0);
			
			return {boxID: boxID, ObjectID: ObjectID};
			
		},
		
		loadDecoding: function (json, boxID) {
			
			var swf = $('<div style="opacity:0;position:fixed;top:-999px;left:-999px;"></div>').appendTo(document.body);
			
			var ObjectID = '__jPlayerObjectID_' + Math.random().toString().substring(2, 5);
			
			if (typeof json.url == 'undefined') {
				
				json.url = 'jPlayerDecoding.swf';
				
			}
			
			var param = escape('param = {"ObjectID": "' + ObjectID + '", "boxID": "' + boxID + '", "callback": "' + json.callback + '"}');
			
			$.jLoadSwf.load(swf, {
			
				id: ObjectID,
				
				url: json.url,
				
				width: '100%',
				
				height: '100%',
				
				param: [
				
					['FlashVars', 'callback=$.jPlayer.callback&param=' + param],
					
					['wmode', 'transparent']
					
				]
				
			});
			
			return ObjectID;
			
		},
		
		callback: function (param) {
			
			param = eval(unescape(param));
			
			var config = this.config();
			
			var swf = document.getElementById(param['ObjectID']);
						
			var box = $('#' + param['boxID']);

			this.loadSound(swf, 'http://www.magesj.com/1.mp3');
			
			this.progressBar(swf, box, config);
			
			this.data.state.objectReady.add(param.ObjectID, 1);
			
			if (param.callback != 'undefined') {
				
				eval(param.callback)();
				
			}
			
		},
		
		getState: function (ObjectID) {
			
			return this.data.state.objectReady.get(ObjectID);
			
		},
		
		loadSound: function (swf, url) {
			
			if (typeof swf == 'string') {
				
				swf = document.getElementById(swf);
			
			}
			
			swf.asFunationAddSound(url);
			
		},
		
		getValue: function (config, key) {
			
			return config.find('add:[key=' + key + ']').attr('value');
			
		},
		
		progressBar: function (swf, box, config) {
			
			this.setLoadLength(config, swf);
			
			this.setPlayerLength(config, swf);
			
		},
		
		setPlayerLength: function (config, swf) {
			
			var _this = this;
			
			var playerLenth = this.getValue(config, 'playerLenth');
			
			var len = swf.asFunationGetSoundPercentage() * 100;
			
			if (isNaN(len)) {
				
				len = 0;
				
			} else if (len >= 100) {
				
				len = 100;
				
			}
			
			$(playerLenth).css('width', len + '%');
				
			var fn = function () {
			
				_this.setPlayerLength(config, swf);
			
			}
			
			setTimeout(fn, 1000);
			
		},
		
		setLoadLength: function (config, swf) {
			
			var _this = this;
			
			var loadLength = this.getValue(config, 'loadLength');
			
			var len = swf.asFunationGetBytePercentage() * 100;
			
			if (isNaN(len)) {

				len = 0;

			} else if (len >= 100) {
				
				len = 100;
				
			}
			
			$(loadLength).css('width', len + '%');
				
			var fn = function () {
			
				_this.setLoadLength(config, swf);
			
			}
			
			setTimeout(fn, 1000);
			
		},
		
		config: function () {

			var config = this.data.config.clone();

			return config;

		},
		
		soundVolume: function (obj, val) {
			
			var obj = $(obj).get(0);
			
			if (typeof val == 'undefined') {
				
				return obj.asFunctionGetSoundVolume();
				
			} else {
			
				obj.asFunctionSetSoundVolume(val);
			
			}
			
		},
		
		showTemplate: function (container, json) {
		
			var boxID = '__jPlayerBoxID_' + Math.random().toString().substring(2, 5);
		
			var box = $('<div id="' + boxID + '"></div>').appendTo(container);
		
			box.html(this.data[json.style]);
			
			return boxID;
		
		},
		
		getTemplate: function () {

			var _this = this;

			var _images, _xmlData;

			if (!_this.data.state.template) {

				var url = jQuery.jStyle.load('jPlayer');

				jQuery.ajax({

					type: 'GET',

					url: url,

					cache: !this.debug,

					async: false,

					dataType: 'xml',

					success: function (xmlHttp) {

						_xmlData = $(xmlHttp).find('template:[key=' + _this.data.defaultTemplate + ']');

					}

				});

				_images = jQuery.jStyle.path + _xmlData.find('style').attr('images');

				_xmlData.find('style').each(

					function () {

						var style = jQuery(this).text();

						var opacity = style.match(/opacity:(\d?\.\d+);/);

						if (opacity != null && ! +"\v1") {

							style = style.replace(/opacity:(\d?\.\d+);/ig, function (s, a) { return 'filter:alpha(opacity=' + (a * 100) + ');' });

						}

						style = style.replace(/{images}/ig, _images);

						jQuery(document.body).append('<style>' + style + '</style>');

					}

				);

				this.data.normal = _xmlData.find('body normal').text();

				this.data.mini = _xmlData.find('body mini').text();

				this.data.config = _xmlData.find('config');

				this.data.images = _images;

				this.data.state.template = true;

			}
			
		},
	
		load: function (container, json) {
		
			container = jQuery(container);
			
			if (typeof json == 'undefined') {
				
				json = {};
				
			}
			
			if (typeof json.style == 'undefined') {
				
				json.style = 'normal';
				
			}
		
			return this.init(container, json);
		
		}
		
	}
	
});