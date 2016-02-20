<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<title>数据源编辑</title>
</head>
<body>
		<table>
			<tr>
				<td>
					<s:label name="datasourceConfig.name" label="名称"></s:label>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
				<s:label name="datasourceConfig.dsType" label="类型" ></s:label>
				</td>
			</tr>
			<tr>
				<td>
				<s:label name="datasourceConfig.dsProperties" label="配置" ></s:label>
				</td>
			</tr>
			<tr>
				<td>
					<s:label name="datasourceConfig.propertyRegex" label="分隔符" ></s:label>
				</td>
			</tr>
		</table>
</body>
</html>

