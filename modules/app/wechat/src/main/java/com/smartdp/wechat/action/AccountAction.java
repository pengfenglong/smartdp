package com.smartdp.wechat.action;

import java.util.List;

import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.wechat.model.WxMessage;

@Component("wechat-AccountAction")
public class AccountAction extends CrudActionSupport<WxMessage>{
	
	private static final long serialVersionUID = -5779218995720922696L;
	
	private WxMessage model;
	
	public void prepareModel(){
		if (id != null) {
			model = (WxMessage)baseService.get(id);
		} else {
			model = new WxMessage();
		}
	}

	@Override
	public WxMessage getModel() {
		return model;
	}
	
	
	public String listAll() {
		List<WxMessage> WxMessages = baseService.getAll();
		result = WxMessages;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<WxMessage> WxMessagePage = baseService.findPage(getPage(),getFilters());
		savePage(WxMessagePage);
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
