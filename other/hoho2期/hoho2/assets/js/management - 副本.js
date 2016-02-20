//var baseUrl = 'https://api.easemob.com/hoho2/hoho2';
var baseUrl = 'http://210.76.97.29:8080/hoho/hoho';
$(function(){
	//设置登录用户
	$('#user_info').html('<small>Welcome,</small>'+getUser());
	//将页面url带上token和user信息发
	var param = getPubParam();
	var menu = $('.nav-list').find('a');
	$(menu[0]).attr('href','index.html?'+param);
	$(menu[1]).attr('href','yydzgl.html?'+param);
	$(menu[2]).attr('href','yhgl.html?'+param);
	
	if ($("#username").size() < 1){
	//开发的时候不用。
	if (!getToken() || getToken()==''){
		logout();
		}
	}
	
})

//分页
var forwardCursor='';//上一页游标
var nextCursor='';//下一页游标
function updatePageStatus(){
	var pageLi = $('.pagination').find('li');
	if(typeof(forwardCursor) == 'undefined' || forwardCursor == ''){
		//$(pageLi[0]).addClass('disabled');
		$(pageLi[0]).hide();
	}else{
		//$(pageLi[0]).removeClass('disabled');
		$(pageLi[0]).show();
	}
	
	if(typeof(nextCursor) == 'undefined' || nextCursor == ''){
		//$(pageLi[1]).addClass('disabled');
		$(pageLi[1]).hide();
	}else{
		//$(pageLi[1]).removeClass('disabled');
		$(pageLi[1]).show();
	}
}


//1387371688262转换成2013-12-16
Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1,
        // month
        "d+": this.getDate(),
        // day
        "h+": this.getHours(),
        // hour
        "m+": this.getMinutes(),
        // minute
        "s+": this.getSeconds(),
        // second
        "q+": Math.floor((this.getMonth() + 3) / 3),
        // quarter
        "S": this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format) || /(Y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
function timestampformat(timestamp) {
    return (new Date(timestamp)).format("MM-dd hh:mm");
}

//获得token和user公共参数
function getPubParam(){
	return 'token='+getToken()+'&user='+getUser();
}

//获得请求参数值
function queryString(qs){  
   var s = location.href;  
   s = s.replace("?","?&").split("&");
   var re = "";  
   for(i=1;i<s.length;i++)  
       if(s[i].indexOf(qs+"=")==0)  
           re = s[i].replace(qs+"=","");      
   return re;  
}  

//获得token
function getToken(){
	return queryString("token");
}

//获得用户
function getUser(){
	return queryString("user");
}

//创建cookie
function setCookie(name, value, expires){ 
	var argv = setCookie.arguments; 
	var argc = setCookie.arguments.length; 
	var expires = (argc > 2) ? argv[2] : null; 
	var path = (argc > 3) ? argv[3] : null; 
	var domain = (argc > 4) ? argv[4] : null; 
	var secure = (argc > 5) ? argv[5] : false; 
	document.cookie = name + "=" + escape (value) + 
	((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + 
	((path == null) ? "" : ("; path=" + path)) + 
	((domain == null) ? "" : ("; domain=" + domain)) + 
	((secure == true) ? "; secure" : ""); 
}
 
//提取cookie中的值
function getCookie (name){ 
	var arg = name + "="; 
	var alen = arg.length; 
	var clen = document.cookie.length; 
	var i = 0; 
	while (i < clen) 
	{ 
	var j = i + alen; 
	//alert(j); 
	if (document.cookie.substring(i, j) == arg) 
	return getCookieVal (j); 
	i = document.cookie.indexOf(" ", i) + 1; 
	if (i == 0) break; 
	} 
	return null; 
	} 
	var isPostBack = "<%= IsPostBack %>"; 
	function getCookieVal (offset) 
	{ 
	var endstr = document.cookie.indexOf (";", offset); 
	if (endstr == -1) 
	endstr = document.cookie.length; 
	//alert(unescape(document.cookie.substring(offset, endstr)));
	return unescape(document.cookie.substring(offset, endstr)); 
}

//记住我
function rememerme(){
	if($('#rememberme:checked').length>0){//设置cookie
		$.cookie('tvs-cookies-userName', $('#username').val(), { expires: 10000 });
		$.cookie('tvs-cookies-password', $('#password').val(),{ expires: 10000 });
	}else{//清除cookie
		$.cookie('tvs-cookies-userName', null);
		$.cookie('tvs-cookies-password', null);
	}			
}

//登陆
function login(){
	$.ajax({
		url:baseUrl+'/token',
		type:'POST',
		data:{
			'grant_type':'password',
			'username':$('#username').val(),
			'password':$('#password').val()
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('登陆失败，用户名或者密码错误');
		},
		success: function(data, textStatus, jqXHR) {
			rememerme();
			window.location.href='index.html?token='+data.access_token+'&user='+$('#username').val();
		}
	});
}

//获取已审核的段子
function getAuditedScripts(cursor){
	var temp = '';
	if(typeof(cursor)!='undefined' && cursor != ''){
		temp = '&cursor='+cursor;
	}
	$.ajax({
		url:baseUrl+"/users/admin/activities?limit=10"+temp+"&ql=select+*+where+verb+%3D+'post'+and+isaudio+%3D+'0'",
		type:'GET',
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			//alert(JSON.stringify(data));
			if(data.cursor){
				nextCursor = data.cursor;
			}else{
				nextCursor = '';
			}
			forwardCursor = cursor;
			updatePageStatus();
			$('tbody').html('');
			var d = data.entities;
			for(var i=0;i<d.length;i++){
				var like_count = 0//赞的数量
				var dislike_count = 0 //踩的数量
				var comment_count = 0//评论的数量
				if(d[i].like_count){
					like_count = d[i].like_count;
				}
				if(d[i].dislike_count){
					dislike_count = d[i].dislike_count;
				}
				if(d[i].comment_count){
					comment_count = d[i].comment_count;
				}
				var image = '';
				if(d[i].image){
					image = JSON.stringify(d[i].image).replace(/"/g,'');
				}
				var param=getPubParam()+'&nickName='+d[i].author_nick+'&content='+d[i].content+'&uuid='+d[i].uuid+'&like_count='+like_count+'&dislike_count='+dislike_count+'&comment_count='+comment_count+'&image='+image+'&audited=1';
				var temp = '<tr>'+
				'<td class="text-center"><input type="checkbox" username="'+d[i].author_username+'" value="'+d[i].uuid+'" name="selecteds" style="opacity:1;" autocomplete="off"></td>'+
				'<td class="text-center">'+decodeURI(d[i].author_nick)+'</td>'+
				'<td class="text-center">'+timestampformat(d[i].modified)+'</td>'+
				'<td class="text-center">'+(d[i].content?decodeURI(d[i].content) : '')+'</td>'+
				'<td class="text-center">'+like_count+'</td>'+
				'<td class="text-center">'+dislike_count+'</td>'+
				'<td class="text-center">'+comment_count+'</td>'+
				'<td class="text-center a-center" style="width:80px;">'+
					'<a onclick="deleteAuditedScripts(\''+d[i].uuid+'\')" href="javascript:void(0);">删除</a>'+
					'<a target="_self">查看</a>'+
				'</td>'+
				'</tr>';
				$('tbody').append(temp);
				$($($('tbody').find('tr')[i]).find('.a-center').find('a')[1]).attr('href','dzxq.html?'+param);
			}
			
		}
	});
}

//获取待审核的段子
function getPendingScripts(cursor){
	var temp = '';
	if(typeof(cursor)!='undefined' && cursor != ''){
		temp = '&cursor='+cursor;
	}
	$.ajax({
		url:baseUrl+'/drafts?limit=10'+temp,
		type:'GET',
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			//alert(JSON.stringify(data));
			if(data.cursor){
				nextCursor = data.cursor;
			}else{
				nextCursor = '';
			}
			forwardCursor = cursor;
			updatePageStatus();
			$('tbody').html('');
			var d = data.entities;
			for(var i=0;i<d.length;i++){
				var like_count = 0//赞的数量
				var dislike_count = 0 //踩的数量
				var comment_count = 0//评论的数量
				if(d[i].like_count){
					like_count = d[i].like_count;
				}
				if(d[i].dislike_count){
					dislike_count = d[i].dislike_count;
				}
				if(d[i].comment_count){
					comment_count = d[i].comment_count;
				}
				var image = '';
				if(d[i].image){
					image = JSON.stringify(d[i].image).replace(/"/g,'');
				}
				var param=getPubParam()+'&nickName='+d[i].author_nick+'&content='+d[i].content+'&uuid='+d[i].uuid+'&like_count='+like_count+'&dislike_count='+dislike_count+'&comment_count='+comment_count+'&image='+image;
				var uuid = d[i].uuid;
				var json = {'verb':'post','uuid':uuid,'content':d[i].content,'author_username':d[i].author_username,'author_nick':d[i].author_nick,'author_avator':'','isaudio':'0','audio':''};
				var arr = [];
				arr.push(json);
				var temp = '<tr>'+
				'<td class="text-center"><input type="checkbox" username="'+d[i].author_username+'" value="'+uuid+'" name="selecteds" style="opacity:1;" autocomplete="off"></td>'+
				'<td class="text-center">'+decodeURI(d[i].author_nick)+'</td>'+
				'<td class="text-center">'+timestampformat(d[i].modified)+'</td>'+
				'<td class="text-center">'+(d[i].content ? decodeURI(d[i].content) : '')+'</td>'+
				'<td class="text-center">'+like_count+'</td>'+
				'<td class="text-center">'+dislike_count+'</td>'+
				'<td class="text-center">'+comment_count+'</td>'+
				'<td class="text-center a-center" style="width:130px;">'+
					'<a href="javascript:void(0);" onclick="_auditScripts()">通过审核</a>'+
					'<a onclick="deleteScripts(\''+uuid+'\')" href="javascript:void(0);">删除</a>'+
					'<a href="" target="_self">查看</a>'+
				'</td>'+
				'</tr>';
				$('tbody').append(temp);
				$($($('tbody').find('tr')[i]).find('.a-center').find('a')[2]).attr('href','dzxq.html?'+param);
			}
			
		}
	});
}

//批量审核段子
function batcPublishScripts(){
	var arr = [];
	$('input[name="selecteds"]:checked').each(function(){  
		var json = {
			'verb':'post',
			'uuid':$(this).val(),
			'content':$(this).parent().next().next().next().text(),
			'author_username':$(this).attr('username'),
			'author_nick':$(this).parent().next().text(),
			'author_avator':'',
			'isaudio':'0',
			'audio':''
		};
		arr.push(json);
	 }); 
	auditScripts(arr,false);
}

//批量删除段子
function batcDeleteScripts(){
	 $('input[name="selecteds"]:checked').each(function(){  
	   deleteScripts($(this).val()); 
	 });  
}

//删除段子
function deleteScripts(uuid,isAlert){
	$.ajax({
		url:baseUrl+'/drafts/'+uuid,
		type:'DELETE',
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			//alert(JSON.stringify(data));	
			if(isAlert){
				alert('操作成功');
			}
			window.location.href='index.html?'+getPubParam();
		}
	});	
}

//删除语音段子
function deleteYYScripts(uuid,isAlert){
	$.ajax({
		url:baseUrl+'/activity/'+uuid,
		type:'DELETE',
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			//alert(JSON.stringify(data));	
			if(isAlert){
				alert('操作成功');
			}
			window.location.href='yydzgl.html?'+getPubParam();
		}
	});	
}

//批量删除已发布的帖子
function batcDeleteAuditedScripts(){
	 $('input[name="selecteds"]:checked').each(function(){  
	   deleteAuditedScripts($(this).val()); 
	 });  
}

//删除已发布的帖子
function deleteAuditedScripts(uuid){
	$.ajax({
		url:baseUrl+'/activity/'+uuid,
		type:'DELETE',
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			//alert(JSON.stringify(data));	
			alert('操作成功');
			window.location.href='index_.html?'+getPubParam();
		}
	});	
}

function _auditScripts(){
	var obj = $(window.event.srcElement).parent().parent().find('input');
	var arr = [];
	var json = {
		'verb':'post',
		'uuid':$(obj).val(),
		'content':$(obj).parent().next().next().next().text(),
		'author_username':$(obj).attr('username'),
		'author_nick':$(obj).parent().next().text(),
		'author_avator':'',
		'isaudio':'0',
		'audio':''
	};
	arr.push(json);
	auditScripts(arr,false);
}

//审核段子
function auditScripts(d,isAlert){
	for(var i=0,n=d.length;i<n;i++){
		var temp = i;
		$.ajax({
			url:baseUrl+'/users/admin/activities',
			type:'post',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			headers: {
				"Authorization" : "Bearer "+getToken(),		
			},
			data:JSON.stringify(d[temp]),
			success: function(data, textStatus, jqXHR) {
				//发布成功后从drafts中删除已经被批准的帖子
				deleteScripts(d[temp].uuid,isAlert);	
			}
		});
	}
}

//发布语音段子页面
function publishScriptsPage(){
	window.location.href='fbyy.html?'+getPubParam();
}

function publishImagePage(){
	window.location.href='fbtp.html?'+getPubParam();
}

//发布语音段子
function publishScripts(){
	var content = $("#content").val();
	var file = $("#file").val();
	var fileInput = $("#fileInput").val()
	var author_username = $("#users").val();
	//fileInput="aa.png";
	if (!content)
	{
		alert("内容不能为空!");
		return;
	}
	if (!fileInput)
	{
		alert("请选择文件或文件上传未成功!");
		return;
	}
	if (!users)
	{
		alert("发布人不能为空!");
		return;
	}
	var audio,image;
	if ($("#file").attr("accept") === "mp3"){
		audio = fileInput;
		image = '';
	}
	else{
		audio = '';
		image = fileInput;
	}
	
	var author_nick = $($("#users option:selected").eq(0)).text();
	var avator = $($("#users option:selected").eq(0)).attr("avator");
	var param = {
			'verb':'post',
			'content':content,
			'author_username':author_username,
			'author_nick':author_nick,
			'author_avator':avator,
			'audio':audio,
			'image':image,
			'audio_length' : '35',
			'isaudio':'1',
			"like_count":0,
			"dislike_count":0,
			"comment_count":0
		};
	$.ajax({
		//url:baseUrl+'/users/'+getUser()+'/activities',
		url:baseUrl+'/users/admin/activities',
		type:'POST',
		data:JSON.stringify(param),
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			window.location.href='yydzgl.html?'+getPubParam();
		}
	})
}

//查看段子
function viewScripts(){
	$('#username').html(decodeURI(queryString('nickName')));
	$('#content').html(decodeURI(queryString('content')));
	$('#like_count').html(queryString('like_count'));
	$('#dislike_count').html(queryString('dislike_count'));
	$('#comment_count').html(queryString('comment_count'));
	if(queryString('image') && queryString('image') != ''){
		var images = queryString('image').substring(1,queryString('image').length-1).split(',');
		for(var i=0;i<images.length;i++)
		$('#image').append('<div style="float:left"><a href="'+images[i]+'"><img src="'+images[i]+'" height="70" width="70" /></a></div>');
	}
	if(queryString('audited')){
		$($('.form-actions').find('a')[0]).hide();
		$($('.form-actions').find('a')[1]).attr('onclick',"deleteAuditedScripts(queryString('uuid'));");
	}
}

//查看语音段子
function viewAudioScripts(){
	$('#username').html(decodeURI(queryString('nickName')));
	$('#content').html(decodeURI(queryString('content')));
	$('#like_count').html(queryString('like_count'));
	$('#dislike_count').html(queryString('dislike_count'));
	$('#comment_count').html(queryString('comment_count'));
	$('#audio').attr('href',queryString('audio'));
	$('#audio_length').text(queryString('audio_length'));
	if(queryString('image') && queryString('image') != ''){
		var images = queryString('image').substring(1,queryString('image').length-1).split(',');
		for(var i=0;i<images.length;i++)
		$('#image').append('<div style="float:left"><img src="'+images[i]+'" height="70" width="70"></div>');
	}
	if(queryString('audited')){
		$($('.form-actions').find('a')[0]).hide();
	}
}

//获取用户列表
function getUserList(cursor){
	var temp = '';
	if(typeof(cursor)!='undefined' && cursor != ''){
		temp = '&cursor='+cursor;
	}
	$.ajax({
		url:baseUrl+'/users?limit=10'+temp,
		type:'GET',
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			//alert(JSON.stringify(data));
			if(data.cursor){
				nextCursor = data.cursor;
			}else{
				nextCursor = '';
			}
			forwardCursor = cursor;
			updatePageStatus();
			$('tbody').html('');
			var d = data.entities;
			for(var i=0;i<d.length;i++){
				var nickName = '';
				if(d[i].nick){
					nickName = d[i].nick;
				}
				var sex= '';
				if(d[i].sex){
					if(d[i].sex == 'F'){
						sex = '女';
					}else if(d[i].sex == 'M'){
						sex = '男';
					}else if(d[i].sex == 'U'){
						sex = '未知';
					}
				}
				var signature='';
				if(d[i].signature){
					signature = d[i].signature;
				}
				var avator='';
				if(d[i].avator){
					avator = d[i].avator;
				}
				var param=getPubParam()+'&id='+d[i].created+'&nickName='+nickName+'&signature='+signature+'&avator='+avator+'&sex='+sex;

				var temp = '<tr>'+
				'<td class="text-center"><input type="checkbox" value="" name="checkbox" style="opacity:1;" autocomplete="off"></td>'+
				//'<td class="text-center">'+timestampformat(d[i].modified)+'</td>'+
				'<td class="text-center">'+d[i].username+'</td>'+				
				'<td class="text-center">'+nickName+'</td>'+
				'<td class="text-center">'+sex+'</td>'+
				'<td class="text-center a-center"><a href="javascript:void(0);" onclick="deleteUser(\''+d[i].username+'\');">删除</a><a href="yhxq.html?'+param+'" target="_self">查看</a></td>'+
				'</tr>';
				$('tbody').append(temp);
			}
		}
	});
}

//新增用户页面
function newUserPage(){
	window.location.href='xzyh.html?'+getPubParam();
}

//用户管理页面
function newListPage(){
	window.location.href='yhgl.html?'+getPubParam();
}

//新增用户
function saveUser(){
	var d = {
		username:$('#username').val(),
		nick:$('#nick').val(),
		signature:$('#signature').val(),
		avator:$('#fileInput').val(),
		password:'123456',
		sex:$("input[name='sex'][checked]").val()
	};
	$.ajax({
		url:baseUrl+'/users',
		type:'post',
		dataType: 'json',
		contentType: "application/json; charset=utf-8",
		data:JSON.stringify(d),
		success: function(data, textStatus, jqXHR) {
			//alert(JSON.stringify(data));
			window.location.href='yhgl.html?'+getPubParam();
		}
	})
}

//删除用户
function deleteUser(username){
		$.ajax({
		url:baseUrl+'/users/'+decodeURI(username),
		type:'DELETE',
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			//alert(data);
			window.location.href='yhgl.html?'+getPubParam();
		}
	})
}

//查看用户详情
function viewUser(){
	$('#id_label').html(queryString('id'));
	$('#nick_label').html(decodeURI(queryString('nickName')));
	$('#signature_label').html(decodeURI(queryString('signature')));
	$('#avator').attr('src',decodeURI(queryString('avator')));
	$('#sex_label').html(decodeURI(queryString('sex')));
}

//获取只听段子
function getAudioScripts(cursor){
	var temp = '';
	if(typeof(cursor)!='undefined' && cursor != ''){
		temp = '&cursor='+cursor;
	}
	$.ajax({
		url:baseUrl+"/users/guest/feed?limit=10"+temp+"&ql=select+*+where+verb+%3D+'post'+and+isaudio+%3D+'1'",
		type:'GET',
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			if(data.cursor){
				nextCursor = data.cursor;
			}else{
				nextCursor = '';
			}
			forwardCursor = cursor;
			updatePageStatus();
			$('tbody').html('');
			var d = data.entities;
			for(var i=0;i<d.length;i++){
				var au = "";
				if(d[i].author){
					au = d[i].author;
				}else{
					au = d[i].author_nick;
				}
				var like_count = 0//赞的数量
				var dislike_count = 0 //踩的数量
				var comment_count = 0//评论的数量
				if(d[i].like_count){
					like_count = d[i].like_count;
				}
				if(d[i].dislike_count){
					dislike_count = d[i].dislike_count;
				}
				if(d[i].comment_count){
					comment_count = d[i].comment_count;
				}
				var audio = '';
				if(d[i].audio){
					audio = d[i].audio;
				}
				var audio_length = 0;
				if(d[i].audio_length){
					audio_length = d[i].audio_length;
				}
				var param=getPubParam()+'&nickName='+d[i].author_nick+'&content='+d[i].content+'&uuid='+d[i].uuid+'&like_count='+like_count+'&dislike_count='+dislike_count+'&comment_count='+comment_count+'&audio='+audio+"&audio_length="+audio_length;
				
				var temp = '<tr>'+
				'<td class="text-center"><input type="checkbox" autocomplete="off" style="opacity:1;" name="checkbox" value=""></td>'+
				'<td class="text-center">'+au+'</td>'+
				'<td class="text-center">'+timestampformat(d[i].published)+'</td>'+
				'<td class="text-center">'+(d[i].content ? d[i].content:'')+'</td>'+
				'<td class="text-center">'+like_count+'</td>'+
				'<td class="text-center">'+dislike_count+'</td>'+
				'<td class="text-center">'+comment_count+'</td>'+
				'<td class="text-center a-center"><a onclick="deleteYYScripts(\''+d[i].uuid+'\')" href="javascript:void(0);">删除</a><a href="yydzxq.html?'+param+'" target="_self">查看</a></td>'+
				'</tr>';
				$('tbody').append(temp);
			}
		}
	});
}

function uploadfile(){
var accept = $("#file").attr("accept");
var reg = new RegExp("(\.|\/)("+accept+")$", "i");
	$('#file').fileupload({
			url:"http://v0.api.upyun.com/hohofiles",//文件上传地址，当然也可以直接写在input的data-url属性内  
			type:'POST',
			dataType: 'json',
			autoUpload: true,
			acceptFileTypes: reg,
			maxFileSize: 5000000, // 5 MB
			previewMaxWidth: 100,
			previewMaxHeight: 100,
			previewCrop: true,
			messages:{
				"uploadedBytes":"上传字节数超过文件大小!",
				"maxNumberOfFiles":"一次只能上传一个文件!",
				"acceptFileTypes":"只允许上传"+accept+"文件格式的类型!",
				"maxFileSize":"文件过大,请上传5M以内的文件!",
				"minFileSize":"文件过小"
			},
			done: function (e, data) {
				if (data._response.result.url){
				var url = "http://hohofiles.b0.upaiyun.com/"+data._response.result.url;
					$("#fileInput").val(url);
					//alert('上传成功！');
					$('#tips').html('上传成功！');
					$('#fileName').html(decodeURI(data._response.result.url.substring(1,data._response.result.url.length)));
				}else{
					//alert('上传失败！');
					$('#tips').html('上传失败！');
				}
			}
		}).bind('fileuploadprocessfail', function(e, data){
			var file = data.files[0];
			if (file.error){
				$("#tips").html(file.error);
			}else{
				$("#tips").html('');
			}
		}).bind('fileuploadsubmit', function (e, data) {
			$('#tips').html('正在上传...');
			var $options = {};
			$options['bucket'] = 'hohofiles';
			$options['expiration'] = new Date().getTime()+60;
			$options['save-key'] = "/"+encodeURI(data.files[0].name);
			var $policy = base64_encode(JSON.stringify($options));
			var $signature = md5($policy+"&I9BI38QUhuzyijf8Ofk1OA1r35c=");
			var params = {
						'policy' : $policy ,
						'signature':$signature
				}
			data.formData = params;
		});
}

function loadUserList(cursor){
	var temp = '';
	if(typeof(cursor)!='undefined' && cursor != ''){
		temp = '&cursor='+cursor;
	}
	$.ajax({
		url:baseUrl+'/users?limit=100000'+temp,
		type:'GET',
		headers: {
			"Authorization" : "Bearer "+getToken()
		},
		success: function(data, textStatus, jqXHR) {
			//alert(JSON.stringify(data));
			if(data.cursor){
				nextCursor = data.cursor;
			}else{
				nextCursor = '';
			}
			forwardCursor = cursor;
			updatePageStatus();
			$('tbody').html('');
			var d = data.entities;
			for(var i=0;i<d.length;i++){
				var nickName = '';
				if(d[i].nick){
					nickName = d[i].nick;
				}
				var sex= '';
				if(d[i].sex){
					if(d[i].sex == 'F'){
						sex = '女';
					}else if(d[i].sex == 'M'){
						sex = '男';
					}else if(d[i].sex == 'U'){
						sex = '未知';
					}
				}
				var signature='';
				if(d[i].signature){
					signature = d[i].signature;
				}
				var avator='';
				if(d[i].avator){
					avator = d[i].avator;
				}
				var param=getPubParam()+'&id='+d[i].created+'&nickName='+nickName+'&signature='+signature+'&avator='+avator+'&sex='+sex;
				var temp = '<option value="'+nickName+'" avator="'+avator+'">'+d[i].username+'</option>';
				$('#users').append(temp);
			}
		}
	});
}

function logout(){
window.location.href="login.html";
}