/*
 * renju.js
 * name:xiaojia
 * email:iatt@qq.com
 * qq:273142650
 * time:2012.6.18 11:23 受风过敏了。。。身上好痒55555555555555555
*/

	jQuery.extend({
			
		jRenju: function () {
		
			var fn, data;
			
			data = {
			
				rank: 15,
				
				row: 15,
				
				size: 32,
				
				chessboard: []
				
			};
			
			fn = {
				
				state: {
				
					now: 1
					
				},
			
				init: function () {
					
					this.canvas();
					
					this.event();
				
				},
				
				check: function (x, y) {
				
					var rank = 0, row = 0;
					
					var lrr = 0, lrl = 0;
					
					var rlr = 0, rll = 0;
					
					var temp;
				
					var now = fn.state.now;
					
					if (now == 2) {
						
						now = 1;
						
					} else {
					
						now = 2;
					
					}
					
					for (var i = data.rank - 1; i >= 0; i--) {
						
						for (var j = data.row - 1; j >= 0; j --) {
						
							if (data.chessboard[i + j - i][i - j] == now) {
								
								rlr ++;
								
								if (rlr > 4) {
									
									fn.win(now);
									
									break;
									
								}
								
							} else {
							
								rlr = 0;
							
							}
							
							temp = i - j;
							
							if (temp < 0) {
								
								continue;
								
							}
							
							if (data.chessboard[i - j + (data.rank - 1 - i)][i + j - i + (data.rank - 1 - i)] == now) {
								
								rll ++;
								
								if (rll > 4) {
									
									fn.win(now);
									
									break;
									
								}
								
							} else {
							
								rll = 0;
							
							}
						
						}
						
					}
					
					for (var i = 0; i < data.rank; i++) {
						
						for (var j = 0; j < data.row; j++) {
							
							if (data.chessboard[i + j - i][j + i] == now) {
								
								lrr ++;
								
								if (lrr > 4) {
									
									fn.win(now);
									
									break;
									
								}
								
							} else {
							
								lrr = 0;
							
							}
							
							temp = j + i;
							
							if (temp > 14) {
								
								continue;
								
							}
							
							if (data.chessboard[temp][i + j - i] == now) {
								
								lrl ++;
								
								if (lrl > 4) {
									
									fn.win(now);
									
									break;
									
								}
								
							} else {
							
								lrl = 0;
							
							}
							
						}
						
					}
							
					for (var i = 0; i < data.rank; i++) {
						
						for (var j = 0; j < data.row; j++) {
							
							if (data.chessboard[i][j] == now) {
								
								rank ++;
								
								if (rank > 4) {
									
									fn.win(now);
									
									break;
									
								}
								
							} else {
							
								rank = 0;
							
							}
							
							if (data.chessboard[j][i] == now) {
								
								row ++;
								
								if (row > 4) {
									
									fn.win(now);
									
									break;
									
								}
								
							} else {
							
								row = 0;
							
							}
							
						}
						
					}
				
				},
				
				win: function (now) {
					
					$('.chessboard-hide li').unbind('click');
					
					if (now == 1) {
						
						top.$.jLayer.alert('黑方获胜');
						
					} else {
						
						top.$.jLayer.alert('白方获胜');
					
					}
					
				},
				
				event: function () {
					
					$('.chessboard-hide li').bind('click', 
					
						function () {
							
							var xy;
							
							if (fn.state.now == 1) {
								
								xy = $(this).attr('class').split(' ')[0].split(',');
																							
								$(this).removeAttr('white').addClass('black');
								
								data.chessboard[xy[0]][xy[1]] = 1;
								
								fn.state.now = 2;
								
							} else {
								
								xy = $(this).attr('class').split(' ')[0].split(',');
							
								$(this).removeAttr('black').addClass('white');
								
								data.chessboard[xy[0]][xy[1]] = 2;
								
								fn.state.now = 1;
							
							}
							
							$(this).unbind('click');
					
							fn.check(xy[0], xy[1]);
						
						}
					
					);
					
				},
				
				canvas: function () {
					
					var cb, rank, row;
					
					$('.chessboard-hide').css('margin', data.size / 2 - 3 + 'px');
					
					for (var i = 0; i < data.rank; i ++) {
						
						data.chessboard[i] = new Array();
						
						for (var j = 0; j < data.row; j++) {
						
							data.chessboard[i][j] = 0;
						
							cb = $('<li title="'+ i + ',' + j +'"></li>').appendTo('.chessboard-hide');
							
							cb.addClass(i + ',' + j);
							
							cb.css({
							
								width: data.size + 'px',
								
								height: data.size + 'px'
								
							});
							
						}
							
						$('<div class="clear"></div>').appendTo('.chessboard-hide');
						
					}
					
					for (var i = 0; i < data.rank; i++) {
						
						rank = $('<li></li>').appendTo('.chessboard-rank');
						
						rank.css('margin-top', data.size + 3 + 'px');
						
					}
					
					for (var i = 0; i < data.row; i++) {
						
						row = $('<li></li>').appendTo('.chessboard-row');
						
						row.css('margin-left', data.size + 3 + 'px');
						
					}
					
				}
				
			};
			
			fn.init();
		
		}
			
	});
	
	$(function() {
		
		jQuery.jRenju();
		
	});