/**
 * DbContextHolder.java
 * com.acpframework.core.util 
 *
 *   version     date      		author
 * ──────────────────────────────────
 *   1.0.0		 2012-2-24 		Liqi
 *
 * Copyright (c) 2012, TNT All Rights Reserved.
*/

package com.smartdp.core.utils;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;

import com.smartdp.core.web.loadpoint.PropertiesBean;

/**
 * 写一段话描述这个类
 * ClassName:DbContextHolder
 *
 * @author   Liqi
 * @version  1.0.0 
 * @Date	 2012-2-24		上午12:17:02	 
 */
public class DbContextHolder
{
    /**
     * 返回数据源
     * @param url URL
     * @param driverName 驱动名
     * @param user 用户名
     * @param password 密码
     * @return 数据源
     */
    public static BasicDataSource getBasicDataSource(String url,String driverName,String  user,String  password)
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverName);
        return dataSource;
    }
    
    /**
     * 获得当前上下文中的数据源
     * @return 数据源
     */
    public static BasicDataSource getContextBasicDataSource(){
        String url = (String)PropertiesBean.getProperty("jdbc.url");
        String driverName = (String)PropertiesBean.getProperty("jdbc.driverClassName");
        String user = (String)PropertiesBean.getProperty("jdbc.username");
        String password = (String)PropertiesBean.getProperty("jdbc.password");
        if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(driverName) && StringUtils.isNotEmpty(user))
        {
            return DbContextHolder.getBasicDataSource(url, driverName, user, password);
        }
        return null;
    }
}

