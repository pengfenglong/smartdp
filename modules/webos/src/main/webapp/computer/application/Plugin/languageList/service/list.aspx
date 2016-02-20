<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="list.aspx.cs" Inherits="PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.articleClass.service.Default" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
	<title></title>
	<script type="text/javascript" src="/js/jscript/jlayer-1.1.js"></script>
	<script type="text/javascript" src="/js/jQuery-1.7.2.min.js"></script>
	<link href="/PublicClass/jPlugin/plugin/UploadFileTemplate/style/index.css" rel="Stylesheet" type="text/css" />
	<link href="/PublicClass/jPlugin/plugin/UploadFileTemplate/style/style.css" rel="Stylesheet" type="text/css" />
	<script>

		addLang = function (obj) {

			obj = $(obj);

			var lang = obj.parents('tr').find('input:eq(0)').val();

			if (lang.length < 1) {

				top.$.jLayer.alert('不能为空！');

				return;

			}

			$.ajax({

				type: 'POST',

				data: 'lang=' + lang,

				url: 'list.aspx?type=add',

				success: function (xmlHttp) {

					location.href = location.href;

				}

			});

		}

		country_delete = function (id) {

			$.ajax({
				type: 'POST',
				data: 'id=' + id,
				url: 'list.aspx?type=delete',
				success: function () {

					$(_this).remove();

				}
			});

		}

		insertGame = function (obj) {

			obj = $(obj);

			parent = obj.parent().parent().parent();

			gameType = parent.find('input:[type=hidden]').val();

			name = parent.find('.name').val();

			hot = parent.find('.hot').val();

			picture = parent.find('.picture').val();

			area = parent.find('.area').val();

			type = parent.find('.gameType').val();

			link = parent.find('.link').val();

			i = obj.parent().parent().parent().parent().index();

			$.ajax({
				type: 'POST',
				url: 'server/Game_List_server.aspx?type=insert',
				data: 'name=' + escape(name) + '&gameType=' + escape(gameType) + '&hot=' + escape(hot) + '&picture=' + escape(picture) + '&area=' + escape(area) + '&type=' + escape(type) + '&link=' + escape(link),
				beforeSend: function () {

					obj.attr('disabled', true);

				},
				success: function (xmlHttp) {

					li = $('<li title="拖动删除" class="listli"><a href="javascript:void(' + xmlHttp + ');" title="' + name + '" class="normal"><span>' + name + '</span>[' + hot + '][' + picture + '][' + area + '][' + type + ']</a></li>').appendTo('.country_list ul:eq(' + i + ')');

					$(li).bind('mousedown',

						function (e) {

							down(this, e);

						}

					);

					data.add.remove();

				}
			});

		}

		$(document).ready(
			function () {

				drag = false;

				down = function (obj, e) {

					document.onselectstart = function () { return false; }

					id = $(obj).find('a').attr('href').replace(/javascript:void\((.*)\);/ig, '$1');

					name = $(obj).find('a').html();

					drag = true;

					downX = e.pageX;
					downY = e.pageY;

					diff = 0;

					deldiff = 50;

					_this = obj;

					offLeft = $(obj).offset().left;
					offTop = $(obj).offset().top;

				}

				$(document).mousemove(
					function (ev) {

						if (drag) {

							nowL = ev.pageX - (downX - offLeft);
							nowT = ev.pageY - (downY - offTop);

							nowX = ev.pageX;
							nowY = ev.pageY;

							if (Math.abs(nowX - downX) > diff || Math.abs(nowY - downY) > diff) {

								if (Math.abs(nowX - downX) > deldiff || Math.abs(nowY - downY) > deldiff) {

									border = 'solid 1px #f00';

									$(_this).find('a').attr('class', 'delete');

								} else {

									border = 'solid 1px #ccc';

									$(_this).find('a').attr('class', 'normal hover');

								}

								$(_this).css({
									border: border,
									position: 'absolute',
									top: nowT + 'px',
									left: nowL + 'px',
									opacity: '0.75',
									zIndex: 1
								});

							}

						}

					}
				);

				$(document).mouseup(

					function (ev) {

						if (drag) {

							upX = ev.pageX;
							upY = ev.pageY;

							if (Math.abs(upX - downX) > deldiff || Math.abs(upY - downY) > deldiff) {

								fn = function () {

									country_delete(id);

								}

								top.$.jLayer.confirm({
								
									subject: '确认删除 "' + name + '" 吗？',

									sure: fn

								});

							}

							$(_this).css({
								position: 'static',
								border: 'none',
								opacity: '1',
								fontSize: '12px',
								border: 'solid 1px #ddd'
							});

							$(_this).find('a').attr('class', 'normal');

						}

						drag = false;
					}

				);

				$('.listli').mousedown(

					function (e) {

						down(this, e);

					}

				);

				data = {

					add: $()

				};

			}
		);

	</script>
	<style>
		body{margin:0;-moz-user-select:none;}
		table{width:100%;margin:0 auto;}
		.country_list ul
		{
			width: 100%;
			border: solid 1px #CCC;
			float: left;
			min-height:42px;
			margin:0;
			padding:0;
			_height:42px;
		}
		.listli
		{
			padding:0 5px;
			height: 30px;
			line-height: 30px;
			float: left;
			text-align: center;
			position: relative;
			cursor: move;
			overflow: hidden;
			background: #FFF;
			z-index: 0;
			font-size:12px;
			border:solid 1px #ddd;
			margin:5px;
			-moz-user-select:none;
		}
		.listli a
		{
			position: relative;

		}
		.listli span
		{
			color:#f00;
		}
		.delete
		{
			text-decoration: line-through;
			color: #F00;
		}
		.normal{
			color:#006bfc;
			text-decoration:none;
		}
		.type{
			width:150px;
			vertical-align:middel;
			font-size:12px;
			font-weight:bold;
			color:#f00;
			text-align:center;
			position:relative;
			cursor:pointer;
			position:relative;
			z-index:1000;
		}
		.add{
			position:absolute;
			top:0;
			left:0;
			border:solid 1px #f00;
			width:754px;
			height:42px;
			z-index:1000;
		}
		.add .del{
			position:absolute;
			top:2px;
			right:5px;
			cursor:pointer;
		}
		.add .con{
			height:100%;
			width:600px;
			float:right;
			background:#fff;
			text-align:left;
		}
		.add .con .input{
			width:100px;
			height:20px;
			margin:9px 5px 0 0;
			border:solid 1px #999;
			line-height:20px;
		}
		.add .con .name{
			color:#f00;
			text-indent:5px;
		}
		.add .con .hot{
			width:30px;
			text-align:center;
		}
		.add .con select{
			height: 23px;
			margin:0 5px;
		}
	</style>
</head>
<body onselectstart="return false">
	<form id="form1" runat="server">
		<table>

			<tr>
			
				<td width="50"><input style="height:20px;line-height:normal;" type="text" class="text" maxlength="50" /></td>
				<td><button class="button" type="button" onclick="addLang(this)">添加</button></td>

			</tr>

			<tr>

				<td colspan="2">
					
					<div class="country_list">

						<ul>

							<asp:Repeater runat="server" ID="rptLnagList">
								<ItemTemplate>
								
									<li title="拖动删除" class="listli"><a href="javascript:void(<%#Eval("id") %>);" title="<%#Eval("lang") %>" class="normal"><span><%#Eval("lang")%></span></a></li>

								</ItemTemplate>
							</asp:Repeater>

						</ul>

					</div>

				</td>					

			</tr>

		</table>

	</form>
</body>
</html>