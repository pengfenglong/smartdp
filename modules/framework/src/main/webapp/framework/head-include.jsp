<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%> 
<sm:base type="jquery,cookie,easyui,tools,DatePicker,multiselect2side,framework"></sm:base>
<% 
String contextPath = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
%>
<script type="text/javascript">
<!--
	var contextPath = '<%=contextPath%>';
	var basePath = '<%=basePath%>';
//-->
</script>
