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

namespace PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.Plugin.article.service {

	public partial class content : System.Web.UI.Page {

		public Hashtable Data = new Hashtable();

		public Hashtable MasterData = new Hashtable();

		public DCMmj mmj = new DCMmj();

		public DCPhw phw = new DCPhw();

		protected void Page_Load ( object sender, EventArgs e ) {

			Data["id"] = Request.QueryString["id"];

			getStudyArticle();

			getStudyClass();

			bind();

		}

		protected void bind () {
	
			DataTable article = (Data["article"] as DataTable);

			string typeDefault = "";

			if ( article.Rows.Count > 0 ) {

				DataRow dr = article.Rows[0];

				subject.Attributes.Add("value", dr["subject"].ToString());

				subtitle.InnerHtml = dr["subtitle"].ToString();

				Content.InnerHtml = dr["content"].ToString();

				images.Attributes.Add("src", dr["images"].ToString());

				typeDefault = dr["type_index"].ToString() + "@@@@" + dr["type_name"].ToString();

			}

			type.DataSource = Data["studyClass"];

			type.DataTextField = "name";

			type.DataValueField = "indexGroup";

			type.DataBind();

			type.Items.FindByValue(typeDefault).Selected = true;

		}

		protected void getStudyArticle () {

			Data["article"] = mmj.getStudyArticle(Convert.ToInt32(Data["id"]));
		
		}

		protected void getStudyClass () {
		
			Data["studyClass"] = mmj.getStudyClass();
		
		}

	}

}