<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<sm:validform formid="smart-form" layout="div" dialog="false" action="weixin-save-Member.action">
	<input type="hidden" name="mark" />
	<sm:div label="微信登陆账号" >
		<sm:validinput datatype="*2-20" name="weixinloginAccount"></sm:validinput>
	</sm:div>
	<sm:div label="微信登陆密码" >
		<sm:validinput type="password" datatype="*2-20" name="weixinLoginPwd"></sm:validinput>
	</sm:div>
	<sm:div label="描述" >
		<sm:validinput datatype="*2-8" name="contact"></sm:validinput>
	</sm:div>
</sm:validform>
