
//百度地图API功能
function loadJScript() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=9c19b7c324039c48929e2d5902116c17&callback=init";
	document.body.appendChild(script);
}
function init() {
	alert(1);
	var map = new BMap.Map("allmap");            // 创建Map实例
	alert(2);
	var point = new BMap.Point(116.404, 39.915); // 创建点坐标
	alert(3);
	map.centerAndZoom(point,15);                 
	alert(4);
	map.enableScrollWheelZoom();                 //启用滚轮放大缩小
}  

loadJScript();