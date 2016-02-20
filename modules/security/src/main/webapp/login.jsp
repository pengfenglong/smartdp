<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springframework.security.web.WebAttributes"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>登录页</title>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/framework/plugin/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var left=($(window).width()-$('#login').width())/2;
			var top=($(window).height()-$('#login').height())/2;
			$('#login').css('left',left);
			$('#login').css('top',top);
		})
	</script>
</head>
<body>

<div id="login">
		
		<form id="loginForm" action="<%=request.getContextPath()%>/j_spring_security_check" method="post">

			<div class="top-logo"></div>
			
			<div class="mid-input">

				<div class="input-um"></div>
				
				<div class="input-bor"><input type="text" id='j_username' name='j_username'/></div>
			
			</div>
			
			<div class="mid-input">
			
				<div class="input-pd"></div>
				
				<div class="input-bor"><input type="password" id='j_password' name='j_password'/></div>
				
			</div>
			
			<div class="mid-login">
			
				<div class="registered"><a target="_blank" href="javascript:;">注册</a> | </div>

				<button type="submit" class="login"></button>
			
			</div>
			
			<div class="but-empty">
				<%
				if (session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) != null) {
				%> 
				登录失败，请重试.
				<%
				}
				%>
			</div>
		</form>
			
	</div>
</body>
</html>

