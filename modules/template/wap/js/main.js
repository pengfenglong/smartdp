/*************************************************头部图片切换开始************************************
var myScroll;
function loaded() {
	myScroll = new iScroll('wrapper', {
		snap : true,
		momentum : false,
		hScrollbar : false,
		onScrollEnd : function() {
			document.querySelector('#indicator > li.active').className = '';
			document.querySelector('#indicator > li:nth-child('
					+ (this.currPageX + 1) + ')').className = 'active';
		}
	});
}
document.addEventListener('DOMContentLoaded', loaded, false);

var count = document.getElementById("thelist").getElementsByTagName("img").length;

var count2 = document.getElementsByClassName("menuimg").length;
for (i = 0; i < count; i++) {
	document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:"
			+ document.body.clientWidth + "px";

}
document.getElementById("scroller").style.cssText = " width:"
		+ document.body.clientWidth * count + "px";

setInterval(function() {
	//myScroll.scrollToPage('next', 0, 400, count);
}, 3500);
window.onresize = function() {
	for (i = 0; i < count; i++) {
		document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:"
				+ document.body.clientWidth + "px";

	}
	document.getElementById("scroller").style.cssText = " width:"
			+ document.body.clientWidth * count + "px";
}
************************************************头部图片切换结束***************************************/




/*************************************************中间分类列表开始***************************************/
var mySwiper = new Swiper('.swiper-container', {
	pagination : '.pagination',
	loop : true,
	grabCursor : true,
	paginationClickable : true
})
$('.arrow-left').on('click', function(e) {
	e.preventDefault()
	mySwiper.swipePrev()
})
$('.arrow-right').on('click', function(e) {
	e.preventDefault()
	mySwiper.swipeNext()
})

/*************************************************中间分类列表结束***************************************/





/*************************************************底部菜单开始***************************************/
function displayit(n) {
	for (i = 0; i < 4; i++) {
		if (i == n) {
			var id = 'menu_list' + n;
			if (document.getElementById(id).style.display == 'none') {
				document.getElementById(id).style.display = '';
				document.getElementById("plug-wrap").style.display = '';
			} else {
				document.getElementById(id).style.display = 'none';
				document.getElementById("plug-wrap").style.display = 'none';
			}
		} else {
			if ($('#menu_list' + i)) {
				$('#menu_list' + i).css('display', 'none');
			}
		}
	}
}
function closeall() {
	var count = document.getElementById("top_menu").getElementsByTagName("ul").length;
	for (i = 0; i < count; i++) {
		document.getElementById("top_menu").getElementsByTagName("ul").item(i).style.display = 'none';
	}
	document.getElementById("plug-wrap").style.display = 'none';
}
/*************************************************底部菜单结束***************************************/



/*************************************************微信分享开始***************************************/
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	WeixinJSBridge.call('hideToolbar');
});

window.shareData = {
	"moduleName" : "Index",
	"moduleID" : '0',
	"imgUrl" : "http://demo.pigcms.cn/tpl/static/images/homelogo.png",
	"timeLineLink" : "http://demo.pigcms.cn/index.php?g=Wap&m=Index&a=index&token=qdsqzk1400838003",
	"sendFriendLink" : "http://demo.pigcms.cn/index.php?g=Wap&m=Index&a=index&token=qdsqzk1400838003",
	"weiboLink" : "http://demo.pigcms.cn/index.php?g=Wap&m=Index&a=index&token=qdsqzk1400838003",
	"tTitle" : "欢迎光临我们的微网站!",
	"tContent" : "欢迎您进入我们的微网站，在对黄框里首页 或者 home —— 当用户输入该关键词时，将会触发此回复。"
};
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	WeixinJSBridge.on('menu:share:appmessage', function(argv) {
		shareHandle('friend');
		WeixinJSBridge.invoke('sendAppMessage', {
			"img_url" : window.shareData.imgUrl,
			"img_width" : "640",
			"img_height" : "640",
			"link" : window.shareData.sendFriendLink,
			"desc" : window.shareData.tContent,
			"title" : window.shareData.tTitle
		}, function(res) {
			_report('send_msg', res.err_msg);
		})
	});

	WeixinJSBridge.on('menu:share:timeline', function(argv) {
		shareHandle('frineds');
		WeixinJSBridge.invoke('shareTimeline', {
			"img_url" : window.shareData.imgUrl,
			"img_width" : "640",
			"img_height" : "640",
			"link" : window.shareData.sendFriendLink,
			"desc" : window.shareData.tContent,
			"title" : window.shareData.tTitle
		}, function(res) {
			_report('timeline', res.err_msg);
		});
	});

	WeixinJSBridge.on('menu:share:weibo', function(argv) {
		shareHandle('weibo');
		WeixinJSBridge.invoke('shareWeibo', {
			"content" : window.shareData.tContent,
			"url" : window.shareData.sendFriendLink,
		}, function(res) {
			_report('weibo', res.err_msg);
		});
	});
}, false)

function shareHandle(to) {
	var submitData = {
		module : window.shareData.moduleName,
		moduleid : window.shareData.moduleID,
		token : 'qdsqzk1400838003',
		wecha_id : 'oAdCqjj5U6u-35m1rUwJjfCnsuz0',
		url : window.shareData.sendFriendLink,
		to : to
	};
	$
			.post(
					'/index.php?g=Wap&m=Share&a=shareData&token=qdsqzk1400838003&wecha_id=oAdCqjj5U6u-35m1rUwJjfCnsuz0',
					submitData, function(data) {
					}, 'json')
}
/*************************************************微信分享结束***************************************/


/*************************************************播放音乐结束*************************************
var playbox = (function() {
	// author:eric_wu
	var _playbox = function() {
		var that = this;
		that.box = null;
		that.player = null;
		that.src = null;
		that.on = false;
		//
		that.autoPlayFix = {
			on : true,
			// evtName: ("ontouchstart" in window)?"touchend":"click"
			evtName : ("ontouchstart" in window) ? "touchstart" : "mouseover"

		}

	}
	_playbox.prototype = {
		init : function(box_ele) {
			this.box = "string" === typeof (box_ele) ? document
					.getElementById(box_ele) : box_ele;
			this.player = this.box.querySelectorAll("audio")[0];
			this.src = this.player.src;
			this.init = function() {
				return this;
			}
			this.autoPlayEvt(true);
			return this;
		},
		play : function() {
			if (this.autoPlayFix.on) {
				this.autoPlayFix.on = false;
				this.autoPlayEvt(false);
			}
			this.on = !this.on;
			if (true == this.on) {
				this.player.src = this.src;
				this.player.play();
			} else {
				this.player.pause();
				this.player.src = null;
			}
			if ("function" == typeof (this.play_fn)) {
				this.play_fn.call(this);
			}
		},
		handleEvent : function(evt) {
			this.play();
		},
		autoPlayEvt : function(important) {
			if (important || this.autoPlayFix.on) {
				document.body.addEventListener(this.autoPlayFix.evtName, this,
						false);
			} else {
				document.body.removeEventListener(this.autoPlayFix.evtName,
						this, false);
			}
		}
	}
	//
	return new _playbox();
})();

playbox.play_fn = function() {
	this.box.className = this.on ? "btn_music on" : "btn_music";
}
$(document).ready(function() {
	//playbox.init("playbox");
	//setTimeout(function() { // IE if(document.all) {
	// document.getElementById("playbox").click(); } // 其它浏览器 else { var e =
	// document.createEvent("MouseEvents"); e.initEvent("click", true, true);
	// document.getElementById("playbox").dispatchEvent(e); } }, 2000);

});
**********************************************播放音乐结束***************************************/
