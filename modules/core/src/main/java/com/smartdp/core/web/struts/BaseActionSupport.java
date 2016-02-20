package com.smartdp.core.web.struts;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.smartdp.core.dao.IPage;
import com.smartdp.core.dao.entity.PropertyFilter;
import com.smartdp.core.dao.impl.DefaultPage;
import com.smartdp.core.service.IBaseService;
import com.smartdp.core.utils.Struts2Utils;

/**
 * 封装一些基本的Action功能.
 * 
 * @author pengfenglong
 * 
 */

public class BaseActionSupport extends ActionSupport {

	private static final long serialVersionUID = 3525445612504421307L;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 自动写入baseService
	 */
	@Autowired
	protected IBaseService baseService;
	
	public Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * 获取请求对象.
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取响应对象.
	 * 
	 * @return current response
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获取session对象.
	 * 
	 * @return the session from the request (request.getSession()).
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获取 application 对象.
	 * 
	 * @return
	 */
	protected ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * 读取表单参数
	 * 
	 * @param name
	 * @return
	 */
	protected String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 将表单参数作为整数返回.
	 * 
	 * @param name
	 *            表单参数名
	 * @return
	 */
	protected int getParameterInt(String name) {
		String s = getParameter(name);

		if (s == null) {
			return 0;
		} else {
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0;
	}

	/**
	 * 将表单参数作为长整数返回.
	 * 
	 * @param name
	 *            表单参数名
	 * @return
	 */
	protected long getParameterLong(String name) {
		String s = getParameter(name);

		if (s == null) {
			return 0L;
		} else {
			try {
				return Long.parseLong(s);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0L;
	}

	/**
	 * 设置 request 的属性.
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	protected void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	/**
	 * 获取 request 的属性.
	 * 
	 * @param name
	 *            属性名
	 */
	protected Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	/**
	 * 读取 session 中的属性值
	 * 
	 * @param name
	 * @return
	 */
	protected Object getSession(String name) {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		return session.get(name);
	}

	/**
	 * 向 session 设置属性值
	 * 
	 * @param name
	 * @param value
	 */
	protected void setSession(Object name, Object value) {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		session.put(name, value);
	}

	/**
	 * 将所有URL参数合并成一个URL字符串(page参数除外), 提供分页时显示.
	 * 
	 * @return 字符串, 如: para1=11&para2=bb
	 */
	protected String mergeParamsAsURI() {
		Map<String, String[]> params = getRequest().getParameterMap();

		StringBuffer out = new StringBuffer();

		Set<String> keys = params.keySet();// 列出所有表单参数

		for (String key : keys) {
			if (!"page".equals(key)) {
				// TODO 发现Map值有乱码 --> Tomcat
				String[] values = params.get(key);// 尝试获取多个参数

				// 不管单个参数还是多个参数统一转成了数组
				if (values.length > 1) {
					values = getRequest().getParameterValues(key);
				} else {
					values = new String[] { getParameter(key) };
				}

				System.out.println("key=" + key);

				try {
					for (String value : values) {
						System.out.println("value=" + value);

						out.append(java.net.URLEncoder.encode(key, "UTF-8")
								+ "=");
						out.append(java.net.URLEncoder.encode(value, "UTF-8")
								+ "&");
						// key=value&
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		// 删除末尾多余的 & 字符
		if (out.toString().endsWith("&")) {
			out.deleteCharAt(out.length() - 1);
		}

		return out.toString();
	}
	
	public void savePage(IPage page){
		Assert.notNull(page,"page must be not null");
		Map resultObj = new HashMap();
		resultObj.put("total", page.getTotalCount()); 
		resultObj.put("rows", page.getResult());
		result = resultObj;
//		getRequest().setAttribute("page", page);
//		getRequest().setAttribute("totalRows", new Long(page.getTotalCount()));
	}
	
	public DefaultPage getPage(){
		DefaultPage page = new DefaultPage();
		page.setPageNo(getParameterInt("page"));
		page.setPageSize(getParameterInt("rows"));
		return page;
	}
	
	public List<PropertyFilter> getFilters(){
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		return filters;
	}
	
	/**
	 * 获取当前会话的登录用户信息
	 * 
	 * @return User
	 */
	// public User getSessionLoginedUser() {
	// User currentUser = (User) getSession("loginedUser");
	// return currentUser;
	// }
	
	
//	protected Map getConfiguration() {
//		Map config = (HashMap) getSession().getServletContext().getAttribute(
//				Constants.CONFIG);
//		// so unit tests don't puke when nothing's been set
//		if (config == null) {
//			return new HashMap();
//		}
//		return config;
//	}
	
	

}
