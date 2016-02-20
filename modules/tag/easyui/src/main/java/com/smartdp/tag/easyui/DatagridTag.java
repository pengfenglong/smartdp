package com.smartdp.tag.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

/**
 * 数据表格标签
 * 
 * @author pengfenglong
 * 
 */
public class DatagridTag extends PanelTag {
	protected String columns;// 列
	protected String frozenColumns;// 固定列
	protected String fitColumns;// 自适应列宽
	protected String striped;// 显示条纹
	protected String method;// 方法
	protected String nowrap;// 截取
	protected String idField;// id字段
	protected String url;// 超链接
	protected String loadMsg;// 载入时信息
	protected String pagination;// 分页
	protected String rownumbers;// 行数
	protected String singleSelect;// 单选模式
	protected String pageNumber;// 当前页码
	protected String pageSize;// 每页记录数
	protected String pageList;// 可选择的每页记录数
	protected String queryParams;// 查询参数
	protected String sortName;// 默认排序
	protected String sortOrder;// 排序顺序
	protected String remoteSort;// 远程排序
	protected String showFooter;// 显示行底
	protected String rowStyler;// 行样式
	protected String loadFilter;// 载入过滤器
	protected String editors;// 编辑模式
	protected String view;// 视图
	protected String onLoadSuccess;// 当数据载入成功时触发。
	protected String onLoadError;// 当载入远程数据发生错误时触发。
	protected String onBeforeLoad;// 在请求载入数据之前触发，如果返回false将取消载入。
	protected String onClickRow;// 当用户点击行时触发，参数如下： rowIndex：被点击的行索引，从0开始。
	// rowData：对应于被点击的行的记录。
	protected String onDblClickRow;// 当用户双击一行时触发，参数如下： rowIndex：被点击的行索引，从0开始。
	// rowData：对应于被点击的行的记录。
	protected String onClickCell;// 当用户点击单元格时触发。
	protected String onDblClickCell;// 当用户双击单元格时触发。
	protected String onSortColumn;// 当用户对列排序时触发，参数如下： sort：排序字段名称。 order：排序顺序。
	protected String onResizeColumn;// 当用户调整列宽时触发。
	protected String onSelect;// 当用户选择一行是触发，参数如下： rowIndex：被选择的行索引，从0开始。
	// rowData：对应于被选择行的记录。
	protected String onUnselect;// 当用户取消选择一行时触发，参数如下： rowIndex：被取消选择的行索引，从0开始。
	// rowData：对应于被取消选择行的记录。
	protected String onSelectAll;// 当用户选择所有行时触发。
	protected String onUnselectAll;// 当用户取消选择所有行时触发。
	protected String onBeforeEdit;// 当用户开始编辑一行时触发，参数如下： rowIndex：正在编辑的行索引，从0开始。
	// rowData：对应于正在编辑的行的记录。
	protected String onAfterEdit;// 当用户编辑完成时触发，参数如下： rowIndex：正在编辑的行索引，从0开始。
	// rowData：对应于正在编辑的行的记录。
	// changes：被改变的字段内容，对应方式为字段：值。
	protected String onCancelEdit;// 当用户取消编辑行时触发，参数如下： rowIndex：正在编辑的行索引，从0开始。
	// rowData：对应于正在编辑的行的记录。
	protected String onHeaderContextMenu;// 当数据表格的列标题被鼠标右键单击时触发。
	protected String onRowContextMenu;// 当一行被鼠标右键单击时触发。
	protected String checkOnSelect;
	protected String selectOnCheck;
	protected String toolbar;
	
	public void populateAttributes() {
		super.populateAttributes();
		attributes.put("idField", idField);
		attributes.put("toolbar", toolbar);
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			StringBuffer start = new StringBuffer();
			start.append("<").append(getTagType());
			start.append(resolveAttrs());
			start.append(" data-options=\"").append(resolveParams()).append(
					"\">").append("\r\n");
			start.append("<thead>").append("\r\n");
			start.append("<tr>").append("\r\n");
			out.println(start.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			StringBuffer end = new StringBuffer();
			end.append("</tr>").append("\r\n");
			end.append("</thead>").append("\r\n");
			end.append("</" + getTagType() + ">").append("\r\n");
			out.println(end.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	public String getCss() {
		return "easyui-datagrid";
	}

	public String getTagType() {
		return "table";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("columns", columns);
		parameters.put("frozenColumns", frozenColumns);
		parameters.put("fitColumns", fitColumns);
		parameters.put("striped", striped);
		parameters.put("method", "'" + method + "'");
		parameters.put("nowrap", nowrap);
		parameters.put("url", "'" + getContextPath() +"/" + url + "'");
		parameters.put("loadMsg", "'" + loadMsg + "'");
		parameters.put("pagination", pagination);
		parameters.put("rownumbers", rownumbers);
		parameters.put("singleSelect", singleSelect);
		parameters.put("pageNumber", "'" + pageNumber + "'");
		parameters.put("pageSize", "'" + pageSize + "'");
		parameters.put("pageList", pageList);
		parameters.put("queryParams", queryParams);
		parameters.put("sortName", "'" + sortName + "'");
		parameters.put("sortOrder", "'" + sortOrder + "'");
		parameters.put("remoteSort", remoteSort);
		parameters.put("showFooter", showFooter);
		parameters.put("rowStyler", "'" + rowStyler + "'");
		parameters.put("loadFilter", "'" + loadFilter + "'");
		parameters.put("editors", editors);
		parameters.put("view", view);
		parameters.put("onLoadSuccess", onLoadSuccess);
		parameters.put("onLoadError", onLoadError);
		parameters.put("onBeforeLoad", onBeforeLoad);
		parameters.put("onClickRow", onClickRow);
		parameters.put("onDblClickRow", onDblClickRow);
		parameters.put("onClickCell", onClickCell);
		parameters.put("onDblClickCell", onDblClickCell);
		parameters.put("onSortColumn", onSortColumn);
		parameters.put("onResizeColumn", onResizeColumn);
		parameters.put("onSelect", onSelect);
		parameters.put("onUnselect", onUnselect);
		parameters.put("onSelectAll", onSelectAll);
		parameters.put("onUnselectAll", onUnselectAll);
		parameters.put("onBeforeEdit", onBeforeEdit);
		parameters.put("onAfterEdit", onAfterEdit);
		parameters.put("onCancelEdit", onCancelEdit);
		parameters.put("onHeaderContextMenu", onHeaderContextMenu);
		parameters.put("onRowContextMenu", onRowContextMenu);
		parameters.put("checkOnSelect", checkOnSelect);
		parameters.put("selectOnCheck", selectOnCheck);
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getFrozenColumns() {
		return frozenColumns;
	}

	public void setFrozenColumns(String frozenColumns) {
		this.frozenColumns = frozenColumns;
	}

	public String getFitColumns() {
		return fitColumns;
	}

	public void setFitColumns(String fitColumns) {
		this.fitColumns = fitColumns;
	}

	public String getStriped() {
		return striped;
	}

	public void setStriped(String striped) {
		this.striped = striped;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getNowrap() {
		return nowrap;
	}

	public void setNowrap(String nowrap) {
		this.nowrap = nowrap;
	}

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLoadMsg() {
		return loadMsg;
	}

	public void setLoadMsg(String loadMsg) {
		this.loadMsg = loadMsg;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String getRownumbers() {
		return rownumbers;
	}

	public void setRownumbers(String rownumbers) {
		this.rownumbers = rownumbers;
	}

	public String getSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(String singleSelect) {
		this.singleSelect = singleSelect;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageList() {
		return pageList;
	}

	public void setPageList(String pageList) {
		this.pageList = pageList;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getRemoteSort() {
		return remoteSort;
	}

	public void setRemoteSort(String remoteSort) {
		this.remoteSort = remoteSort;
	}

	public String getShowFooter() {
		return showFooter;
	}

	public void setShowFooter(String showFooter) {
		this.showFooter = showFooter;
	}

	public String getRowStyler() {
		return rowStyler;
	}

	public void setRowStyler(String rowStyler) {
		this.rowStyler = rowStyler;
	}

	public String getLoadFilter() {
		return loadFilter;
	}

	public void setLoadFilter(String loadFilter) {
		this.loadFilter = loadFilter;
	}

	public String getEditors() {
		return editors;
	}

	public void setEditors(String editors) {
		this.editors = editors;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
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

	public String getOnBeforeLoad() {
		return onBeforeLoad;
	}

	public void setOnBeforeLoad(String onBeforeLoad) {
		this.onBeforeLoad = onBeforeLoad;
	}

	public String getOnClickRow() {
		return onClickRow;
	}

	public void setOnClickRow(String onClickRow) {
		this.onClickRow = onClickRow;
	}

	public String getOnDblClickRow() {
		return onDblClickRow;
	}

	public void setOnDblClickRow(String onDblClickRow) {
		this.onDblClickRow = onDblClickRow;
	}

	public String getOnClickCell() {
		return onClickCell;
	}

	public void setOnClickCell(String onClickCell) {
		this.onClickCell = onClickCell;
	}

	public String getOnDblClickCell() {
		return onDblClickCell;
	}

	public void setOnDblClickCell(String onDblClickCell) {
		this.onDblClickCell = onDblClickCell;
	}

	public String getOnSortColumn() {
		return onSortColumn;
	}

	public void setOnSortColumn(String onSortColumn) {
		this.onSortColumn = onSortColumn;
	}

	public String getOnResizeColumn() {
		return onResizeColumn;
	}

	public void setOnResizeColumn(String onResizeColumn) {
		this.onResizeColumn = onResizeColumn;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}

	public String getOnUnselect() {
		return onUnselect;
	}

	public void setOnUnselect(String onUnselect) {
		this.onUnselect = onUnselect;
	}

	public String getOnSelectAll() {
		return onSelectAll;
	}

	public void setOnSelectAll(String onSelectAll) {
		this.onSelectAll = onSelectAll;
	}

	public String getOnUnselectAll() {
		return onUnselectAll;
	}

	public void setOnUnselectAll(String onUnselectAll) {
		this.onUnselectAll = onUnselectAll;
	}

	public String getOnBeforeEdit() {
		return onBeforeEdit;
	}

	public void setOnBeforeEdit(String onBeforeEdit) {
		this.onBeforeEdit = onBeforeEdit;
	}

	public String getOnAfterEdit() {
		return onAfterEdit;
	}

	public void setOnAfterEdit(String onAfterEdit) {
		this.onAfterEdit = onAfterEdit;
	}

	public String getOnCancelEdit() {
		return onCancelEdit;
	}

	public void setOnCancelEdit(String onCancelEdit) {
		this.onCancelEdit = onCancelEdit;
	}

	public String getOnHeaderContextMenu() {
		return onHeaderContextMenu;
	}

	public void setOnHeaderContextMenu(String onHeaderContextMenu) {
		this.onHeaderContextMenu = onHeaderContextMenu;
	}

	public String getOnRowContextMenu() {
		return onRowContextMenu;
	}

	public void setOnRowContextMenu(String onRowContextMenu) {
		this.onRowContextMenu = onRowContextMenu;
	}

	public String getCheckOnSelect() {
		return checkOnSelect;
	}

	public void setCheckOnSelect(String checkOnSelect) {
		this.checkOnSelect = checkOnSelect;
	}

	public String getSelectOnCheck() {
		return selectOnCheck;
	}

	public void setSelectOnCheck(String selectOnCheck) {
		this.selectOnCheck = selectOnCheck;
	}

	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

}