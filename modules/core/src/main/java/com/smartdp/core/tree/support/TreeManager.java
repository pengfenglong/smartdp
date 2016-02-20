package com.smartdp.core.tree.support;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.smartdp.core.dao.DbConnectionManager;


public class TreeManager {
	
    private static TreeManager treeManager = new TreeManager();   
    
    private TreeManager() {   
    }   
    
    public static TreeManager getInstance() {    
  
        return treeManager;   
    }

	//sql ="select mod_id as id,mod_name as name,mod_parentid as parentid,menu_url as url,1 as type,0 as state,0 as flag from masa_func order by mod_parentid"

	/**
	 * 将sql语句得到的递归值转换成xml
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public String getXml(String sql,String parentid) throws SQLException {

		Connection conn = DbConnectionManager.getConnection(new Properties());
		Statement stmt = conn.createStatement(1004, 1007);
		ResultSet rs = stmt.executeQuery(sql);

		int iRecordCount = 0;
		StringBuffer sxml = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sxml.append("  <record>\n");
		while (rs.next()) {
			iRecordCount++;
		}

		if(parentid == null){
			if (rs.first()) {
				parentid = rs.getString("parentid");
			}
			if(parentid == null){
				parentid = "-1";
			}
		}else{
			
		}
		buildXml(rs, parentid, sxml, iRecordCount);
		DbConnectionManager.closeConnection(rs, stmt, conn);
		return sxml.toString();

	}

	/**
	 * 将sql语句得到的递归值转换成json 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public String getJson(String sql,String parentid) throws SQLException {
		Connection conn = DbConnectionManager.getConnection(new Properties());
		Statement stmt = conn.createStatement(1004, 1007);
		ResultSet rs = stmt.executeQuery(sql);

		int iRecordCount = 0;
		StringBuffer sjson = new StringBuffer();
		while (rs.next()) {
			iRecordCount++;
		}

		if(parentid == null){
			if (rs.first()) {
				parentid = rs.getString("parentid");
			}
			if(parentid == null){
				parentid = "-1";
			}
		}else{
			
		}

		buildJson(rs, parentid, sjson, iRecordCount);
		DbConnectionManager.closeConnection(rs, stmt, conn);
		
		String s = sjson.toString();
		
		//将有多个children的用,分开
		if(s.indexOf("}{")!=-1)
		{
			s = s.replaceAll("\\}\\{", "},{");
		}
		//将没有children的节点的children属性替换为'leaf':true
		s = s.replaceAll("\\,\\'children\\'\\:\\[\\]", ",'leaf':true");
		return s;

	}

	/**
	 * 将结果集数转换成xml
	 * 
	 * @param rs
	 * @param parentid
	 * @param sxml
	 * @param iRecordCount
	 * @return
	 */
	public String buildXml(ResultSet rs, String parentid,
			StringBuffer sxml, int iRecordCount) throws SQLException {
		for (int i = 1; i <= iRecordCount; i++) {
			rs.absolute(i);
			if (rs.getString("parentid").equals(parentid)) {
				int iCount = rs.getMetaData().getColumnCount();
				sxml.append("  <record");
				for (int j = 1; j <= iCount; j++) {
					String columnName = rs.getMetaData().getColumnName(j);
					sxml.append(" ").append(columnName.toLowerCase());
					sxml.append("=\"").append(rs.getString(columnName));
					sxml.append("\" ");
				}
				sxml.append(">").append("\n");
				ResultSet the_rs = rs;
				buildXml(the_rs, rs.getString("id"), sxml, iRecordCount);				
			}
		}
		sxml.append("  </record>").append("\n");

		return sxml.toString();

	}

	/**
	 * 将结果集数转换成json格式的
	 * @param rs
	 * @param parentid
	 * @param sjson
	 * @param iRecordCount
	 * @return
	 */
	public String buildJson(ResultSet rs, String parentid,
			StringBuffer sjson, int iRecordCount) throws SQLException {

		sjson.append("[");
		for (int i = 1; i <= iRecordCount; i++) {
			rs.absolute(i);
			String pId = rs.getString("parentid");
			if(pId == null){
				pId = "-1";
			}
			if (pId.equals(parentid)) {
				int iCount = rs.getMetaData().getColumnCount();
				sjson.append("{");
				for (int j = 1; j <= iCount; j++) {
					String columnName = rs.getMetaData().getColumnName(j);
					sjson.append("'"+columnName.toLowerCase()+"'");
					sjson.append(":").append("'"+rs.getString(columnName)+"'");
					sjson.append(",");
				}
				sjson.append("'children':");
				ResultSet the_rs = rs;
				buildJson(the_rs, rs.getString("id"), sjson, iRecordCount);
				sjson.append("}");	
			}			
		}
		sjson.append("]");
		return sjson.toString();

	}

}
