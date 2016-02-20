package com.smartdp.tag.easyui;

/**
 * 菜单标签
 * 
 * @author pengfenglong
 * 
 */
public class MenuTag extends AbstractUITag {
	protected String zIndex;// 堆叠顺序
	protected String left;// 左边距
	protected String top;// 顶边距
	protected String onShow;// 当一个菜单被显示后触发。
	protected String onHide;// 在一个菜单被隐藏后触发。
	protected String onClick;// 当一个菜单被点击时触发。

	public String getCss() {
		return "easyui-menu";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("zIndex", "'" + zIndex + "'");
		parameters.put("left", "'" + left + "'");
		parameters.put("top", "'" + top + "'");
		parameters.put("onShow", onShow);
		parameters.put("onHide", onHide);
		parameters.put("onClick", onClick);
	}

	public String getZIndex() {
		return zIndex;
	}

	public void setZIndex(String zIndex) {
		this.zIndex = zIndex;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getOnShow() {
		return onShow;
	}

	public void setOnShow(String onShow) {
		this.onShow = onShow;
	}

	public String getOnHide() {
		return onHide;
	}

	public void setOnHide(String onHide) {
		this.onHide = onHide;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
}