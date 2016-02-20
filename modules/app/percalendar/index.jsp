<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<sm:base type="jquery,jqueryui"></sm:base>
<link type="text/css" href="css/ui.calendar.perpetual.css" rel="stylesheet" />
<script type="text/javascript" src="js/ui.calendar.perpetual.js"></script>
<script type="text/javascript">
function pageLoad(){
	$('#divSliderWrapper').cal_perpetual0_1({fullPage: true});
}
</script>
</head>
<body onload="pageLoad()">
	<div id="divSliderWrapper">
	</div>
</body>
</html>