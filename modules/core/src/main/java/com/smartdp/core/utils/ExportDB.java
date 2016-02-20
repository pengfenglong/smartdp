package com.smartdp.core.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Hibernate通过实体类和映射关系生成数据库
 * @author peng
 *
 */
public class ExportDB {
	
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}

}
