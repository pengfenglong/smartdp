package com.smartdp.tag.easyui;

/**
 * 分页标签
 * 
 * @author pengfenglong
 * 
 */
public class PaginationTag extends AbstractUITag {
	protected String total;// 总数
	protected String pageSize;// 每页记录数
	protected String pageNumber;// 当前页码
	protected String pageList;// 可选择的每页记录数
	protected String loading;// 是否正在载入
	protected String buttons;// 按钮
	protected String showPageList;// 显示可选择的每页记录数
	protected String showRefresh;// 显示刷新
	protected String beforePageText;// 输入框之前的文本
	protected String afterPageText;// 输入框之后的文本
	protected String displayMsg;// 显示信息
	protected String onSelectPage;// 当用户进行翻页时触发，回调函数包含2个参数: pageNumber： 下一页的页码
									// pageSize： 下一页的显示记录数
	protected String onBeforeRefresh;// 刷新之前触发, 返回false将取消刷新。
	protected String onRefresh;// 刷新之后触发。
	protected String onChangePageSize;// 当用户修改每页显示记录数时触发。

	public String getCss() {
		return "easyui-pagination";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("total", "'" + total + "'");
		parameters.put("pageSize", "'" + pageSize + "'");
		parameters.put("pageNumber", "'" + pageNumber + "'");
		parameters.put("pageList", pageList);
		parameters.put("loading", loading);
		parameters.put("buttons", buttons);
		parameters.put("showPageList", showPageList);
		parameters.put("showRefresh", showRefresh);
		parameters.put("beforePageText", "'" + beforePageText + "'");
		parameters.put("afterPageText", "'" + afterPageText + "'");
		parameters.put("displayMsg", "'" + displayMsg + "'");
		parameters.put("onSelectPage", onSelectPage);
		parameters.put("onBeforeRefresh", onBeforeRefresh);
		parameters.put("onRefresh", onRefresh);
		parameters.put("onChangePageSize", onChangePageSize);
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageList() {
		return pageList;
	}

	public void setPageList(String pageList) {
		this.pageList = pageList;
	}

	public String getLoading() {
		return loading;
	}

	public void setLoading(String loading) {
		this.loading = loading;
	}

	public String getButtons() {
		return buttons;
	}

	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	public String getShowPageList() {
		return showPageList;
	}

	public void setShowPageList(String showPageList) {
		this.showPageList = showPageList;
	}

	public String getShowRefresh() {
		return showRefresh;
	}

	public void setShowRefresh(String showRefresh) {
		this.showRefresh = showRefresh;
	}

	public String getBeforePageText() {
		return beforePageText;
	}

	public void setBeforePageText(String beforePageText) {
		this.beforePageText = beforePageText;
	}

	public String getAfterPageText() {
		return afterPageText;
	}

	public void setAfterPageText(String afterPageText) {
		this.afterPageText = afterPageText;
	}

	public String getDisplayMsg() {
		return displayMsg;
	}

	public void setDisplayMsg(String displayMsg) {
		this.displayMsg = displayMsg;
	}

	public String getOnSelectPage() {
		return onSelectPage;
	}

	public void setOnSelectPage(String onSelectPage) {
		this.onSelectPage = onSelectPage;
	}

	public String getOnBeforeRefresh() {
		return onBeforeRefresh;
	}

	public void setOnBeforeRefresh(String onBeforeRefresh) {
		this.onBeforeRefresh = onBeforeRefresh;
	}

	public String getOnRefresh() {
		return onRefresh;
	}

	public void setOnRefresh(String onRefresh) {
		this.onRefresh = onRefresh;
	}

	public String getOnChangePageSize() {
		return onChangePageSize;
	}

	public void setOnChangePageSize(String onChangePageSize) {
		this.onChangePageSize = onChangePageSize;
	}
}