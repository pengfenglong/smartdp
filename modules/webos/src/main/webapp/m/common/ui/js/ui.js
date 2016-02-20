pframe.define('pframe.ui',{
	id:'',
	style:'',
	cls:'',
	renderTo:'',
	init:function(){			
		this.initAttribute();
		this.beforeRender();
		this.render();
		this.afterRender();
	},
	/**
	* 初始化属性
	*/
	initAttribute:function(){
		//设置id
		if($(this.renderTo).attr('id') == undefined){
			if(pframe.util.isEmpty(this.id)){
				this.id = pframe.util.uuid();
			}
			$(this.renderTo).attr('id',this.id);
		}
		
		//设置style样式
		if(!pframe.util.isEmpty(this.style)){
			$(this.renderTo).attr('style',this.style);
		}		
		
		//设置class
		if(!pframe.util.isEmpty(this.cls)){
			$(this.renderTo).addClass(this.cls);
		}
	},
	build:function(){
		return '';
	},
	beforeRender:function(){},
	render:function(){
		$(this.renderTo).html(this.build());
	},
	afterRender:function(){}
});