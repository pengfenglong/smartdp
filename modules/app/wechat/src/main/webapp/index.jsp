<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.sina.sae.util.SaeUserInfo"%>
<html>
<head>
<title>微信公众账号营销平台</title>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
$(function(){
	$('#mainframe').attr('src',contextPath+'/component/wechat/page/func/index.jsp');
	$('.accordion-header').removeClass('accordion-header-selected');
	$('.accordion-header').click(function(){});
	$('.accordion-collapse').removeClass('accordion-collapse');
	$('.tree-node').click(function(){
		$('.tree-node').removeClass('tree-node-selected');
		$(this).addClass('tree-node-selected');
	});
})
</script>
</head>
<body>

<div id="mainlayout" class="easyui-layout" fit="true">
		<div region="north" border="false" style="height: 40px; background: #086A87">
			<div class="header">
				<div style="padding: 11px; text-align: right;">
					<a href="#" style="color: #fff" onclick="window.open ('component/chat/index.jsp','newwindow', 'height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')">在线聊天</a>
					<span style="color: #fff;margin: 0 20px">欢迎您:<sec:authentication property="name"/></span> 
					<%
						if ("appname".equals(SaeUserInfo.getAppName())) {
					%>
					<a href="<%=request.getContextPath()%>/j_spring_security_logout" style="color: #fff">退出</a>
					<%
						}else{
							//request.getSession().invalidate();
					%>
						<a href="<%=request.getContextPath()%>/index.html" style="color: #fff">退出</a>
					<%
						}
					%>
				</div>
				<div class="toptitle">微信公众账号营销平台</div>
			</div>
		</div>
		<div region="center" border="false">
			<sm:layoutcontainer>
			<sm:layout region="west" width="150px" border="false" split="true">
				<sm:accordion animate="false" fit="false" border="false">
					<sm:accordionpanel title="基本设置">
						<sm:tree  data="[
								{
									id:11,
									text:'功能管理',
									attributes:{'url':'func/index.jsp'}
								},{
									id:12,
									text:'账号设置',
									attributes:{'url':'account/index.html'}
								},{
									id:13,
									text:'消息管理',
									attributes:{'url':'message/index.jsp'}
								},{
									id:14,
									text:'默认设置',
									attributes:{'url':'library/default.jsp'}
								},{
									id:15,
									text:'关键字回复',
									attributes:{'url':'reply/index.jsp'}
								}
						
						]" onClick="function(node){
							$('#mainframe').attr('src',contextPath+'/component/wechat/page/'+node.attributes.url);
						}"></sm:tree>
					</sm:accordionpanel>
				</sm:accordion>
				<sm:accordion animate="false" fit="false" border="false">
					<sm:accordionpanel title="微官网">
						<sm:tree  data="[
								{
									id:31,
									text:'基本设置',
									attributes:{'url':'cms/page/site/form.jsp'}
								},{
									id:32,
									text:'首页幻灯片',
									attributes:{'url':''}
								},{
									id:33,
									text:'文章管理',
									attributes:{'url':'cms/page/channelarticle/index.jsp'}
								},{
									id:34,
									text:'分类管理',
									attributes:{'url':'cms/page/channel/index.jsp'}
								},{
									id:35,
									text:'模板管理',
									attributes:{'url':'cms/page/template/select.jsp'}
								}
						
						]" onClick="function(node){
							$('#mainframe').attr('src',contextPath+'/component/'+node.attributes.url);
						}"></sm:tree>
					</sm:accordionpanel>
				</sm:accordion>
				<sm:accordion animate="false" fit="false" border="false">
					<sm:accordionpanel title="微商城">
						<sm:tree  data="[
								{
									id:41,
									text:'基本设置',
									attributes:{'url':''}
								},{
									id:42,
									text:'分类管理',
									attributes:{'url':''}
								},{
									id:43,
									text:'商品管理',
									attributes:{'url':''}
								},{
									id:44,
									text:'订单管理',
									attributes:{'url':''}
								}
						
						]" onClick="function(node){
							$('#mainframe').attr('src',node.attributes.url);
						}"></sm:tree>
					</sm:accordionpanel>
				</sm:accordion>
				<sm:accordion animate="false" fit="false" border="false">
					<sm:accordionpanel title="微活动">
						<sm:tree  data="[
								{
									id:51,
									text:'会员卡',
									attributes:{'url':''}
								},{
									id:52,
									text:'优惠券',
									attributes:{'url':''}
								},{
									id:53,
									text:'抽奖',
									attributes:{'url':''}
								},{
									id:54,
									text:'刮刮卡',
									attributes:{'url':''}
								}
						
						]" onClick="function(node){
							$('#mainframe').attr('src',node.attributes.url);
						}"></sm:tree>
					</sm:accordionpanel>
				</sm:accordion>
				<sm:accordion animate="false" fit="false" border="false">
					
					<!-- 
					<sm:accordionpanel title="行业应用">
						<sm:tree  data="[
								{
									id:61,
									text:'餐饮',
									attributes:{'url':''}
								},{
									id:62,
									text:'酒店',
									attributes:{'url':''}
								},{
									id:63,
									text:'房产',
									attributes:{'url':''}
								},{
									id:64,
									text:'汽车',
									attributes:{'url':''}
								},{
									id:65,
									text:'教育',
									attributes:{'url':''}
								},{
									id:66,
									text:'医疗',
									attributes:{'url':''}
								}
						
						]" onClick="function(node){
							$('#mainframe').attr('src',node.attributes.url);
						}"></sm:tree>
					</sm:accordionpanel>
						 -->
					<sm:accordionpanel title="数据分析">
						<sm:tree  data="[
								{
									id:71,
									text:'请求数分析',
									attributes:{'url':''}
								},{
									id:72,
									text:'粉丝行为分析',
									attributes:{'url':''}
								}
						
						]" onClick="function(node){
							$('#mainframe').attr('src',node.attributes.url);
						}"></sm:tree>
					</sm:accordionpanel>
				</sm:accordion>
			</sm:layout> 
			<sm:layout region="center" border="false">
				<iframe width="100%" height="100%" frameborder="0" id="mainframe" src=""></iframe>
			</sm:layout>
		</sm:layoutcontainer>
		</div>
	</div>

</body>
</html>