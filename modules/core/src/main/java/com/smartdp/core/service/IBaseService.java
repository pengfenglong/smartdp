package com.smartdp.core.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.smartdp.core.dao.IBaseDao;
import com.smartdp.core.dao.entity.PropertyFilter;
import com.smartdp.core.dao.entity.PropertyFilter.MatchType;
import com.smartdp.core.dao.impl.DefaultPage;
import com.smartdp.core.pojo.EasyuiTree;
import com.smartdp.core.pojo.EasyuiTreeModel;

public interface IBaseService<T, PK> {

	/**
	 * 获得baseDao对象
	 * 
	 * @return
	 */
	public IBaseDao<T, PK> getBaseDao();

	/**
	 * 保存新增或修改的对象.
	 */
	public void save(final T entity);

	/**
	 * 删除对象.
	 * 
	 * @param entity
	 *            对象必须是session中的对象或含id属性的transient对象.
	 */
	public void delete(final T entity);

	/**
	 * 按id删除对象.
	 */
	public void deleteById(final PK id);
	
	/**
	 * 按id和class类型删除对象.
	 */
	public void deleteById(final PK id, Class<T> entityClass);

	/**
	 * 按ids删除对象.
	 */
	public void deleteByIds(String ids);
	
	/**
	 * 按ids和class类型删除对象.
	 */
	public void deleteByIds(String ids, Class<T> entityClass);

	/**
	 * 按id获取对象.
	 */
	public T get(final PK id);

	/**
	 * 按id和class类型获取对象.
	 */
	public T get(final PK id, Class<T> entityClass);

	/**
	 * 按id获取对象.
	 */
	public T load(final PK id);

	/**
	 * 按id和class类型获取对象.
	 */
	public T load(final PK id, Class<T> entityClass);

	/**
	 * 按id列表获取对象列表.
	 */
	public List<T> get(final Collection<PK> ids);

	/**
	 * 获取全部对象.
	 */
	public List<T> getAll();

	/**
	 * 获取全部对象, 支持按属性行序.
	 */
	public List<T> getAll(String orderByProperty, boolean isAsc);

	/**
	 * 按属性查找对象列表, 匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value);

	/**
	 * 按属性和class类型查找对象列表, 匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value,
			Class<T> entityClass);

	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 */
	public T findUniqueBy(final String propertyName, final Object value);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public <X> List<X> find(final String hql, final Object... values);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> List<X> find(final String hql, final Map<String, ?> values);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public <X> X findUnique(final String hql, final Object... values);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> X findUnique(final String hql, final Map<String, ?> values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Object... values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Map<String, ?> values);

	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, final Object... values);

	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public Query createQuery(final String queryString,
			final Map<String, ?> values);

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public List<T> find(final Criterion... criterions);

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public T findUnique(final Criterion... criterions);

	/**
	 * 根据Criterion条件创建Criteria. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions);

	/**
	 * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化. 如果传入entity,
	 * 则只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,需执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize
	 * (user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public void initProxyObject(Object proxy);

	/**
	 * Flush当前Session.
	 */
	public void flush();

	/**
	 * 为Query添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
	 */
	public Query distinct(Query query);

	/**
	 * 为Criteria添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
	 */
	public Criteria distinct(Criteria criteria);

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName();

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName,
			final Object newValue, final Object oldValue);

	/**
	 * 分页获取全部对象.
	 */
	public DefaultPage<T> getAll(final DefaultPage<T> page);

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数. 注意不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	@SuppressWarnings("unchecked")
	public DefaultPage<T> findPage(final DefaultPage<T> page, final String hql,
			final Object... values);

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数. 注意不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	@SuppressWarnings("unchecked")
	public DefaultPage<T> findPage(final DefaultPage<T> page, final String hql,
			final Map<String, ?> values);

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param criterions
	 *            数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询输入参数.
	 */
	@SuppressWarnings("unchecked")
	public DefaultPage<T> findPage(final DefaultPage<T> page,
			final Criterion... criterions);

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType
	 *            匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
	 */
	public List<T> findBy(final String propertyName, final Object value,
			final MatchType matchType);

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public List<T> find(List<PropertyFilter> filters);

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	public DefaultPage<T> findPage(final DefaultPage<T> page,
			final List<PropertyFilter> filters);

	/**
	 * 构建easyui tree模型
	 * 
	 * @param all
	 * @param easyuiTreeModel
	 * @return
	 */
	public List<EasyuiTree> buildEasyuiTree(List all,
			EasyuiTreeModel easyuiTreeModel);

}
