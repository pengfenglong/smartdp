<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="list.aspx.cs" Inherits="PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.Plugin.slides.service.list" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
	<script src="../../../../plugin/jQuery-1.7.2.min.js" type="text/javascript"></script>
	<script src="../../../Public/script/public.js"></script>
	<script src="../../../Public/script/model.js"></script>
	<script src="../../../../../../../PublicClass/jPlugin/plugin/UploadFileTemplate/source/script/jQueryExtend-1.0.1.js" type="text/javascript"></script>
	<script src="/PublicClass/jPlugin/plugin/UploadFileTemplate/source/script/jQuery.jUpload-0.0.1.js"></script>
	<script src="jmove-1.0.js"></script>
	<script>

		Submit = function (obj) {

			jAdmin.oper.Submit(obj, 'add.aspx?index=<%=Data["index"] %>', 'insert', {

				sure: function () {

					location.href = location.href;

				}

			});

		}

		$(function () {

			$('.typeList a').bind('click',

				function () {

					var title = $(this).html();

					var index = $(this).attr('class');

					jAdmin.oper.Upload(title, 'application/Plugin/slides/service/list.aspx?index=' + index, { width: '700px', height: '400px' });

				}

			);

			$('.imageList').mousedown(

				function () {

					Jmove(this).move({ flow: document.getElementById('imageListFlow'), parent: this });

				}

			);

			$('.del').hide();

			$('.del').click(

				function () {

					var title = '确认删除吗？';

					var _this = this;

					jAdmin.oper.Delete(title, 'delete.aspx?id=' + $(this).parent().parent().find('a').attr('class'), this, {

						sure: function () {

							$(_this).parent().parent().remove();

						}

					});

				}

			);

			$('.imageList').mouseover(

				function () {

					$(this).find('.del').show();

				}

			);

			$('.imageList').mouseout(

				function () {

					$(this).find('.del').hide();

				}

			);

		});
	</script>
	<style>
		ul{margin:0;padding:0;width:80%;}
		.imageList,.typeList{padding:5px 10px;width:100%;float:left;}
		.oper{float:left;}
		body a{font-size:12px;height:20px;line-height:20px;font-weight:bold;font-family:'微软雅黑';color:#F60;text-decoration:none;}
		body a:hover{text-decoration:underline;}
		.del{width:16px;height:20px;background:url(cross.gif) no-repeat center;cursor:pointer;display:inline-block;padding-left:5px;}
	</style>
</head>
<body>
	<ul>
		<asp:Repeater runat="server" ID="typeList">
			<ItemTemplate>
				<div class="typeList"><a href="javascript:;" class="<%#Eval("index") %>"><%#Eval("name") %></a></div>
			</ItemTemplate>
		</asp:Repeater>
	</ul>

	<ul id="imageListFlow">
		<asp:Repeater runat="server" ID="imageList">
			<ItemTemplate>
				<div class="imageList">
					<div class="oper"><a href="javascript:;" class="<%#Eval("id") %>"><%#Eval("image") %>[<%#Eval("link") %>][<%#Eval("title") %>]</a></div>
					<div class="oper"><span class="del"></span></div>
				</div>
			</ItemTemplate>
		</asp:Repeater>
	</ul>
	<%
	
		if ( Data["index"] != "" ) {

	%>

		<form>
			<table style="width:80%;">
		
				<tr>
			
					<td>
						<input type="text" name="image" onclick="$.jUpload.get(this)" value="图片路径" /><input type="text" name="link" onclick="this.select()" value="连接地址" /><input type="text" name="title" onclick="this.select()" value="说明" /><button type="button" onclick="Submit(this);">添加</button>
					</td>

				</tr>

			</table>
		</form>

	<%

		}

	%>
</body>
</html>
