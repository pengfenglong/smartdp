package com.smartdp.core.utils;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * 类描述：标签工具类
 * 
 * @author: jeecg
 * @date： 日期：2012-12-28 时间：上午09:58:00
 * @version 1.0
 */
public class TagUtil {
	/**
	 * <b>Summary: </b> getFiled(获得实体Bean中所有属性)
	 * 
	 * @param objClass
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Field[] getFiled(Class objClass) throws ClassNotFoundException {
		Field[] field = null;
		if (objClass != null) {
			Class class1 = Class.forName(objClass.getName());
			field = class1.getDeclaredFields();// 这里便是获得实体Bean中所有属性的方法
			return field;
		} else {
			return field;
		}
	}

	/**
	 * 
	 * 获取对象内对应字段的值
	 * @param fields
	 */
	public static Object fieldNametoValues(String FiledName, Object o){
		Object value = "";
		String fieldName = "";
		String childFieldName = null;
		ReflectHelper reflectHelper=new ReflectHelper(o);
		if (FiledName.indexOf("_") == -1) {
			fieldName = FiledName;
		} else {
			fieldName = FiledName.substring(0, FiledName.indexOf("_"));//外键字段引用名
			childFieldName = FiledName.substring(FiledName.indexOf("_") + 1);//外键字段名
		}
		value = reflectHelper.getMethodValue(fieldName)==null?"":reflectHelper.getMethodValue(fieldName);
		if (value !=""&&value != null && FiledName.indexOf("_") != -1) {
			value = fieldNametoValues(childFieldName, value);
		}
		return value;
	}

	/**
	 * 对象转数组
	 * @param fields
	 * @param o
	 * @param stack
	 * @return
	 * @throws Exception
	 */
	protected static Object[] field2Values(String[] fields, Object o) throws Exception {
		Object[] values = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].toString();
			values[i] = fieldNametoValues(fieldName, o);
		}
		return values;
	}
	/**
	 * 循环LIST对象拼接EASYUI格式的JSON数据
	 * @param fields
	 * @param total
	 * @param list
	 */
	private static String listtojson(String[] fields, int total, List list,String[] footers) throws Exception {
		Object[] values = new Object[fields.length];
		String jsonTemp = "{\'total\':" + total + ",\'rows\':[";
		for (int j = 0; j < list.size(); j++) {
			jsonTemp = jsonTemp + "{\'state\':\'closed\',";
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].toString();
				values[i] = fieldNametoValues(fieldName, list.get(j));
				jsonTemp = jsonTemp + "\'" + fieldName + "\'" + ":\'" + values[i] + "\'";
				if (i != fields.length - 1) {
					jsonTemp = jsonTemp + ",";
				}
			}
			if (j != list.size() - 1) {
				jsonTemp = jsonTemp + "},";
			} else {
				jsonTemp = jsonTemp + "}";
			}
		}
		jsonTemp = jsonTemp + "]";
		//update-begin--Author:zhaojunfu  Date:20130520 for：TASK #109 datagrid标签没有封装合计功能
		if(footers!=null){
			jsonTemp = jsonTemp + ",";
			jsonTemp = jsonTemp + "\'footer\':[";
			jsonTemp = jsonTemp + "{";
			jsonTemp = jsonTemp + "\'name\':\'合计\',";
			for(String footer:footers){
				String footerFiled = footer.split(":")[0];
				Object value = null;
				if(footer.split(":").length==2){
					value = footer.split(":")[1];
				}else{
					value = getTotalValue(footerFiled,list);
				}
				jsonTemp = jsonTemp +"\'"+footerFiled+"\':\'"+value+"\',";
			}
			if(jsonTemp.endsWith(",")){
				jsonTemp = jsonTemp.substring(0,jsonTemp.length()-1);
			}
			jsonTemp = jsonTemp + "}";
			jsonTemp = jsonTemp + "]";
		}
		//update-end--Author:zhaojunfu  Date:20130520 for：TASK #109 datagrid标签没有封装合计功能
		jsonTemp = jsonTemp + "}";
		return jsonTemp;
	}
	/**
	 * 计算指定列的合计
	 * @param filed 字段名
	 * @param list 列表数据
	 * @return
	 */
	private static Object getTotalValue(String fieldName,List list){
		Double sum = 0D;
		try{
			for (int j = 0; j < list.size(); j++) {
				Double v = 0d;
				String vstr =String.valueOf(fieldNametoValues(fieldName, list.get(j)));
				if(!StringUtils.isEmpty(vstr)){
					v = Double.valueOf(vstr);
				}else{
					
				}
				sum+=v;
			}
		}catch (Exception e) {
			return "";
		}
		return sum;
	}
	/**
	 * 循环LIST对象拼接DATATABLE格式的JSON数据
	 * @param fields
	 * @param total
	 * @param list
	 */
	private static String datatable(String field, int total, List list) throws Exception {
		String[] fields=field.split(",");
		Object[] values = new Object[fields.length];
		String jsonTemp = "{\'iTotalDisplayRecords\':" + total + ",\'iTotalRecords\':" + total + ",\'aaData\':[";
		for (int j = 0; j < list.size(); j++) {
			jsonTemp = jsonTemp + "{";
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].toString();
				values[i] = fieldNametoValues(fieldName, list.get(j));
				jsonTemp = jsonTemp + "\'" + fieldName + "\'" + ":\'" + values[i] + "\'";
				if (i != fields.length - 1) {
					jsonTemp = jsonTemp + ",";
				}
			}
			if (j != list.size() - 1) {
				jsonTemp = jsonTemp + "},";
			} else {
				jsonTemp = jsonTemp + "}";
			}
		}
		jsonTemp = jsonTemp + "]}";
		return jsonTemp;
	}
	


	/**
	 * 获取指定字段类型 <b>Summary: </b> getColumnType(请用一句话描述这个方法的作用)
	 * 
	 * @param fileName
	 * @param fields
	 * @return
	 */
	public static String getColumnType(String fileName, Field[] fields) {
		String type = "";
		if (fields.length > 0) {
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName(); // 获取属性的名字
				String filedType = fields[i].getGenericType().toString(); // 获取属性的类型
				if (fileName.equals(name)) {
					if (filedType.equals("class java.lang.Integer")) {
						filedType = "int";
						type = filedType;
					}else if (filedType.equals("class java.lang.Short")) {
						filedType = "short";
						type = filedType;
					}else if (filedType.equals("class java.lang.Double")) {
						filedType = "double";
						type = filedType;
					}else if (filedType.equals("class java.util.Date")) {
						filedType = "date";
						type = filedType;
					}else if (filedType.equals("class java.lang.String")) {
						filedType = "string";
						type = filedType;
					}else if (filedType.equals("class java.sql.Timestamp")) {
						filedType = "Timestamp";
						type = filedType;
					}else if (filedType.equals("class java.lang.Character")) {
						filedType = "character";
						type = filedType;
					}else if (filedType.equals("class java.lang.Boolean")) {
						filedType = "boolean";
						type = filedType;
					}else if (filedType.equals("class java.lang.Long")) {
						filedType = "long";
						type = filedType;
					}

				}
			}
		}
		return type;
	}

	/**
	 * 
	 * <b>Summary: </b> getSortColumnIndex(获取指定字段索引)
	 * 
	 * @param fileName
	 * @param fieldString
	 * @return
	 */
	protected static String getSortColumnIndex(String fileName, String[] fieldString) {
		String index = "";
		if (fieldString.length > 0) {
			for (int i = 0; i < fieldString.length; i++) {
				if (fileName.equals(fieldString[i])) {
					int j = i + 1;
					index = ConvertUtils.getString(j);
				}
			}
		}
		return index;

	}



	/**
	 * 获取自定义函数名
	 * 
	 * @param functionname
	 * @return
	 */
	public static String getFunction(String functionname) {
		int index = functionname.indexOf("(");
		if (index == -1) {
			return functionname;
		} else {
			return functionname.substring(0, functionname.indexOf("("));
		}
	}

}
