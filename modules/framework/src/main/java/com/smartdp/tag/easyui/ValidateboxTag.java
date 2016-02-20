package com.smartdp.tag.easyui;

/**
 * 表单验证标签
 * 
 * @author pengfenglong
 * 
 */
public class ValidateboxTag extends AbstractUITag {
	protected String required;// 必填
	protected String validType;// 验证类型
	protected String missingMessage;// 未填提示
	protected String invalidMessage;// 无效提示
	protected String destroy;// 移除并注销组件。
	protected String validate;// 验证表单域的内容是否有效。
	protected String isValid;// 调用validate方法并且返回验证结果，true或者false。

	public String getCss() {
		return "easyui-validatebox";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("required", required);
		parameters.put("validType", "'" + validType + "'");
		parameters.put("missingMessage", "'" + missingMessage + "'");
		parameters.put("invalidMessage", "'" + invalidMessage + "'");
		parameters.put("destroy", destroy);
		parameters.put("validate", validate);
		parameters.put("isValid", isValid);
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getValidType() {
		return validType;
	}

	public void setValidType(String validType) {
		this.validType = validType;
	}

	public String getMissingMessage() {
		return missingMessage;
	}

	public void setMissingMessage(String missingMessage) {
		this.missingMessage = missingMessage;
	}

	public String getInvalidMessage() {
		return invalidMessage;
	}

	public void setInvalidMessage(String invalidMessage) {
		this.invalidMessage = invalidMessage;
	}

	public String getDestroy() {
		return destroy;
	}

	public void setDestroy(String destroy) {
		this.destroy = destroy;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
}