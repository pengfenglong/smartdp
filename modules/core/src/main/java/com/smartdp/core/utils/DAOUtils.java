/**
 * DAOUtils.java
 * com.acpframework.core.util 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2011-9-10 		Liqi
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
 */

package com.smartdp.core.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.xwork.ArrayUtils;
import org.hibernate.Query;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;

import com.smartdp.core.dao.entity.SQLEntity;


/**
 * dao工具类
 * ClassName:DAOUtils
 * 
 * @author Liqi
 * @version 1.0.0
 * @Date 2011-9-10 上午08:35:50
 */
public abstract class DAOUtils
{
	/**
	 * 设置SQL预制参数
	 * @param pstmt
	 * @param params 参数
	 * @return PreparedStatement
	 * @throws SQLException
	 */
	public static PreparedStatement setPreparedStatementParams(
			PreparedStatement pstmt, Object[] params) throws SQLException
	{
		if ((pstmt == null) || ArrayUtils.isEmpty(params))
		{
			return pstmt;
		}
		for (int i = 0; i < params.length; i++)
		{
			int paramIndex = i + 1;
			StatementCreatorUtils.setParameterValue(pstmt, paramIndex,
					SqlTypeValue.TYPE_UNKNOWN, params[i]);
		}
		return pstmt;
	}
	
	/**
	 * 设置hibernate查询参数
	 * @param query
	 * @param params
	 * @return
	 */
	public static Query setQueryParams(Query query, Object[] params)
	{
		if (null == query || ArrayUtils.isEmpty(params))
		{
			return query;
		}
		for (int i = 0; i < params.length; i++)
		{
			query.setParameter(i, params[i]);
		}
		return query;
	}
	
	/**
	 * 重新组装SQL为查询所有记录的SQL
	 * @param hsql
	 * @return 查询所有记录的SQL
	 */
	public static String prepareCountSql(String hsql)
	{
		String fromHsql = hsql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHsql = "from " + StringUtils.substringAfter(fromHsql, "from");
		fromHsql = StringUtils.substringBefore(fromHsql, "order by");

		String countHsql = "select count(*) " + fromHsql;
		return countHsql;
	}
	
	/**
	 * 重新组装SQL为查询所有记录的SQL
	 * @param sqlEntity sql实体
	 * @return 查询所有记录的SQL
	 */
	public static SQLEntity prepareCountSqlForSQLEntity(SQLEntity sqlEntity)
	{
		SQLEntity newSQLEntity = sqlEntity.cloneMe();
		String hsql = newSQLEntity.parseSql();
		newSQLEntity.clear();
		newSQLEntity.append(prepareCountSql(hsql));
		return newSQLEntity;
	}
}
