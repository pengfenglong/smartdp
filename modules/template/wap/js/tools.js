(function($) {
	$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null){
			return unescape(r[2]);
		}
		return null;
	};
	$.getContextPath = function() {
		if(document.location.toString().indexOf('sinaapp') != -1){
			return 'http://weixinext.sinaapp.com';
		}else{
			var pathName = document.location.pathname;
		    var index = pathName.substr(1).indexOf("/");
		    var result = pathName.substr(0,index+1);
		    return result;
		}

	}
})(jQuery);
