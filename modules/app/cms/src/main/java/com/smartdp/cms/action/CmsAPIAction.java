package com.smartdp.cms.action;

import java.util.List;

import org.springframework.stereotype.Component;

import com.smartdp.cms.model.Article;
import com.smartdp.cms.model.Channel;
import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.BaseActionSupport;

@Component("cms-CmsAPIAction")
public class CmsAPIAction extends BaseActionSupport {

	/**
	 * 忽略分类下的属性
	 * 
	 * @param channel
	 */
	private void channelIgnoreAttr(Channel channel) {
		channel.setParent(null);
		channel.setArticles(null);
		channel.setSite(null);
		List<Channel> children = channel.getChildren();
		if (children.size() > 0) {
			for (Channel c : children) {
				channelIgnoreAttr(c);
			}
		}
	}

	/**
	 * 查询站点下的分类
	 * 
	 * @return
	 */
	public String listAllChannelBySite() {
		long siteId = Long.parseLong(getParameter("siteId"));
		List<Channel> channels = baseService.find(
				"from Channel where parent.id is null and site.id = ?", siteId);
		for (Channel channel : channels) {
			channelIgnoreAttr(channel);
		}
		result = channels;
		return SUCCESS;
	}

	/**
	 * 查询分类下的文章
	 * 
	 * @return
	 */
	public String listArticleByChannel() {
		String channelId = getParameter("channelId");
		String hql = "from Article where channel.id = " + channelId
				+ " order by createDate desc";
		IPage<Article> modelPage = baseService.findPage(getPage(), hql);
		for (Article article : modelPage.getResult()) {
			article.setChannel(null);
		}
		savePage(modelPage);
		return SUCCESS;
	}
	
	/**
	 * 根据ID查询文章
	 * 
	 * @return
	 */
	public String getArticle() {
		long articleId = Long.parseLong(getParameter("articleId"));
		Article article = (Article)baseService.get(articleId, Article.class);
		article.setChannel(null);
		result = article;
		return SUCCESS;
	}
}
