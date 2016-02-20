(function(window, document, pframe) {'use strict';
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			return unescape(r[2]);
		}
		return null;
	};
	pframe.util.setUrlParam = function(destiny, par, par_value) {
		var pattern = par + '=([^&]*)';
		var replaceText = par + '=' + par_value;
		if (destiny.match(pattern)) {
			var tmp = '/\\' + par + '=[^&]*/';
			tmp = destiny.replace(eval(tmp), replaceText);
			return (tmp);
		} else {
			if (destiny.match('[\?]')) {
				return destiny + '&' + replaceText;
			} else {
				return destiny + '?' + replaceText;
			}
		}
		return destiny + '\n' + par + '\n' + par_value;
	};
