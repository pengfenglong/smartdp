package com.smartdp.core.web.struts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.smartdp.core.pojo.ResultJson;
import com.smartdp.core.utils.ReflectionUtils;

/**
 * Struts2中典型CRUD Action的抽象基类.
 * 
 * 主要定义了对Preparable,ModelDriven接口的使用,以及CRUD函数和返回值的命名.
 *
 * @param <T> CRUDAction所管理的对象类型.
 * 
 * @author pengfenglong
 */
public abstract class CrudActionSupport<T> extends BaseActionSupport implements ModelDriven<T>, Preparable {

	private static final long serialVersionUID = -1653204626115064950L;

	/** 进行增删改操作后,以redirect方式重新打开action默认页的result名.*/
	public static final String RELOAD = "reload";

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Long id;
	
	/**
	 * ajax调用返回json
	 */
	protected ResultJson resultJson = new ResultJson();
	
	/**
	 * Action函数, 默认的action函数, 默认调用list()函数.
	 */
	@Override
	public String execute(){
		return list();
	}

	//-- CRUD Action函数 --//
	/**
	 * Action函数,显示Entity列表界面.
	 * 建议return SUCCESS.
	 */
	public abstract String list();

	/**
	 * Action函数,显示新增或修改Entity界面.
	 * 建议return INPUT.
	 */
	@Override
	public abstract String input();

	/**
	 * Action函数,新增或修改Entity. 
	 * 建议return RELOAD.
	 */
	public abstract String save();

	/**
	 * Action函数,删除Entity.
	 * 建议return RELOAD.
	 */
	public abstract String delete();

	//-- Preparable函数 --//
	/**
	 * 实现空的prepare()函数,屏蔽了所有Action函数都会执行的公共的二次绑定.
	 */
	public void prepare(){
		//设置泛型基类
		baseService.getBaseDao().setEntityClass(ReflectionUtils.getSuperClassGenricType(getClass()));
	}

	/**
	 * 定义在input()前执行二次绑定.
	 */
	public void prepareInput(){
		prepareModel();
	}

	/**
	 * 定义在save()前执行二次绑定.
	 */
	public void prepareSave(){
		prepareModel();
	}

	/**
	 * 等同于prepare()的内部函数,供prepardMethodName()函数调用. 
	 */
	public abstract void prepareModel();
}
