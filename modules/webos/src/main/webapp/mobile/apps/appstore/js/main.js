$.ajax({
	url:'apps/appstore/js/data.json',
	dataType:'json',
	success:function(data){
		var apps = data.apps;
		loadAppstorePage(apps);
	}
});

var loadAppstorePage = function(apps){
	$('.mod-app-list').empty();
	$(apps).each(function(i,app){
		var s = ''
		+'<li data-url="'+app.url+'" data-appid="">'
		+'	<div class="icon-app">'
		+'		<img data-src="" style="width: 48px; height: 48px;" src="'+app.icon+'">'
		+'	</div>'
		+'	<div class="app-content">'
		+'		<h3>'+app.name+'</h3>'
		+'		<div class="icon-star-list clearfix">'
		+'			<span><span style="width: 90%"></span></span>'
		+'		</div>'
		+'		<p class="c-tx1">160万人使用<span>|</span>4.3M</p>'
		+'	</div> '
		+'  <a class="btn-green" href="javascript:void(0);" data-click="ACTION.downApp" data-appcode="'+app.code+'">添加</a>';
		+'</li>';
		$('.mod-app-list').append(s);
	});
//		$('.mod-app-list li').click(function(){
//			window.open($(this).attr('data-url'));
//		});
	$.ajax({
		headers: $.getApiCloudHeaher(),
		type:'get',
		url:$.getApiCloudUrl()+'user_app?filter[where][user]='+uid,
		success:function(myapps){
			var clickEvent = function(_this){
				$(_this).click(function(){
					homeReload = true;
					if($(_this).hasClass('btn-del')){
						$.ajax({
							headers: $.getApiCloudHeaher(),
							type:'delete',
							url:$.getApiCloudUrl()+'user_app/'+$(_this).attr('data-appid'),
							success:function(data){
								$(_this).text('添加');
								$(_this).removeClass('btn-del');
								$(_this).css('background','');
								clickEvent(_this);
							}
						});
						
					}else{
						$.ajax({
							headers: $.getApiCloudHeaher(),
							type:'post',
							data:'{"user": "'+uid+'","app": "'+$(_this).attr('data-appcode')+'"}',
							url:$.getApiCloudUrl()+'user_app',
							success:function(data){
								//console.log(data)
								$(_this).text('删除');
								$(_this).addClass('btn-del');
								$(_this).css('background','gray');
								$(_this).attr('data-appid',data.id);
								clickEvent(_this);
							}
						});
						
					}
				});
			}
			
			$('.mod-app-list li a').each(function(i,a){
				var _this = this;
				var code = $(_this).attr('data-appcode');
				$(myapps).each(function(i,myapp){
					if(myapp.app == code){
						$(_this).addClass('btn-del');
						$(_this).css('background','gray');
						$(_this).text('删除');
						$(_this).attr('data-appid',myapp.id);
						return false;
					}
				});
				clickEvent(_this);
			})
		}
	});

}