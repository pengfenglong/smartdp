package com.smartdp.codegenerator.provider.db;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.smartdp.codegenerator.IGeneratorModelProvider;
import com.smartdp.codegenerator.provider.db.model.Table;

/**
 * 
 * @author pengfenglong
 *
 */
public class DbTableGeneratorModelProvider implements IGeneratorModelProvider {
	Table table;
	
	public DbTableGeneratorModelProvider(Table table) {
		super();
		this.table = table;
	}

	public String getDisaplyText() {
		return table.toString();
	}

	public void mergeFilePathModel(Map model) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		model.putAll(BeanUtils.describe(table));
	}

	public void mergeTemplateModel(Map model) {
		model.put("table",table);
	}

}
