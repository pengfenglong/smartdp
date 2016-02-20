package com.smartdp.webos.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.webos.model.Application;
import com.smartdp.webos.service.IApplicationService;

@Component("webos-ApplicationAction")
public class ApplicationAction extends CrudActionSupport<Application> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5405409908536946656L;

	@Autowired
	private IApplicationService applicationService;
	
	private Application model;
	
	private Long id;

	@Override
	public Application getModel() {
		return model;
	}
	
	public String listMenu() throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("/com/smartdp/webos/config/menu.xml");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		JSON json = new XMLSerializer().read(baos.toString());
		result = json;
		return SUCCESS;
	}

	@Override
	public String list() {
		System.out.println(getRequest());
		String catalog = null;
		List<Application> applications = applicationService
				.getApplicationsByCatalog(getRequest().getParameter("lessKey"));
		result = applications;
		return SUCCESS;
	}

	public String getById() {
		String id = getRequest().getParameter("id");
		Application application = applicationService.getApplicationById(id);
		result = application;
		return SUCCESS;
	}

	@Override
	public String input() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	public String save() {
		model.setProvider("system");
		model.setExplain(new Timestamp(System.currentTimeMillis()));
		applicationService.save(model);
		return SUCCESS;
	}

	@Override
	public String delete() {
		String ids = getParameter("ids");
		applicationService.deleteByIds(ids);
		result = "SUCCESS";
		return SUCCESS;
	}

	public void setApplicationService(IApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@Override
	public void prepareModel() {
		if (id != null) {
			model = applicationService.get(id);
		} else {
			model = new Application();
		}
		
	}

}
