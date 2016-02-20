using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.HtmlControls;
using System.Collections;
using PeopleRich.DataControllor;
using System.Reflection;
using System.Data;
using System.Text.RegularExpressions;

namespace PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.Plugin.article.service.server {

	public partial class UploadorInsert : System.Web.UI.Page {

		public Hashtable Data = new Hashtable();

		public Hashtable MasterData = new Hashtable();

		public DCMmj mmj = new DCMmj();

		public DCPhw phw = new DCPhw();

		protected void Page_Load ( object sender, EventArgs e ) {
		
			getForm();

			bind();

		}

		protected void bind() {
		
			string type = Request.QueryString["type"];

			if (type == "upload") {

				string id = Request.QueryString["id"];

				mmj.upload("QMFY_Study_article", Data, "id = " + id);

			} else {

				mmj.insert("QMFY_Study_article", Data);
			
			}
		
		}

		protected void getForm(){

			string[] arr = Regex.Split(Request.Form["type"], "@@@@", RegexOptions.IgnoreCase);

			Data["subject"] = Request.Form["subject"];

			Data["subtitle"] = phw.SubStr(Request.Form["subtitle"].ToString(), 250);

			Data["content"] = Request.Form["content"];

			Data["images"] = Request.Form["images"];

			Data["type_index"] = arr[0];

			Data["type_name"] = arr[1];

		}

	}

}