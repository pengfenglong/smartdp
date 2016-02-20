/**
 * IPage.java
 * com.acpframework.core.dao 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2011-9-3 		Liqi
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
*/

package com.smartdp.core.dao;

import java.util.List;

import com.smartdp.core.dao.entity.SQLEntity;
import com.smartdp.core.dao.impl.DefaultPage;

/**
 * 写一段话描述这个类
 * ClassName:IPage
 *
 * @author   Liqi
 * @version  1.0.0 
 * @Date	 2011-9-3		下午09:34:13	 
 */
public abstract interface IPage<T>
{
	/**
	 * 获得每页的记录数量, 默认为-1.
	 */
	int getPageSize();

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	int getFirst();

	/**
	 * 获得排序字段,无默认值. 多个排序字段时用','分隔.
	 */
	String getOrderBy();


	/**
	 * 返回Page对象自身的setOrderBy函数,可用于连续设置。
	 */
	DefaultPage<T> orderBy(final String theOrderBy);

	/**
	 * 获得排序方向, 无默认值.
	 */
	String getOrder();

	/**
	 * 设置排序方式向.
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	void setOrder(final String order);

	/**
	 * 返回Page对象自身的setOrder函数,可用于连续设置。
	 */
	DefaultPage<T> order(final String theOrder);

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	boolean isOrderBySetted();

	/**
	 * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为false.
	 */
	boolean isAutoCount();

	/**
	 * 获得页内的记录列表.
	 */
	List<T> getResult();
	
	/**
	 * 设置页内的记录列表.
	 */
	void setResult(List<T> list);

	/**
	 * 获得总记录数, 默认值为-1.
	 */
	long getTotalCount();
	
	/**
	 * 设置总记录数
	 */
	void setTotalCount(long totalCount);

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	long getTotalPages();

	/**
	 * 是否还有下一页.
	 */
	boolean isHasNext();
	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	int getNextPage();

	/**
	 * 是否还有上一页.
	 */
	boolean isHasPre();

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	int getPrePage();
	
	/**
	 * 获得查询的SQL实体
	 * (这里用一句话描述这个方法的作用)
	 * @return
	 */
	SQLEntity getQuerySQLEntity();
}

