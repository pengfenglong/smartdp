var homeReload = true;
$(function(){
	/**
	 * 页面初始化
	 */
	$.ajax({
		url:'apps/navigation/js/data.json',
		dataType:'json',
		success:function(data){
			$('#navigation').load('apps/navigation/index.html',function(){
				$('#top_menu').html(getNavigationHtml(data.navigations));
				$('a.navigation_a').click(function(){
					displayit(this);
				});
				$('li.home > a').click();
				var _left = $('.navigation_a img:first').position().left+18;
				$('.navigation_a:first').append('<div style="position:absolute;top:2px;left:'
						+_left+
						'px;width: 20px;height: 20px;background-color: red;-webkit-border-radius: 10px;">5</div>');
			});
		}
	});
	/**
	 * 得到导航栏HTML
	 */
	var getNavigationHtml = function(navigations){
		var s = [];
		for(var i=0;i<navigations.length;i++){
			if(navigations[i].code == 'home'){
				s.push('<li class="home"><a class="navigation_a" attr=\''+JSON.stringify(navigations[i])+'\'></a></li>');
			}else{
				s.push('<li>');
				s.push('<a class="navigation_a" attr=\''+JSON.stringify(navigations[i])+'\'><img src="apps/navigation/'+navigations[i].icon+'"><label>'+navigations[i].name+'</label></a>');
				if(navigations[i].children){
					var children = navigations[i].children;
					if(children.length > 0){
						s.push('<ul id="menu_list'+i+'" class="menu_font" style="display: none">');
					}
					for(var j=0;j<children.length;j++){
						s.push('<li><a href="'+children[j].url+'"><img src="'+children[j].icon+'"><label>'+children[j].name+'</label></a></li>');
					}
					if(children.length > 0){
						s.push('</ul>');
					}
				}
				s.push('</li>');
			}
		}
		return s.join('');
	};
	
	/**
	 * 导航条点击事件
	 */
	var displayit = function(selector) {
		$('#top_menu .menu_font').hide();
		$(selector).next('ul').toggle('');
		var attr = $.parseJSON($(selector).attr('attr'));
		$.ajax({
			url:'apps/'+attr.url+'/config.js',
			dataType:'json',
			success:function(config){
				$(config.css).each(function(i,_css){
					var head = document.getElementsByTagName('head')[0];
			        var link = document.createElement('link');
			        link.href = './apps/'+attr.url+'/'+_css;
			        link.rel = 'stylesheet';
			        link.type = 'text/css';
			        head.appendChild(link);
				})
				if(attr.code == 'home'){
					$('#container_home').show();
					$('#container_other').hide();
					if($('#container_home').children().length == 0 || homeReload){
						$('#container_home').load('apps/'+attr.url+'/'+config.page,function(){
							homeReload = false;
							$(config.js).each(function(i,_js){
								$.getScript('apps/'+attr.url+'/'+_js);
							})
						});
					}
					
				}else{
					$('#container_home').hide();
					$('#container_other').show();
					$('#container_other').load('apps/'+attr.url+'/'+config.page,function(){
						$(config.js).each(function(i,_js){
							$.getScript('apps/'+attr.url+'/'+_js+'?r=' + Math.random());
						})
					});
				}
				
				
			}
		})		
		
	};
	
})

