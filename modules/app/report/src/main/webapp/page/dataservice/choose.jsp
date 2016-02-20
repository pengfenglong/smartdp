<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<html>
	<head>
		<%@ include file="/WEB-INF/page/common/meta.jsp" %>
		<title>选择数据服务</title>
	</head>
	
	<body>
	
	<ec:table items='page.result' var="item" method="get" filterable="false" imagePath="${ctx}/resources/rae/images/table/*.gif"
		retrieveRowsCallback="limit" sortRowsCallback="limit" filterRowsCallback="limit"
		action="${ctx}/rae/chooseDataservice.action" autoIncludeParameters="true" rowsDisplayed="15">
		<ec:row>
			<ec:column property="&nbsp" sortable="false" width="20" viewsAllowed="html" filterable="false">
				<input type="radio" name="items" value="${item.id}"/>
			</ec:column>
			<ec:column property="name"  title="名称"/>
			<ec:column property="description"  title="描述"/>
		</ec:row>
	</ec:table>
	</body>
</html>
