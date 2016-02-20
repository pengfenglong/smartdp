<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript">
	$(function(){
		$.get(contextPath+'/cms-lists-Template.action',function(data){
			var dir = data.dir;
			var list = data.list;
			for(var i=0;i<list.length;i++){
				var s = '<div>';
				s += '<div><img style="width:160px;height:auto;" src="'+contextPath+dir+'/'+list[i]+'/demo.jpg'+'" /></div>';
				s += '<div><input type="radio"/>'+list[i]+'</div>';
				s += '</div>';
				$('#template').append(s);
			}
			
		});
	})
</script>
</head>
<body style="padding: 20px;">
<div id="template"></div>
</body>
</html>