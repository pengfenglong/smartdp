<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/framework/head-include.jsp"%>
<style type="text/css">
	.site {
		margin: 5px 35px;
		padding: 5px;
		height: 60px;
		width: 60px;
		cursor: pointer;
	}
</style>
<script type="text/javascript">
	$(function(){
		$.get(contextPath+'/cms-listAll-Site.action',function(data){
			for(var i=0;i<data.length;i++){
				var s = [];
				s.push('<div id="'+data[i].id+'" class="site">');
				s.push('	<img src="../../../framework/icon/Apple/dubadh.png" />');
				s.push('	<div class="site-name">'+data[i].siteName+'</div>');
				s.push('</div>');
				$('#site').append(s.join(''));
				$('#'+data[i].id).click(function(){
					$('#mainframe').attr('src','channelarticle/index.jsp?siteId='+$(this).attr('id')+
									'&siteName='+$(this).find('.site-name').text());
				});
			}
		});
	})
</script>
</head>
<body>
<sm:layoutcontainer>
	<sm:layout region="west" width="150px" border="false" split="true">
		<sm:accordion animate="false" fit="false" border="false">
			<sm:accordionpanel title="内容管理">
				<div style="padding: 5px;">
				<sm:tree  data="[
						{
							id:1,
							text:'模板管理',
							attributes:{'url':'template/index.jsp'}
						},{
							id:1,
							text:'模板文件管理',
							attributes:{'url':'file/index.jsp'}
						},{
							id:1,
							text:'站点管理',
							attributes:{'url':'site/index.jsp'}
						}
				
				]" onClick="function(node){
					$('#mainframe').attr('src',node.attributes.url);
				}"></sm:tree>
				</div>
			</sm:accordionpanel>
			<sm:accordionpanel title="站点">
				<div id="site">
				</div>
			</sm:accordionpanel>
		</sm:accordion>
	</sm:layout> 
	<sm:layout region="center" border="false">
		<iframe width="100%" height="100%" frameborder="0" id="mainframe" src=""></iframe>
	</sm:layout>
</sm:layoutcontainer>
</body>
</html>