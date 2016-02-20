package com.smartdp.core.web.interceptor;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.dao.DataAccessException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.smartdp.core.pojo.ResultJson;

/**
 * 异常拦截器
 * 
 * @author pengfenglong
 * 
 */
public class ExceptionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

//	private Logger logger = LoggerFactory.getLogger(getClass());
	private final Logger logger = Logger
			.getLogger(ExceptionInterceptor.class);

	public String intercept(ActionInvocation actionInvocation) throws Exception {

		String result = "";
		String errorMsg = "";
		Throwable e = null;
		try {
			result = actionInvocation.invoke();
		} catch (DataAccessException ex) {
			errorMsg = "数据库操作失败";
			e = ex;
		} catch (NullPointerException ex) {
			errorMsg = "空指针，调用了未经初始化或者是不存在的对象";
			e = ex;
		} catch (IOException ex) {
			errorMsg = "IO读写异常";
			e = ex;
		} catch (ClassNotFoundException ex) {
			errorMsg = "指定的类不存在";
			e = ex;
		} catch (ArithmeticException ex) {
			errorMsg = "数学运算异常";
			e = ex;
		} catch (ArrayIndexOutOfBoundsException ex) {
			errorMsg = "数组下标越界";
			e = ex;
		} catch (IllegalArgumentException ex) {
			errorMsg = "调用方法的参数错误";
			e = ex;
		} catch (ClassCastException ex) {
			errorMsg = "类型强制转换错误";
			e = ex;
		} catch (SecurityException ex) {
			errorMsg = "违背安全原则异常";
			e = ex;
		} catch (SQLException ex) {
			errorMsg = "操作数据库异常";
			e = ex;
		} catch (NoSuchMethodError ex) {
			errorMsg = "调用了未定义的方法";
			e = ex;
		} catch (InternalError ex) {
			errorMsg = "Java虚拟机发生了内部错误";
			e = ex;
		} catch (Exception ex) {
			errorMsg = "程序内部错误，操作失败";
			e = ex;
		}
		if (StringUtils.isNotEmpty(errorMsg)) {
			ResultJson resultJson = new ResultJson();
			resultJson.setSuccess(false);
			resultJson.setMsg("系统异常，请稍后再试或与管理员联系！");
			//
			resultJson.setObj(e);
			logger.error(errorMsg, e);
			HttpServletResponse response = ServletActionContext.getResponse();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonGenerator jsonGenerator;
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
					response.getWriter());
			jsonGenerator.writeObject(resultJson);
			result = "error";
		}

		return result;

	}

}