/***
	Jmove.1.0.js
	name:xiaojia
	qq:273142650
	email:iatt@qq.com
	date:2011.10.26
***/
(function ()  {
	
	var PARENT;
	var EVENTS;
	var FLOW;
	var TEMP;
	var MOVE = false;
	var BEGIN = true;
	
	window.Jmove = function (events) {
		EVENTS = events;
		return new newJmove();
	}
	
	var newJmove = function () {}
	
	newJmove.prototype = {
		
		move: function (json) {
			
			FLOW = json.flow;
			PARENT = json.parent;
			POS = json.pos;
			PADDING = json.padding;
			
			typeof PADDING == 'undefined' ? PADDING = Array(0,0,0,0) : '';
			typeof POS == 'undefined' ? POS = 30 : '';
			
			tagname = EVENTS.tagName;
			$Jmove_temp = document.createElement(tagname);
			$Jmove_temp.innerHTML = PARENT.innerHTML;
			$Jmove_temp.className = PARENT.className;
			$Jmove_temp.id = 'Jmove_temp';
				
			parent_xy = this.offset(PARENT);
			
			parent_w = PARENT.clientWidth - PADDING[1] - PADDING[3];
			parent_h = PARENT.clientHeight - PADDING[0] - PADDING[2];
			
			$Jmove_temp.style.width = parent_w + 'px';
			$Jmove_temp.style.position = 'absolute';
			$Jmove_temp.style.margin = 0;
			$Jmove_temp.style.top = parent_xy[0] + 'px';
			$Jmove_temp.style.left = parent_xy[1] + 'px';
			$Jmove_temp.style.opacity = 0.7;
			$Jmove_temp.style.filter = 'alpha(opacity=70)';
			$Jmove_temp.style.zIndex = '-1';
			
			$drag = true;
			ev = window.event||arguments.callee.caller.arguments[0];
			downMouseY = ev.clientY;
			downMouseX = ev.clientX;
			mousexy = this.mouseXY(ev, $Jmove_temp);
			nowX = mousexy[0];
			nowY = mousexy[1];
			
			pos_x = POS;
			pos_y = POS - POS * 2;
			
			document.onmouseup = function () {
				$drag = false;
				if (MOVE) {
					MOVE = false;
					BEGIN = true;
					FLOW.removeChild($Jmove_temp);
					TEMP.parentNode.insertBefore(PARENT, TEMP);
					FLOW.removeChild(TEMP);
					document.body.style.cssText = '-moz-user-select:;-webkit-user-select:;';
					document.onselectstart = function() { return true; }
				}
			}
			
			document.onmousemove = function () {
				
				if ($drag) {
			
					document.body.style.cssText = ';-moz-user-select:-moz-none;-webkit-user-select:none;';
					document.onselectstart = function() { return false; }
					
					$scrollTop = document.documentElement.scrollTop + document.body.scrollTop;
					$scrollLeft = document.documentElement.scrollLeft + document.body.scrollLeft;
					
					ev = window.event||arguments.callee.arguments[0];
					
					nowMouseY = ev.clientY;
					nowMouseX = ev.clientX;
					
					divY = nowMouseY - nowY + $scrollTop;
					divX = nowMouseX - nowX + $scrollLeft;
					
					mY = downMouseY - nowMouseY;
					mX = downMouseX - nowMouseX;
					if (mY >= pos_x || mX >= pos_x || mY <= pos_y || mX <= pos_y) {
						
						MOVE = true;
						if (BEGIN) {
							FLOW.appendChild($Jmove_temp);
							TEMP = document.createElement(PARENT.tagName);
							TEMP.style.cssText = PARENT.style.cssText;
							TEMP.style.width = parent_w + 'px';
							TEMP.style.height = parent_h + 'px';
							TEMP.style.border = 'dotted 1px #f00';
							TEMP.className = PARENT.className;
							FLOW.insertBefore(TEMP, PARENT);
							FLOW.removeChild(PARENT);
						}
								
						BEGIN = false;
						
						Jmove().position(parseInt($Jmove_temp.style.top), parseInt($Jmove_temp.style.left));
						
						$Jmove_temp.style.zIndex = 1;
						pos_x = 0;
						pos_y = 0;
						$Jmove_temp.style.top = divY + 'px';
						$Jmove_temp.style.left = divX + 'px';
						
					}
				
				}
				
			}
			
		},
		
		mouseXY: function(e,obj) {
			var X = e.clientX - ( parseInt( obj.style.left ) - document.documentElement.scrollLeft - document.body.scrollLeft );
			var Y = e.clientY - ( parseInt( obj.style.top ) - document.documentElement.scrollTop - document.body.scrollTop );
			return Array(X,Y);
		},
		
		offset: function( obj ) {
			divTop = obj.offsetTop;
			divLeft = obj.offsetLeft;
			while( obj = obj.offsetParent ) {
				divTop += obj.offsetTop;
				divLeft += obj.offsetLeft;
			}
			return Array(divTop, divLeft);
		},
		
		position: function (y, x) {
		
			var flow = FLOW.getElementsByTagName(PARENT.tagName);
		
			for (var i = 0; i < flow.length; i++) {
				
				if (flow[i].parentNode == FLOW && flow[i].id != 'Jmove_temp') {
					
					fos = this.offset(flow[i]);
					
					if (y - fos[0] >= 0 && x - fos[1] >= 0) {
						
						i == 0 ? index = 0 : index = this.nextDOM(i);
						
						FLOW.insertBefore(TEMP, flow[index]);
				
					}
				
				}
			
			}
		
		},
		
		nextDOM: function (index) {
			var flow = FLOW.getElementsByTagName(PARENT.tagName);
			var par = flow[index].parentNode;
			for (var i = index + 1; i < flow.length; i++) {
				if (flow[i].parentNode == par) {
					return i;
				}
			}
		}
		
	}
	
})();