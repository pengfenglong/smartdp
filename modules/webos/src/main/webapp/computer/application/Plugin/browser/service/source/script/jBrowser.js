/*
 * jBrowser.js
 * name:xiaojia 小嘉
 * email:iatt@qq.com
 * qq:273142650
 * time:2012.6.28 14:37
*/

$(document).ready(

	function () {

		jBrowser.init();

		$(window).bind('resize',

			function () {

				jBrowser.bodyHeight();

				jBrowser.addressWidth();

			}

		);

	}

);

$.fn.setCursorPosition = function (position) {

	if (this.lengh == 0) return this;

	return $(this).setSelection(position, position);

}

$.fn.setSelection = function (selectionStart, selectionEnd) {

	if (this.lengh == 0) return this;

	input = this[0];

	if (input.createTextRange) {

    	var range = input.createTextRange();

    	range.collapse(true);

    	range.moveEnd('character', selectionEnd);

    	range.moveStart('character', selectionStart);

    	range.select();

	} else if (input.setSelectionRange) {

    	input.focus();

    	input.setSelectionRange(selectionStart, selectionEnd);

	}

	return this;
}

$.fn.focusEnd = function () {

   	this.setCursorPosition(this.val().length);

}

var jBrowser = {

	init: function () {

		this.bodyHeight();

		this.addressWidth();

		this.getIco();

		this.addressOper();

		this.eventUrl();

	},

	eventUrl: function () {

		var url = this.get('url');

		if (url != null) {

			$('#url').val(url);

			$('.refresh').click();

		}

	},

	bodyHeight: function () {

		var documentHeight = $(document).height();

		$('#body').css('height', documentHeight - 61 + 'px');

	},

	addressWidth: function () {

		var documentWidth = $(document).width();

		$('#head .address').css('width', documentWidth - 160 + 'px');

		$('#head .address input').css('width', $('#head .address').width() - 72 + 'px');

	},

	get: function (name) {

		url = location.href;

		url = url.replace(/.*#/ig, '').split('&');

		for (i = 0; i < url.length; i++) {

			jQueryleft = url[i].replace(/[=](\s|\S)*/ig, '');

			if (jQueryleft == name) {

				return decodeURIComponent(url[i].replace(/(\s|\S)*[^=]*=/ig, ''));

			}

		}

		return null;

	},

	urlSubmit: function () {

		var body = $('#body iframe');

		var url = $('#url');

		var fn = function () {

			body.attr('src', url.val());

			body.load(

				function () {

					jBrowser.getIco();

				}

			);

		}

		url.val(function (i, c) {

			return c.toLowerCase();

		});

		if (url.val().substring(0, 1) == '?') {

			var search = top.JAdminBinUser().userinfo().find('add:[key=search]').attr('value');

			url.val(function (i, c) {

				return search + c.replace(/^\?/ig, '');

			});

			fn();

		} else if (url.val().length == 0 || url.val() == 'jBrowser:Home') {

			url.val('jBrowser:Home');

			jBrowser.getIco();

		} else if (url.val().search(/http\:\/\//ig) == -1) {

			url.val(function (i, c) {

				return 'http://' + c;

			});

			fn();

		} else {

			fn();

		}

		$('.ico img').attr('src', 'style/images/loading.gif');

	},

	addressOper: function () {

		$('.address form').bind('submit',

			function () {

				jBrowser.urlSubmit();

				return false;

			}

		);

		$('.address .refresh').bind('click',

			function () {

				$('.address form').submit();

			}

		);

		$('.address .search').bind('click',

			function () {

				$('#url').val('?').focus().focusEnd();

			}

		);

		$('.head-oper .back').bind('click',

			function () {

				//alert(top.document.referrer);

				$(window.frames['iframe'])[0].history.go(-1);

			}

		);

		$('.head-oper .next').bind('click',

			function () {

				$(window.frames['iframe'])[0].history.go(1);

			}

		);

		$('.address .Esc').bind('click',

			function () {

				window.event.returnValue = false;

			}

		);

	},

	getIco: function () {

		var img, ico = 'favicon.ico';

		var url = 'http://' + $('#url').val().replace(/http\:\/\//ig, '').replace(/\/.*/ig, '') + '/favicon.ico';

		if ($('#url').val() == 'jBrowser:Home') {

			$('.ico img').attr('src', ico);

		} else {

			$('.ico').each(

				function () {

					img = $(this).find('img');

					img.attr('src', url);

					img.load().error(

						function () {

							img.attr('src', ico);

						}

					);

				}

			);

		}

	}

}