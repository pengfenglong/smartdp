$(function() {

	pframe.util.importTpl('apps/navigate/tpl/app.tpl');
	pframe.define('app.navigate.model', {

	});
	pframe.define('app.navigate.view', {
		init: function() {
			$.getJSON('apps/navigate/data/app.json', function(data) {
				var tpl = pframe.util.getTpl("navigate-template");
				var template = Handlebars.compile(tpl);
				$('#navigate').html(template(data));
				$('a.navigation_a').click(function() {
					$('.menu_font').hide();
					if ($(this).next().children().length > 0) {
						$(this).next().toggle();
					} else {
						var code = $(this).attr('code');
						$('#container').load('apps/' + code);
					}
				});
				
				$('#navigate .home a.navigation_a').click();
				

				var _left = $('.navigation_a img:first').position().left + 18;
				$('.navigation_a:first').append('<div style="position:absolute;top:2px;left:' + _left +
					'px;width: 20px;height: 20px;background-color: red;-webkit-border-radius: 10px;">5</div>');
			});
		}
	});
	pframe.create('app.navigate.view');





});

function onBridgeReady() {
	WeixinJSBridge.call('hideOptionMenu');
}

if (typeof WeixinJSBridge == "undefined") {
	if (document.addEventListener) {
		document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	} else if (document.attachEvent) {
		document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
		document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	}
} else {
	onBridgeReady();
}