/**
*类管理器
*/
(function(window, document, pframe) {'use strict';

	//克隆
	function clone(obj){
	    var o;
	    if(typeof obj == "object"){
	        if(obj === null){
	            o = null;
	        }else{
	            if(obj instanceof Array){
	                o = [];
	                for(var i = 0, len = obj.length; i < len; i++){
	                    o.push(clone(obj[i]));
	                }
	            }else{
	                o = {};
	                for(var k in obj){
	                    o[k] = clone(obj[k]);
	                }
	            }
	        }
	    }else{
	        o = obj;
	    }
	    return o;
	}
	
	//继承
	function _extend(a,b){
		a = clone(a);
		b = clone(b);
		var key;
		if(!a){
			a = {};
		}
		for(key in b){				
			a[key] = b[key];								
		}
		return a;
	};
	
	//class容器
	pframe.classes = {};
	pframe.classManager = {	
		get:function(className){
			if(pframe.classes[className] == undefined){
				return null;
			}else{
				return pframe.classes[className];
			}
		},
		put:function(className,classData){
			pframe.classes[className] = classData;
		}
	};
	
	//定义类
	pframe.define = function(className,options) {
		
		//默认的object
		var _object = {
			//初始化
			init:function(){},
			//销毁
			destory:function(){}		
		};
		
		var Class = null;		
		
		//继承
		if(options.extend){
			var extend = pframe.classManager.get(options.extend);
			if(extend != null){
				Class = _extend(Class,extend);
			}
		}
		
		if(Class == null){
			Class = _object;
		}
		
		Class = _extend(Class,options);
				
		pframe.classManager.put(className,Class);
	
	};
	
	function Obj(){
		
	};

	//创建对象
	pframe.create = function(className,options) {
		
		var Class = pframe.classManager.get(className);
		
		Class = _extend(Class,options);
		
		if(Class.init){
			Class.init.call(Class);
		}
		
		return Class;
	}


})(window, document, pframe);