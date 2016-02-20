var pframe = window.pframe || {};

/**
*框架
*/
(function(window, document, pframe) {'use strict';
	
	//命名空间
	pframe.namespace = { 
		reg : function(s){ 
			var arr = s.split('.'); 
			var namespace = window; 
	  
			for(var i=0,k=arr.length;i<k;i++){ 
				if(typeof namespace[arr[i]] == 'undefined'){ 
					namespace[arr[i]] = {};  
				} 
	  
				namespace = namespace[arr[i]];           
			} 
		}, 
		del : function(s){ 
			var arr = s.split('.'); 
			var namespace = window; 
	  
			for(var i=0,k=arr.length;i<k;i++){ 
				if(typeof namespace[arr[i]] == 'undefined'){ 
					return; 
				}else if( k == i+1 ){ 
					delete  namespace[arr[i]]; 
					return; 
				}else{ 
					namespace = namespace[arr[i]];       
				}        
			}            
		} 
	};
	
	/**
	 * 获取contextPath
	 */
	pframe.getContextPath = function() {
		var pathName = document.location.pathname;
		var index = pathName.substr(1).indexOf("/");
		var result = pathName.substr(0, index + 1);
		return result + '/';
	};
	
	/**
	*动态加载css
	*/
	pframe.requirecss = function(url) {
		var link = document.createElement("link");
		link.type = "text/css";
		link.rel = "stylesheet";
		link.href = url;
		document.getElementsByTagName("head")[0].appendChild(link);
	};
	
	/**
	*动态加载js
	*/
	pframe.requirejs = function(url){
		document.write('<script src="'+pframe.getContextPath()+url+'"><\/script>');
		//document.write('<script src="'+url+'?r='+Math.random()+'"><\/script>');
	};
	pframe.requirejs('common/pframe/js/require.js');

})(window, document, pframe);





