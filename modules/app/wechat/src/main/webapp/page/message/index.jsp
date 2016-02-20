<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<sm:datagrid title="消息管理" url="wechat-list-Message.action" idField="id" autotool="delete">
	<sm:datagridcolumn field="msgType" width="1" query="true">消息类型</sm:datagridcolumn>
	<sm:datagridcolumn field="fromUserName" width="2" query="true">发送者</sm:datagridcolumn>
	<sm:datagridcolumn field="toUserName" width="2" query="true">接受者</sm:datagridcolumn>
	<sm:datagridcolumn field="createTime" width="2">时间</sm:datagridcolumn>
	<sm:datagridcolumn field="content" width="4">内容</sm:datagridcolumn>
	<sm:datagridcolumn field="picUrl" width="2">图片地址</sm:datagridcolumn>
	<sm:datagridcolumn field="location_X" width="2">地理位置维度</sm:datagridcolumn>
	<sm:datagridcolumn field="location_Y" width="2">地理位置经度</sm:datagridcolumn>
	<sm:datagridcolumn field="url" width="2">消息链接</sm:datagridcolumn>
</sm:datagrid>
