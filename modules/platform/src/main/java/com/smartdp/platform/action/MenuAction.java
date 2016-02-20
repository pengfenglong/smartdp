/*
 * pengfenglong
 */

package com.smartdp.platform.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.pojo.EasyuiTreeModel;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.platform.model.Menu;
import com.smartdp.platform.model.ModuleOperate;
import com.smartdp.platform.model.Privilege;

@Component("platform-MenuAction")
public class MenuAction extends CrudActionSupport<Menu> {

	private Menu model;

	public void prepareModel() {
		if (id != null) {
			model = (Menu) baseService.get(id);
		} else {
			model = new Menu();
		}
	}

	@Override
	public Menu getModel() {
		return model;
	}

	public String listAll() {
		List<Menu> menus = baseService.getAll();
		result = menus;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Menu> menuPage = baseService.findPage(getPage(), getFilters());
		savePage(menuPage);
		return SUCCESS;
	}

	public String treegrid() throws Exception {
		List<Menu> menus = null;
		if (StringUtils.isEmpty(getParameter("parentId"))) {
			menus = baseService
					.find("from Menu where parentMenu.menuId is null");
		} else {
			menus = baseService.findBy("parentMenu.menuId",
					getParameterLong("parentId"));
		}
		result = menus;
		return SUCCESS;
	}

	public String tree() throws Exception {

		List<Menu> menus = null;
		if (StringUtils.isEmpty(getParameter("parentId"))) {
			menus = baseService
					.find("from Menu where parentMenu.menuId is null");
		} else {
			menus = baseService.findBy("parentMenu.menuId",
					getParameterLong("parentId"));
		}
		EasyuiTreeModel easyuiTreeModel = new EasyuiTreeModel();
		easyuiTreeModel.setIdField("menuId");
		easyuiTreeModel.setTextField("menuName");
		easyuiTreeModel.setParentId("parentMenu_menuId");
		easyuiTreeModel.setParentText("parentMenu_menuName");
		easyuiTreeModel.setSrc("menuUrl");
		easyuiTreeModel.setOrder("menuOrder");
		easyuiTreeModel.setChildList("menus");
		result = baseService.buildEasyuiTree(menus, easyuiTreeModel);
		return SUCCESS;
	}

	/**
	 * 构建菜单和操作树
	 * @return
	 */
	public String listMenuAndOperate() {
		List<Privilege> privis = new ArrayList<Privilege>();
		if (!StringUtils.isEmpty(getParameter("roleId"))) {
			long roleId = getParameterLong("roleId");
			privis = baseService.findBy("roleId", roleId,Privilege.class);
		}
		List<Menu> chilrendMenus = null;
		if (StringUtils.isEmpty(getParameter("parentId"))) {
			chilrendMenus = baseService
					.find("from Menu where parentMenu.menuId is null");
		} else {
			chilrendMenus = baseService.find(
					"from Menu where parentMenu.menuId = ?",
					getParameterLong("parentId"));
		}
		List easyuiTree = new ArrayList();

		if (StringUtils.isNotEmpty(getParameter("parentId"))) {
			Menu menu = (Menu) baseService.findUnique(
					"from Menu where menuId = ?", getParameterLong("parentId"));
			Set<ModuleOperate> moduleOperates = menu.getModuleOperates();
			for (ModuleOperate moduleOperate : moduleOperates) {
				Map json = new HashMap();
				json.put("id", moduleOperate.getOperateId());
				json.put("text", moduleOperate.getOperateName());
				json.put("state", "opened");
				json.put("iconCls", "icon-default");
				for (Privilege privi : privis) {
					if ("operate".equals(privi.getPrivilegeType())) {
						String[] ress = privi.getResourceId().split(":");
						long menuId = Long.parseLong(ress[0]);
						long operateId = Long.parseLong(ress[1]);
						if (menuId == menu.getMenuId()
								&& operateId == moduleOperate.getOperateId()) {
							json.put("checked", true);
						}
					}
				}
				easyuiTree.add(json);
			}
		}
		for (Menu menu : chilrendMenus) {
			Map json = new HashMap();
			json.put("id", menu.getMenuId());
			json.put("text", menu.getMenuName());
			if (menu.getMenus().size() == 0
					&& menu.getModuleOperates().size() == 0) {
				json.put("state", "opened");
			} else {
				json.put("state", "closed");
			}
			for (Privilege privi : privis) {
				if ("menu".equals(privi.getPrivilegeType())) {
					if (Long.parseLong(privi.getResourceId()) == menu
							.getMenuId()) {
						json.put("checked", true);
					}
				}
			}
			easyuiTree.add(json);
		}
		result = easyuiTree;
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
			model.setMenuId(id);
		}
		if (model.getParentMenu().getMenuId() == null) {
			model.setParentMenu(null);
		}

		Set<ModuleOperate> moduleOperates = new HashSet<ModuleOperate>();
		String[] moduleOperateArr = getRequest().getParameterValues(
				"moduleOperates");
		if (moduleOperateArr != null && moduleOperateArr.length > 0) {
			for (String moduleOperate : moduleOperateArr) {
				ModuleOperate operate = (ModuleOperate) baseService.get(Long
						.parseLong(moduleOperate),ModuleOperate.class);
				moduleOperates.add(operate);
			}
		}
		model.setModuleOperates(moduleOperates);
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

	public String changeParent() {
		long id = getParameterLong("id");
		model = (Menu) baseService.get(id);
		Menu parentMenu = new Menu();
		parentMenu.setMenuId(getParameterLong("parentId"));
		model.setParentMenu(parentMenu);
		baseService.save(model);
		result = resultJson;
		return SUCCESS;
	}

	public String configOpetate() {
		long id = getParameterLong("id");
		Menu menu = (Menu) baseService.get(id);
		Set<ModuleOperate> moduleOperates = new HashSet<ModuleOperate>();
		String[] moduleOperateArr = getRequest().getParameterValues(
				"moduleOperates");
		if (moduleOperateArr != null && moduleOperateArr.length > 0) {
			for (String moduleOperate : moduleOperateArr) {
				ModuleOperate operate = (ModuleOperate) baseService.get(Long
						.parseLong(moduleOperate),ModuleOperate.class);
				moduleOperates.add(operate);
			}
		}
		menu.setModuleOperates(moduleOperates);
		baseService.save(menu);
		result = resultJson;
		return SUCCESS;
	}

}
