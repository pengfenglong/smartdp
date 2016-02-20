<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<title>用户列表</title>
<style type="text/css">
body {
	font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica,
		sans-serif;
	color: #4f6b72;
}

a {
	color: #c75f3e;
}

.el-layout {
	width: 80%;
	padding: 0;
	margin: 15px;
}

caption {
	padding: 0 0 5px 0;
	width: 700px;
	font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	text-align: right;
}

th {
	font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #4f6b72;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	border-top: 1px solid #C1DAD7;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-align: left;
	padding: 6px 6px 6px 12px;
	background: #CAE8EA no-repeat;
}
/*power by www.winshell.cn*/
th.nobg {
	border-top: 0;
	border-left: 0;
	border-right: 1px solid #C1DAD7;
	background: none;
}

td {
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	background: #fff;
	font-size: 11px;
	padding: 6px 6px 6px 12px;
	color: #4f6b72;
}
/*power by www.winshell.cn*/
td.alt {
	background: #F5FAFA;
	color: #797268;
}

th.spec {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #fff no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
}

th.specalt {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #f5fafa no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #797268;
}
/*---------for IE 5.x bug*/
html>body td {
	font-size: 11px;
}

body,td,th {
	font-family: 宋体, Arial;
	font-size: 12px;
}
</style>

<script type="text/javascript">
	function delFildMapping(item) {
		$(item).parent().parent().remove();
	}

	$(function() {
		$(".button").button();
	});
</script>
</head>

<body>
	<s:form method="post" action="saveDataservice" namespace="/rae">
	    <input type="hidden" name="mode" value="<%=request.getAttribute("mode") %>"/>
	    <s:if test="#request.mode == 'edit'">
	       <s:hidden name="dataService.id" value="%{dataService.id}"></s:hidden>
	    </s:if>
		<s:hidden name="dataService.name" value="%{dataService.name}"></s:hidden>
		<s:hidden name="dataService.description" value="%{dataService.description}"></s:hidden>
		<s:hidden name="dataService.executeSql" value="%{dataService.executeSql}"></s:hidden>
		<s:hidden name="dataService.datasourceConfig.id" value="%{dataService.datasourceConfig.id}"></s:hidden>
		</br>
		<span style="margin:0 10px;font-size:20px;font-weight: bolder;">参数列表</span>		
		</br>
		<table class="el-layout" style="" border="1px">
			<thead>
				<tr>
					<th scope="col">字段名</th>
					<th scope="col">字段别名</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="fieldMappings" var="fieldMapping" status="stat">
					<s:if test="%{#fieldMapping.isParameter == true}">
						<tr>
							<td class="row">
								${fieldMapping.fieldName}
								<input type="hidden" name="needSavefieldMappings[<s:property value="%{#stat.index}"/>].fieldName" value="${fieldMapping.fieldName}" />
								<input type="hidden" name="needSavefieldMappings[<s:property value="%{#stat.index}"/>].isParameter" value="${fieldMapping.isParameter}" />							</td>
								<input type="hidden" name="needSavefieldMappings[<s:property value="%{#stat.index}"/>].fieldType" value="${fieldMapping.fieldType}" />							</td>
							</td>
							<td class="row"><input name="needSavefieldMappings[<s:property value="%{#stat.index}"/>].fieldAlias" />
							</td>
						</tr>

					</s:if>
				</s:iterator>

			</tbody>

		</table>
		</br>

		<span style="margin:0 10px;font-size:20px;font-weight: bolder;">字段列表</span>
		</br>
		<table class="el-layout" style="" border="1px">
			<thead>
				<tr>
					<th scope="col">字段名</th>
					<th scope="col">字段别名</th>
					<th scope="col">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="fieldMappings" var="fieldMapping" status="stat">
					<s:if test="%{#fieldMapping.isParameter == false}">
						<tr>
							<td class="row">
								${fieldMapping.fieldName}
								<input type="hidden" name="needSavefieldMappings[<s:property value="%{#stat.index}"/>].fieldName" value="${fieldMapping.fieldName}" />
								<input type="hidden" name="needSavefieldMappings[<s:property value="%{#stat.index}"/>].isParameter" value="${fieldMapping.isParameter}" />
								<input type="hidden" name="needSavefieldMappings[<s:property value="%{#stat.index}"/>].fieldType" value="${fieldMapping.fieldType}" />
							</td>
							<s:if test="%{#fieldMapping.fieldAlias == null}">
								<td class="row"><input style="height: 25px;width: 280px" name="needSavefieldMappings[<s:property value="%{#stat.index}"/>].fieldAlias"  value="${fieldMapping.fieldName}"/>
								</td>
							</s:if>
							<s:else>
							    <td class="row"><input style="height: 25px;width: 280px" name="needSavefieldMappings[<s:property value="%{#stat.index}"/>].fieldAlias"  value="${fieldMapping.fieldAlias}"/>
                                </td> 
							</s:else>
							<td class="row"><button onclick="delFildMapping(this);">x</button>
							</td>
						</tr>

					</s:if>
				</s:iterator>

			</tbody>

		</table>
		<p class="el-layout" align="right">
			<input type="submit" value="保存 " class="button"/>
		</p>
	</s:form>

</body>
</html>