package com.smartdp.wechat.action;

import java.util.List;

import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.wechat.model.Account;

@Component("wechat-MessageAction")
public class MessageAction extends CrudActionSupport<Account>{
	
	private static final long serialVersionUID = -5779218995720922696L;
	
	private Account model;
	
	public void prepareModel(){
		if (id != null) {
			model = (Account)baseService.get(id);
		} else {
			model = new Account();
		}
	}

	@Override
	public Account getModel() {
		return model;
	}
	
	
	public String listAll() {
		List<Account> list = baseService.getAll();
		result = list;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Account> page = baseService.findPage(getPage(),getFilters());
		savePage(page);
		return SUCCESS;
	}

	@Override
	public String input() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		return SUCCESS;
	}

	@Override
	public String delete() {
		return SUCCESS;
	}

}
