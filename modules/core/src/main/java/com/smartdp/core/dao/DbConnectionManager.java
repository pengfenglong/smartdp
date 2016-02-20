package com.smartdp.core.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * jdbc管理类
 * @author peng
 *
 */
public class DbConnectionManager {
	
	private static Logger Log = Logger.getLogger(DbConnectionManager.class);

	public static Connection getConnection(Properties prop){
		String driverName = prop.getProperty("driverName");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		try {
			Class.forName(driverName);
			return DriverManager.getConnection(url,
					username, password);
		} catch (ClassNotFoundException e) {
			Log.warn("database driver can not be found", e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			Log.error(e);
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			Log.error(e);
		}
	}

	public static void closeConnection(ResultSet rs, Statement stmt,
			Connection conn) {
		closeResultSet(rs);
		closeStatement(stmt);
		closeConnection(conn);
	}

	public static void closeConnection(Statement stmt, Connection conn) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			Log.error(e);
		}
		closeConnection(conn);
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			Log.error(e);
		}
	}
	
	public static void transactionRollback(Connection conn){
		if(conn != null){
			try {
				conn.rollback();
			} catch (SQLException e) {
				Log.error(e);
			}
		}
	}

}
