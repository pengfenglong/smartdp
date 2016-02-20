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

namespace PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.article.service {

	public partial class Default : System.Web.UI.Page {

		public Hashtable Data = new Hashtable();

		public Hashtable MasterData = new Hashtable();

		public DCMmj mmj = new DCMmj();

		public DCPhw phw = new DCPhw();

		protected void Page_Load ( object sender, EventArgs e ) {

			getArticleList();

			bind();

		}

		protected void bind () {

			articleList.DataSource = Data["articleList"];
			articleList.DataBind();

		}

		protected void getArticleList () {

			Data["articleList"] = mmj.getStudyArticleList(9999);

		}

	}

}