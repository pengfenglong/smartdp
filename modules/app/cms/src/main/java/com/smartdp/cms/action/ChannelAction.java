/*
 * pengfenglong
 */

package com.smartdp.cms.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.cms.model.Channel;
import com.smartdp.core.dao.IPage;
import com.smartdp.core.pojo.EasyuiTreeModel;
import com.smartdp.core.web.struts.CrudActionSupport;

@Component("cms-ChannelAction")
public class ChannelAction extends CrudActionSupport<Channel> {

	private Channel model;

	public void prepareModel() {
		if (id != null) {
			model = (Channel) baseService.get(id);
		} else {
			model = new Channel();
		}
	}

	public String treegrid() throws Exception {
		List<Channel> channels = null;
		if (StringUtils.isEmpty(getParameter("parentId"))) {
			channels = baseService
					.find("from Channel where parent.id is null order by priority");
		} else {
			channels = baseService.find(
					"from Channel where parent.id = ? order by priority",
					getParameterLong("parentId"));
		}
		for (Channel channel : channels) {
			channel.setArticles(null);
			if (channel.getChildren() != null
					&& channel.getChildren().size() > 0) {
				channel.setState("closed");
			} else {
				channel.setState("open");
			}
			channel.setChildren(null);
			if (channel.getParent() != null) {
				Channel parent = new Channel();
				parent.setId(channel.getParent().getId());
				channel.setParent(parent);
			}

			channel.setSite(null);
		}
		result = channels;
		return SUCCESS;
	}

	public String tree() {
		long siteId = Long.parseLong(getParameter("siteId"));
		List<Channel> channels = null;
		if (StringUtils.isEmpty(getParameter("parentId"))) {
			channels = baseService.find(
					"from Channel where parent.id is null and site.id = ?",
					siteId);
		} else {
			channels = baseService.find(
					"from Channel where parent.id = ? and site.id = ?",
					getParameterLong("parentId"), siteId);
		}
		EasyuiTreeModel easyuiTreeModel = new EasyuiTreeModel();
		easyuiTreeModel.setIdField("id");
		easyuiTreeModel.setTextField("name");
		easyuiTreeModel.setParentId("parent_id");
		easyuiTreeModel.setParentText("parent_name");
		easyuiTreeModel.setChildList("children");
		result = baseService.buildEasyuiTree(channels, easyuiTreeModel);
		return SUCCESS;
	}

	@Override
	public Channel getModel() {
		return model;
	}

	public String listAll() {
		List<Channel> models = baseService.getAll();
		result = models;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Channel> modelPage = baseService
				.findPage(getPage(), getFilters());
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
		baseService.deleteById(getParameterLong("id"));
		result = resultJson;
		return SUCCESS;
	}

}
