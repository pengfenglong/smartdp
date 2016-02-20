function formatDate(date, fmt) {
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
}

function getContextPath(){
	if(document.location.toString().indexOf('sinaapp') != -1){
		return 'http://weixinext.sinaapp.com';
	}else{
		var pathName = document.location.pathname;
	    var index = pathName.substr(1).indexOf("/");
	    var result = pathName.substr(0,index+1);
	    return result;
	}
	
}

var begin = 0;
var count = 10;
function listArticle(){
	$.ajax({
		url:'apps/message/js/data.json?begin='+begin+'&count='+count,
		dataType:'json',
		async: true, 
		success:function(data){
				begin += count;
				var item = data.item;
				for(var i=0;i<item.length;i++){
					var multi_item = item[i].multi_item;
					for(var j=0;j<multi_item.length;j++){
						var html = [];
						html.push('<li class="newsHead">');
						html.push('    <a target="_bank" href="'+multi_item[j].content_url+'">');
						html.push('        <img src="'+multi_item[j].cover+'" width="70" height="52">');
						html.push('        <div>');
						html.push('            <p class="newsTitle">'+multi_item[j].title+'</p>');
						html.push('            <p>'+multi_item[j].digest.substr(0,30)+'...</p>');
						//html.push('             <span class="newsTips">'+item[i].create_time+'</span>');
						html.push('         </div>');
						html.push('     </a>');
						html.push('  </li>');
						$('#newsListContent').append(html.join(''));
					}
				}
			
		}
	});
	
}

listArticle();