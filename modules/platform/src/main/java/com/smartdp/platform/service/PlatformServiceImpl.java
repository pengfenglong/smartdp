package com.smartdp.platform.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.dao.entity.PropertyFilter;
import com.smartdp.core.dao.impl.DefaultPage;
import com.smartdp.core.service.impl.BaseServiceImpl;
import com.smartdp.platform.model.User;

@Component("platformService")
public class PlatformServiceImpl extends BaseServiceImpl implements IPlatformService{
	
	
	@Override
	public IPage<User> getUsersFromGroupByPage(Long groupId, DefaultPage page,List<PropertyFilter> filters) {
		Criterion[] criterions = this.baseDao.buildCriterionByPropertyFilter(filters);
		Criteria c = createCriteria(criterions);
		c.createAlias("groups", "g")
		.add(Restrictions.eq("g.groupId", groupId));
		if (page.isAutoCount()) {
			long totalCount = this.baseDao.countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}
		this.baseDao.setPageParameterToCriteria(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}

}
