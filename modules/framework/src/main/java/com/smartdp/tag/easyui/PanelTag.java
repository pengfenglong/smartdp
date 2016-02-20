package com.smartdp.tag.easyui;

/**
 * 控制面板标签
 * 
 * @author pengfenglong
 * 
 */
public class PanelTag extends AbstractUITag {
	protected String title;// 标题
	protected String iconCls;// 图标CSS类
	protected String width;// 宽度
	protected String height;// 高度
	protected String left;// 左边距
	protected String top;// 顶边距
	protected String cls;// 类
	protected String headerCls;// 头部css类
	protected String bodyCls;// 主体类
	protected String style;// 样式
	protected String fit;// 铺满浏览器
	protected String border;// 边框
	protected String doSize;// 调整大小
	protected String noheader;// 无头部
	protected String content;// 内容
	protected String collapsible;// 可折叠
	protected String minimizable;// 最小化
	protected String maximizable;// 最大化
	protected String closable;// 关闭
	protected String tools;// 工具栏
	protected String collapsed;// 已折叠
	protected String minimized;// 已最小化
	protected String maximized;// 已最大化
	protected String closed;// 已关闭
	protected String href;// 超链接
	protected String cache;// 缓存
	protected String loadingMessage;// 载入时信息
	protected String extractor;// 提取器
	protected String onLoad;// 在远程数据被载入时触发。
	protected String onBeforeOpen;// 在控制面板被打开之前触发，返回false将停止打开。
	protected String onOpen;// 在控制面板被打开之后触发。
	protected String onBeforeClose;// 在控制面板被关闭之前触发，返回false将取消关闭。
	protected String onClose;// 在控制面板被关闭后触发。
	protected String onBeforeDestroy;// 在控制面板被注销前触发，返回false将取消注销。
	protected String onDestroy;// 在控制面板被注销后触发。
	protected String onBeforeCollapse;// 在控制面板被折叠之前触发，返回false将停止折叠。
	protected String onCollapse;// 在控制面板被折叠之后触发。
	protected String onBeforeExpand;// 在控制面板被扩展之前触发，返回false将停止扩展（这里应该是指扩展区域，宽、高等）。
	protected String onExpand;// 在控制面板被扩展之后触发。
	protected String onResize;// 在控制面板被缩放后触发。 width： 新的控制面板宽度 height：新的控制面板高度
	protected String onMove;// 在控制面板被移动后触发。 left：新的控制面板左边距 top：新的控制面板顶边距
	protected String onMaximize;// 在控制面板被最大化后触发
	protected String onRestore;// 在控制面板被重置为初始大小后触发。
	protected String onMinimize;// 在控制面板被最小化后触发。

	public String getCss() {
		return "easyui-panel";
	}

	public void populateParams() {
		super.populateParams();
		parameters.put("title", "'" + title + "'");
		parameters.put("iconCls", "'" + iconCls + "'");
		parameters.put("width", "'" + width + "'");
		parameters.put("height", "'" + height + "'");
		parameters.put("left", "'" + left + "'");
		parameters.put("top", "'" + top + "'");
		parameters.put("cls", "'" + cls + "'");
		parameters.put("headerCls", "'" + headerCls + "'");
		parameters.put("bodyCls", "'" + bodyCls + "'");
		parameters.put("style", style);
		parameters.put("fit", fit);
		parameters.put("border", border);
		parameters.put("doSize", doSize);
		parameters.put("noheader", noheader);
		parameters.put("content", "'" + content + "'");
		parameters.put("collapsible", collapsible);
		parameters.put("minimizable", minimizable);
		parameters.put("maximizable", maximizable);
		parameters.put("closable", closable);
		parameters.put("tools", tools);
		parameters.put("collapsed", collapsed);
		parameters.put("minimized", minimized);
		parameters.put("maximized", maximized);
		parameters.put("closed", closed);
		parameters.put("href", "'" + href + "'");
		parameters.put("cache", cache);
		parameters.put("loadingMessage", "'" + loadingMessage + "'");
		parameters.put("extractor", "'" + extractor + "'");
		parameters.put("onLoad", onLoad);
		parameters.put("onBeforeOpen", onBeforeOpen);
		parameters.put("onOpen", onOpen);
		parameters.put("onBeforeClose", onBeforeClose);
		parameters.put("onClose", onClose);
		parameters.put("onBeforeDestroy", onBeforeDestroy);
		parameters.put("onDestroy", onDestroy);
		parameters.put("onBeforeCollapse", onBeforeCollapse);
		parameters.put("onCollapse", onCollapse);
		parameters.put("onBeforeExpand", onBeforeExpand);
		parameters.put("onExpand", onExpand);
		parameters.put("onResize", onResize);
		parameters.put("onMove", onMove);
		parameters.put("onMaximize", onMaximize);
		parameters.put("onRestore", onRestore);
		parameters.put("onMinimize", onMinimize);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
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

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getHeaderCls() {
		return headerCls;
	}

	public void setHeaderCls(String headerCls) {
		this.headerCls = headerCls;
	}

	public String getBodyCls() {
		return bodyCls;
	}

	public void setBodyCls(String bodyCls) {
		this.bodyCls = bodyCls;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getFit() {
		return fit;
	}

	public void setFit(String fit) {
		this.fit = fit;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getDoSize() {
		return doSize;
	}

	public void setDoSize(String doSize) {
		this.doSize = doSize;
	}

	public String getNoheader() {
		return noheader;
	}

	public void setNoheader(String noheader) {
		this.noheader = noheader;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCollapsible() {
		return collapsible;
	}

	public void setCollapsible(String collapsible) {
		this.collapsible = collapsible;
	}

	public String getMinimizable() {
		return minimizable;
	}

	public void setMinimizable(String minimizable) {
		this.minimizable = minimizable;
	}

	public String getMaximizable() {
		return maximizable;
	}

	public void setMaximizable(String maximizable) {
		this.maximizable = maximizable;
	}

	public String getClosable() {
		return closable;
	}

	public void setClosable(String closable) {
		this.closable = closable;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getCollapsed() {
		return collapsed;
	}

	public void setCollapsed(String collapsed) {
		this.collapsed = collapsed;
	}

	public String getMinimized() {
		return minimized;
	}

	public void setMinimized(String minimized) {
		this.minimized = minimized;
	}

	public String getMaximized() {
		return maximized;
	}

	public void setMaximized(String maximized) {
		this.maximized = maximized;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCache() {
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public String getLoadingMessage() {
		return loadingMessage;
	}

	public void setLoadingMessage(String loadingMessage) {
		this.loadingMessage = loadingMessage;
	}

	public String getExtractor() {
		return extractor;
	}

	public void setExtractor(String extractor) {
		this.extractor = extractor;
	}

	public String getOnLoad() {
		return onLoad;
	}

	public void setOnLoad(String onLoad) {
		this.onLoad = onLoad;
	}

	public String getOnBeforeOpen() {
		return onBeforeOpen;
	}

	public void setOnBeforeOpen(String onBeforeOpen) {
		this.onBeforeOpen = onBeforeOpen;
	}

	public String getOnOpen() {
		return onOpen;
	}

	public void setOnOpen(String onOpen) {
		this.onOpen = onOpen;
	}

	public String getOnBeforeClose() {
		return onBeforeClose;
	}

	public void setOnBeforeClose(String onBeforeClose) {
		this.onBeforeClose = onBeforeClose;
	}

	public String getOnClose() {
		return onClose;
	}

	public void setOnClose(String onClose) {
		this.onClose = onClose;
	}

	public String getOnBeforeDestroy() {
		return onBeforeDestroy;
	}

	public void setOnBeforeDestroy(String onBeforeDestroy) {
		this.onBeforeDestroy = onBeforeDestroy;
	}

	public String getOnDestroy() {
		return onDestroy;
	}

	public void setOnDestroy(String onDestroy) {
		this.onDestroy = onDestroy;
	}

	public String getOnBeforeCollapse() {
		return onBeforeCollapse;
	}

	public void setOnBeforeCollapse(String onBeforeCollapse) {
		this.onBeforeCollapse = onBeforeCollapse;
	}

	public String getOnCollapse() {
		return onCollapse;
	}

	public void setOnCollapse(String onCollapse) {
		this.onCollapse = onCollapse;
	}

	public String getOnBeforeExpand() {
		return onBeforeExpand;
	}

	public void setOnBeforeExpand(String onBeforeExpand) {
		this.onBeforeExpand = onBeforeExpand;
	}

	public String getOnExpand() {
		return onExpand;
	}

	public void setOnExpand(String onExpand) {
		this.onExpand = onExpand;
	}

	public String getOnResize() {
		return onResize;
	}

	public void setOnResize(String onResize) {
		this.onResize = onResize;
	}

	public String getOnMove() {
		return onMove;
	}

	public void setOnMove(String onMove) {
		this.onMove = onMove;
	}

	public String getOnMaximize() {
		return onMaximize;
	}

	public void setOnMaximize(String onMaximize) {
		this.onMaximize = onMaximize;
	}

	public String getOnRestore() {
		return onRestore;
	}

	public void setOnRestore(String onRestore) {
		this.onRestore = onRestore;
	}

	public String getOnMinimize() {
		return onMinimize;
	}

	public void setOnMinimize(String onMinimize) {
		this.onMinimize = onMinimize;
	}
}