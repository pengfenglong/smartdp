package com.smartdp.dbexportdoc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.smartdp.dbexportdoc.entity.DBColumn;
import com.smartdp.dbexportdoc.entity.DBTable;

public class DbTableFactory {

	/**
	 * 这里是Oracle连接方法 private static final String driver =
	 * "oracle.jdbc.driver.OracleDriver"; private static final String url =
	 * "jdbc:oracle:thin:@localhost:1521:orcl"; private static final String uid
	 * = "system"; private static final String pwd = "sys"; 这里是SQL Server连接方法
	 * private static final String url =
	 * "jdbc:sqlserver://localhost:1433;DateBaseName=数据库名"; private static final
	 * String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver" private
	 * static final String uid = "sa"; private static final String pwd = "sa";
	 * 
	 * 
	 * 这里是MySQL连接方法
	 */
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String pwd = "coship";
	private static final String user = "root";
	private static final String url = "jdbc:mysql://localhost/tvmall"
			+ "?user=" + user + "&password=" + pwd
			+ "&useUnicode=true&characterEncoding=UTF-8";
	private static Connection getConnection = null;
	private static DbTableFactory instance = null;

	public synchronized static DbTableFactory getInstance() {
		if (instance == null)
			instance = new DbTableFactory();
		return instance;
	}

	public List<DBTable> getAllTables() {
		List<DBTable> tables = new ArrayList<DBTable>();
		getConnection = getConnections();
		try {
			DatabaseMetaData dbmd = getConnection.getMetaData();
			ResultSet resultSet = dbmd.getTables(null, "%", "%",
					new String[] { "TABLE" });
			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				String tableRemarks = resultSet.getString("REMARKS");
				String tableCat = resultSet.getString("TABLE_CAT");
				if(StringUtils.isEmpty(tableRemarks)){
					Statement st = getConnection.createStatement();
					ResultSet rs1 = st.executeQuery("SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES  WHERE table_schema = '"+tableCat+"' and table_name = '"+tableName+"' ");
					while(rs1.next()){
						tableRemarks = rs1.getString("TABLE_COMMENT");
					}
				}
				// ResultSet rs =getConnection.getMetaData().getColumns(null,
				// getXMLConfig.getSchema(),tableName.toUpperCase(),
				// "%");//其他数据库不需要这个方法的，直接传null，这个是oracle和db2这么用
				ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
				DBTable table = new DBTable();
				table.setName(tableName);
				table.setRemarks(tableRemarks);
				List<DBColumn> columns = new ArrayList<DBColumn>();
				while (rs.next()) {
					DBColumn column = new DBColumn();
					column.setName(rs.getString("COLUMN_NAME"));
					column.setRemarks(rs.getString("REMARKS"));
					column.setType(rs.getString("TYPE_NAME"));
					column.setDefaultValue(rs.getString("COLUMN_DEF"));
					column.setNullable("0".equals(rs.getString("NULLABLE"))?false:true);
					column.setPk("ID".equals(rs.getString("COLUMN_NAME"))?true:false);
					column.setSize(rs.getInt("COLUMN_SIZE"));
					columns.add(column);
				}
				table.setColumns(columns);
				tables.add(table);
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tables;
	}

	public static Connection getConnections() {
		try {
			// Properties props =new Properties();
			// props.put("remarksReporting","true");
			Class.forName(driver);
			getConnection = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getConnection;
	}

	// //其他数据库不需要这个方法 oracle和db2需要
	public static String getSchema() throws Exception {
		String schema;
		schema = getConnection.getMetaData().getUserName();
		if ((schema == null) || (schema.length() == 0)) {
			throw new Exception("ORACLE数据库模式不允许为空");
		}
		return schema.toUpperCase().toString();

	}

}
