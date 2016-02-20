package com.smartdp.core.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smartdp.core.utils.TypeConvertUtils;

/**
 * 组装SQL或HQL
 * @author   Liqi
 * @version  1.0.0 
 * @Date	 2011	2011-9-6		下午11:32:22
 * @see java.sql.types
 * @see com.acpframework.core.dao.IHibernatePrepareSQLQueryCallback
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SQLEntity implements Serializable
{
	public static final long serialVersionUID = 20071022L;
	public static final String SQL_TYPE = "SQL";
	public static final String HQL_TYPE = "HQL";
	private static final String LIKE_SYMBOL = "%";
	private static final String WHERE_KEYWORD = " where ";
	private static final String AND_KEYWORD = " and ";
	private static final String OR_KEYWORD = " or ";
	private String type;
	private Class entity;
	/**可以为SQL或HQL*/
	private StringBuffer sql = new StringBuffer();
	/**参数列表*/
	private List params = new ArrayList();
	/**参数类型*/
	private Map<Integer, Integer> paramTypeMap = new LinkedHashMap<Integer, Integer>();
	/**SQLQuery预处理回调*/
	//private transient IHibernatePrepareSQLQueryCallback prepareSQLQueryCallback;

	public SQLEntity()
	{
	}

	public SQLEntity(String sqlp, Collection params)
	{
		this(sqlp, params, null);
	}

	public SQLEntity(String sqlp, Collection params,
			Collection<Integer> paramTypes)
	{
		if (sqlp != null)
		{
			append(sqlp);
		}

		if (params != null)
		{
			if (paramTypes != null)
			{
				addParams(params, paramTypes);
			}
			else
			{
				addParams(params);
			}
		}
	}

	public SQLEntity(String sqlp, Object[] params)
	{
		this(sqlp, params, null);
	}

	public SQLEntity(String sqlp, Object[] params, int[] paramTypes)
	{
		if (sqlp != null)
		{
			append(sqlp);
		}

		if (params != null)
		{
			if (paramTypes != null)
			{
				addParams(params, paramTypes);
			}
			else
			{
				addParams(params);
			}
		}
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * 追加SQL或HQL
	 * @param SQL或HQL
	 * @return 自己
	 */
	public SQLEntity append(String sqlp)
	{
		this.sql.append(sqlp);
		return this;
	}

	/**
	 * 追加SQL或HQL
	 * @param sqlp SQL或HQL
	 * @param param 参数
	 * @return 自己
	 */
	public SQLEntity append(String sqlp, Object param)
	{
		return append(sqlp).addParam(param);
	}

	/**
	 * 追加SQL或HQL
	 * @param sqlp SQL或HQL
	 * @param param 参数
	 * @param paramType 参数类型
	 * @return 自己
	 */
	public SQLEntity append(String sqlp, Object param, int paramType)
	{
		return append(sqlp).addParam(param, paramType);
	}

	/**
	 * 追加SQL或HQL
	 * @param sqlp SQL或HQL
	 * @param params 参数数组
	 * @return 自己
	 */
	public SQLEntity append(String sqlp, Object[] params)
	{
		return append(sqlp).addParams(params);
	}

	/**
	 * 追加SQL或HQL
	 * @param sqlp SQL或HQL
	 * @param params 参数数组
	 * @param paramTypes 参数类型数组
	 * @return 自己
	 */
	public SQLEntity append(String sqlp, Object[] params, int[] paramTypes)
	{
		return append(sqlp).addParams(params, paramTypes);
	}

	/**
	 * 判断sql是否包含where
	 * @return boolean
	 */
	public boolean hasWhere()
	{
		return this.sql.toString().toLowerCase(new Locale("zh_CN"))
				.indexOf(WHERE_KEYWORD) != -1;
	}

	/**
	 * 添加and条件
	 * @param andCondition and条件
	 * @return 自己
	 */
	public SQLEntity appendWhereAnd(String andCondition)
	{
		if (!hasWhere())
		{
			this.sql.append(WHERE_KEYWORD + andCondition);
		}
		else
		{
			this.sql.append(AND_KEYWORD + andCondition);
		}
		return this;
	}

	/**
	 * 添加or条件
	 * @param orCondition or条件
	 * @return 自己
	 */
	public SQLEntity appendWhereOr(String orCondition)
	{
		if (!hasWhere())
		{
			this.sql.append(WHERE_KEYWORD + orCondition);
		}
		else
		{
			this.sql.append(OR_KEYWORD + orCondition);
		}
		return this;
	}

	/**
	 * 判断这个参数是否存在
	 * @param param 参数
	 * @return boolean
	 */
	private boolean paramIsExist(Object param)
	{
		if (param == null)
		{
			return false;
		}
		if ((param instanceof String))
		{
			if (StringUtils.isEmpty((String) param))
			{
				return false;
			}
		}
		else if ((param instanceof Number))
		{
			if (TypeConvertUtils.toDouble(param) == 0.0D)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否重复添加参数
	 * @param sqlp SQL或HQL
	 * @param param 参数
	 * @return 自己
	 */
	public SQLEntity appendIfExist(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return append(sqlp, param);
	}

	/**
	 * 判断是否重复添加参数,并添加左%
	 * @param sqlp SQL或HQL
	 * @param param 参数
	 * @return 自己
	 */
	public SQLEntity appendIfExistLeftMatching(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return append(sqlp, param + LIKE_SYMBOL);
	}

	/**
	 * 判断是否重复添加参数,并添加右%
	 * @param sqlp SQL或HQL
	 * @param param 参数
	 * @return 自己
	 */
	public SQLEntity appendIfExistRightMatching(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return append(sqlp, LIKE_SYMBOL + param);
	}

	/**
	 * 判断是否重复添加参数,并添加左右%
	 * @param sqlp SQL或HQL
	 * @param param 参数
	 * @return 自己
	 */
	public SQLEntity appendIfExistBothMatching(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return append(sqlp, LIKE_SYMBOL + param + LIKE_SYMBOL);
	}

	/**
	 * like参数是否配置
	 * @param param 参数
	 * @return boolean
	 */
	private boolean paramHaveMatching(Object param)
	{
		if ((param instanceof String))
		{
			String str = (String) param;
			if ((str.startsWith(LIKE_SYMBOL)) && (str.length() == 1))
			{
				return true;
			}
			if ((str.endsWith(LIKE_SYMBOL)) && (str.length() == 1))
			{
				return true;
			}
			if ((str.startsWith(LIKE_SYMBOL)) && (str.endsWith(LIKE_SYMBOL))
					&& (str.length() == 2))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 追加SQL或HQL
	 * @param sqlp SQL或HQL
	 * @param param 参数
	 * @return 自己
	 */
	public SQLEntity appendIfExistIgnoreMatching(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		if (paramHaveMatching(param))
		{
			return this;
		}
		return append(sqlp, param);
	}

	/**
	 * 追加带参数的and条件
	 * @param andCondition and条件
	 * @param param 参数
	 * @return 自己
	 */
	public SQLEntity appendWhereAnd(String andCondition, Object param)
	{
		return appendWhereAnd(andCondition).addParam(param);
	}

	/**
	 * 追加带参数的and条件，如果参数已经存在则不追加
	 * @param andCondition and条件
	 * @param param 参数
	 * @return 自己
	 */
	public SQLEntity appendWhereAndIfExist(String andCondition, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return appendWhereAnd(andCondition, param);
	}

	/**
	 * 
	 * (这里用一句话描述这个方法的作用)
	 * @param sqlp
	 * @param param
	 * @return
	 */
	public SQLEntity appendWhereAndIfExistLeftMatching(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return appendWhereAnd(sqlp, param + LIKE_SYMBOL);
	}

	public SQLEntity appendWhereAndIfExistRightMatching(String sqlp,
			Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return appendWhereAnd(sqlp, LIKE_SYMBOL + param);
	}

	public SQLEntity appendWhereAndIfExistBothMatching(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return appendWhereAnd(sqlp, LIKE_SYMBOL + param + LIKE_SYMBOL);
	}

	public SQLEntity appendWhereAndIfExistIgnoreMatching(String sqlp,
			Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		if (paramHaveMatching(param))
		{
			return this;
		}
		return appendWhereAnd(sqlp, param);
	}

	public SQLEntity appendWhereOr(String sqlp, Object param)
	{
		return appendWhereOr(sqlp).addParam(param);
	}

	public SQLEntity appendWhereOrIfExist(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return appendWhereOr(sqlp, param);
	}

	public SQLEntity appendWhereOrIfExistLeftMatching(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return appendWhereOr(sqlp, param + LIKE_SYMBOL);
	}

	public SQLEntity appendWhereOrIfExistRightMatching(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return appendWhereOr(sqlp, LIKE_SYMBOL + param);
	}

	public SQLEntity appendWhereOrIfExistBothMatching(String sqlp, Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		return appendWhereOr(sqlp, LIKE_SYMBOL + param + LIKE_SYMBOL);
	}

	public SQLEntity appendWhereOrIfExistIgnoreMatching(String sqlp,
			Object param)
	{
		if (!paramIsExist(param))
		{
			return this;
		}
		if (paramHaveMatching(param))
		{
			return this;
		}
		return appendWhereOr(sqlp, param);
	}

	public SQLEntity appendWhereAnd(String sqlp, Object[] params)
	{
		return appendWhereAnd(sqlp).addParams(params);
	}

	public SQLEntity appendWhereOr(String sqlp, Object[] params)
	{
		return appendWhereOr(sqlp).addParams(params);
	}

	public SQLEntity append(String sqlp, Collection params)
	{
		return append(sqlp).addParams(params);
	}

	public SQLEntity appendWhereAnd(String sqlp, Collection params)
	{
		return appendWhereAnd(sqlp).addParams(params);
	}

	public SQLEntity appendWhereOr(String sqlp, Collection params)
	{
		return appendWhereOr(sqlp).addParams(params);
	}

	/**
	 * 添加参数
	 * @param param 参数
	 * @return 自己
	 */
	
	public SQLEntity addParam(Object param)
	{
		this.params.add(param);
		return this;
	}

	/**
	 * 添加参数
	 * @param param 参数
	 * @param paramType 参数类型
	 * @return 自己
	 */
	
	public SQLEntity addParam(Object param, int paramType)
	{
		this.params.add(param);
		this.paramTypeMap.put(Integer.valueOf(this.params.indexOf(param)),
				Integer.valueOf(paramType));

		return this;
	}

	/**
	 * 添加参数
	 * @param params 参数
	 * @return 自己
	 */
	public SQLEntity addParams(Collection params)
	{
		this.params.addAll(params);
		return this;
	}

	/**
	 * 添加参数
	 * @param params 参数
	 * @param paramTypes 参数类型
	 * @return 自己
	 */
	public SQLEntity addParams(Collection params,
			Collection<Integer> paramTypes)
	{
		if (params.size() != paramTypes.size())
		{
//			throw new Exception(
//					"Params count not equals types count.");
		}

		Iterator tIt = paramTypes.iterator();
		for (Iterator it = params.iterator(); it.hasNext();)
		{
			addParam(it.next(), ((Integer) tIt.next()).intValue());
		}

		return this;
	}

	/**
	 * 添加参数
	 * @param params 参数数组
	 * @return 自己
	 */
	public SQLEntity addParams(Object[] params)
	{
		for (int i = 0; i < params.length; i++)
		{
			this.params.add(params[i]);
		}
		return this;
	}

	/**
	 * 添加参数
	 * @param params 参数数组
	 * @param paramTypes 参数类型数组
	 * @return 自己
	 */
	public SQLEntity addParams(Object[] params, int[] paramTypes)
	{
		if (params.length != paramTypes.length)
		{
//			throw new Exception(
//					"Params count not equals types count.");
		}

		for (int i = 0; i < params.length; i++)
		{
			addParam(params[i], paramTypes[i]);
		}

		return this;
	}

	/**
	 * 添加多个参数
	 * 如(?,?,?,?)
	 * @param params 参数
	 * @return 自己
	 */
	public SQLEntity addInParams(Collection params)
	{
		return addParams(params).addLikeSymbol(params.size());
	}

	/**
	 * 添加多个参数
	 * 如(?,?,?,?)
	 * @param params 参数
	 * @param paramTypes 参数类型
	 * @return 自己
	 */
	public SQLEntity addInParams(Collection params,
			Collection<Integer> paramTypes)
	{
		return addParams(params, paramTypes).addLikeSymbol(params.size());
	}

	/**
	 * 添加多个参数
	 * 如(?,?,?,?)
	 * @param params 参数数组
	 * @return 自己
	 */
	public SQLEntity addInParams(Object[] params)
	{
		return addParams(params).addLikeSymbol(params.length);
	}

	/**
	 * 添加多个参数
	 * 如(?,?,?,?)
	 * @param params 参数数组
	 * @param paramTypes 参数类型数组
	 * @return 自己
	 */
	public SQLEntity addInParams(Object[] params, int[] paramTypes)
	{
		return addParams(params, paramTypes).addLikeSymbol(params.length);
	}

	/**
	 * 在SQL或HQL后追加"?,"
	 * 结果如?,?,?,?
	 * (这里用一句话描述这个方法的作用)
	 * @param count
	 * @return
	 */
	private SQLEntity addLikeSymbol(int count)
	{
		for (int i = 0; i < count; i++)
		{
			this.sql.append("?");
			if (i >= count - 1) continue;
			this.sql.append(", ");
		}

		return this;
	}

	public SQLEntity addParamType(int type)
	{
		return addParamType(this.paramTypeMap.size(), type);
	}

	public SQLEntity addParamType(int index, int type)
	{
		this.paramTypeMap.put(Integer.valueOf(index), Integer.valueOf(type));
		return this;
	}

	/**
	 * 解析sql
	 * @return SQL或HQL
	 */
	public String parseSql()
	{
		return this.sql.toString();
	}

	/**
	 * 解析sql
	 * @param dbSymbol
	 * @param dateFormatString 时间格式
	 * @return SQL或HQL
	 */
	public String parseSql(String dbSymbol, String dateFormatString)
	{
		StringBuffer parseSql = new StringBuffer();
		int paramCount = 0;
		for (int i = 0; i < this.sql.length(); i++)
		{
			char c = this.sql.charAt(i);
			if (c == '?')
			{
				Object param = this.params.get(paramCount++);
				if (param != null)
				{
					parseSql.append(dbSymbol);
					String paramString = null;
					if ((param instanceof Date))
					{
						if (dateFormatString != null)
						{
							paramString = TypeConvertUtils.toString(
									(Date) param, dateFormatString);
						}
						else
						{
							paramString = TypeConvertUtils
									.toString((Date) param);
						}
					}
					else if ((param instanceof Calendar))
					{
						if (dateFormatString != null)
						{
							paramString = TypeConvertUtils.toString(
									(Calendar) param, dateFormatString);
						}
						else
						{
							paramString = TypeConvertUtils
									.toString((Calendar) param);
						}
					}
					else
					{
						paramString = param.toString();
					}
					parseSql.append(paramString);
					parseSql.append(dbSymbol);
				}
				else
				{
					parseSql.append("null");
				}
			}
			else
			{
				parseSql.append(c);
			}
		}
		return parseSql.toString();
	}

	/**
	 * 解析参数
	 * @return 对象数组
	 */
	public Object[] parseParams()
	{
		if (this.params.isEmpty())
		{
			return null;
		}
		Object[] ps = new Object[this.params.size()];
		for (int i = 0; i < ps.length; i++)
		{
			ps[i] = this.params.get(i);
		}
		return ps;
	}

	/**
	 * 解析参数类型
	 * @return 数组
	 */
	public int[] parseParamsTypes()
	{
		if (this.params.isEmpty())
		{
			return null;
		}

		int[] argTypes = new int[this.params.size()];
		for (int i = 0; i < argTypes.length; i++)
		{
			Integer argType = (Integer) this.paramTypeMap.get(Integer
					.valueOf(i));
			if (argType == null)
			{
				argTypes[i] = -2147483648;
			}
			else
			{
				argTypes[i] = argType.intValue();
			}
		}
		return argTypes;
	}

	/**
	 * 参数转换为字符串
	 * @return 字符串
	 */
	public String paramsToString()
	{
		StringBuffer paramsString = new StringBuffer();
		if (this.params.isEmpty())
		{
			paramsString.append("null");
		}
		for (int i = 0; i < this.params.size(); i++)
		{
			paramsString.append("'").append(this.params.get(i)).append("'");
			if (i >= this.params.size() - 1) continue;
			paramsString.append(", ");
		}

		return paramsString.toString();
	}

	/**
	 * 清理内存
	 */
	public void clear()
	{
		clearSql();
		clearParams();
		clearParamTypes();
		//this.prepareSQLQueryCallback = null;
	}

	/**
	 * 清空SQL
	 */
	public void clearSql()
	{
		this.sql.delete(0, this.sql.length());
	}

	/**
	 * 清空参数
	 */
	public void clearParams()
	{
		this.params.clear();
	}

	/**
	 * 清空参数类型
	 */
	public void clearParamTypes()
	{
		this.paramTypeMap.clear();
	}

	/**
	 * 得到参数个数
	 * @return 参数个数
	 */
	public int paramsLength()
	{
		return this.params.size();
	}

	public List getParams()
	{
		return this.params;
	}

	public void setParams(List params)
	{
		this.params = params;
	}

	public StringBuffer getSql()
	{
		return this.sql;
	}

	public void setSql(StringBuffer sql)
	{
		this.sql = sql;
	}

	public int sqlLength()
	{
		return this.sql.length();
	}

	/**
	 * 从SQL尾部删除count长度的字符
	 * @param count 字符长度
	 * @return
	 */
	public SQLEntity sqlDeleteEnd(int count)
	{
		sqlDelete(this.sql.length() - count, this.sql.length());
		return this;
	}

	/**
	 * 从SQL头部删除count长度的字符
	 * @param count 字符长度
	 * @return 自己
	 */
	public SQLEntity sqlDeleteStart(int count)
	{
		sqlDelete(0, count);
		return this;
	}

	/**
	 * 区间删除count长度的字符
	 * @param count 字符长度
	 * @return 自己
	 */
	public SQLEntity sqlDelete(int start, int end)
	{
		this.sql.delete(start, end);
		return this;
	}

	/**
	 * SQL是否是参数的后缀
	 * @param suffix suffix
	 * @return boolean
	 */
	public boolean sqlEndsWith(String suffix)
	{
		return this.sql.toString().endsWith(suffix);
	}

	/**
	 * SQL是否是参数的前缀
	 * @param suffix suffix
	 * @return boolean
	 */
	public boolean sqlStartsWith(String suffix)
	{
		return this.sql.toString().startsWith(suffix);
	}

	/**
	 * 复制自己
	 * @return 一个新的 SQLEntity
	 */
	public SQLEntity cloneMe()
	{
		SQLEntity sqlEntity = new SQLEntity();
		sqlEntity.type = this.type;
		sqlEntity.sql = new StringBuffer(this.sql.toString());
		sqlEntity.params = new ArrayList(this.params);
		sqlEntity.paramTypeMap = new LinkedHashMap(this.paramTypeMap);
		//sqlEntity.prepareSQLQueryCallback = this.prepareSQLQueryCallback;
		return sqlEntity;
	}

	public String toString()
	{
		return parseSql("'", "yyyy-MM-dd HH:mm:ss");
	}

//	public IHibernatePrepareSQLQueryCallback getPrepareSQLQueryCallback()
//	{
//		return this.prepareSQLQueryCallback;
//	}
//
//	public void setPrepareSQLQueryCallback(
//			IHibernatePrepareSQLQueryCallback prepareSQLQueryCallback)
//	{
//		this.prepareSQLQueryCallback = prepareSQLQueryCallback;
//	}

	/**
	 * entity
	 *
	 * @return  the entity
	 * @since   CodingExample Ver 1.0
	 */
	
	public Class getEntity()
	{
		return entity;
	}

	/**
	 * entity
	 *
	 * @param   entity    the entity to set
	 * @since   CodingExample Ver 1.0
	 */
	public void setEntity(Class entity)
	{
		this.entity = entity;
	}
}