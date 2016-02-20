/*
* jBrowser.js
* name:xiaojia 小嘉
* email:iatt@qq.com
* qq:273142650
* time:2012.6.28 14:37
*/

$(document).ready(

	function () {

		jLMessageWin.init();

		$(window).bind('resize',

			function () {

				jBrowser.bodyHeight();

			}

		);

	}

);

	var jLMessageWin = {

		data: {

			ToMessageKey: null,

			ParentWindow: null

		},

		init: function () {

			this.getToMessageKey();

			this.getParentWindow();

			this.TextFocus();

			this.getMessage();

			this.setUserInfo();

			this.TextPost();

		},

		getMessage: function () {

			var key = this.get('key');

			var jLM = this.data.ParentWindow.jLMessage.data.ReceiveUserList;

			if (typeof jLM[key] != 'undefined') {

				for (var i = 0; i < jLM[key]['message'].length; i++) {

					var date = new Date();

					this.textAppend(jLM[key].nickname, date, jLM[key]['message'][i]);

				}

				clearTimeout(jLM[key]['animate']);

				this.data.ParentWindow.jLMessage.deleteReceive(key);

			}

		},

		get: function (name) {

			var url = location.href;

			url = url.replace(/.*[^\#]*\#/ig, '').split('&');

			for (i = 0; i < url.length; i++) {

				$left = url[i].replace(/[=].*/ig, '');

				if ($left == name) {

					return url[i].replace(/.*[^=]*=/ig, '');

				}

			}

			return '';

		},

		setUserInfo: function () {

			var body = $('#main #head');

			body.find('.info .head img').attr('src', unescape(this.get('headimg')));

			body.find('.info .nickname input').val(unescape(this.get('nickname')));

		},

		TextPost: function () {

			var _this = this;

			$('#chat-text textarea').on('keypress', function (e) {

				if (e.keyCode == 10) {

					var date = new Date();

					var myNickname = _this.data.ParentWindow.$('#head .nickname input').val() + '（自己）';

					var text = $(this).val();

					var key = _this.data.ParentWindow.$.cookies.get(_this.data.ParentWindow.jLMessage.data.cookieName);

					_this.textAppend(myNickname, date, text);

					_this.data.ParentWindow.jLMessage.data.socket.emit('message', { text: text, key: key });

					$(this).val('');

					$(this).focus();

				}

			});

		},

		textAppend: function (myNickname, date, text) {

			var nowTime = date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();

			$('#chat-record').append('<p>' + myNickname + ' ' + nowTime + '<br/>' + text + '</p>');

		},

		TextFocus: function () {

			$('#chat-text textarea').focus();

		},

		getToMessageKey: function () {

			this.data.ToMessageKey = this.get('key');

		},

		getParentWindow: function () {

			this.data.ParentWindow = top.$('#application_jLMessage iframe').get(0).contentWindow;

		}

	}