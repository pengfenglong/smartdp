/*
 * pengfenglong
 */

package com.smartdp.cms.action;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.dao.entity.PropertyFilter;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.cms.model.Article;
import com.smartdp.cms.model.Channel;

@Component("cms-ArticleAction")
public class ArticleAction extends CrudActionSupport<Article>{
	
	private Article model;
	
	public void prepareModel(){
		if (id != null) {
			model = (Article)baseService.get(id);
		} else {
			model = new Article();
		}
	}

	@Override
	public Article getModel() {
		return model;
	}
	
	
	public String listAll() {
		List<Article> models = baseService.getAll();
		result = models;
		return SUCCESS;
	}

	@Override
	public String list() {
		String hql = null;
		String channelId = getParameter("channelId");
		List<PropertyFilter> filterList = getFilters();
		if(StringUtils.isEmpty(channelId)){
			hql = "select a from Article a, Channel c ,Site s where a.channel = c and c.site = s and s.id =  " + getParameter("siteId");
			if(filterList.size()>0){
				hql += " and a.title = " + filterList.get(0).getMatchValue();
			}
			hql +=" order by a.createDate desc";
		}else{
			hql = "from Article where channel.id = " + channelId;
			if(filterList.size()>0){
				hql += " and title = " + filterList.get(0).getMatchValue();
			}
			hql +=" order by createDate desc";
		}
		IPage<Article> modelPage = baseService.findPage(getPage(),hql);
		for(Article article : modelPage.getResult()){
			Channel channel = new Channel();
			channel.setId(article.getChannel().getId());
			channel.setName(article.getChannel().getName());
			article.setChannel(channel);
		}
		savePage(modelPage);
		return SUCCESS;
	}

	@Override
	public String input() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		if(!StringUtils.isEmpty(getParameter("id"))){
			long id = getParameterLong("id");
			model.setId(id);
		}
		baseService.save(model);
		result = resultJson;
		return SUCCESS;
	}

	@Override
	public String delete() {
		String ids = getParameter("ids");
		baseService.deleteByIds(ids);
		result = resultJson;
		return SUCCESS;
	}

}
