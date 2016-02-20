/*
* jPushInit.js
* name:xiaojia 小嘉
* email:iatt@qq.com
* qq:273142650
* time:2012.4.2 16:45
*/

var jPushInit = {

	data: {

		theme: {},

		defaultScreen: 2,
		
		jPlayerData: {}

	},

	init: function () {

		this.applicationMouse();

		this.MenuLeft();

		this.screenMenu();

		this.masks();

		this.search();

		this.config();

		this.resize();

		this.chrome();

		this.getTheme();

		this.createTheme(true);
		
		this.myFm();
		
		this.jPlayer();

	},
	
	myFm: function () {
		
		$('#system-myfm').on('click', function (e) {
		
			var target = $(e.target);
			
			if (!target.is('.app-system-myfm *')) {
		
				var check = $(this).hasClass('app-menu-li-hover');
			
				if (check) {
					
					$(this).removeClass('app-menu-li-hover');
					
					$(this).find('.app-system-myfm').hide();
					
				} else {
				
					$(this).addClass('app-menu-li-hover');
					
					$(this).find('.app-system-myfm').show();
				
				}
			
			}
		
		});
		
	},
	
	showFmList: function () {
		
		var dom = $('#system-myfm .app-system-myfm-musicList ul');
		
		var rand = parseInt(Math.random() * (15 - 5 + 1) + 5);
		
		$.getJSON('http://moe.fm/listen/playlist?api=json&amp;api_key=4baa3f7c04e30ddb3164720cf4533b650507cb2fc&amp;perpage='+ rand + '&callback=?', function (json, status) {
			
			jPushInit.setFmList(json, dom);
			
			jQuery.jScroll.load(dom, {

				margin: 'auto 0px 0 0'

			});

		});
		
	},
	
	setFmList: function (json, dom) {
		
		var _this = this;
		
		var response = json.response;
		
		dom.html('');
		
		if (typeof response.error == 'undefined') {
			
			var list = response['playlist'];
			
			for (var i = 0; i < list.length; i++) {
				
				if (list[i]['file_type'] == 'mp3') {
				
					var li = $('<li title="' + list[i]['title'] + '"><div class="app-system-myfm-musicList-title"><div class="app-s-m-m-t-title">' + list[i]['sub_title'] + '<div><span>' + list[i]['stream_time'] + '</span></div><div class="app-system-myfm-musicList-explain">' + list[i]['title'] + '</div></li>').appendTo(dom);
					
					_this.setMusicEvent(li, list[i], dom);
				
				}
				
			}
		
		}
		
	},
	
	setMusicEvent: function (li, json, dom) {
	
		var _this = this;
	
		li.on('click', function () {
		
			dom.find('li').removeClass('app-system-myfm-musicList-player');
		
			$(this).addClass('app-system-myfm-musicList-player');
		
			$.jPlayer.loadSound(_this.data.jPlayerData.ObjectID, json.url);
		
		});
	
	},
	
	dateFormat: function (ss) {
		
		var date = new Date(parseInt(ss) * 1000);
		
		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
		
		var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		
		var hour = date.getHours( )< 10 ? "0" + date.getHours() : date.getHours();
		
		var mints = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
		
		var sec = date.getSeconds() < 10 ? "0" + date.getSeconds() :date.getSeconds();
		
		return mints + ":" + sec;
		
	},
	
	jPlayer: function () {
	
		var rand = parseInt(Math.random() * (6 - 1 + 1) + 1);
	
		this.data.jPlayerData = $.jPlayer.load('#player', {
		
			style: 'mini',
			
			url: '/Flex/jPlayer/bin-release/jPlayer.swf',
			
			callback: 'jPushInit.showFmList'
				
		});
	
	},

	resize: function () {

		$(window).bind('resize',

			function () {

				jPushInit.MenuLeft();

				jPushInit.autoTheme();

				jPushInit.masks();

			}

		);

	},

	chrome: function () {

		if (navigator.userAgent.toLowerCase().indexOf("chrome") > -1) {

			$(window).scroll(

				function () {

					$(this).scrollTop(0);

				}

			);

		}

	},

	ie6PNG: function () {

		if (typeof DD_belatedPNG != 'undefined' && $.browser.msie) {

			DD_belatedPNG.fix('.ie6PNG,.ie6PNG:hover');

		}

	},

	config: function () {

		var _this = this;
		
		var lang = jPushDefaultLanguage;

		var SystemIcon = 'resources/images/icon/tools.png';

		$('#login .registered a').attr('href', jPush.data.Config.registered);

		$('#login form').bind('submit',

			function () {

				jPushInit.loginAjax();

				return false;

			}

		);

		$('#login .close').click(

			function () {

				$('#masks').remove();

				$('#login').hide();

			}

		);

		$('.oper-list .o1').attr('href', jPushUser.userinfo().find('add:[key=inputMethod]').attr('value'));

		$('.oper-list .o2').bind('click',

			function () {
				
				var objectID = _this.data.jPlayerData.ObjectID;

				if ($.jPlayer.getState(objectID) == 0) {
					
					return;
					
				}

				if ($(this).attr('class') == 'o2') {
					
					$(this).attr('class', 'o5');
					
					$(this).attr('title', lang.clearVoice);
					
					$(this).attr('closeVolume', $.jPlayer.soundVolume('#' + objectID));
					
					$.jPlayer.soundVolume('#' + objectID, 0);
					
				} else {
					
					$(this).attr('class', 'o2');
					
					$(this).attr('title', lang.voice);
					
					$.jPlayer.soundVolume('#' + objectID, $(this).attr('closeVolume'));
				
				}

			}

		);

		$('.oper-list .o3').bind('click',

			function () {

				if (jPushUser.userState() == 200) {

					var layerData = {

						id: 'jPush_SystemSet',

						title: jPushDefaultLanguage.SystemSet,

						MenuData: {

							name: jPushDefaultLanguage.SystemSet,

							icon: SystemIcon

						},

						width: '800px',

						height: '450px',

						size: 0

					};

					var url = "page/SystemSet.html";

					jPushApplication.getLayer(layerData, url, function () { });

				} else {

					jPushInit.login();

				}

			}

		);

		$('.oper-list .o4').bind('click',

			function () {

				_this.applicationCenter('Theme');

			}

		);

	},

	loginAjax: function () {

		var user, pass;

		user = $('#login .input-bor:eq(0) input').val();

		pass = $('#login .input-bor:eq(1) input').val();

		jPushUser.login({ user: user, pass: pass });

	},

	search: function () {

		var val;

		var text = jPushDefaultLanguage.searchExplain;

		$('#search input').bind('blur',

			function () {

				val = $(this).val();

				if (val.length <= 0) {

					$(this).val(text);

				}

			}

		);

		$('#search input').bind('focus',

			function () {

				val = $(this).val();

				if (val == text) {

					$(this).val('');

				}

			}

		);

		$('#search a').bind('click',

			function () {

				$(this).parents('form').submit();

			}

		);

		$('#search form').bind('submit',

			function () {

				var val = $(this).find('input').val();

				var url = 'application/Plugin/browser/service/index.html#url=' + encodeURIComponent(jPushUser.userinfo().find('add:[key=search]').attr('value') + val);

				var layerData = {

					id: 'jPush_Search',

					title: jPushDefaultLanguage.searchTitle,

					MenuData: {

						name: jPushDefaultLanguage.searchTitle,

						icon: 'resources/images/icon/tools.png'

					},

					width: '1024px',

					height: '600px',

					bodyBG: ''

				}

				jPushApplication.getLayer(layerData, url);

				return false;

			}

		);

	},

	login: function () {

		var main = $('#main');

		main.append('<div id="masks"></div>');

		$('#login').show();

		var loginTop = ($(window).height() / 2 - $('#login').height() / 2 - 80);

		if (loginTop <= 0) {

			loginTop = 20;

		}

		$('#login').css({

			left: ($(window).width() / 2 - 384 / 2) + 'px',

			top: loginTop + 'px'

		});

		$('#login .top-logo').jDrag('#login', {

			top: 10,

			left: 10,

			right: $(window).width() - $('#login').width() - 14,

			bottom: $(window).height() - $('#login').height() - 10

		});

		$('#login .input-bor:eq(0) input').focus();

	},

	screenTheme: function () {

		var winW, imgW;

		var screen = this.data.defaultScreen;

		var config = this.data.theme;

		winW = $(window).width();

		imgW = config.img.width();

		config.img.stop();

		config.img.animate({

			left: (winW - imgW) / 4 * screen + 'px'

		}, 500, 'easeOutCirc');

	},

	autoTheme: function () {

		var windowW, windowH, p1, p2;

		var config = this.data.theme;

		windowW = $(window).width();

		windowH = $(window).height();

		p1 = windowW / windowH;

		p2 = config.width / config.height;

		if (p1 >= p2) {

			config.img.css({

				width: windowW + 'px',

				height: 'auto'

			});

		} else {

			config.img.css({

				width: 'auto',

				height: windowH + 'px'

			});

		}

		this.screenTheme();

	},

	createTheme: function (t) {

		var _this = this;

		var themeImg = $('#theme .theme .themeImg');

		themeImg.html('<img src="' + this.data.theme.href + '"/>');

		this.data.theme['img'] = themeImg.find('img');

		themeImg.find('img').load(

			function () {

				_this.showApplicationList();

				_this.autoTheme();

				if (typeof t != 'undefined') {

					jPush.loadingSuccess('templateData');

				}

			}

		);

	},

	getTheme: function () {

		var theme = jPushUser.userinfo().find('add:[key=theme]');

		var userState = jPushUser.userState();

		this.data.theme = {

			href: theme.attr('href'),

			width: theme.attr('width'),

			height: theme.attr('height')

		};

	},

	masks: function () {

		$('#main #masks').css({

			width: $(window).width(),

			height: $(window).height()

		});

	},

	applicationCenter: function (key) {

		var lang = jPushDefaultLanguage;

		var layerData = {

			id: 'jPush_ApplicationCenter',

			title: lang.applicationcenter,

			MenuData: {

				name: lang.applicationcenter,

				icon: 'resources/images/icon/tools.png'

			},

			width: '950px',

			height: '550px',

			size: 0

		};

		var url = "page/ApplicationCenter.html";

		jPushApplication.getLayer(layerData, url, function () {

			jPushPlugin.init(key);

		});

	},

	screenMenuSet: function () {

		this.applicationCenter();

	},

	screenMenuSearch: function (obj) {

		var l, t;

		var search = $('#search');

		obj = $(obj);

		l = obj.offset().left - 185;

		t = obj.offset().top + 33;

		search.css({

			top: t + 'px',

			left: l + 'px',

			display: 'block'

		});

		search.find('input').focus();

	},

	screenMenu: function () {

		var windowW;

		var _sM = $('#screen-menu');

		var _sMLi = _sM.find('.menu-list ul li');

		var bp = ['-334px', '-364px', '-393px', '-424px', '-454px'];

		var bphover = ['-137px', '-178px', '-217px', '-258px', '-298px'];

		var menuBg = ['-5px', '20px', '45px', '69px', '94px'];

		var _this = this;

		var headImg = _sM.find('.head-img');

		windowW = $(window).width();

		_sM.css('left', (windowW / 2 - 240 / 2) + 'px');

		headImg.append('<img src="' + jPushUser.userinfo().find('add:[key=headImage]').attr('value') + '"/>');

		headImg.find('.transparent').attr('title', jPushUser.userinfo().find('add:[key=nickname]').attr('value'));

		if (jPushUser.userState() != '200') {

			headImg.find('.transparent').on('click', jPushInit.login);

		}

		$('.exp-button .set').on('click',

			function () {

				jPushInit.screenMenuSet(this);

			}

		);

		$('.exp-button .search').on('click',

			function () {

				jPushInit.screenMenuSearch(this);

			}

		);

		$(document).on('mousedown',

			function (e) {

				var target = $(e.target);

				if (!target.is('#search *')) {

					$('#search').hide();

				}

			}

		);

		_sMLi.on('mouseover',

			function () {

				$(this).css('background-position', bphover[$(this).index()] + ' -129px');

			}

		);

		_sMLi.on('mouseout',

			function () {

				$(this).css('background-position', bp[$(this).index()] + ' -129px');

			}

		);

		_sMLi.on('click',

			function () {

				var _this = $(this).parent().parent().find('.menu-bg');

				var _index = $(this).index();

				var theme;

				_this.stop();

				_this.animate({ left: menuBg[_index] }, 200);

				$('#app-main ul').stop();

				if ($.browser.msie) {

					$('#app-main ul').css('display', 'none');

					$('#app-main ul:eq(' + _index + ')').css('display', '');

				} else {

					$('#app-main ul').fadeOut(300);

					$('#app-main ul:eq(' + _index + ')').fadeIn(500);

				}

				theme = $('#theme .theme .themeImg img');

				jPushInit.data.defaultScreen = _index;

				jPushInit.screenTheme();

			}

		);

		$('#screen-menu *').jDrag('#screen-menu', {

			top: 10,

			left: 10,

			right: $(window).width() - $('#screen-menu').width() - 10,

			bottom: $(window).height() - $('#screen-menu').height() - 50,

			up: function () {

				$('#search').hide();

			}

		});

		$('#main').mousewheel(function (e, i) {

			var target = $(e.target);
			
			if (target.is('.app-menu-li *')) {
				
				return;
				
			}

			var screenIndex = _this.data.defaultScreen;

			if (i < 0) {

				if (screenIndex < 5) {

					_this.gotoScreen(screenIndex + 1);

				}

			} else {

				if (screenIndex > 0) {

					_this.gotoScreen(screenIndex - 1);

				}

			}

		});

	},

	gotoScreen: function (index) {

		$('#screen-menu .menu-list ul li').eq(index).click();

	},

	showApplicationList: function () {

		var screenIndex = 2;

		var userDefault = jPushUser.userinfo().find('add:[key=defaultScreen]').attr('value');

		var app = $(jPushApplication.hash());

		if (app.size() > 0) {

			scrrenIndex = jPushApplication.appScreen(app);

			app.click();

		} else {

			scrrenIndex = userDefault;

		}

		if (scrrenIndex == -1) {

			scrrenIndex = userDefault;

		}

		this.data.defaultScreen = scrrenIndex;

		this.gotoScreen(scrrenIndex);

	},

	menuBottomMouse: function (app, dom) {

		$(app).click(

			function () {

				if (jPushApplication.data.nowApp == dom || $('#' + dom).css('display') == 'none') {

					jQuery.jLayer.minimize('#' + dom);

				} else {

					jQuery('#' + dom + '_list').click();

					jPushApplication.hash = dom + '_list';

					jPushApplication.menu(dom);

				}

				jPushApplication.data.nowApp = null;

			}

		);

	},

	applicationMouse: function () {

		document.onselectstart = function () { return false; }

		$(document.body).contextmenu(

			function (event) {

				var target = $(event.target);

				if (target.is('#app-main,ul')) {

					return false;

				}

			}

		);

	},

	MenuLeft: function () {

		var mTop, mLeft, wWidth, wHeight, style;

		wWidth = $(window).width();

		wHeight = $(window).height();

		mTop = (wHeight / 2) - (569 / 2) + 'px';

		$('#menu-left').css({

			top: mTop

		});

		width = wWidth - 100;

		height = wHeight - 80;

		mTop = 50;

		mLeft = wWidth - width;

		if ($.browser.msie) {

			style = {

				width: width + 'px',

				height: height + 'px',

				top: mTop + 'px',

				left: mLeft + 'px'

			};

		} else {

			style = {

				width: height + 'px',

				height: width + 'px',

				top: mTop - ((width - height) / 2) + 'px',

				left: mLeft + ((width - height) / 2) + 'px'

			};

		}

		$('#app-main').css(style);

	}

};