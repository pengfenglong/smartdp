/*
 * model.js
 * name:xiaojia
 * email:iatt@qq.com
 * qq:273142650
 * time:2012.6.15 11:20
*/

$(document).ready(

	function () {

		jAdminModel.modelMain();

		document.body.onresize = function () {

			jAdminModel.modelMain();

		}

	}

);

var jAdminModel = {

	modelMain: function () {

		$.browser.msie ? $scroll = 0 : $scroll = 0;

		margin = $(document.body).width() * 0.05 - $scroll;

		margin > 40 ? margin = 30 : '';

		margin < 20 ? margin = 15 : '';

		bWidth = parseInt($(document.body).width() - margin);

		$('#model-main').css('width', bWidth + 'px');

	}

}

jQuery.fn.extend({

	jTextLimit: function (obj) {

		var len, num, temp;

		var _this = $(this);

		var obj = $(obj);

		var temp = obj.html();

		var limit = temp.replace(/.*\{(\d*)\}.*/ig, '$1');

		var fn = function (len) {

			obj.html(temp.replace(/{num}/ig, len));

		}

		temp = temp.replace(/(.*)\{\d*\}(.*)/ig, '$1' + limit + '$2');

		obj.html(temp.replace(/{num}/ig, 0));

		if ($.browser.msie) {

			_this.get(0).attachEvent("onpropertychange", function (o) {

				len = o.srcElement.value.length;
				
				fn(len);

			});

		} else {

			_this.get(0).addEventListener("input", function (o) {

				len = o.target.value.length;
				
				fn(len);

			}, false);

		}

	}

});