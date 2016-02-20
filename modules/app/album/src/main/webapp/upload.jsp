<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<html>
<head>
<title></title>
</head>
<body>
	<div style="width: 70%;height: 90%;padding: 50px auto;margin: 10px auto">
		<sm:layoutcontainer>
			<sm:layout region="north" height="38px">
				 <div style="background-color: #E6E6E6;height: 38px;padding-top: 0px;overflow: hidden;">
				 	<sm:upload uploader="/album-upload-Photo.action" extend="pic" view="true"></sm:upload>
				 </div>
			</sm:layout>
			<sm:layout region="center">
				<div id="filediv" style="border: 2px #D8D8D8 solid;height: 99%"></div>
			</sm:layout>
			<sm:layout region="south" height="80px">
				<div style="padding: 8px;float: right;">
					<sm:linkbutton onclick="window.location.href='index.jsp'">返回相册首页</sm:linkbutton>
					<sm:linkbutton onclick="upload()">取消上传</sm:linkbutton>
					<sm:linkbutton onclick="cancelUpload()">开始上传</sm:linkbutton>
				</div>
			</sm:layout>
		</sm:layoutcontainer>
	</div>
</body>
</html>