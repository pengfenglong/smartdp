package com.smartdp.webos.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IBaseDao;
import com.smartdp.core.service.impl.BaseServiceImpl;
import com.smartdp.webos.model.Application;
import com.smartdp.webos.service.IApplicationService;

@Component("applicationService")
public class ApplicationServiceImpl extends BaseServiceImpl<Application, Long> implements IApplicationService {
	
	@Autowired
	public void setApplicationDao(IBaseDao applicationDao) {
		this.baseDao = applicationDao;
	}

	@Override
	public List<Application> getApplicationsByCatalog(final String catalog) {
		final List<Application> applications = new ArrayList<Application>();
		try {
			File configFiles = new File(Thread.currentThread()
					.getContextClassLoader().getResource("../../component")
					.toURI());
			for (final File file : configFiles.listFiles()) {
				file.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						if ("config.xml".equals(pathname.getName())) {
							SAXReader saxReader = new SAXReader();
							try {
								Document document = saxReader.read(pathname);
								Application application = new Application();
								Node node = null;

								String appCatalog = null;
								if ((node = document
										.selectSingleNode("/applicationConfig/catalog")) != null) {
									appCatalog = node.getText();
								}

								if (StringUtils.isEmpty(catalog)
										|| "newApp".equals(catalog)
										|| "riseApp".equals(catalog)
										|| "hotApp".equals(catalog)) {
									application.setCatalog(appCatalog);
								} else {
									if (appCatalog.equals(catalog)) {
										application.setCatalog(appCatalog);
									} else {
										return false;
									}
								}

								if ((node = document
										.selectSingleNode("/applicationConfig/id")) != null) {
									application.setId(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/name")) != null) {
									application.setName(node.getText());
								}

								if ((node = document
										.selectSingleNode("/applicationConfig/ico")) != null) {
									if (StringUtils.isEmpty(node.getText())) {
										application
												.setIco("/smartdp/component/"
														+ file.getName()
														+ "/big.png");
									} else {
										application
												.setIco("/smartdp/component/icon/"
														+ node.getText());
									}
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/explain")) != null) {
									application.setExplain(new Timestamp(System.currentTimeMillis()));
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/verify")) != null) {
									application.setVerify(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/mode")) != null) {
									application.setMode(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/href")) != null) {
									if (node.getText().indexOf("http://") == -1) {
										application
												.setHref("/smartdp/component/"
														+ file.getName() + "/"
														+ node.getText());
									} else {
										application.setHref(node.getText());
									}

								}
								if ((node = document
										.selectSingleNode("/applicationConfig/fullScreen")) != null) {
									application.setFullScreen(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/size")) != null) {
									application.setSize(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/width")) != null) {
									application.setWidth(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/height")) != null) {
									application.setHeight(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/minWidth")) != null) {
									application.setMinWidth(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/minHeight")) != null) {
									application.setMinHeight(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/bodyBG")) != null) {
									application.setBodyBG(node.getText());
								}
								if ((node = document
										.selectSingleNode("/applicationConfig/margin")) != null) {
									application.setMargin(node.getText());
								}

								application.setProvider("system");
								applications.add(application);
							} catch (DocumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						return false;
					}
				});
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		applications.addAll(getAll());
		return applications;
	}

	@Override
	public Application getApplicationById(String id) {
		List<Application> applications = getApplicationsByCatalog(null);
		for (Application application : applications) {
			if (application.getId().equals(id)) {
				return application;
			}
		}
		return null;
	}

}
