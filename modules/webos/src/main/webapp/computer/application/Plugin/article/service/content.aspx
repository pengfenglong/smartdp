<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="content.aspx.cs" Inherits="PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.Plugin.article.service.content" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="../../../Public/script/jQuery-1.7.2.min.js"></script>
	<script src="../../../Public/script/public.js"></script>
	<script src="../../../Public/script/model.js"></script>
	<script src="/js/jPlugin.js#edit"></script>
	<link href="../../../Public/style/public.css" rel="stylesheet" type="text/css" />
	<script>

		Submit = function (obj) {

			jAdmin.oper.Submit(obj, 'server/UploadorInsert.aspx?id=<%=Data["id"] %>&type=upload', 'upload');

		}

		$(document).ready(

			function () {

				$('.head-canvas img').bind('click',

					function () {

						$.jUpload.get(this);

					}

				);

				$('textarea:[name=subtitle]').jTextLimit('.textLimit');

			}

		);

	</script>
</head>
<body>

    <form id="form1" runat="server">

		<div id="model-main">

			<table class="table-subject">
		
				<tr>
			
					<td><input type="text" name="subject" class="subject" runat="server" id="subject" maxlength="40" onclick="this.select();" /></td>
					
				</tr>

				<tr>
				
					<td>
					
						<table class="table-subject-table">
						
							<tr>

								<td width="92"><div class="head-canvas"><img name="images" src="" runat="server" id="images" /></div></td>
							
								<td>
									<div class="relative subtitle">
										<textarea name="subtitle" runat="server" id="subtitle"></textarea>
										<div class="select">
											<span class="textLimit">{num} / <span>{250}</span></span>
											<select runat="server" id="type" name="type"></select>
										</div>
									</div>
								</td>

							</tr>

						</table>

					</td>

				</tr>
					
				<tr>
				
					<td><textarea name="content" style="width:100%;" id="Content" runat="server" class="jEdit content"></textarea></td>

				</tr>

				<tr>
				
					<td><button class="button left" type="button" onclick="Submit(this);">提交</button><button class="button" type="reset">重填</button></td>

				</tr>

			</table>

		</div>

    </form>

</body>
</html>