package com.smartdp.webos.service;

import java.util.List;

import com.smartdp.core.service.IBaseService;
import com.smartdp.webos.model.Application;

public interface IApplicationService  extends IBaseService<Application, Long>{
	/**
	 * 指定分类下的应用
	 * @return
	 */
	public List<Application> getApplicationsByCatalog(String catalog);
	
	/**
	 * 根据ID获得应用
	 * @return
	 */
	public Application getApplicationById(String id);

}
