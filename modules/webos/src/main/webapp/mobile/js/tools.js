$.ajaxSetup ({ 
    cache: false, //关闭AJAX相应的缓存 
	dataType:'json',
	type:'get'
});

(function($) {
	$.extend({
		/**
		 * 命名空间
		 */
		namespace : function(){
			
		},
		getUrlParam : function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null){
				return unescape(r[2]);
			}
			return null;
		},
		getContextPath : function() {
			if(document.location.toString().indexOf('sinaapp') != -1){
				return 'http://pengfenglong.sinaapp.com';
			}else{
				var pathName = document.location.pathname;
			    var index = pathName.substr(1).indexOf("/");
			    var result = pathName.substr(0,index+1);
			    return result;
			}
		},
		formatDate : function(date, fmt) {
		    function pad(value) {
		        return (value.toString().length < 2) ? '0' + value : value;
		    }
		    return fmt.replace(/%([a-zA-Z])/g, function (_, fmtCode) {
		        switch (fmtCode) {
		        case 'Y':
		            return date.getFullYear();
		        case 'M':
		            return pad(date.getMonth() + 1);
		        case 'd':
		            return pad(date.getDate());
		        case 'H':
		            return pad(date.getHours());
		        case 'm':
		            return pad(date.getMinutes());
		        case 's':
		            return pad(date.getSeconds());
		        default:
		            throw new Error('Unsupported format code: ' + fmtCode);
		        }
		    });
	   },
	   getApiCloudUrl : function(){
			return 'https://d.apicloud.com/mcm/api/';
		},
		getApiCloudHeaher : function(){
			var appid = 'A6963902293644';
			var appkey = '51B85664-52FF-0C03-EB9E-2A5037BA3CBC';
			var now = Date.now();
			var _appKey = sha1(appid+'UZ'+appkey+'UZ'+now)+'.'+now;
			var _header = {	
				'X-APICloud-AppId': appid,
				'X-APICloud-AppKey': _appKey,
				'Content-Type':'application/json'
				//'Access-Control-Allow-Headers':'Origin, X-Requested-With, Content-Type, Accept'
			};
			return _header;
		}
	});
	
	/**
	 * 数据存储
	 */
	$.storage = {
		/**
		 * 添加数据
		 */
		insertData : function(data,success,error){
			
		},
		/**
		 * 删除数据
		 */
		deleteData : function(query,success,error){
			
		},
		/**
		 * 更新数据
		 */
		updateData : function(query,data,success,error){
			
		},
		/**
		 * 查询数据
		 */
		findData : function(query,success,error){
			$.ajax({
				headers: $.getApiCloudHeaher(),
				type:'get',
				url:$.getApiCloudUrl()+query,
				success:function(result){
					success(result);
				},
				error:function(e){
					error(e);
				}
			})
		}
	}
})(jQuery);







+function(exports){
	
	//命名空间
	var MML={};
	
	/**
	 * 简单继承的实现
	 * 
	 * 1、通过代理function实例对象区分父对象与子对象的prototype属性指向的prototype对象
	 * 2、通过闭包原理实现动态生成私有属性存取器
	 * 3、子类可以添加自身的私有属性、特权方法、原型对象
	 * 
	 * @time 2015年2月5日13:32:05
	 * @author 710335997@qq.com
	 * 
	 * @param {Object} parent
	 * @param {Object} param
	 */
	MML.extend=function(parent,param){
		
		function Proxy(){};
		
		Proxy.prototype=parent.prototype;
		
		function Son(){
			
			parent.call(this);//拷贝父类属性到子类
			
			if(param&&typeof param ==="object"){
			
				for(var p in param){
					
					var _=p.toString().charAt(0).toUpperCase()+p.toString().substr(1);
					
					if(p!="prototype"&&param.hasOwnProperty(p)&& typeof param[p] ==="string"){
						
						Son.prototype["set"+_]=(function(key){
							var __=key;
							return function(value){
								this[__]=value;
							}
							
						}(p));
						
						Son.prototype["set"+_](param[p]);
						
						Son.prototype["get"+_]=(function(key){
							var __=key;
							return function(){
								return this[__];
							}
						}(p));
					}
					if(p=="prototype" && param[p] && typeof param[p]==="object"){
						for(var a in param[p]){
							Son.prototype[a]=param[p][a];
						}
					}
					
				}
				
			}
		};
		
		Son.prototype=new Proxy();
		
		return Son;
	};
	exports.MML=MML;
	
}(window);

/**

function Animate(){
	this.type="";
	this.age=3;
}

var Dog=new MML.extend(Animate,{
	name:"dog",
	type:"Dog",
	prototype:{
		say:function(){
			console.log("i am "+this.getName());
		}
	}
});
var animate=new Animate();

var dog=new Dog();

console.log(dog.getName())//输出: dog 

Animate.prototype.test=function(){
	console.log("父类原型方法");
};


console.log(Animate.prototype==Dog.prototype);//false 分开了原型


console.log(animate.test==dog.test);// true  依然可以继续原型方法

dog.test();//输出: 父类原型方法

*/


