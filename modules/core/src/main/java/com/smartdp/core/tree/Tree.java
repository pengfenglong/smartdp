package com.smartdp.core.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.smartdp.core.tree.exception.UncodeException;
import com.smartdp.core.tree.support.AbstractTreeModelCreator;
import com.smartdp.core.tree.support.DefaultTreeNode;
import com.smartdp.core.tree.support.JsTreeCoverter;

public class Tree {
	public static void main(String[] args) {
		//业务数据
		List orgs =  new ArrayList();
		Org o = new Org("001",null,"华为公司", 1);
		Org k = new Org("002",null,"中兴公司", 2);
		Org l = new Org("003",null,"思科公司", 3);
		Org o1 = new Org("001001","001","软件公司", 1);
		Org o2 = new Org("001002","001","硬件公司", 1);
		Org o11 = new Org("0010010011","001001","公共部件部门", 1);
		Org o12 = new Org("0010010012","001001","CC部门", 2);
		Org o13 = new Org("0010010013","001001","CRM部门", 3);
		Org o111 = new Org("0010010011001","0010010011","BI", 1);
		Org o112 = new Org("0010010011002","0010010011","ETL", 2);
		Org o113 = new Org("0010010011003","0010010011","NG", 3);
		
		Org k1 = new Org("002001","002","软件公司", 1);
		Org k2 = new Org("002002","002","硬件公司", 1);
		Org k11 = new Org("0020010011","002001","公共部件部门", 1);
		Org k12 = new Org("0020010012","002001","CC部门", 2);
		Org k13 = new Org("0020010013","002001","CRM部门", 3);
		Org k111 = new Org("0020010011001","0020010011","BI", 1);
		Org k112 = new Org("0020010011002","0020010011","ETL", 2);
		Org k113 = new Org("0020010011003","0020010011","NG", 3);
		

		orgs.add(k);
		orgs.add(l);
		orgs.add(o);
		orgs.add(o1);
		orgs.add(o2);
		orgs.add(o11);
		orgs.add(o12);
		orgs.add(o13);
		orgs.add(o111);
		orgs.add(o112);
		orgs.add(o113);
		
		orgs.add(k1);
		orgs.add(k2);
		orgs.add(k11);
		orgs.add(k12);
		orgs.add(k13);
		orgs.add(k111);
		orgs.add(k112);
		orgs.add(k113);

		
		//业务数据解码器，从业务数据中分解出id和parentid
		IUserDataUncoder orgUncoder = new IUserDataUncoder(){
			public Object getID(Object pUserData) throws UncodeException {
				Org org = (Org)pUserData;
				return org.getId();
			} 
			public Object getParentID(Object pUserData) throws UncodeException {
				Org org = (Org)pUserData;
				return org.getParentId();
			}
		};
		
		//Tree模型构造器，用于生成树模型		
		ITreeModelCreator treeModelCreator = new AbstractTreeModelCreator() {
						
			protected ITreeNode createNode(Object pUserData, IUserDataUncoder pUncoder) {
				Org org = (Org)pUserData;
				ITreeNode result = new DefaultTreeNode(org.getName(), org);
				return result;
			}
		};
				
		ITreeModel treeModel = treeModelCreator.create(orgs,orgUncoder);
		
		//使用转换器转换成json
		Iterator<ITreeNode> nodes = treeModel.getRootNodes();
		ITreeConverter converter = new JsTreeCoverter();
		System.out.println(converter.tree2Json(treeModel));
		
		
		/**
		TreeDirector director = new DefaultTreeDirector();//构造树导向器
		WebTreeBuilder treeBuilder = new XTreeBuilder();//构造树Builder
		director.build(treeModel, treeBuilder);//执行构造		
		String treeScript = treeBuilder.getTreeScript();//获取构造树的脚本
		System.out.println(treeScript);
		**/

	}

}

class Org {
	private String id;
	private String parentId;
	private String name;
	private int viewOrder;

	public Org(){
		
	}
	
	public Org(String pId, String pParentId, String pName, int pViewOrder){
		this.id = pId;
		this.parentId = pParentId;
		this.name = pName;
		this.viewOrder = pViewOrder;
	}
	public int getViewOrder() {
		return viewOrder;
	}

	public void setViewOrder(int viewOrder) {
		this.viewOrder = viewOrder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}


