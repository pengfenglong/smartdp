/**
 * TypeConvertUtils.java
 * com.acpframework.core.util 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2011-9-3 		Liqi
 *
 * Copyright (c) 2011, TNT All Rights Reserved.
*/

package com.smartdp.core.utils;

import java.awt.Color;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import com.smartdp.core.exception.ServiceAccessException;

/**
 * 写一段话描述这个类
 * ClassName:TypeConvertUtils
 *
 * @author   Liqi
 * @version  1.0.0 
 * @Date	 2011-9-3		下午09:47:36	 
 */
public class TypeConvertUtils
{
	private static final Logger log = Logger.getLogger(ConvertUtils.class);
	  public static final String DATE_FMT = "yyyy-MM-dd";
	  public static final String TIME_FMT = "HH:mm:ss";
	  public static final String DT_FMT = "yyyy-MM-dd HH:mm:ss";
	  public static final String DEFAULT_FMT = "yyyy-MM-dd";
	  private static Hashtable<Locale, String[]> cachedLocaleData = new Hashtable(3);

	  public static byte toByte(Object object)
	  {
	    Assert.notNull(object);

	    if ((object instanceof Byte))
	    {
	      return ((Byte)object).byteValue();
	    }

	    return Byte.parseByte(object.toString());
	  }

	  public static Integer[] toInteger(Object[] objects)
	  {
	    if (objects == null)
	    {
	      return null;
	    }

	    Integer[] intObjects = new Integer[objects.length];
	    for (int i = 0; i < intObjects.length; i++)
	    {
	      intObjects[i] = toInteger(objects[i]);
	    }
	    return intObjects;
	  }

	  public static Integer toInteger(Object object)
	  {
	    if (object == null)
	    {
	      return null;
	    }
	    return Integer.valueOf(toInt(object));
	  }

	  public static int[] toInt(Object[] objects)
	  {
	    if (objects == null)
	    {
	      return null;
	    }

	    int[] intObjects = new int[objects.length];
	    for (int i = 0; i < intObjects.length; i++)
	    {
	      intObjects[i] = toInt(objects[i]);
	    }
	    return intObjects;
	  }

	  public static int toInt(Object object)
	  {
	    Assert.notNull(object);

	    if ((object instanceof Integer))
	    {
	      return ((Integer)object).intValue();
	    }
	    if ((object instanceof Short))
	    {
	      return ((Short)object).intValue();
	    }
	    if ((object instanceof Double))
	    {
	      return ((Double)object).intValue();
	    }
	    if ((object instanceof Long))
	    {
	      return ((Long)object).intValue();
	    }
	    if ((object instanceof Float))
	    {
	      return ((Float)object).intValue();
	    }
	    if ((object instanceof Number))
	    {
	      return ((Number)object).intValue();
	    }

	    return Integer.parseInt(object.toString());
	  }

	  public static int toInt(Object object, int defaultValue)
	  {
	    try
	    {
	      return toInt(object);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static short toShort(Object object)
	  {
	    Assert.notNull(object);

	    if ((object instanceof Short))
	    {
	      return ((Short)object).shortValue();
	    }
	    if ((object instanceof Integer))
	    {
	      return ((Integer)object).shortValue();
	    }
	    if ((object instanceof Double))
	    {
	      return ((Double)object).shortValue();
	    }
	    if ((object instanceof Long))
	    {
	      return ((Long)object).shortValue();
	    }
	    if ((object instanceof Float))
	    {
	      return ((Float)object).shortValue();
	    }
	    if ((object instanceof Number))
	    {
	      return ((Number)object).shortValue();
	    }

	    return Short.parseShort(object.toString());
	  }

	  public static short toShort(Object object, short defaultValue)
	  {
	    try
	    {
	      return toShort(object);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static float toFloat(Object object)
	  {
	    Assert.notNull(object);

	    if ((object instanceof Float))
	    {
	      return ((Float)object).floatValue();
	    }
	    if ((object instanceof Short))
	    {
	      return ((Short)object).floatValue();
	    }
	    if ((object instanceof Integer))
	    {
	      return ((Integer)object).floatValue();
	    }
	    if ((object instanceof Long))
	    {
	      return ((Long)object).floatValue();
	    }
	    if ((object instanceof Double))
	    {
	      return ((Double)object).floatValue();
	    }
	    if ((object instanceof Number))
	    {
	      return ((Number)object).floatValue();
	    }

	    return Float.parseFloat(object.toString());
	  }

	  public static float toFloat(Object object, float defaultValue)
	  {
	    try
	    {
	      return toFloat(object);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static double toDouble(Object object)
	  {
	    Assert.notNull(object);

	    if ((object instanceof Double))
	    {
	      return ((Double)object).doubleValue();
	    }
	    if ((object instanceof Short))
	    {
	      return ((Short)object).doubleValue();
	    }
	    if ((object instanceof Integer))
	    {
	      return ((Integer)object).doubleValue();
	    }
	    if ((object instanceof Long))
	    {
	      return ((Long)object).doubleValue();
	    }
	    if ((object instanceof Float))
	    {
	      return ((Float)object).doubleValue();
	    }
	    if ((object instanceof Number))
	    {
	      return ((Number)object).doubleValue();
	    }

	    return Double.parseDouble(object.toString());
	  }

	  public static double toDouble(Object object, double defaultValue)
	  {
	    try
	    {
	      return toDouble(object);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static double[] toDouble(Object[] doubleArray)
	  {
	    if (doubleArray == null)
	    {
	      return null;
	    }

	    double[] returnDoubleArray = new double[doubleArray.length];
	    for (int i = 0; i < returnDoubleArray.length; i++)
	    {
	      returnDoubleArray[i] = toDouble(doubleArray[i]);
	    }
	    return returnDoubleArray;
	  }

	  public static double[] toDouble(Object[] doubleArray, double[] defaultValue)
	  {
	    try
	    {
	      return toDouble(doubleArray);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static Double toDoubleObject(Object object)
	  {
	    return Double.valueOf(toDouble(object));
	  }

	  public static Double toDoubleObject(Object object, Double defaultValue)
	  {
	    try
	    {
	      return toDoubleObject(object);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static Double[] toDoubleObject(Object[] doubleArray)
	  {
	    if (doubleArray == null)
	    {
	      return null;
	    }

	    Double[] returnDoubleArray = new Double[doubleArray.length];
	    for (int i = 0; i < returnDoubleArray.length; i++)
	    {
	      returnDoubleArray[i] = toDoubleObject(doubleArray[i]);
	    }
	    return returnDoubleArray;
	  }

	  public static Double[] toDoubleObject(Object[] doubleArray, Double[] defaultValues)
	  {
	    try
	    {
	      return toDoubleObject(doubleArray);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValues;
	  }

	  public static java.util.Date toDate(Object object)
	  {
	    if (object == null)
	    {
	      return null;
	    }
	    if ((object instanceof java.sql.Date))
	    {
	      java.util.Date date = new java.util.Date();
	      date.setTime(((java.sql.Date)object).getTime());
	      return date;
	    }
	    if ((object instanceof Timestamp))
	    {
	      java.util.Date date = new java.util.Date();
	      date.setTime(((Timestamp)object).getTime());
	      return date;
	    }
	    if ((object instanceof java.util.Date))
	    {
	      java.util.Date date = new java.util.Date();
	      date.setTime(((java.util.Date)object).getTime());
	      return date;
	    }
	    if ((object instanceof Calendar))
	    {
	      return ((Calendar)object).getTime();
	    }
	    if ((object instanceof Number))
	    {
	      return new java.util.Date(toLong(object));
	    }

	    try
	    {
	      return getDateFormat(object.toString()).parse(object.toString());
	    }
	    catch (ParseException pe)
	    {
	      log.error("threw in toDate(" + object + ")", pe);
	    }return null;
	  }

	  public static java.sql.Date toSqlDate(Object object)
	  {
	    if (object == null)
	    {
	      return null;
	    }
	    if ((object instanceof java.sql.Date))
	    {
	      return (java.sql.Date)object;
	    }
	    if ((object instanceof Timestamp))
	    {
	      return new java.sql.Date(((Timestamp)object).getTime());
	    }
	    if ((object instanceof java.util.Date))
	    {
	      return new java.sql.Date(((java.util.Date)object).getTime());
	    }
	    if ((object instanceof Calendar))
	    {
	      return new java.sql.Date(((Calendar)object).getTimeInMillis());
	    }
	    if ((object instanceof Number))
	    {
	      return new java.sql.Date(toLong(object));
	    }

	    try
	    {
	      return new java.sql.Date(getDateFormat(object.toString()).parse(object.toString()).getTime());
	    }
	    catch (ParseException pe)
	    {
	      log.error("threw in toDate(" + object + ")", pe);
	    }return null;
	  }

	  public static Timestamp toTimestamp(Calendar cal)
	  {
	    if (cal == null)
	    {
	      return null;
	    }

	    return new Timestamp(cal.getTime().getTime());
	  }

	  public static Timestamp toTimestamp(String dateStr)
	  {
	    if (dateStr == null)
	    {
	      return null;
	    }

	    return new Timestamp(toDate(dateStr).getTime());
	  }

	  public static Timestamp toTimestamp(java.util.Date date)
	  {
	    if (date == null)
	    {
	      return null;
	    }

	    return new Timestamp(date.getTime());
	  }

	  public static DateFormat getDefineDateFormat(String dateStr)
	  {
	    if ("yyyy-MM-dd".equals(dateStr))
	    {
	      return new SimpleDateFormat("yyyy-MM-dd");
	    }
	    if ("HH:mm:ss".equals(dateStr))
	    {
	      return new SimpleDateFormat("HH:mm:ss");
	    }
	    if ("yyyy-MM-dd HH:mm:ss".equals(dateStr))
	    {
	      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    }

	    return null;
	  }

	  public static DateFormat getDateFormat(String dateStr)
	  {
	    int pos1 = dateStr.indexOf("-");
	    int pos2 = dateStr.indexOf(":");
	    DateFormat dt = null;
	    if ((pos1 != -1) && (pos2 != -1))
	    {
	      dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    }
	    else if ((pos1 != -1) && (pos2 == -1))
	    {
	      dt = new SimpleDateFormat("yyyy-MM-dd");
	    }
	    else if ((pos1 == -1) && (pos2 != -1))
	    {
	      dt = new SimpleDateFormat("HH:mm:ss");
	    }
	    else if (dateStr.length() == "yyyyMM".length())
	    {
	      dt = new SimpleDateFormat("yyyyMM");
	    }
	    else if (dateStr.length() == "yyyyMMdd".length())
	    {
	      dt = new SimpleDateFormat("yyyyMMdd");
	    }
	    else
	    {
	      dt = new SimpleDateFormat("yyyy-MM-dd");
	    }
	    return dt;
	  }

	  public static String getDateFormatStr(String dateStr)
	  {
	    int pos1 = dateStr.indexOf("-");
	    int pos2 = dateStr.indexOf(":");
	    String dt = null;
	    if ((pos1 != -1) && (pos2 != -1))
	    {
	      dt = "yyyy-MM-dd HH:mm:ss";
	    }
	    else if ((pos1 != -1) && (pos2 == -1))
	    {
	      dt = "yyyy-MM-dd";
	    }
	    else if ((pos1 == -1) && (pos2 != -1))
	    {
	      dt = "HH:mm:ss";
	    }
	    else if (dateStr.length() == "yyyyMM".length())
	    {
	      dt = "yyyyMM";
	    }
	    else
	    {
	      dt = "yyyyMMdd";
	    }

	    return dt;
	  }

	  public static Calendar toCalendar(Object obj)
	  {
	    if (obj == null)
	    {
	      return null;
	    }
	    if ((obj instanceof Calendar))
	    {
	      Calendar cal = Calendar.getInstance();
	      cal.setTimeInMillis(((Calendar)obj).getTimeInMillis());
	      return cal;
	    }
	    if ((obj instanceof java.util.Date))
	    {
	      Calendar cal = Calendar.getInstance();
	      cal.setTimeInMillis(((java.util.Date)obj).getTime());
	      return cal;
	    }
	    if ((obj instanceof Number))
	    {
	      Calendar cal = Calendar.getInstance();
	      cal.setTimeInMillis(toLong(obj));
	      return cal;
	    }

	    try
	    {
	      Calendar cal = Calendar.getInstance();
	      cal.setTime(getDateFormat(obj.toString()).parse(obj.toString()));
	      return cal;
	    }
	    catch (ParseException pe)
	    {
	      log.error(null, pe);
	    }return null;
	  }

	  public static Calendar toCalendar(Object obj, String dateFormatStr)
	  {
	    if (obj == null)
	    {
	      return null;
	    }
	    if ((obj instanceof Calendar))
	    {
	      Calendar cal = Calendar.getInstance();
	      cal.setTimeInMillis(((Calendar)obj).getTimeInMillis());
	      return cal;
	    }
	    if ((obj instanceof java.util.Date))
	    {
	      Calendar cal = Calendar.getInstance();
	      cal.setTimeInMillis(((java.util.Date)obj).getTime());
	      return cal;
	    }
	    if ((obj instanceof Number))
	    {
	      Calendar cal = Calendar.getInstance();
	      cal.setTimeInMillis(toLong(obj));
	      return cal;
	    }

	    try
	    {
	      Calendar cal = Calendar.getInstance();
	      SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
	      cal.setTime(sdf.parse(obj.toString()));
	      return cal;
	    }
	    catch (ParseException pe)
	    {
	      log.error(null, pe);
	    }return null;
	  }

	  public static Boolean toBoolean(Object object)
	  {
	    if (object == null)
	    {
	      Boolean obj = null;
	      return obj;
	    }
	    return Boolean.valueOf(toBool(object));
	  }

	  public static boolean toBool(Object object)
	  {
	    Assert.notNull(object);

	    if ((object instanceof Boolean))
	    {
	      return ((Boolean)object).booleanValue();
	    }
	    return Boolean.parseBoolean(object.toString());
	  }

	  public static boolean toBool(Object object, boolean defaultValue)
	  {
	    try
	    {
	      return toBool(object);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static boolean toBool(int object)
	  {
	    return object == 1;
	  }

	  public static boolean toCustomizeBool(Object object)
	  {
	    return toCustomizeBool(object, false);
	  }

	  public static boolean toCustomizeBool(Object object, boolean defaultValue)
	  {
	    if (object == null)
	    {
	      return defaultValue;
	    }

	    String s = object.toString().trim();
	    if (s.length() == 0)
	    {
	      return defaultValue;
	    }

	    char c = s.charAt(0);
	    if ((c == 'n') || (c == 'N') || (c == 'f') || (c == 'F'))
	    {
	      return false;
	    }
	    if ((c == 'y') || (c == 'Y') || (c == 't') || (c == 'T'))
	    {
	      return true;
	    }

	    try
	    {
	      return Integer.valueOf(s).intValue() != 0;
	    }
	    catch (Exception ex) {
	    }
	    return defaultValue;
	  }

	  public static long toLong(Object object)
	  {
	    Assert.notNull(object);

	    if ((object instanceof Long))
	    {
	      return ((Long)object).longValue();
	    }
	    if ((object instanceof Double))
	    {
	      return ((Double)object).longValue();
	    }
	    if ((object instanceof Short))
	    {
	      return ((Short)object).longValue();
	    }
	    if ((object instanceof Integer))
	    {
	      return ((Integer)object).longValue();
	    }
	    if ((object instanceof Float))
	    {
	      return ((Float)object).longValue();
	    }
	    if ((object instanceof Number))
	    {
	      return ((Number)object).longValue();
	    }

	    return Long.parseLong(object.toString());
	  }

	  public static long toLong(Object object, long defaultValue)
	  {
	    try
	    {
	      return toLong(object);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static String toString(java.util.Date date)
	  {
	    return toString(date, "yyyy-MM-dd");
	  }

	  public static String toString(java.util.Date date, String dateFormatString)
	  {
	    Assert.notNull(dateFormatString, "Date format string is required");

	    return toString(date, dateFormatString, "");
	  }

	  public static String toString(java.util.Date date, String dateFormatString, String defaultValue)
	  {
	    if (date == null)
	    {
	      return defaultValue;
	    }

	    try
	    {
	      return new SimpleDateFormat(dateFormatString).format(date);
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static String toString(Calendar cal)
	  {
	    return toString(cal, "yyyy-MM-dd");
	  }

	  public static String toString(Calendar cal, String dateFormatString)
	  {
	    Assert.notNull(dateFormatString, "Date format string is required");

	    return toString(cal, dateFormatString, "");
	  }

	  public static String toString(Calendar cal, String dateFormatString, String defaultValue)
	  {
	    if (cal == null)
	    {
	      return defaultValue;
	    }

	    try
	    {
	      return new SimpleDateFormat(dateFormatString, Locale.US).format(cal.getTime());
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static String toString(int i)
	  {
	    return Integer.toString(i);
	  }

	  public static String toString(int i, String formatString)
	  {
	    Assert.notNull(formatString, "Format string is required");
	    return new DecimalFormat(formatString).format(i);
	  }

	  public static String toString(short s)
	  {
	    return Short.toString(s);
	  }

	  public static String toString(long l)
	  {
	    return Long.toString(l);
	  }

	  public static String toString(long l, String formatString)
	  {
	    Assert.notNull(formatString, "Format string is required");
	    return new DecimalFormat(formatString).format(l);
	  }

	  public static String toString(float f)
	  {
	    return Float.toString(f);
	  }

	  public static String toString(float f, String formatString)
	  {
	    Assert.notNull(formatString, "Format string is required");
	    return new DecimalFormat(formatString).format(f);
	  }

	  public static String toString(double d)
	  {
	    return Double.toString(d);
	  }

	  public static String toString(double d, String formatString)
	  {
	    Assert.notNull(formatString, "Format string is required");
	    return new DecimalFormat(formatString).format(d);
	  }

	  public static String toString(boolean b)
	  {
	    return Boolean.toString(b);
	  }

	  public static String toString(Object object)
	  {
	    return toString(object, null);
	  }

	  public static String toString(Object object, String formatString)
	  {
	    if (object == null)
	    {
	      return "";
	    }
	    if ((object instanceof String))
	    {
	      return (String)object;
	    }
	    if ((object instanceof Integer))
	    {
	      if (formatString == null)
	      {
	        return ((Integer)object).toString();
	      }

	      return toString(toInt(object), formatString);
	    }

	    if ((object instanceof Short))
	    {
	      if (formatString == null)
	      {
	        return ((Short)object).toString();
	      }

	      return toString(toShort(object), formatString);
	    }

	    if ((object instanceof Double))
	    {
	      if (formatString == null)
	      {
	        return ((Double)object).toString();
	      }

	      return toString(toDouble(object), formatString);
	    }

	    if ((object instanceof Long))
	    {
	      if (formatString == null)
	      {
	        return ((Long)object).toString();
	      }

	      return toString(toLong(object), formatString);
	    }

	    if ((object instanceof Float))
	    {
	      if (formatString == null)
	      {
	        return ((Float)object).toString();
	      }

	      return toString(toFloat(object), formatString);
	    }

	    if ((object instanceof Number))
	    {
	      if (formatString == null)
	      {
	        return ((Number)object).toString();
	      }

	      return toString(toDouble(object), formatString);
	    }

	    if ((object instanceof Calendar))
	    {
	      if (formatString == null)
	      {
	        return toString((Calendar)object);
	      }

	      return toString((Calendar)object, formatString);
	    }

	    if ((object instanceof java.util.Date))
	    {
	      if (formatString == null)
	      {
	        return toString((java.util.Date)object);
	      }

	      return toString((java.util.Date)object, formatString);
	    }

	    if ((object instanceof Collection))
	    {
	      return "[" + toString((Collection)object, ", ") + "]";
	    }
	    if ((object instanceof Map))
	    {
	      return "[" + toString((Map)object, "=", ", ") + "]";
	    }
	    if (object.getClass().isArray())
	    {
	      return "[" + toString((Object[])object, ", ") + "]";
	    }

	    return String.valueOf(object);
	  }

	  public static String toString(Collection collection, String token)
	  {
	    if ((collection == null) || (collection.isEmpty()))
	    {
	      return null;
	    }
	    Assert.notNull(token, "token is required; it must not be null");

	    StringBuffer sb = new StringBuffer();
	    for (Iterator it = collection.iterator(); it.hasNext(); )
	    {
	      Object object = it.next();
	      sb.append(object == null ? null : object.toString()).append(token);
	    }

	    if (sb.length() - token.length() >= 0)
	    {
	      sb.delete(sb.length() - token.length(), sb.length());
	    }

	    return sb.toString();
	  }

	  public static String toString(Map map, String keyValueToken, String token)
	  {
	    if ((map == null) || (map.isEmpty()))
	    {
	      return null;
	    }

	    Assert.notNull(token, "token is required; it must not be null");
	    Assert.notNull(keyValueToken, "keyValueToken is required; it must not be null");
	    StringBuffer sb = new StringBuffer();
	    Iterator it = map.entrySet().iterator();
	    while (it.hasNext())
	    {
	      Map.Entry entry = (Map.Entry)it.next();
	      String key = entry.getKey() == null ? null : entry.getKey().toString();
	      String value = entry.getValue() == null ? null : entry.getValue().toString();
	      sb.append(key).append(keyValueToken).append(value).append(token);
	    }

	    if (sb.length() - token.length() >= 0)
	    {
	      sb.delete(sb.length() - token.length(), sb.length());
	    }

	    return sb.toString();
	  }

	  public static String toString(Object[] array, String split)
	  {
	    if (array == null)
	    {
	      return null;
	    }

	    Assert.notNull(split, "split is required; it must not be null");
	    StringBuffer sb = new StringBuffer();
	    Object[] arrayOfObject = array; int j = array.length; for (int i = 0; i < j; i++) { Object element = arrayOfObject[i];

	      sb.append(element == null ? null : element.toString())
	        .append(split);
	    }

	    if (sb.length() - split.length() >= 0)
	    {
	      sb.delete(sb.length() - split.length(), sb.length());
	    }

	    return sb.toString();
	  }

	  public static Object toJavaObject(int sqlType, Object value)
	  {
	    switch (sqlType)
	    {
	    case 4:
	      return toInteger(value);
	    case 5:
	      return Integer.valueOf(toInt(value));
	    case -5:
	      return Integer.valueOf(toInt(value));
	    case -6:
	      return Integer.valueOf(toInt(value));
	    case 8:
	      return Double.valueOf(toDouble(value));
	    case 3:
	      return Integer.valueOf(toInt(value));
	    case 2:
	      return Integer.valueOf(toInt(value));
	    case 6:
	      return Float.valueOf(toFloat(value));
	    case 91:
	      return toDate(value);
	    case 92:
	      return toCalendar(value);
	    case 93:
	      return toCalendar(value);
	    case 1:
	      return value == null ? null : value.toString();
	    case 12:
	      return value == null ? null : value.toString();
	    }
	    log.warn("sqlType [" + sqlType + "] not find java type mapping, setting to String...");
	    return value == null ? null : value.toString();
	  }

	  public static Properties toProperties(String props, String splitRegex, String regex)
	  {
	    Properties properties = new Properties();

	    if (props == null)
	    {
	      return properties;
	    }
	    String[] nameAndValues = StringUtils.split(props, splitRegex);
	    for (String nameAndValue : nameAndValues)
	    {
	      int index = nameAndValue.indexOf(regex);
	      if (index == -1)
	      {
	        continue;
	      }

	      String value = nameAndValue.substring(index + regex.length(), nameAndValue.length());
//	      value = PropertyProcessCipherSupport.getInstance().process("", value);
	      properties.put(nameAndValue.substring(0, index), value);
	    }
	    return properties;
	  }

	  public static Properties toProperties(Resource[] resources)
	  {
	    if (ArrayUtils.isEmpty(resources))
	    {
	      return null;
	    }

	    PropertiesFactoryBean pfb = new PropertiesFactoryBean();
	    pfb.setLocations(resources);
	    try
	    {
	      pfb.afterPropertiesSet();
	      return (Properties)pfb.getObject();
	    }
	    catch (IOException e) {
	    	throw new ServiceAccessException(e);
	    }
	    
	  }

	  public static Map toMap(Properties properties)
	  {
	    Map map = new LinkedHashMap();
	    if (properties != null)
	    {
	      for (Map.Entry entry : properties.entrySet())
	      {
	        map.put(entry.getKey(), entry.getValue());
	      }
	    }
	    return map;
	  }


	  public static String toXmlData(Object object)
	  {
	    StringWriter writer = new StringWriter();
	    return writer.toString();
	  }

	  public static Object toObject(Class clazz, String xmlData)
	  {
	    StringReader reader = new StringReader(xmlData);
	    return reader;
	  }

	  public static Color toAWTColor(String htmlColor)
	  {
	    String c = htmlColor.startsWith("#") ? StringUtils.substringAfter(htmlColor, "#") : htmlColor;
	    return new Color(Integer.parseInt(c, 16));
	  }

	  public static Color toAWTColor(String htmlColor, Color defaultColor)
	  {
	    try
	    {
	      return toAWTColor(htmlColor);
	    }
	    catch (Exception e) {
	    }
	    return defaultColor;
	  }

	  public static String getI18nDataString(Calendar cal, int dateStyle, int timeStyle, Locale loc)
	  {
	    String[] dateTimePatterns = (String[])cachedLocaleData.get(loc);
	    if (dateTimePatterns == null)
	    {
	      ResourceBundle r = ResourceBundle.getBundle("com.huawei.bass.query.core.i18n.LocaleElements", 
	        loc);
	      dateTimePatterns = r.getStringArray("DateTimePatterns");

	      cachedLocaleData.put(loc, dateTimePatterns);
	    }
	    String pattern = null;
	    if ((timeStyle >= 0) && (dateStyle >= 0))
	    {
	      Object[] dateTimeArgs = { dateTimePatterns[timeStyle], 
	        dateTimePatterns[(dateStyle + 4)] };
	      pattern = MessageFormat.format(dateTimePatterns[8], dateTimeArgs);
	    }
	    else if (timeStyle >= 0)
	    {
	      pattern = dateTimePatterns[timeStyle];
	    }
	    else if (dateStyle >= 0)
	    {
	      pattern = dateTimePatterns[(dateStyle + 4)];
	    }
	    else
	    {
	      throw new IllegalArgumentException(
	        "No date or time style specified");
	    }

	    String defaultValue = "";
	    if (cal == null)
	    {
	      return defaultValue;
	    }

	    try
	    {
	      return new SimpleDateFormat(pattern, loc).format(cal.getTime());
	    }
	    catch (Throwable e) {
	    }
	    return defaultValue;
	  }

	  public static String getI18nDataFormat(int dateStyle, int timeStyle, Locale loc)
	  {
	    String[] dateTimePatterns = (String[])cachedLocaleData.get(loc);
	    if (dateTimePatterns == null)
	    {
	      ResourceBundle r = ResourceBundle.getBundle("com.huawei.bass.query.core.i18n.LocaleElements", 
	        loc);
	      dateTimePatterns = r.getStringArray("DateTimePatterns");

	      cachedLocaleData.put(loc, dateTimePatterns);
	    }
	    String pattern = null;
	    if ((timeStyle >= 0) && (dateStyle >= 0))
	    {
	      Object[] dateTimeArgs = { dateTimePatterns[timeStyle], 
	        dateTimePatterns[(dateStyle + 4)] };
	      pattern = MessageFormat.format(dateTimePatterns[8], dateTimeArgs);
	    }
	    else if (timeStyle >= 0)
	    {
	      pattern = dateTimePatterns[timeStyle];
	    }
	    else if (dateStyle >= 0)
	    {
	      pattern = dateTimePatterns[(dateStyle + 4)];
	    }
	    else
	    {
	      throw new IllegalArgumentException(
	        "No date or time style specified");
	    }

	    return pattern;
	  }

	  public static String tranDateFormat(String date, String beforeFormat, String dateFormat)
	  {
	    Calendar startCal = toCalendar(date, beforeFormat);
	    String formatDate = toString(startCal, dateFormat);
	    return formatDate;
	  }
}

