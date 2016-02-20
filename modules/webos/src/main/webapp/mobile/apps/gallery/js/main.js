$(document).ready(function() {
	$.ajax({
		headers: $.getApiCloudHeaher(),
		type:'get',
		url:$.getApiCloudUrl()+'gallery',
		success:function(datas){
			$("#gallery-container").empty();
			$(datas).each(function(i,data){
				var file = data.file; 
				var thumb = data.thumb;
				var li = '<li data-src="'
				+file.url
				+'"><a href="#"><img src="'
				+thumb.url+'" /></a></li>';
				console.log(li);
				$("#gallery-container").append(li);
			});
			$("#gallery-container").lightGallery({
				loop:true,
				auto:true,
				pause:4000
			});
		}
	})
	
});
