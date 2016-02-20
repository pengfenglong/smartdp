/**
 * 页面初始化
 */
$.ajax({
	url:'apps/home/js/data.json',
	dataType:'json',
	success:function(data){
		var apps = data.apps;
//		var addApp = {
//				"code": "add",
//				"name": "",
//				"icon": "image/add.png",
//				"url": "apps/appstore/index.html"
//			}
//		apps.push(addApp);
		$.ajax({
			url:'apps/appstore/js/data.json',
			dataType:'json',
			success:function(data){
				var allapps = data.apps;
		       $.storage.findData('user_app?filter[where][user]='+uid,function(myapps){
	 			   $(allapps).each(function(i,app){
						$(myapps).each(function(j,myapp){
							if(app.code == myapp.app){
								apps.push(app);
								return false;
							}
						})
					})
					loadHomePage(apps);
		        },function(){
		        	loadHomePage(apps);
		        });
			}
		});
	}
});

var loadHomePage = function(apps){
	$('#container_home').load('home.html',function(){
		var contextPath = $.getContextPath();
		var buildAppA = function(app){
			var s = '<a target="_bank" href="'+(app.url.indexOf('http://')!=-1 ? app.url : (contextPath+'/'+app.url))+'">'
				  + '<div class="mbg">'
				  + '<p class="ico">';
				if(app.icon.indexOf('fa-')!=-1){
					s+='<i class="'+app.icon+'"></i>';
				}else{
					s+='<img src="'+app.icon+'" />';
				}
				 s+='</p>'
				  + '<p class="title">'+app.name+'</p>'
				  + '</div></a>';
			return s;
		}
		var ss = [];
		ss.push('<div class="swiper-slide">');
		ss.push('<div class="content-slide">');
		for(var j=0;j<(apps.length <=16 ? apps.length : 16);j++){
			ss.push(buildAppA(apps[j]));
		}
		ss.push('</div>');
		ss.push('</div>');
		if(apps.length > 16){
			var n = Math.floor(apps.length/16);
			var m = apps.length%16;
			var num;
			if(m == 0){
				num = n-1;
			}else{
				num = n;
			}
			for(var tem=1;tem<=num;tem++){
				ss.push('<div class="swiper-slide swiper-slide-visible swiper-slide-active">');
				ss.push('<div class="content-slide">');
				if(tem==num){
					for(var j=0;j<( m == 0 ? 16 : m);j++){
						ss.push(buildAppA(apps[tem*16+j]));
					}
				}else{
					for(var j=0;j<16;j++){
						ss.push(buildAppA(apps[tem*16+j]));
					}
				}
				ss.push('</div>');
				ss.push('</div>');
			}
		}
		//alert(navigator.userAgent);
		$('.swiper-wrapper').html(ss.join(''));
		$('.swiper-container').height($(window).height()-$('.header').height()-$('.top_bar').height()-20);
		$('.content-slide > a').css('padding-top',($('.swiper-container').height()-$('.content-slide > a > .mbg').height()*4)/5-12+'px');
		
		$('#searchtxt').focus(function(){
			$('#searchtxt').animate({width:$(window).width()-$('.logo').width()-70});
		});
		$('#searchtxt').blur(function(){
			$('#searchtxt').animate({width:10});
		});
		
		
		var mySwiper = new Swiper('.swiper-container', {
			pagination : '.pagination',
			loop : true,
			grabCursor : true,
			paginationClickable : true
		})
		$('.arrow-left').on('click', function(e) {
			e.preventDefault()
			mySwiper.swipePrev()
		})
		$('.arrow-right').on('click', function(e) {
			e.preventDefault()
			mySwiper.swipeNext()
		})
		
	});
}
