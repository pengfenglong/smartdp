using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Collections;
using PeopleRich.DataControllor;
using System.Data;

namespace PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.articleClass.service {

	public partial class Default : System.Web.UI.Page {

		public Hashtable Data = new Hashtable();

		public Hashtable MasterData = new Hashtable();

		public DCMmj mmj = new DCMmj();

		public DCPhw phw = new DCPhw();

		protected void Page_Load ( object sender, EventArgs e ) {

			string type = Request.QueryString["type"];

			if (type == "delete") {

				string id = Request.Form["id"];

				DbHelperSQL.ExecuteSql("DELETE FROM [QMFY_Study_language] WHERE id = " + id + "");

				Response.End();

			} else if (type == "add") {

				string lang = Request.Form["lang"];

				DbHelperSQL.ExecuteSql("INSERT INTO [QMFY_Study_language] (lang) VALUES ('" + lang + "')");

				Response.End();

			}

			bind();

		}

		protected void bind () {

			rptLnagList.DataSource = mmj.getStudyLanguageList(99999);
			rptLnagList.DataBind();

		}

	}

}