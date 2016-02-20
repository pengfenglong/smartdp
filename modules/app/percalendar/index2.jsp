<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<sm:base type="jquery,jqueryui"></sm:base>
<link type="text/css" href="css/ui.calendar.perpetual.css" rel="stylesheet" />
<script type="text/javascript" src="js/ui.calendar.perpetual.js"></script>
<script type="text/javascript">
	function AddDiagram(){
		var myDate = new Date();
		var _id = "tp" + String(myDate.getTime());
		divEle = document.createElement("div");
		divEle.setAttribute('id', _id);
		divEle.setAttribute('style', "height:400px;");
		document.getElementById("divSliderWrapper").appendChild(divEle);
		$('#' + _id).cal_perpetual0_1({});
	}
	function RemoveDiagram(){
		var ele = document.getElementById("divSliderWrapper");
		if(ele.children.length > 0){
			$(ele.children[0]).remove();
		}
	}
</script>
</head>
<body>
	<h2 class="demoHeaders">diagram</h2>
	<input type="button" value="add diagram" onclick="AddDiagram()" />
	<input type="button" value="rem diagram" onclick="RemoveDiagram()" />
	<div id="divSliderWrapper">
	</div>
</body>
</html>