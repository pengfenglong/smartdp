(function(window, document, $){
	
$(document).ready(function(){
	/**
	 * document加载完毕后初始化颜色控件
	 */
	$(".color-picker").miniColors({
		letterCase: 'uppercase',
		change: function(hex, rgb) {
			logData(hex, rgb);
		}
	});
});

/**
 * 加载颜色选择日志
 */
function logData(hex, rgb) {
	logger.debug('HEX: ' + hex + ' (RGB: ' + rgb.r + ', ' + rgb.g + ', ' + rgb.b + ')');
}
})(window, document, jQuery);