<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:datagrid title="微信会员管理" url="weixin-list-Member.action" idField="id" autotool="add,edit,delete">
	<sm:datagridcolumn field="weixinloginAccount" width="1" query="true">微信登陆账号</sm:datagridcolumn>
	<sm:datagridcolumn field="contact" width="1">描述</sm:datagridcolumn>
	<sm:datagridcolumn field="createDate" width="1">创建日期</sm:datagridcolumn>
	<sm:datagridcolumn field="user" width="1">所属用户</sm:datagridcolumn>
</sm:datagrid>
