var contextPath='/smartdp';
var isNeedRefresh=true;
var chat_user_id;//当前聊天对象ID
var chat_user_name;//当前聊天对象名称
var login_user_id;//当前登陆用户ID
var login_user_name;//当前登陆用户名称
var users;//全部user对象

//重写Jquery的$.ajax的error方法
(function($){
	//备份jquery的ajax方法
	var _ajax=$.ajax;
	
	//重写jquery的ajax方法
	$.ajax=function(opt){
		//备份opt中error方法
		var fn = {
			error:function(XMLHttpRequest, textStatus, errorThrown){}
		}
		if(opt.error){
			fn.error=opt.error;
		}
		
		//扩展增强处理
		var _opt = $.extend(opt,{
			error:function(XMLHttpRequest, textStatus, errorThrown){
				//错误方法增强处理
				//fn.error(XMLHttpRequest, textStatus, errorThrown);
				window.location.href = 'index.jsp';
			}
		});
		_ajax(_opt);
	};
})(jQuery);

$(function(){
	login();
	window.onunload = function(){
		//页面关闭时候通知处理该用户的线程停止
		isNeedRefresh=false;
		$.post(contextPath+'/chat-logout-Chat.action?userCode='+login_user_id, function(content) {
		});
		
	}
})

function datetime(date) {
    return date.replace('T',' ');
}

function login(){
	$.post(contextPath+'/chat-login-Chat.action', function(d) {
		var data = eval('(' + d +')');
		if(data.code=="0"){
			var _user = eval('(' + data.user +')');
			login_user_id = _user.userCode;
			login_user_name = _user.userName;
			$('#my-name').text(_user.userName);
			refresh();
		}else{
			alert(data.msg);
			login();
		}
	});

};

function refresh(){
	$.post(contextPath+'/chat-initialization-Chat.action?userCode='+login_user_id, function(content) {
		handlerContent(content);
		refreshUserAndMsg();
		//me._bindEvent();
	});
};
function getHistory(){
	var historyUsers = $.cookie('chat-msg-history-users-'+login_user_id);
	if(historyUsers != null){
		var us = historyUsers.split(',');
		for(var i=0,n=us.length;i<n;i++){
			if($('#history-message-'+us[i]).length == 0 && us[i] != login_user_id){
				var _html=''+
				'<div id="history-message-'+us[i]+'" class="message-detail">'+
				'	<div style="float: left;"><img style="z-index:-999;" src="user_img/0.jpg" width="30px" height="30px" /><span class="no-readmessage-num"></span></div>'+
				'	<div>'+
				'		<div><span class="chat-history-user">'+getUserByuserCode(us[i]).userName+'</span><span style="float:right;color:gray;">&nbsp;</span></div>'+
				'		<div class="chat-history-message">&nbsp;</div>'+
				'	</div>'+
				'</div>';
				$('.message-list').prepend(_html);
			}
		}
		$('.message-list div').click(function(){
			$(".message-list div").removeClass("history_choose");
			$(this).addClass("history_choose");
		});
		$('.message-list div').hover(function(){$(this).addClass("history_over");},function(){$(this).removeClass("history_over");});
	}
}
function getUserByuserCode(id){
	for(var i=0;i<users.length;i++){
		if(users[i].userCode==id){
			return users[i];
		}
	}
};
function refreshUserAndMsg(){
	$.post(contextPath+'/chat-refresh-Chat.action?userCode='+login_user_id, function(content) {
		handlerContent(content);
		if(isNeedRefresh){
			refreshUserAndMsg();
		}
	})
};
/**
 * 填充消息到聊天框
 * msgs 消息集合
 */
function fillContent(msgs,flag){
	for(var i=0,n=msgs.length;i<n;i++){
		var receiver = msgs[i].receiver;
		if(flag){
			receiver = msgs[i].sender;
		}
		$("#msg_container_"+receiver).append('<div style=\"margin:5px\"><font color=\"#0000FF\">'+
				getUserByuserCode(msgs[i].sender).userName+'&nbsp;&nbsp;'+msgs[i].createDate+
				"</font><p style=\"padding:5px 10px\"><font color=\"#333333\">"+
				msgs[i].content+
				'</font></p></div>');
		$("#msg_container_"+receiver).parent().scrollTop(100000000000);//让滚动滚到最底端
		var historyUsers = $.cookie('chat-msg-history-users-'+login_user_id);
		if(historyUsers != null){
			var us = historyUsers.split(',');
			for(var j=0,m=us.length;j<m;j++){
				if(us[j] == msgs[i].sender){
					us.splice(j,1);
					break;
				}
			}
			us.push(msgs[i].sender);
			$.cookie('chat-msg-history-users-'+login_user_id, us.join(','), { expires: 1000000 });
		}else{
			$.cookie('chat-msg-history-users-'+login_user_id, msgs[i].sender, { expires: 1000000 });
		}
	}
};
function subMsg() {
	if($('#usertabs').tabs('tabs').length == 0){
	   alert("你还没选中跟哪个聊天，请点左边好友选中一个再聊");
	   return false;
	};
	if(editor.html()){
		var content = editor.html();
		var msgs=[];
		var msg={};
		msg.sender=login_user_id;
		msg.receiver=chat_user_id;
		msg.content=content;
		msg.createDate=new Date().toLocaleTimeString();//当前时间
		msgs.push(msg);
		fillContent(msgs);
		editor.html('');
		$.post(contextPath+'/chat-sendMsg-Chat.action', {
			'sender':login_user_id,
			'receiver' : chat_user_id,
			'content' : content
		}, function(content) {
			handlerContent(content);
		});
		
	}else{
		alert("你输入的内容为空")  
	}
	$("#content").focus();//光标焦点		
};
/**
 * 创建聊天窗口
 */
function createChatWin(user,_fillContent){
	var title = user.userName+'/'+user.userCode;
	if ($('#usertabs').tabs('exists', title)){    
        $('#usertabs').tabs('select', title);    
    }else{
		$('#usertabs').tabs('add',{    
            title:title,    
            content:'<span id="msg_container_'+user.userCode+'"></span>',    
            closable:true    
        });  
		if(_fillContent){
			_fillContent();
		}
    }
    chat_user_id = user.userCode;
    chat_user_name = user.userName;
}

function bindHisttoryDblclick(){
	$('.message-detail').each(function(){
		$(this).dblclick(function(){
			var num = $(this).find('.no-readmessage-num').text();
			var sender = $(this).attr('id').split('-')[2];
			var _user = getUserByuserCode(sender);
			if(num && num != '' && Number(num) >0){
				$(this).find('.no-readmessage-num').text('');
				//createChatWin(_user,function(){fillContent(sender,sender,content);});
				createChatWin(_user,function(){
					$.ajax({
						'url':contextPath+'/chat-getNoReadMsg-Chat.action?sender='+sender+'&userCode='+login_user_id,
						dataType:'json',
						success:function(data){
							fillContent(data,'1');
						}
					})
				});
			}else{
				createChatWin(_user);
			}
		});
	})

}

/**
 * 显示历史消息
 * @param editor
 */
function showHistoryMessage(editor){
	if($('#usertabs').tabs('tabs').length == 0){
	   alert("请选择聊天对象");
	   return false;
	};
	//$.crud.createWindow('历史消息','history.jsp',670,500);
	$('#smartdp-window').dialog({
		title : '历史消息 -- '+chat_user_name,
		height: 500,
		width: 670,
		href : 'history.jsp',
		modal : true,
		minimizable : false,
		maximizable : false,
		shadow : true,
		cache : false,
		closed : false,
		closable : true,
		collapsible : false,
		draggable : false,
		resizable : false
	});
}

/**
 * 新消息提示
 */
function newMsgPrompt(){
	 //窗口聚焦
	 window.focus();
	 //提示声音
	 if (typeof(Worker) != 'undefined') {  
		 //支持HTML
		 $('audio').parent().remove();  
	     $('body').append('<div><audio src="sound/msg.mp3" controls="controls" hidden="true" autoplay="autoplay"></audio></div>');  		
	 } else { 			
		 $('embed').parent().remove();  
	     $('body').append('<div><embed src="sound/msg.mp3" autostart="true" hidden="true" loop="false"></div>'); 		
	 }
	 
     
     
}


/**
 * 处理服务器返回的消息
 */
function handlerContent(data) {
	
//	alert(data);
	if(data==''){
		return;
	}
	var noReadMessage={};//未读消息
	var c = eval('(' + data +')');
	users = c.users;
	getHistory();
	var noReadNum={};
	if(c.messages){
		for(var m=0;m<c.messages.length;m++){
			newMsgPrompt();
			//没有打开聊天窗口
			if($('#msg_container_'+c.messages[m].sender).length==0){
				if(c.messages[m].receiver == login_user_id){
					var sender = c.messages[m].sender;
					if(typeof(noReadNum[sender]) == 'undefined'){
						noReadNum[sender]=1;
					}else{
						noReadNum[sender]=new Number(noReadNum[sender])+1;
					}
					var content = c.messages[m].content;
					var createDate = datetime(c.messages[m].createDate);
					if($('#history-message-'+sender).length == 0){
						var _html=''+
						'<div id="history-message-'+sender+'" class="message-detail">'+
						'	<div style="float: left;"><img src="user_img/0.jpg" width="35px" height="35px"><span class="no-readmessage-num">0</span></div>'+
						'	<div>'+
						'		<div><span class="chat-history-user">'+getUserByuserCode(sender).userName+'</span><span style="float:right;color:gray;">'+createDate.substring(0,10)+'<span></div>'+
						'		<div class="chat-history-message">'+content+'</div>'+
						'	</div>'+
						'</div>';
						$('.message-list').prepend(_html);
					}else{
						$('#history-message-'+sender).find('.chat-history-message').html(content);
					}
					if($('.text_overflow1').text().length>8){
						 var text=$('.text_overflow1').text().substring(0,8)+"...";  
						 $('.text_overflow1').text(text);  
					}
					if($('.text_overflow2').text().length>15){
						 var text=$('.text_overflow2').text().substring(0,15)+"...";  
						 $('.text_overflow2').text(text);  
					}
					//var num = $('#history-message-'+sender).find('.no-readmessage-num').text();
					$('#history-message-'+sender).find('.no-readmessage-num').text(noReadNum[sender]);
				}
			}else{
				/**
				 * 不是当前tab给予提示
				 */
				//消息tab
				var title = getUserByuserCode(c.messages[m].sender).userName+'/'+c.messages[m].sender;
				//当前选择tab 
				var _title = $('#usertabs').tabs('getSelected').panel('options').title;
				if(title != _title){
					$("span:contains("+title+")").addClass('no-read-tips');
				}
				var content = c.messages[m].content;
				var msgs=[];
				var msg={};
				msg.sender=c.messages[m].sender;
				msg.receiver=c.messages[m].sender;
				msg.content=content;
				msg.createDate=datetime(c.messages[m].createDate);
				msgs.push(msg);
				fillContent(msgs);
				$.post(contextPath+'/chat-updateMsgStatus-Chat.action', {
					'msgId':c.messages[m].id
				}, function(data) {
				});
			}
		}
	}
	bindHisttoryDblclick();
};

