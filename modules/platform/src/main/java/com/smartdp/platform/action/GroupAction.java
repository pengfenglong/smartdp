package com.smartdp.platform.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.pojo.EasyuiTreeModel;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.platform.model.Group;

@Component("platform-GroupAction")
public class GroupAction extends CrudActionSupport<Group> {

	private static final long serialVersionUID = 1L;

	private Group model;
	
	@Override
	public void prepareModel() {
		if (id != null) {
			model = (Group) baseService.get(id);
		} else {
			model = new Group();
		}
	}

	@Override
	public Group getModel() {
		return model;
	}
	
	public String list(){
		IPage<Group> groupPage = baseService.findPage(getPage(),getFilters());
		savePage(groupPage);
		return SUCCESS;
	}
	
	public String treegrid() {
		List<Group> groups = null;
		if (StringUtils.isEmpty(getParameter("parentId"))) {
			groups = baseService
					.find("from Group where parentGroup.groupId is null");
		} else {
			groups = baseService.findBy("parentGroup.groupId",
					getParameterLong("parentId"));
		}
		result = groups;
		return SUCCESS;

	}

	public String tree() {
		List<Group> groups = null;
		if(StringUtils.isEmpty(getParameter("parentId"))){
			groups = baseService.find("from Group where parentGroup.groupId is null");
		}else{
			groups = baseService
					.findBy("parentGroup.groupId",getParameterLong("parentId"));
		}
		EasyuiTreeModel easyuiTreeModel = new EasyuiTreeModel();
		easyuiTreeModel.setIdField("groupId");
		easyuiTreeModel.setTextField("groupSimpleName");
		easyuiTreeModel.setParentId("parentGroup_groupId");
		easyuiTreeModel.setParentText("parentGroup_groupSimpleName");
		easyuiTreeModel.setChildList("groups");
		result = baseService.buildEasyuiTree(groups, easyuiTreeModel);
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
			model.setGroupId(id);
		}

		if(StringUtils.isEmpty(getParameter("parentId"))){
			model.setParentGroup(null);
		}else{
			Group parentGroup = new Group();
			parentGroup.setGroupId(getParameterLong("parentId"));
			model.setParentGroup(parentGroup);
		}
		model.setStatus("1");
		baseService.save(model);
//		Map resultObj = new HashMap();
//		resultObj.put("action", "append");
		result = resultJson;
		return SUCCESS;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}
}
