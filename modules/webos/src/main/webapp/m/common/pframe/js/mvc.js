/**
*MVC框架
*/
(function(window, document, pframe) {'use strict';	
	
	/**
	*Model
	*/
	pframe.define('Model',{
		/*
		* 添加 name 对应的 事件callback，保存在this._events里
		*/
		on: function( name, callback ){
			this._events || (this._events = {});				//确保this._events存在
			this._events[name] = this._events[name] || [];		//确保this._events[name]存在
			this._events[name].push({ callback: callback });	//将callback 添加到 this._events[name] 内
		},
		
		/*
		* 执行已经保存在this._events里的 name 对应的事件
		*/
		trigger : function( name ){
			var args = Array.prototype.slice.call( arguments, 1 );		//将参数中除 name 外的其它参数取出来
			if( this._events && this._events[name] ){					//确保 this._events[name] 存在
				for( var i = 0; i < this._events[name].length; i++){	//循环执行里面的callback
					this._events[name][i].callback.apply( this, args );	//将步骤一取出的值作用callback的参数
				}
			}
		},
		
		/*
		* 删除this._events里 name 对应的事件
		*/
		off: function( name ){
			if( this._events ){
				delete this._events[name];							//删除  this._events[name]
			}
		}
		
	});
	
	/**
	*View
	*/
	pframe.define('View',{
		extend:'pframe.ui',
		listenTo:function(model,name,callback){
			model.on(name,callback);
		}
	});
	
	
	
	
	//	//全局的事件监听模块，可用于对象之间的消息传递
//	window.Event = (function(){
//	    var events = {},
//	    registerEvent = function(eName, handler, scope){
//	        events[eName] = events[eName] || [];
//	        events[eName].push({
//	            scope: scope || this,
//	            handler: handler
//	        });
//	    },
//	    removeEvent = function(eName, handler, scope){
//	        scope = scope || this;
//	        if(!fns) return;
//	        events[eName] = events[eName].filter(function(fn){
//	            return fn.scope!=scope || fn.handler!=handler
//	        });
//	    },
//	    triggerEvent = function(eventName,params){
//	        var fns = events[eventName],i,fn;
//	        if(!fns) return;
//	        for(i=0;fn=fns[i];i++){
//	            fn.handler.apply(fns.scope,params||[]);
//	        }
//	    };
//	    return {
//	        listen: registerEvent,
//	        ignore: removeEvent,
//	        trigger: triggerEvent
//	    }
//	})();
	 
//	Event.listen('韩寒写博客', 方舟子.doSomeResearch, 方舟子);
//	(function(){
//	    alert('我是路人甲，我告诉方舟子，韩寒写博客了');
//	    Event.trigger('韩寒写博客');
//	})();
	
	

})(window, document, pframe);