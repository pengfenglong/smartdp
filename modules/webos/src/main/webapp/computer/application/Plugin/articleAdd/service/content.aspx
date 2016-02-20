<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="content.aspx.cs" Inherits="PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.Plugin.articleAdd.service.content" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="../../../Public/script/jQuery-1.7.2.min.js"></script>
	<script src="../../../Public/script/public.js"></script>
	<script src="../../../Public/script/model.js"></script>
	<link href="../../../Public/style/public.css" rel="stylesheet" type="text/css" />
	<script src="/PublicClass/jPlugin/plugin/UploadFileTemplate/source/script/jQueryExtend-1.0.1.js" type="text/javascript"></script>
	<script src="/PublicClass/jPlugin/plugin/UploadFileTemplate/source/script/jQuery.jUpload-0.0.1.js" type="text/javascript"></script>
	<script>

		Submit = function (obj) {

			jAdmin.oper.Submit(obj, '../../article/service/server/UploadorInsert.aspx?type=insert', 'insert');

		}

		$(document).ready(

			function () {

				var rand = parseInt(Math.random() * 7);

				$('.head-canvas img').attr('src', '../../../Public/style/head/h' + rand + '.png');

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
			
					<td><input type="text" name="subject" class="subject" value="文章标题" maxlength="40" onclick="this.select();" /></td>
					
				</tr>

				<tr>

					<td>
					
						<table class="table-subject-table">
						
							<tr>

								<td width="92"><div class="head-canvas"><img name="images" src="" /></div></td>
							
								<td>
									<div class="relative subtitle">
										<textarea name="subtitle"></textarea>
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
				
					<td><textarea name="content" style="width:100%;" class="jEdit content"></textarea></td>

				</tr>

				<tr>
				
					<td><button class="button left" type="button" onclick="Submit(this);">提交</button><button class="button" type="reset">重填</button></td>

				</tr>

			</table>

		</div>

    </form>

</body>
</html>