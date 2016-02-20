<%@ page contentType="text/html;charset=UTF-8"%>
<head>
<title>权限管理</title>
<%@ include file="/framework/head-include.jsp"%>
<style type="text/css">
.subtitle{
	font-size:14px;
	font-weight:bold;
	color:#666;
	padding:10px 5px;
}
.toolbar{
	padding:4px 5px;
	background:#efefef;
	border-bottom:1px solid #92B7D0;
}
.toolbar a.l-btn{
	background:url('images/btn.jpg') repeat-x;
	border:1px solid #ccc;
	border-radius:3px;
	-moz-border-radius:3px;
	-webkit-border-radius: 3px;
}
.toolbar a.l-btn:hover{
	background:#fafafa;
}
.submenu{
}
.submenu a{
	display:block;
	line-height:150%;
	padding:3px 15px;
	text-decoration:none;
	color:#666;
	margin-bottom1:5px;
	background:url('images/bullet_blue.png') no-repeat 0 center;
}
.submenu a:hover{
	background-color:#ffffcc;
}
</style>
<script type="text/javascript">
	$(function(){
		$('a').click(function(){
			$('a').css('background-color','');
			$(this).css('background-color','#a1c4ff');
			$('#mainframe').attr('src',$(this).attr('targeturl'));
		});
	})
</script>
</head>
<body>
	<sm:layoutcontainer>
		<sm:layout region="west" width="180px" split="true">
			<div class="subtitle">权限类型</div>
			<div class="submenu" style="padding:0 20px;">
				<a href="#" targeturl="moduleoperate.jsp" style="background-color: #a1c4ff">模块操作权限</a>
				<a href="#" targeturl="module.jsp">模块菜单权限</a>
				<a href="#" targeturl="url.jsp">URL访问权限</a>
				<a href="#" targeturl="class.jsp">类方法权限</a>
				<a href="#" targeturl="virtual.jsp">虚拟权限</a>
			</div>
		</sm:layout>
		<sm:layout region="center">
			<iframe width="100%" height="100%" frameborder="0" id="mainframe" src="moduleoperate.jsp"></iframe>
		</sm:layout>
	</sm:layoutcontainer>
</body>
