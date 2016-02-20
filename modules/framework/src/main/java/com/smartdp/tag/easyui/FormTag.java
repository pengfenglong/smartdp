package com.smartdp.tag.easyui;

/**
 * 表单标签
 * 
 * @author pengfenglong
 * 
 */
public class FormTag extends AbstractUITag {
	protected String url;// 目标网址
	protected String onSubmit;// 在表单提交之前触发，返回false将阻止表单提交。
	protected String success;// 当表单成功提交时触发。
	protected String onBeforeLoad;// 在发送载入远程数据请求之前触发，返回false将取消请求。
	protected String onLoadSuccess;// 在表单数据被载入时触发。
	protected String onLoadError;// 在载入表单数据发生错误时触发。

	public String getCss() {
		return "easyui-form";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("url", "'" + url + "'");
		parameters.put("onSubmit", onSubmit);
		parameters.put("success", success);
		parameters.put("onBeforeLoad", onBeforeLoad);
		parameters.put("onLoadSuccess", onLoadSuccess);
		parameters.put("onLoadError", onLoadError);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOnSubmit() {
		return onSubmit;
	}

	public void setOnSubmit(String onSubmit) {
		this.onSubmit = onSubmit;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getOnBeforeLoad() {
		return onBeforeLoad;
	}

	public void setOnBeforeLoad(String onBeforeLoad) {
		this.onBeforeLoad = onBeforeLoad;
	}

	public String getOnLoadSuccess() {
		return onLoadSuccess;
	}

	public void setOnLoadSuccess(String onLoadSuccess) {
		this.onLoadSuccess = onLoadSuccess;
	}

	public String getOnLoadError() {
		return onLoadError;
	}

	public void setOnLoadError(String onLoadError) {
		this.onLoadError = onLoadError;
	}
}