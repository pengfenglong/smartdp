<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<html>
<head>
<title></title>
<style type="text/css">
body{overflow-y:scroll;font-family:"HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, sans-serif;background:#f4f4f4;padding:0;margin:0;}
h1{font-size:31px;line-height:32px;font-weight:normal;margin-bottom:25px;}
a,a:hover{border:none;text-decoration:none;}
img,a img{border:none;}
pre{overflow-x:scroll;background:#ffffff;border:1px solid #cecece;padding:10px;}
.clear{clear:both;}
.zoomed > .container{-webkit-filter:blur(3px);filter:blur(3px);}
.container{width:990px;margin:0 auto;}
.gallery{list-style-type:none;float:left;background:#ffffff;padding:20px 20px 10px 20px;margin:0;-webkit-box-shadow:0 1px 3px rgba(0,0,0,0.25);-moz-box-shadow:0 1px 3px rgba(0,0,0,0.25);box-shadow:0 1px 3px rgba(0,0,0,0.25);-webkit-border-radius:2px;-moz-border-radius:2px;border-radius:2px;}
.gallery li{float:left;padding:0 10px 10px 0;}
.gallery li:nth-child(6n){padding-right:0;}
.gallery li a,.gallery li img{float:left;}
.gallery li img{height: 100px;width: 150px;}
</style>
    <script type="text/javascript">
  $(function() {
  	$.ajax({
		 url:contextPath+'/album-list-Photo.action',
		 dataType:'json',
		 success:function(data){
	
			 for(var i=0;i<data.length;i++){
				 $('.gallery').append('<li><a href="browse.html#ad-image-'+i+'"><img src="'+data[i]+'" /></a></li>');
			 }
		 }
	});
    
  });
  </script>
</head>
<body>

<div class="container">
	<div style="float:right;margin:6px 15px;">
		<sm:linkbutton iconCls="icon-add" onclick="window.location.href='upload.jsp'">上传照片</sm:linkbutton>
	</div>
	<ul class="gallery"></ul>
</div>
</body>
</html>