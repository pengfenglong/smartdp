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


namespace PeopleRich.PeopleRichWeb.StudyInfo.Admin.source.application.Plugin.slides.service {

	public partial class add : System.Web.UI.Page {

		public Hashtable Data = new Hashtable();

		public Hashtable MasterData = new Hashtable();

		public DCMmj mmj = new DCMmj();

		public DCPhw phw = new DCPhw();

		protected void Page_Load ( object sender, EventArgs e ) {

			getForm();

			bind();

		}

		protected void bind () {

			mmj.insert("QMFY_slides", Data);

		}

		protected void getForm () {

			Data["image"] = Request.Form["image"];

			Data["link"] = Request.Form["link"];

			Data["title"] = Request.Form["title"];

			Data["type"] = Request.QueryString["index"];

		}

	}

}