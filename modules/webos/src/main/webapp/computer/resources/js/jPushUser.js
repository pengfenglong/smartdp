/*
 * jPushUser.js
 * name:xiaojia 小嘉
 * email:iatt@qq.com
 * qq:273142650
 * time:2012.4.3 14:38
*/

var jPushUser = {

	data: {

		userinfo: null

	},

	refresh: function () {

		this.data.userinfo = null;

	},

	userState: function () {

		return this.userinfo().find('add:[key=state]').attr('value');

	},

	userinfo: function () {

		if (this.data.userinfo != null) {

			return this.data.userinfo;

		} else {

			var _userinfo;

			$.ajax({

				type: 'GET',

				url: jPush.data.Config.userinfo,

				cache: false,

				async: false,

				dataType: 'xml',

				success: function (xmlHttp) {

					_userinfo = $(xmlHttp).find('UserInfo');

				}

			});

			this.data.userinfo = _userinfo;

			return this.data.userinfo;

		}

	},

	login: function (json) {

		var loginBut = $('#login .mid-login .login');

		var regBut = $('#login .registered a');

		$.ajax({

			type: 'POST',

			url: jPush.data.Config.login,

			data: 'user=' + json.user + '&pass=' + json.pass,

			cache: false,

			dataType: 'xml',

			beforeSend: function () {

				loginBut.attr('disabled', true);

				regBut.html(jPushDefaultLanguage.loginLoading);

				loginBut.attr('class', 'login-gray');

			},

			success: function (xmlHttp) {

				var xml = $(xmlHttp);

				var zIndex = jQuery.jLayer.zIndex;

				var fn = function () {

					jQuery.jLayer.zIndex = zIndex;

					loginBut.attr('disabled', false);

					loginBut.attr('class', 'login');

					regBut.html(jPushDefaultLanguage.registered);

				}

				jQuery.jLayer.zIndex = 10002;

				var config = {

					subject: xml.find('explain').text(),

					opacity: 0,

					sure: fn,

					close: fn

				};

				if (xml.find('state').text() != 200) {

					jQuery.jLayer.alert(config);

				} else {

					window.location.reload();

				}

			}

		});

	}

}