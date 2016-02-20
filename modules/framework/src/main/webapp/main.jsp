<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@ include file="framework/head-include.jsp"%>
<script type="text/javascript">
	$(function() {
		var url = $.getUrlParam('url');
		if(url){
			$('#mainframe').attr('src', url);
			$('.easyui-linkbutton').each(function(){
				if($(this).attr('targeturl') == url){
					$(this).css('background-color', '#a1c4ff');
				}
			});
			$('.menu-item').each(function(){
				if($(this).attr('targeturl') == url){
					$(this).css('background-color', '#a1c4ff');
					var _id = $(this).parent().attr('id');
					$('.easyui-menubutton').each(function(){
						if($(this).attr('menu') == ('#'+_id)){
							$(this).css('background-color', '#a1c4ff');
						}
					});
					
				}
			});

		}
		$('.easyui-linkbutton,.menu-item').click(function() {
			var url = $(this).attr('targeturl');
			if(url.indexOf('http://') != -1){
				$('#mainframe').attr('src',url);
			}else{
				var _href = window.location.href;
				if(url != 'welcome.jsp'){
					url = 'component/' + url;
				}
				window.location.href = _href.substring(0,_href.indexOf('?'))+'?url='+url;
			}
			
		});
	})
	
	function search(value,name){
		
	}
</script>
</head>
<body>
<body>
	<div id="mainlayout" class="easyui-layout" fit="true">
		<div region="north" border="false" style="height: 70px; background: #086A87">
			<div class="header">
				<div style="padding: 11px; text-align: right;">
					<a href="#" style="color: #fff" onclick="window.open ('component/chat/index.jsp','newwindow', 'height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')">在线聊天</a>
					<span style="color: #fff;margin: 0 20px">欢迎您:<sec:authentication property="name"/></span> 
					<a href="<%=request.getContextPath()%>/j_spring_security_logout" style="color: #fff">退出</a>
				</div>
				<div class="toptitle">SMARTDP开发平台</div>
			</div>
			<div id="topmenu" class="topmenu">
				<a href="#" targeturl="welcome.jsp" class="easyui-linkbutton" plain="true">首页</a>
				<sm:menubutton></sm:menubutton>
				<a href="#" class="easyui-menubutton" plain="true" menu="#sys">系统管理</a> 
				<a href="#" class="easyui-menubutton" plain="true" menu="#yule">在线娱乐</a>
				<a href="#" class="easyui-menubutton" plain="true" menu="#tools">常用工具</a>
				<a href="#" class="easyui-menubutton" plain="true" menu="#app">企业应用</a>
				<a href="#" class="easyui-menubutton" plain="true" menu="#industryapp">行业应用</a>
				<div id="searchbox" style="float: right;margin:0 2px;">
					<input id="ss" class="easyui-searchbox" searcher="search"  prompt="请输入关键字" menu="#searchm" style="width:230px;height: 26px;">
					<div id="searchm" style="width:60px">  
					    <div name="all">全部</div>  
					    <div name="weixin">微信</div>  
					</div>  
				</div>
			</div>
			<div id="sys" class="easyui-menu">
				<div href="#" targeturl="platform/page/menu/indexTree.jsp">菜单管理</div>
				<div href="#" targeturl="platform/page/group/indexTree.jsp">部门管理</div>
				<div href="#" targeturl="platform/page/user/index.jsp">用户管理</div>
				<div href="#" targeturl="platform/page/role/index.jsp">角色管理</div>
				<div href="#" targeturl="platform/page/privilege/index.jsp">权限管理</div>
				<div href="#" targeturl="platform/page/parameter/index.jsp">参数管理</div>
			</div>
			<div id="yule" class="easyui-menu">
				<div href="#" targeturl="webos/index.html">webos</div>
				<div href="#" targeturl="site/index.jsp">网址之家</div>
				<div href="#" targeturl="FlappyBird/index.html">笨鸟先飞</div>
				<div href="#" targeturl="donkeyjump/index.html">驴子跳</div>
				<div href="#" targeturl="llk/index.html">连连看</div>
				<div href="#" targeturl="renju/index.html">五子棋</div>
				<div href="#" targeturl="supermary/index.html">超级马里奥</div>
				<div href="#" targeturl="http://y.qq.com/player">QQ音乐</div>
				<div href="#" targeturl="http://douban.fm/partner/qq_plus">豆瓣FM</div>
				<div href="#" targeturl="http://play.baidu.com/">百度音乐</div>
				<div href="#" targeturl="album/index.jsp">相册</div>
				<div href="#" targeturl="../framework/plugin/carousel/index.html">3D圆盘图片</div>
				<div href="#" targeturl="chat/index.jsp">在线聊天</div>
			</div>
			<div id="tools" class="easyui-menu">
				<div href="#" targeturl="apidocs/index.jsp">在线API</div>
				<div href="#" targeturl="code-generator/index.html">代码生成器</div>
				<div href="#" targeturl="codetools/index.html">代码格式化</div>
				<div href="#" targeturl="calendar/index.jsp">日历</div>
				<div href="#" targeturl="percalendar/index.jsp">万年历</div>
				<div href="#" targeturl="clock/index.html">在线时钟</div>
				<div href="#" targeturl="calculator/index.html">计算器</div>
				<div href="#" targeturl="keyboard/index.html">键盘</div>
				<div href="#" targeturl="flowbuilder/index.html">流程设计器</div>
			</div>
			<div id="app" class="easyui-menu">
				<div href="#" targeturl="weixin/page/member/index.jsp">微信管家</div>
				<div href="#" targeturl="cms/page/index.jsp">网站管理</div>
				<div href="#" targeturl="mall/page/index.jsp">商城管理</div>
				<div href="#" targeturl="">客户关系管理</div>
				<div href="#" targeturl="">推广活动</div>
				<div href="#" targeturl="report/page/index.jsp">统计分析</div>
				
			</div>
			<div id="industryapp" class="easyui-menu">
				<div href="#" targeturl="">餐饮</div>
				<div href="#" targeturl="">酒店</div>
				<div href="#" targeturl="">婚庆</div>
				<div href="#" targeturl="">汽车</div>
				<div href="#" targeturl="">房产</div>
				<div href="#" targeturl="">教育</div>
				<div href="#" targeturl="">医疗</div>
				<div href="#" targeturl="">美容</div>
				<div href="#" targeturl="">健身</div>
				<div href="#" targeturl="">政务</div>
				<div href="#" targeturl="">食品</div>
				<div href="#" targeturl="">旅游</div>
				<div href="#" targeturl="">花店</div>
				<div href="#" targeturl="">物业</div>
				<div href="#" targeturl="">KTV</div>
				<div href="#" targeturl="">酒吧</div>
				<div href="#" targeturl="">装修</div>
				<div href="#" targeturl="">家政</div>
				<div href="#" targeturl="">宠物</div>
				<div href="#" targeturl="">租赁</div>
				<div href="#" targeturl="">数码</div>
				<div href="#" targeturl="">金融</div>
				<div href="#" targeturl="">零售</div>
				<div href="#" targeturl="">珠宝</div>
				<div href="#" targeturl="">IT</div>
				<div href="#" targeturl="">通讯</div>
			</div>
		</div>
		<!-- 
		<div region="west" border="true" style="width: 38px;">
			<img alt="在线聊天" src="framework/icon/Applications/Messaging.png"
				style="margin: 10px 3px; cursor: pointer;" width="28px"
				height="28px" onclick="window.open ('component/chat/index.jsp','newwindow', 'height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')"/>
		</div>
		 -->
		<div region="center" border="false">
			<iframe width="100%" height="100%" frameborder="0" id="mainframe"
				src=""></iframe>
		</div>
	</div>
</body>
</html>