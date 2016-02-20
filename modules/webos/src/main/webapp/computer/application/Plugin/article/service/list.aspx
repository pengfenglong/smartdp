<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="list.aspx.cs" Inherits="PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.article.service.Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="../../../Public/script/jQuery-1.7.2.min.js"></script>
	<script src="../../../Public/script/public.js"></script>
	<script src="../../../Public/script/model.js"></script>
	<link href="../../../Public/style/public.css" rel="stylesheet" type="text/css" />
	<script>

		Upload = function (id, title) {

			jAdmin.oper.Upload(title, 'application/Plugin/article/service/content.aspx?id=' + id);

		}

		Delete = function (id, title, obj) {
		
			jAdmin.oper.Delete(title, 'server/Delete.aspx?id=' + id, obj);
		
		}

	</script>
</head>
<body>

    <form id="form1" runat="server">

		<div id="model-main">

			<table class="table-list">
		
				<tr class="table-title-head">
			
					<td width="20%">标题</td>
					<td width="30%">副标题</td>
					<td width="10%">类别</td>
					<td width="15%">添加时间</td>
					<td width="10%">操作</td>

				</tr>

				<asp:Repeater runat="server" ID="articleList">
					<ItemTemplate>

						<tr class="table-title-list">
			
							<td><a href="javascript:;" onclick="Upload(<%#Eval("id") %>, '<%#Eval("subject") %>')"><%#Eval("subject") %></a></td>
							<td><%#Eval("subtitle")%></td>
							<td><%#Eval("type_name")%></td>
							<td><%#Convert.ToDateTime(Eval("addTime")).ToString("yyyy-MM-dd HH:mm:ss") %></td>
							<td><div class="table-list-oper-m" onclick="Upload(<%#Eval("id") %>, '<%#Eval("subject") %>')"></div><div class="table-list-oper-d" onclick="Delete(<%#Eval("id") %>, '<%#Eval("subject") %>', this);"></div></td>

						</tr>

					</ItemTemplate>
				</asp:Repeater>

			</table>

		</div>

    </form>

</body>
</html>