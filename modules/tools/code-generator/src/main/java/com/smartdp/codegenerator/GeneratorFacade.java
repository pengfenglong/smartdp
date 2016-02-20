package com.smartdp.codegenerator;


import java.io.File;
import java.io.IOException;
import java.util.List;

import com.smartdp.codegenerator.provider.db.DbTableFactory;
import com.smartdp.codegenerator.provider.db.DbTableGeneratorModelProvider;
import com.smartdp.codegenerator.provider.db.model.Table;
import com.smartdp.codegenerator.provider.java.JavaClassGeneratorModelProvider;
import com.smartdp.codegenerator.provider.java.model.JavaClass;

/**
 * 
 * @author pengfenglong
 *
 */
public class GeneratorFacade {
	
	
	public GeneratorFacade() {
	}
	
	public void printAllTableNames() throws Exception {
		List tables = DbTableFactory.getInstance().getAllTables();
		System.out.println("\n----All TableNames BEGIN----");
		for(int i = 0; i < tables.size(); i++ ) {
			String sqlName = ((Table)tables.get(i)).getSqlName();
			System.out.println("g.generateTable(\""+sqlName+"\");");
		}
		System.out.println("----All TableNames END----");
	}
	
	public void generateByAllTable() throws Exception {
		List<Table> tables = DbTableFactory.getInstance().getAllTables();
		for(int i = 0; i < tables.size(); i++ ) {
			//generateByTable(tables.get(i).getSqlName());
			generateByTable(tables.get(i));
		}
	}
	
	public void generateByTable(Table table) throws Exception {
		Generator g = createGeneratorForDbTable();
		g.generateByModelProvider(new DbTableGeneratorModelProvider(table));
	}
	
	public void generateByTable(String tableName) throws Exception {
		Generator g = createGeneratorForDbTable();
		Table table = DbTableFactory.getInstance().getTable(tableName);
		g.generateByModelProvider(new DbTableGeneratorModelProvider(table));
	}
	
	public void generateByTable(String tableName,String className) throws Exception {
		Generator g = createGeneratorForDbTable();
		Table table = DbTableFactory.getInstance().getTable(tableName);
		table.setClassName(className);
		g.generateByModelProvider(new DbTableGeneratorModelProvider(table));
	}
	
	public void generateByClass(Class clazz) throws Exception {
		Generator g = createGeneratorForJavaClass();
		g.generateByModelProvider(new JavaClassGeneratorModelProvider(new JavaClass(clazz)));
	}
	
	public void clean() throws IOException {
		Generator g = createGeneratorForDbTable();
		g.clean();
	}
	
	public Generator createGeneratorForDbTable() {
		Generator g = new Generator();
		g.setTemplateRootDir(new File("template/2.0").getAbsoluteFile());
		//g.setOutRootDir(GeneratorProperties.getRequiredProperty("outRoot"));
		g.setOutRootDir(new File("./generator-output").getAbsolutePath());
		return g;
	}
	
	private Generator createGeneratorForJavaClass() {
		Generator g = new Generator();
		g.setTemplateRootDir(new File("template/javaclass").getAbsoluteFile());
		//g.setOutRootDir(GeneratorProperties.getRequiredProperty("outRoot"));
		g.setOutRootDir(new File("./generator-output").getAbsolutePath());
		return g;
	}
}
