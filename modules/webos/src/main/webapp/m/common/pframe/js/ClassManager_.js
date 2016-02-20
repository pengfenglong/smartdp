/**
*类管理器
*/
(function(window, document, pframe) {'use strict';
	
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
		
		var _class;		
		
		//继承
		if(options.extend){
			var Parent = pframe.classManager.get(options.extend);
			if(Parent != null){
				_class = Parent.extend(options);
			}
		}else{
			_class = Class.extend(_object);
			_class = Class.extend(options);
		}
				
		pframe.classManager.put(className,_class);
	
	};
	

	//创建对象
	pframe.create = function(className,options) {
		
		var _class = pframe.classManager.get(className);	
		
		_class = _class.extend(options);		
		
		return new _class();
	}


})(window, document, pframe);