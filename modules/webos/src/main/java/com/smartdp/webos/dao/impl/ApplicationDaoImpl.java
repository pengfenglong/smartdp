package com.smartdp.webos.dao.impl;

import org.springframework.stereotype.Component;

import com.smartdp.core.dao.impl.BaseDaoSupport;
import com.smartdp.webos.dao.IApplicationDao;
import com.smartdp.webos.model.Application;

@Component("applicationDao")
public class ApplicationDaoImpl extends BaseDaoSupport<Application, Long>
		implements IApplicationDao {

}
