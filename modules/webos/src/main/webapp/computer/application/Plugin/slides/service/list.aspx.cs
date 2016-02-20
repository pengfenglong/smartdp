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

	public partial class list : System.Web.UI.Page {

		public Hashtable Data = new Hashtable();

		public Hashtable MasterData = new Hashtable();

		public DCMmj mmj = new DCMmj();

		public DCPhw phw = new DCPhw();

		protected void Page_Load ( object sender, EventArgs e ) {

			Data["index"] = Request.QueryString["index"];

			if ( Data["index"] == null ) {

				getAllSlidesType();

				Data["index"] = "";

			}
			else {

				getSlides();

			}

		}

		protected void getSlides () {

			imageList.DataSource = mmj.getSlides(Data["index"].ToString());
			imageList.DataBind();

		}

		protected void getAllSlidesType () {

			typeList.DataSource = mmj.getAllSlidesType();
			typeList.DataBind();

		}

	}

}