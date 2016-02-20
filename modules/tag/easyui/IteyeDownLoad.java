import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IteyeDownLoad {

	/**
	 * @throws IOException
	 * @method 测试获取内容程序
	 */
	public static void main(String[] args) {

		/**
		 * 执行分析程序
		 */
		String url = "http://www.phptogether.com/juidoc/";
		String HtmlContent = getContentByJsoup(url);
		List<String> divContent = getDivContentByJsoup(HtmlContent, "item-p");
		List<String> urls = getLinksByJsoup(divContent.get(0));

		Map<String, Map> map = getResultMap(urls);
		try {

			if (!map.isEmpty()) {

				buildTld(map);
				buildTagClass(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Map<String, Map> getResultMap(List<String> urls) {
		Map<String, Map> map = new HashMap<String, Map>();
		for (String u : urls) {
			// System.out.println(u);
			String desc = u.split(",")[0];
			String ur = u.split(",")[1];
			String name = ur.substring(ur.lastIndexOf("/") + 1, ur
					.lastIndexOf("."));
			Map ma = new HashMap();
			String hc = getContentByJsoup(ur);
			List<String> dc = getDivContentByJsoup(hc, "doc-table");
			ma.put("desc", desc);
			if (dc.size() == 3) {
//				System.out
//						.println("========================属性=======================");
				ma.put("attribute", getByJsoup(dc.get(0), 0));
//				System.out.println("========================事件===============");
				ma.put("event", getByJsoup(dc.get(1), 1));
//				System.out
//						.println("========================方法===========================");
				ma.put("function", getByJsoup(dc.get(2), 2));

				map.put(name, ma);
				//System.out.println(name+"已经生成");
			}else if(dc.size() == 2){
				//System.out.println(name+":"+dc.size());
				ma.put("attribute", getByJsoup(dc.get(0), 0));
				map.put(name, ma);
			}else{
				System.out.println(name+":"+dc.size()+",需要优化");
				ma.put("attribute", getByJsoup(dc.get(0), 0));
				map.put(name, ma);
			}

		}
		return map;
	}

	public static void buildTld(Map<String, Map> map) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<taglib>");
		sb.append("<tlib-version>1.0</tlib-version>");
		sb.append("<jsp-version>1.2</jsp-version>");
		sb.append("<short-name>easyuitag</short-name>");

		sb.append("<uri>/easyui-tag</uri>");

		sb.append("<display-name>easyuitag</display-name>");
		sb.append("<description><![CDATA[easyuitag.]]></description>");
		for (String name : map.keySet()) {

			String _name = name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());

			sb
					.append("<tag><name>")
					.append(name)
					.append("</name><tag-class>com.smartdp.tag.easyui.")
					.append(_name)
					.append(
							"Tag</tag-class><body-content>JSP</body-content><display-name>")
					.append(name).append(
							"</display-name><description><![CDATA[").append(
							map.get(name).get("desc")).append(
							"]]></description>");

			sb
					.append("<attribute><name>id</name><required>true</required><rtexprvalue>true</rtexprvalue><description><![CDATA[ID]]></description></attribute>");

			List<Map> attributes = (List<Map>) map.get(name).get("attribute");
			if (attributes != null && attributes.size() != 0) {
				for (Map attribute : attributes) {
					if (attribute.get("name") != null) {

						String name_note = ((String) attribute.get("name"))
								.trim();

						int index = name_note.indexOf("（");
						if (index == -1) {
							index = name_note.indexOf("(");
						}

						String n = "";
						String note = "";
						if (index == -1) {
							n = name_note;
							note = name_note;
						} else {
							n = name_note.substring(0, index).trim();
							note = name_note.substring(index + 1,
									name_note.length() - 1).trim();
						}

						sb.append("<attribute>");
						sb.append("<name>").append(n).append("</name>");
						sb.append("<required>false</required>");
						sb.append("<rtexprvalue>true</rtexprvalue>");
						sb.append("<description><![CDATA[属性名:").append(note)
								.append("; 类型:").append(attribute.get("type"))
								.append("; 默认值:").append(
										attribute.get("defaultvalue")).append(
										"; 描述:").append(attribute.get("desc"))
								.append("]]></description>");
						sb.append("</attribute>");
					}
				}
			}
			List<Map> events = (List<Map>) map.get(name).get("event");
			if (events != null && events.size() != 0) {
				for (Map event : events) {
					if (event.get("name") != null) {
						sb.append("<attribute>");
						sb.append("<name>").append(event.get("name")).append(
								"</name>");
						sb.append("<required>false</required>");
						sb.append("<rtexprvalue>true</rtexprvalue>");
						sb.append("<description><![CDATA[事件名:").append(
								event.get("name")).append("; 参数:").append(
								event.get("param")).append("; 描述:").append(
								event.get("desc")).append("]]></description>");
						sb.append("</attribute>");
					}
				}
			}
			sb.append("</tag>");

		}
		sb.append("</taglib>");

		File f = new File("F:\\workspace\\jgroup\\tld\\easyui.tld");

		FileOutputStream fs = new FileOutputStream(f);
		fs.write(sb.toString().getBytes());
	}

	public static void buildTagClass(Map<String, Map> map) throws IOException {
		StringBuffer sb = new StringBuffer();

		for (String name : map.keySet()) {

			String _name = name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());

			sb.append("package com.smartdp.tag.easyui;").append("\r\n");
			sb.append("import java.io.IOException;").append("\r\n");
			sb.append("import javax.servlet.jsp.JspException;").append("\r\n");
			sb.append("import javax.servlet.jsp.JspWriter;").append("\r\n");
			sb.append("import javax.servlet.jsp.tagext.TagSupport;").append(
					"\r\n");

			sb.append("public class ").append(_name).append(
					"Tag extends TagSupport {").append("\r\n");
			sb.append("private String id;//ID").append("\r\n");
			List<Map> attributes = (List<Map>) map.get(name).get("attribute");
			if (attributes != null && attributes.size() != 0) {
				for (Map attribute : attributes) {
					if (attribute.get("name") != null) {

						String name_note = ((String) attribute.get("name"))
								.trim();

						int index = name_note.indexOf("（");
						if (index == -1) {
							index = name_note.indexOf("(");
						}

						String n = "";
						String note = "";
						if (index == -1) {
							n = name_note;
							note = name_note;
						} else {
							n = name_note.substring(0, index).trim();
							note = name_note.substring(index + 1,
									name_note.length() - 1).trim();
						}

						sb.append("private ");
						if (attribute.get("type").toString().indexOf("boolean") != -1) {
							sb.append("boolean ");
						} else {
							sb.append("String ");
						}
						sb.append(n).append(";//").append(note).append("\r\n");

					}
				}
			}
			List<Map> events = (List<Map>) map.get(name).get("event");
			if (events != null && events.size() != 0) {
				for (Map event : events) {
					if (event.get("name") != null) {
						sb.append("private String ");
						sb.append(event.get("name")).append(";//").append(
								event.get("desc")).append("\r\n");
					}
				}
			}

			sb.append("public int doStartTag() throws JspException {").append(
					"\r\n");
			sb.append("	JspWriter out = pageContext.getOut();").append("\r\n");
			sb.append("	try {").append("\r\n");
			sb.append("		out.println(start());").append("\r\n");
			sb.append("	} catch (IOException e) {").append("\r\n");
			sb.append("		throw new JspException(e);").append("\r\n");
			sb.append("	}").append("\r\n");
			sb.append("	return EVAL_PAGE;").append("\r\n");
			sb.append("}").append("\r\n");

			sb.append("public int doEndTag() throws JspException {").append(
					"\r\n");
			sb.append("JspWriter out = pageContext.getOut();").append("\r\n");
			sb.append("try {").append("\r\n");
			sb.append("	out.println(\"</div>\");").append("\r\n");
			sb.append("} catch (IOException e) {").append("\r\n");
			sb.append("	throw new JspException(e);").append("\r\n");
			sb.append("}").append("\r\n");
			sb.append("return EVAL_PAGE;").append("\r\n");
			sb.append("}").append("\r\n");

			sb.append("public String start() {").append("\r\n");
			sb.append("	StringBuffer start = new StringBuffer();").append(
					"\r\n");
			sb.append(
					"		start.append(\"<div class=\\\"easyui-" + name + "\\\" ")
					.append("id=\\\"\").append(id).append(\"\\\"\")").append(
							"\r\n");

			if (attributes != null && attributes.size() != 0) {
				for (Map attribute : attributes) {
					if (attribute.get("name") != null) {

						String name_note = ((String) attribute.get("name"))
								.trim();

						int index = name_note.indexOf("（");
						if (index == -1) {
							index = name_note.indexOf("(");
						}

						String n = "";
						String note = "";
						if (index == -1) {
							n = name_note;
							note = name_note;
						} else {
							n = name_note.substring(0, index).trim();
							note = name_note.substring(index + 1,
									name_note.length() - 1).trim();
						}

						sb.append(
								"		.append(\" " + n + "=\\\"\").append(" + n
										+ ").append(\"\\\"\")   ").append(
								"\r\n");

					}
				}
			}

			if (events != null && events.size() != 0) {

				sb.append("		.append(\" data-options=\\\"\")").append("\r\n");
				int i = 0;
				for (Map event : events) {
					if (event.get("name") != null) {
						if (i == 0) {
							sb
									.append(
											"		.append(\"" + event.get("name")
													+ ":\")")
									.append(".append(").append(
											event.get("name")).append(")")
									.append("\r\n");
						} else {
							sb.append(
									"		.append(\"," + event.get("name")
											+ ":\")").append(".append(")
									.append(event.get("name")).append(")")
									.append("\r\n");
						}
						i++;
					}
				}
				sb.append("		.append(\"\\\"\")").append("\r\n");
			}

			sb.append("	.append(\">\");").append("\r\n");
			sb.append("return start.toString();").append("\r\n");
			sb.append("}").append("\r\n");

			sb.append("public String getId(){").append("\r\n");
			sb.append("	return id;").append("\r\n");
			sb.append("}").append("\r\n");

			sb.append("public void setId(String id){").append("\r\n");
			sb.append("	this.id=id;").append("\r\n");
			sb.append("}").append("\r\n");

			if (attributes != null && attributes.size() != 0) {
				for (Map attribute : attributes) {
					if (attribute.get("name") != null) {

						String name_note = ((String) attribute.get("name"))
								.trim();

						int index = name_note.indexOf("（");
						if (index == -1) {
							index = name_note.indexOf("(");
						}

						String n = "";
						String note = "";
						if (index == -1) {
							n = name_note;
							note = name_note;
						} else {
							n = name_note.substring(0, index).trim();
							note = name_note.substring(index + 1,
									name_note.length() - 1).trim();
						}

						String _n = n.substring(0, 1).toUpperCase()
								+ n.substring(1, n.length());

						sb.append("public ");
						if (attribute.get("type").toString().indexOf("boolean") != -1) {
							sb.append("boolean ");
							sb.append("is" + _n).append("(){").append("\r\n");
						} else {
							sb.append("String ");
							sb.append("get" + _n).append("(){").append("\r\n");
						}
						sb.append("return " + n).append(";").append("\r\n");
						sb.append("}").append("\r\n");

						sb.append("public void set").append(_n).append("(");
						if (attribute.get("type").toString().indexOf("boolean") != -1) {
							sb.append("boolean ");
						} else {
							sb.append("String ");
						}
						sb.append(n).append("){");

						sb.append("this." + n).append("=").append(n)
								.append(";").append("\r\n");
						sb.append("}").append("\r\n");

					}
				}
			}

			if (events != null && events.size() != 0) {

				for (Map event : events) {
					if (event.get("name") != null) {
						String n = event.get("name").toString();
						String _n = n.substring(0, 1).toUpperCase()
								+ n.substring(1, n.length());

						sb.append("public String ");

						sb.append("get" + _n).append("(){").append("\r\n");

						sb.append("return " + n).append(";").append("\r\n");
						sb.append("}").append("\r\n");

						sb.append("public void set").append(_n).append(
								"(String ");
						sb.append(n).append("){");
						sb.append("this." + n).append("=").append(n)
								.append(";").append("\r\n");
						sb.append("}").append("\r\n");
					}
				}
			}

			sb.append("}");

			File f = new File("F:\\workspace\\jgroup\\java\\" + _name
					+ "Tag.java");

			FileOutputStream fs = new FileOutputStream(f);
			fs.write(sb.toString().getBytes());
			sb = new StringBuffer();
		}

	}

	public static List<Map> getByJsoup(String divContent, int type) {

		List<String> urls = new ArrayList<String>();
		String abs = "http://www.phptogether.com/juidoc/";
		Document doc = Jsoup.parse(divContent, abs);
		Elements trs = doc.getElementsByTag("tr");
		// System.out.println("链接==="+linkStrs.size());
		List<Map> list = new ArrayList<Map>();
		for (Element tr : trs) {
			Map<String, String> map = new HashMap<String, String>();
			Elements tds = tr.children();
			if (tds.get(0).tagName().equals("td")) {
				if (type == 0) {
					map.put("name", tds.get(0).text());
					map.put("type", tds.get(1).text());
					map.put("desc", tds.get(2).text());
					if(tds.size()==4){
						map.put("defaultvalue", tds.get(3).text());
					}
				} else if (type == 1) {
					map.put("name", tds.get(0).text());
					map.put("param", tds.get(1).text());
					map.put("desc", tds.get(2).text());
				} else if (type == 2) {
					map.put("name", tds.get(0).text());
					map.put("param", tds.get(1).text());
					map.put("desc", tds.get(2).text());
				}
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 使用jsoup来对文档分析
	 */
	public static List<String> getDivContentByJsoup(String content, String style) {
		List<String> ss = new ArrayList<String>();
		String divContent = "";
		Document doc = Jsoup.parse(content);
		Elements divs = doc.getElementsByClass(style);
		if ("doc-table".equals(style)) {
			
			for(Element div : divs){
				ss.add(div.toString());
			}
		} else{
			ss.add(divs.toString());
		}
		// System.out.println("size="+divs.size()+"div==="+divContent);
		return ss;
	}

	/**
	 * 使用jsoup分析divContent 1.获取链接 2.获取url地址
	 */
	public static List<String> getLinksByJsoup(String divContent) {
		List<String> urls = new ArrayList<String>();
		String abs = "http://www.phptogether.com/juidoc/";
		Document doc = Jsoup.parse(divContent, abs);
		Elements linkStrs = doc.getElementsByTag("li");
		// System.out.println("链接==="+linkStrs.size());
		for (Element linkStr : linkStrs) {
			String url = linkStr.getElementsByTag("a").attr("abs:href");
			String title = linkStr.getElementsByTag("a").text();
			// System.out.println("标题:"+title+" url:"+url);
			urls.add(title + "," + url);
		}
		return urls;
	}

	/**
	 * 根据jsoup方法获取htmlContent
	 */
	public static String getContentByJsoup(String url) {
		String content = "";
		try {
			// Date startdate = new Date();
			Document doc = Jsoup.connect(url).data("jquery", "java").userAgent(
					"Mozilla").cookie("auth", "token").timeout(50000).get();
			// Date enddate = new Date();
			// Long time = enddate.getTime() - startdate.getTime();
			content = doc.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
