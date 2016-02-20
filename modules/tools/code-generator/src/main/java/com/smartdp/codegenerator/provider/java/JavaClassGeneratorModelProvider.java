package com.smartdp.codegenerator.provider.java;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.smartdp.codegenerator.IGeneratorModelProvider;
import com.smartdp.codegenerator.provider.java.model.JavaClass;


public class JavaClassGeneratorModelProvider implements IGeneratorModelProvider{
	JavaClass clazz;
	
	public JavaClassGeneratorModelProvider(JavaClass clazz) {
		super();
		this.clazz = clazz;
	}

	public String getDisaplyText() {
		return "JavaClass:"+clazz.getClassName();
	}

	public void mergeFilePathModel(Map model) throws Exception {
		model.putAll(BeanUtils.describe(clazz));
	}

	public void mergeTemplateModel(Map model) throws Exception {
		model.put("clazz",clazz);
	}

}
