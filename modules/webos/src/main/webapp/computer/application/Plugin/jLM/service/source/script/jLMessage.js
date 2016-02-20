/*
 * jBrowser.js
 * name:xiaojia 小嘉
 * email:iatt@qq.com
 * qq:273142650
 * time:2012.6.28 14:37
*/

$(document).ready(

	function () {

		jLMessage.init();

		$(window).bind('resize',

			function () {

				jBrowser.bodyHeight();

			}

		);

	}

);

	var jLMessage = {

		data: {

			socket: null,

			cookieName: 'jLMessageSessionId'

		},

		init: function () {

			this.identification();

			this.bodyHeight();

			this.userinfo();

			this.socket();

		},

		identification: function () {

			if ($.cookies.get(this.data.cookieName) == null) {

				var time = new Date();

				$.cookies.set(this.data.cookieName, escape(Math.random() + time).replace(/[\.\%\D]/ig, ''));

			}

		},

		socket: function () {

			if (typeof io == 'undefined') {

				top.$.jLayer.alert('服务器连接失败');

				return;

			}

			this.ConnectServer();

		},

		ConnectServer: function () {

			var _this = this;

			this.data.socket = io.connect('http://192.168.1.40:32100');

			this.data.socket.on('connect', function () {

				_this.RegUser();

				_this.monitoring();

			});

		},

		RegUser: function () {

			this.data.socket.emit('RegUser', { key: $.cookies.get(this.data.cookieName), nickname: escape($('#head .nickname input').val()), headimg: escape($('#head .head img').attr('src')) });
			
		},

		monitoring: function () {

			var _this = this;

			this.data.socket.on('showUserList', function (json) {

				_this.addUser(json);

			});

			this.data.socket.on('deleteUser', function (json) {

				_this.removeUser(json);

			});

			this.data.socket.on('insertUser', function (key) {

				_this.addUser(key);

			});

			this.renewID();

		},

		addUser: function (json) {

			var _this = this;

			var body = $('#body ul');

			for (var val in json) {

				var exp = '';

				if (val == $.cookies.get(jLMessage.data.cookieName)) {

					exp = '（自己）';

				}

				var user = $('<li key="' + val + '"><div class="head"><img src="' + unescape(json[val].headimg) + '" /></div><div class="nick">' + unescape(json[val].nickname) + exp + '</div></li>').appendTo(body);

				_this.toMessage(user, val);

			}

		},

		removeUser: function (key) {

			var body = $('#body ul');

			body.find('li:[key=' + key + ']').remove();

		},

		renewID: function () {

			jLMessage.data.socket.emit('renewID', $.cookies.get(jLMessage.data.cookieName));

			setTimeout(jLMessage.renewID, 5000);

		},

		userinfo: function () {

			$('#head .head img').attr('src', top.jPushUser.userinfo().find('add:[key=headImage]').attr('value'));

			$('#head .nickname input').val('游客');

		},

		bodyHeight: function () {

			var height = $('#main').height() - $('#head').height() - $('#tab').height();

			$('#body').css('height', height + 'px');

		},

		toMessage: function (user, key) {

			var _this = this;

			var url = 'application/Plugin/jLM/service/chat.html';

			user.on('click', function () {

				var id = key;

				var name = user.find('.nick').html();

				var head = user.find('.head img').attr('src');

				var par = '#key=' + escape(key) + '&headimg=' + escape(head) + '&nickname=' + escape(name);

				top.jPushApplication.getLayer({

					id: id,

					title: name,

					bodyBG: '',

					MenuData: {

						name: name,

						icon: head

					},

					width: '550px',

					height: '500px',

					minWidth: '550',

					minHeight: '500',

					size: 0

				}, url + par);

			});

		}

	}