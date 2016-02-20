package com.smartdp.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.smartdp.core.dao.IBaseDao;
import com.smartdp.core.dao.entity.PropertyFilter;
import com.smartdp.core.dao.entity.PropertyFilter.MatchType;
import com.smartdp.core.dao.impl.DefaultPage;
import com.smartdp.core.pojo.EasyuiTree;
import com.smartdp.core.pojo.EasyuiTreeModel;
import com.smartdp.core.service.IBaseService;
import com.smartdp.core.utils.ConvertUtils;
import com.smartdp.core.utils.ReflectHelper;
import com.smartdp.core.utils.TagUtil;

@Component("baseService")
@Transactional
public class BaseServiceImpl<T, PK> implements IBaseService<T, PK> {

	@Autowired
	protected IBaseDao<T, PK> baseDao;

	public void setBaseDao(IBaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	public IBaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void deleteById(PK id) {
		baseDao.deleteById(id);
	}

	@Override
	public void deleteById(PK id, Class<T> entityClass) {
		baseDao.setEntityClass(entityClass);
		deleteById(id);
	}

	@Override
	public void deleteByIds(String ids) {
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			Long _id = Long.parseLong(id);
			baseDao.deleteById((PK) _id);
		}
	}
	
	@Override
	public void deleteByIds(String ids, Class<T> entityClass) {
		baseDao.setEntityClass(entityClass);
		deleteByIds(ids);
	}

	@Override
	public T get(PK id) {
		return baseDao.get(id);
	}

	@Override
	public T get(final PK id, Class<T> entityClass) {
		baseDao.setEntityClass(entityClass);
		return get(id);
	}

	@Override
	public T load(PK id) {
		return baseDao.load(id);
	}

	@Override
	public T load(PK id, Class<T> entityClass) {
		baseDao.setEntityClass(entityClass);
		return load(id);
	}

	@Override
	public List<T> get(Collection<PK> ids) {
		return baseDao.get(ids);
	}

	@Override
	public List<T> getAll() {
		return baseDao.getAll();
	}

	@Override
	public List<T> getAll(String orderByProperty, boolean isAsc) {
		return baseDao.getAll(orderByProperty, isAsc);
	}

	@Override
	public List<T> findBy(String propertyName, Object value) {
		return baseDao.findBy(propertyName, value);
	}

	@Override
	public List<T> findBy(final String propertyName, final Object value,
			Class<T> entityClass) {
		baseDao.setEntityClass(entityClass);
		return findBy(propertyName, value);
	}

	@Override
	public T findUniqueBy(String propertyName, Object value) {
		return baseDao.findUniqueBy(propertyName, value);
	}

	@Override
	public <X> List<X> find(String hql, Object... values) {
		return baseDao.find(hql, values);
	}

	@Override
	public <X> List<X> find(String hql, Map<String, ?> values) {
		return baseDao.find(hql, values);
	}

	@Override
	public <X> X findUnique(String hql, Object... values) {
		return baseDao.findUnique(hql, values);
	}

	@Override
	public <X> X findUnique(String hql, Map<String, ?> values) {
		return baseDao.findUnique(hql, values);
	}

	@Override
	public int batchExecute(String hql, Object... values) {
		return baseDao.batchExecute(hql, values);
	}

	@Override
	public int batchExecute(String hql, Map<String, ?> values) {
		return baseDao.batchExecute(hql, values);
	}

	@Override
	public Query createQuery(String queryString, Object... values) {
		return baseDao.createQuery(queryString, values);
	}

	@Override
	public Query createQuery(String queryString, Map<String, ?> values) {
		return baseDao.createQuery(queryString, values);
	}

	@Override
	public List<T> find(Criterion... criterions) {
		return baseDao.find(criterions);
	}

	@Override
	public T findUnique(Criterion... criterions) {
		return baseDao.findUnique(criterions);
	}

	@Override
	public Criteria createCriteria(Criterion... criterions) {
		return baseDao.createCriteria(criterions);
	}

	@Override
	public void initProxyObject(Object proxy) {
		baseDao.initProxyObject(proxy);
	}

	@Override
	public void flush() {
		baseDao.flush();
	}

	@Override
	public Query distinct(Query query) {
		return baseDao.distinct(query);
	}

	@Override
	public Criteria distinct(Criteria criteria) {
		return baseDao.distinct(criteria);
	}

	@Override
	public String getIdName() {
		return baseDao.getIdName();
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object newValue,
			Object oldValue) {
		return baseDao.isPropertyUnique(propertyName, newValue, oldValue);
	}

	@Override
	public DefaultPage<T> getAll(DefaultPage<T> page) {
		return baseDao.getAll(page);
	}

	@Override
	public DefaultPage<T> findPage(DefaultPage<T> page, String hql,
			Object... values) {
		return baseDao.findPage(page, hql, values);
	}

	@Override
	public DefaultPage<T> findPage(DefaultPage<T> page, String hql,
			Map<String, ?> values) {
		return baseDao.findPage(page, hql, values);
	}

	@Override
	public DefaultPage<T> findPage(DefaultPage<T> page, Criterion... criterions) {
		return baseDao.findPage(page, criterions);
	}

	@Override
	public List<T> findBy(String propertyName, Object value, MatchType matchType) {
		return baseDao.findBy(propertyName, value, matchType);
	}

	@Override
	public List<T> find(List<PropertyFilter> filters) {
		return baseDao.find(filters);
	}

	@Override
	public DefaultPage<T> findPage(DefaultPage<T> page,
			List<PropertyFilter> filters) {
		return baseDao.findPage(page, filters);
	}

	@Override
	public List<EasyuiTree> buildEasyuiTree(List all,
			EasyuiTreeModel easyuiTreeModel) {
		List<EasyuiTree> easyuiTree = new ArrayList<EasyuiTree>();
		for (Object obj : all) {
			ReflectHelper reflectHelper = new ReflectHelper(obj);
			EasyuiTree tg = new EasyuiTree();
			if (StringUtils.isNotEmpty(easyuiTreeModel.getIdField())) {
				String id = ConvertUtils.getString(reflectHelper
						.getMethodValue(easyuiTreeModel.getIdField()));
				tg.setId(id);
			}

			if (StringUtils.isNotEmpty(easyuiTreeModel.getTextField())) {
				String text = ConvertUtils.getString(reflectHelper
						.getMethodValue(easyuiTreeModel.getTextField()));
				tg.setText(text);
			}

			if (StringUtils.isNotEmpty(easyuiTreeModel.getSrc())) {
				String src = ConvertUtils.getString(reflectHelper
						.getMethodValue(easyuiTreeModel.getSrc()));
				tg.setSrc(src);
			}

			if (StringUtils.isNotEmpty(easyuiTreeModel.getOrder())) {
				String order = ConvertUtils.getString(reflectHelper
						.getMethodValue(easyuiTreeModel.getOrder()));
				tg.setOrder(order);
			}
			// ----------------------------------------------------------------
			// update-end--Author:wangyang Date:20130402 for：添加排序
			// ----------------------------------------------------------------

			if (easyuiTreeModel.getIcon() != null) {
				String iconpath = TagUtil.fieldNametoValues(
						easyuiTreeModel.getIcon(), obj).toString();
				if (iconpath != null) {
					tg.setCode(iconpath);
				} else {
					tg.setCode("");
				}
			}

			if (easyuiTreeModel.getParentId() != null) {
				Object pid = TagUtil.fieldNametoValues(
						easyuiTreeModel.getParentId(), obj);
				if (pid != null) {
					tg.setParentId(pid.toString());
				} else {
					tg.setParentId("");
				}
			}
			if (easyuiTreeModel.getParentText() != null) {
				Object ptext = TagUtil.fieldNametoValues(
						easyuiTreeModel.getTextField(), obj);
				if (ptext != null) {
					tg.setParentText(ptext.toString());
				} else {
					tg.setParentText("");
				}

			}
			List childList = (List) reflectHelper
					.getMethodValue(easyuiTreeModel.getChildList());

			if (childList != null && childList.size() > 0) {
				tg.setState("closed");
			}
			easyuiTree.add(tg);
		}
		return easyuiTree;
	}

}
