/**
*拖动
*/
pframe.define('pframe.ui.drag',{
	extend:'pframe.ui',
	init:function(){
		var mouseDown = false,oldPageX,oldPageY,me = this;

	        $(me.renderTo).mousemove(function(e){
			if (mouseDown) {
				var transX = oldPageX + e.pageX;
				var transY = oldPageY + e.pageY;

				me.callback(transX,transY);

				oldPageX = e.pageX;
				oldPageY = e.pageY;
			}
			return false;
	        }).mousedown(function(e){
			mouseDown = true;
			oldPageX = e.pageX;
			oldPageY = e.pageY;
			return false;
	        });

	        $(document).mouseup(function(){
			mouseDown = false;
	        });
	},
	callback:function(transX,transY){
		console.log('transX:'+transX+',transY:'+transY);
	}
});
