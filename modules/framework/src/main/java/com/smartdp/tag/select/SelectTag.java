package com.smartdp.tag.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.thoughtworks.xstream.XStream;

public class SelectTag extends TagSupport {

	protected String id;
	protected String name;
	protected String style;
	protected String dict;

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			StringBuffer results = new StringBuffer("<select class=\"easyui-combobox\" style=\"width:150px;\"");
			if (id != null) {
				results.append(" id=\"");
				results.append(id);
				results.append("\"");
			}
			if (name != null) {
				results.append(" name=\"");
				results.append(name);
				results.append("\"");
			}
			if (style != null) {
				results.append(" style=\"");
				results.append(style);
				results.append("\"");
			}
			results.append(">");

			XStream xstream = new XStream();
			xstream.alias("DictList", DictList.class);
			xstream.alias("DictType", DictType.class);
			xstream.alias("Dict", Dict.class);
			DictList dictList = (DictList) xstream.fromXML(this.getClass()
					.getClassLoader().getResourceAsStream(
							"com/smartdp/tag/select/dict.xml"));
			
			List<DictType> dictTypes = DictUtils.getDictTypes();
			for(DictType dictType : dictTypes){
				if (dict.equals(dictType.getNameEn())) {
					List<Dict> dicts = dictType.getDicts();
					for(Dict dict : dicts){
						results.append("<option value=\""+dictType.getNameEn()+"-"+dict.getNameEn()+"\">"+dict.getName()+"</option>");
					}
				}
			}
			
			results.append("</select");
			pageContext.getOut().write(results.toString());
		} catch (IOException ex) {
			throw new JspTagException("错误");
		}
		return EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getDict() {
		return dict;
	}

	public void setDict(String dict) {
		this.dict = dict;
	}

}
