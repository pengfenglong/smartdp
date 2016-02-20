/*
 * pengfenglong
 */

package com.smartdp.platform.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PLATFORM_MENU")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "menu_id", nullable = false)
	private Long menuId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Menu parentMenu;// 父菜单
	
	@Column(name = "menuName")
	private String menuName;// 菜单名称
	
	@Column(name = "menuCode")
	private String menuCode;// 菜单编码
	
	@Column(name = "menuLevel")
	private Short menuLevel;// 菜单等级
	
	@Column(name = "menuUrl")
	private String menuUrl;// 菜单地址
	
	@Column(name = "menuIframe")
	private Short menuIframe;// 菜单地址打开方式
	
	@Column(name = "menuOrder")
	private String menuOrder;// 菜单排序
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentMenu")
	private List<Menu> menus = new ArrayList<Menu>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(
            name = "PLATFORM_MODULE_OPERATE_REF",
       		joinColumns = { @JoinColumn(name = "MENU_ID") },
	       	inverseJoinColumns = @JoinColumn(name = "OPERATE_ID"))
	private Set<ModuleOperate> moduleOperates = new HashSet<ModuleOperate>(0);
	
	private String state;
	
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public Short getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Short menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public Short getMenuIframe() {
		return menuIframe;
	}
	public void setMenuIframe(Short menuIframe) {
		this.menuIframe = menuIframe;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public Set<ModuleOperate> getModuleOperates() {
		return moduleOperates;
	}
	public void setModuleOperates(Set<ModuleOperate> moduleOperates) {
		this.moduleOperates = moduleOperates;
	}
	public String getState() {
		if(menus.size() > 0){
			return "closed";
		}else{
			return "open";
		}
	}
	public void setState(String state) {
		this.state = state;
	}

}
