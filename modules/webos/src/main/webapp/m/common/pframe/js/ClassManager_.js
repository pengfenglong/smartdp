/**
*�������
*/
(function(window, document, pframe) {'use strict';
	
	//class����
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
	
	//������
	pframe.define = function(className,options) {
		
		//Ĭ�ϵ�object
		var _object = {
			//��ʼ��
			init:function(){},
			//����
			destory:function(){}		
		};
		
		var _class;		
		
		//�̳�
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
	

	//��������
	pframe.create = function(className,options) {
		
		var _class = pframe.classManager.get(className);	
		
		_class = _class.extend(options);		
		
		return new _class();
	}


})(window, document, pframe);