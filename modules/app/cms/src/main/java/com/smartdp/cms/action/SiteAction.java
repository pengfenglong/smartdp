/*
 * pengfenglong
 */

package com.smartdp.cms.action;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.smartdp.cms.model.Article;
import com.smartdp.cms.model.Channel;
import com.smartdp.cms.model.Site;
import com.smartdp.core.config.PropertyConfigurer;
import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;

@Component("cms-SiteAction")
public class SiteAction extends CrudActionSupport<Site> {
	
	private Site model;

	public void prepareModel() {
		if (id != null) {
			model = (Site) baseService.get(id);
		} else {
			model = new Site();
		}
	}

	@Override
	public Site getModel() {
		return model;
	}
	
	public String getById() {
		Long siteId = getParameterLong("siteId");
		Site site = (Site)baseService.get(siteId);
		site.setTemplate(null);
		site.setChannels(null);
		result = site;
		return SUCCESS;
	}

	private File getReleaseFile(String fileName)
			throws Exception {
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	@SuppressWarnings("deprecation")
	public String release() throws Exception {
		ObjectMapper mapper =  new  ObjectMapper();   
         
		String[] siteIds = getParameter("siteIds").split(",");
		for (String siteId : siteIds) {
			Site site = (Site) baseService.get(Long.parseLong(siteId),
					Site.class);
			
			// 文件发布路径
			String releaseDir = ServletActionContext.getServletContext().getRealPath("/")
					+ PropertyConfigurer.get("release.site.path");
			File releasePath = new File(releaseDir);
			if (!releasePath.exists()) {
				releasePath.mkdir();
			}
			
			String siteDir = releasePath + "/" + siteId;
			File sitePath = new File(siteDir);
			if (sitePath.exists()) {
				FileUtils.deleteDirectory(sitePath);
			}
			sitePath.mkdir();
			

//			String destDir = System.getProperty("catalina.base")+"/webapps/"+site.getCode();
//			File destFile = new File(destDir);
//			if(destFile.exists()){
//				FileUtils.deleteDirectory(destFile);
//			}
//			destFile.mkdir();
//			FileUtils.copyDirectory(new File(srcDir), destFile);
			
			mapper.writeValue(getReleaseFile(siteDir+"/site.json"), site);
			Set<Channel> channels = site.getChannels();
			List<Channel> _channels = new ArrayList<Channel>();
			for(Channel channel : channels){
				if(channel.getParent() == null){
					_channels.add(channel);
				}
			}
			mapper.writeValue(getReleaseFile(siteDir+"/channels.json"), _channels);
			for (Channel channel : channels) {
				Set<Article> articles = channel.getArticles();
				mapper.writeValue(getReleaseFile(siteDir+"/articles-"+channel.getId()+".json"), articles);
			}
		}

		result = resultJson;
		return SUCCESS;
	}

	public String listAll() {
		List<Site> models = baseService.getAll();
		result = models;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Site> modelPage = baseService.findPage(getPage(), getFilters());
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
		if (!StringUtils.isEmpty(getParameter("id"))) {
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
