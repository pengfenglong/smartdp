package com.smartdp.core.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Hiberate拦截器：实现创建人，创建时间自动注入
 */
@Component
public class HiberAspect extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		try {
			// 添加数据
			for (int index = 0; index < propertyNames.length; index++) {
				/* 找到名为"创建时间"的属性 */
				if ("createDate".equals(propertyNames[index])) {
					/* 使用拦截器将对象的"创建时间"属性赋上值 */
					if (state[index] == null) {
						state[index] = new Timestamp(System.currentTimeMillis());
					}
					continue;
				}
				/* 找到名为"创建人"的属性 */
				else if ("creator".equals(propertyNames[index])) {
					if (SecurityContextHolder.getContext().getAuthentication() != null) {
						UserDetails userDetails = (UserDetails) SecurityContextHolder
								.getContext().getAuthentication()
								.getPrincipal();
						/* 使用拦截器将对象的"创建人"属性赋上值 */
						if (state[index] == null) {
							state[index] = userDetails.getUsername();
						}
						continue;
					}
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		// 添加数据
		for (int index = 0; index < propertyNames.length; index++) {
			/* 找到名为"创建时间"的属性 */
			if ("createDate".equals(propertyNames[index])) {
				/* 使用拦截器将对象的"修改时间"属性赋上值 */
				currentState[index] = new Timestamp(System.currentTimeMillis());
				continue;
			}
			/* 找到名为"创建人"的属性 */
			else if ("creator".equals(propertyNames[index])) {
				if (SecurityContextHolder.getContext().getAuthentication() != null) {
					UserDetails userDetails = (UserDetails) SecurityContextHolder
							.getContext().getAuthentication().getPrincipal();
					/* 使用拦截器将对象的"修改人"属性赋上值 */
					currentState[index] = userDetails.getUsername();
					continue;
				}
			}
		}

		return true;
	}
}
