<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<style>
	#func_t thead td{
		border-left: 1px solid #ddd;
		font-size: 16px;
		font-weight: bold;
		padding: 8px;
	}
	#func_t tbody td{
		border-left: 1px solid #ddd;
		font-size: 14px;
		padding: 5px;
		border-bottom: 1px dotted #ddd;
	}
</style>
<sm:panel title="功能列表" fit="true" border="false">
<table style="margin: 20px;border: 1px solid #ddd;" id="func_t">
	<thead style="background-color: #f2f2f2">
		<tr>
			<td>序号</td>
			<td>服务</td>
			<td>回复关键字</td>
			<td>状态</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1</td>
			<td>查看帮助</td>
			<td>查看所有服务的帮助信息，输入"帮助"或者"?"</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>2</td>
			<td>进入微官网</td>
			<td>获得微官网的链接，输入"首页"，或者直接点击<a onclick="window.open(basePath+'webos/index.html','newwindow','height=485,width=300,top=50,left=800,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');" style="color: red;font-weight: bolder;" href="#"> 这里 </a>进入</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>3</td>
			<td>进入新闻频道</td>
			<td>获得新闻频道的链接，输入"首页"，或者直接点击<a onclick="window.open(basePath+'template/wx/index.html','newwindow','height=485,width=300,top=50,left=800,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');" style="color: red;font-weight: bolder;" href="#"> 这里 </a>进入</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>4</td>
			<td>天气查询</td>
			<td>查询城市天气，例如输入"深圳天气"</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>5</td>
			<td>手机归属地查询</td>
			<td>查询手机的归属地以及所属品牌，例如输入"手机13812345678"</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>6</td>
			<td>身份证查询</td>
			<td>查询身份证的性别、地址等信息，例如输入"身份证123456789123456789"</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>7</td>
			<td>公交查询</td>
			<td>城市的公交线路和公交站点查询，例如输入"深圳公交365"查询深圳365路公交，"深圳公交科技园"查询深圳科技园站公交</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>8</td>
			<td>火车查询</td>
			<td>例如输入“火车北京到上海、火车T109”</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>9</td>
			<td>快递查询</td>
			<td>“快递单号”，例如”查申通快递222222“</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>10</td>
			<td>股票查询</td>
			<td>例如输入“股票000001”关键词</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
		<tr>
			<td>11</td>
			<td>周边信息查询</td>
			<td>请先发送位置给我们,再按照提示输入需要查询的内容</td>
			<td><input type="checkbox" checked="checked"/></td>
		</tr>
	</tbody>
</table>
</sm:panel>