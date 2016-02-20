/***
	JavaScriptExtend.js
	name:小嘉
	qq:273142650
	email:iatt@qq.com
	date:2011.10.10
***/
	
	//in_array
	Array.prototype.in_array = function ($string) {
		
		for (var i = 0; i < this.length; i++) {
			
			if (this[i] == $string) {
				
				return i;
				
			}
		
		}
		
		return -1;
	}
	
	Array.prototype.implode = function (s) {
	
		var string = '';
	
		for (var i = 0; i < this.length; i ++) {
			
			string += this[i] + s;
			
		}
		
		var regexp = new RegExp(s + '$', 'ig');
		
		return string.replace(regexp, '');
	
	}
	
	String.prototype.trim = function() {
		
    	return this.replace(/(^\s*)|(\s*$)/g, '');
		
	}

	String.prototype.ltrim = function() {
		
    	return this.replace(/(^\s*)/g, '');
		
	}
	
	String.prototype.rtrim = function() {
		
    	return this.replace(/(\s*$)/g, '');
		
	}