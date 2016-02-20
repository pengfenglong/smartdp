/**
 * JdbcUtils.java
 * com.acpframework.core.util 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2011-12-9 		Liqi
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
 */

package com.smartdp.core.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.sql.DataSource;
import javax.sql.XAConnection;

import org.apache.commons.lang.StringUtils;

import com.smartdp.core.exception.ServiceAccessException;

/**
 * jdbc工具类
 * ClassName:JdbcUtils
 * 
 * @author Liqi
 * @version 1.0.0
 * @Date 2011-12-9 下午8:22:20
 */
public abstract class JdbcUtils
{
	private static final String[] DRIVERS = {
        "h2:", "org.h2.Driver",
        "Cache:", "com.intersys.jdbc.CacheDriver",
        "daffodilDB://", "in.co.daffodil.db.rmi.RmiDaffodilDBDriver",
        "daffodil", "in.co.daffodil.db.jdbc.DaffodilDBDriver",
        "db2:", "COM.ibm.db2.jdbc.net.DB2Driver",
        "derby:net:", "org.apache.derby.jdbc.ClientDriver",
        "derby://", "org.apache.derby.jdbc.ClientDriver",
        "derby:", "org.apache.derby.jdbc.EmbeddedDriver",
        "FrontBase:", "com.frontbase.jdbc.FBJDriver",
        "firebirdsql:", "org.firebirdsql.jdbc.FBDriver",
        "hsqldb:", "org.hsqldb.jdbcDriver",
        "informix-sqli:", "com.informix.jdbc.IfxDriver",
        "jtds:", "net.sourceforge.jtds.jdbc.Driver",
        "microsoft:", "com.microsoft.jdbc.sqlserver.SQLServerDriver",
        "mimer:", "com.mimer.jdbc.Driver",
        "mysql:", "com.mysql.jdbc.Driver",
        "odbc:", "sun.jdbc.odbc.JdbcOdbcDriver",
        "oracle:", "oracle.jdbc.driver.OracleDriver",
        "pervasive:", "com.pervasive.jdbc.v2.Driver",
        "pointbase:micro:", "com.pointbase.me.jdbc.jdbcDriver",
        "pointbase:", "com.pointbase.jdbc.jdbcUniversalDriver",
        "postgresql:", "org.postgresql.Driver",
        "sybase:", "com.sybase.jdbc3.jdbc.SybDriver",
        "sqlserver:", "com.microsoft.sqlserver.jdbc.SQLServerDriver",
        "teradata:", "com.ncr.teradata.TeraDriver",
    };
	
	/**
	 * 关闭连接
	 * Close a statement without throwing an exception.
	 * 
	 * @param stat
	 *            the statement or null
	 */
	public static void closeSilently(Statement stat)
	{
		if (stat != null)
		{
			try
			{
				stat.close();
			}
			catch (SQLException e)
			{
				// ignore
			}
		}
	}

	/**
	 * 关闭连接
	 * Close a connection without throwing an exception.
	 * 
	 * @param conn
	 *            the connection or null
	 */
	public static void closeSilently(Connection conn)
	{
		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				// ignore
			}
		}
	}

	/**
	 * 关闭连接
	 * Close a result set without throwing an exception.
	 * 
	 * @param rs
	 *            the result set or null
	 */
	public static void closeSilently(ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (SQLException e)
			{
				// ignore
			}
		}
	}

	/**
	 * 关闭连接
	 * Close an XA connection set without throwing an exception.
	 * 
	 * @param conn
	 *            the XA connection or null
	 */
	// ## Java 1.4 begin ##
	public static void closeSilently(XAConnection conn)
	{
		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				// ignore
			}
		}
	}

	// ## Java 1.4 end ##

	/**
	 * 打开一个连接
	 * Open a new database connection with the given settings.
	 * 
	 * @param driver
	 *            the driver class name
	 * @param url
	 *            the database URL
	 * @param user
	 *            the user name
	 * @param password
	 *            the password
	 * @return the database connection
	 */
	public static Connection getConnection(String driver, String url, String user, String password) throws SQLException
	{
		Properties prop = new Properties();
		if (user != null)
		{
			prop.setProperty("user", user);
		}
		if (password != null)
		{
			prop.setProperty("password", password);
		}
		return getConnection(driver, url, prop);
	}

	/**
	 * Escape table or schema patterns used for DatabaseMetaData functions.
	 * 
	 * @param pattern
	 *            the pattern
	 * @return the escaped pattern
	 */
	public static String escapeMetaDataPattern(String pattern)
	{
		if (pattern == null || pattern.length() == 0)
		{
			return pattern;
		}
		return StringUtils.replace(pattern, "\\", "\\\\");
	}

	/**
	 * 打开一个连接
	 * Open a new database connection with the given settings.
	 * 
	 * @param driver
	 *            the driver class name
	 * @param url
	 *            the database URL
	 * @param prop
	 *            the properties containing at least the user name and password
	 * @return the database connection
	 */
	public static Connection getConnection(String driver, String url, Properties prop) throws SQLException
	{
		if (StringUtils.isEmpty(driver))
		{
			JdbcUtils.load(url);
		}
		else
		{
			Class<?> d = loadUserClass(driver);
			if (java.sql.Driver.class.isAssignableFrom(d))
			{
				return DriverManager.getConnection(url, prop);
				// ## Java 1.4 begin ##
			}
			else if (javax.naming.Context.class.isAssignableFrom(d))
			{
				// JNDI context
				try
				{
					Context context = (Context) d.newInstance();
					DataSource ds = (DataSource) context.lookup(url);
					String user = prop.getProperty("user");
					String password = prop.getProperty("password");
					if (StringUtils.isEmpty(user) && StringUtils.isEmpty(password))
					{
						return ds.getConnection();
					}
					return ds.getConnection(user, password);
				}
				catch (Exception e)
				{
					throw new ServiceAccessException("获得jdbc连接失败。", e);
				}
				// ## Java 1.4 end ##
			}
			else
			{
				// Don't know, but maybe it loaded a JDBC Driver
				return DriverManager.getConnection(url, prop);
			}
		}
		return DriverManager.getConnection(url, prop);
	}

	/**
	 * 通过database URL得到一个驱动
	 * Get the driver class name for the given URL, or null if the URL is
	 * unknown.
	 * 
	 * @param url
	 *            the database URL
	 * @return the driver class name
	 */
	public static String getDriver(String url)
	{
		if (url.startsWith("jdbc:"))
		{
			url = url.substring("jdbc:".length());
			for (int i = 0; i < DRIVERS.length; i += 2)
			{
				String prefix = DRIVERS[i];
				if (url.startsWith(prefix))
				{
					return DRIVERS[i + 1];
				}
			}
		}
		return null;
	}

	/**
	 * 加载数据库驱动
	 * Load the driver class for the given URL, if the database URL is known.
	 * 
	 * @param url
	 *            the database URL
	 */
	public static void load(String url)
	{
		String driver = getDriver(url);
		if (driver != null)
		{
			loadUserClass(url);
		}
	}
	
	/**
	 * 通过类名加载到容器中并得到class
	 * @param className 类名
	 * @return Class
	 */
	public static Class< ? > loadUserClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
            } catch (Exception e2) {
                throw new ServiceAccessException("加载jdbc驱动失败，className=" + className, e);
            }
        } catch (NoClassDefFoundError e) {
        	throw new ServiceAccessException("加载jdbc驱动失败，className=" + className, e);
        } catch (Error e) {
        	throw new ServiceAccessException("加载jdbc驱动失败，className=" + className, e);
        }
    }
}
