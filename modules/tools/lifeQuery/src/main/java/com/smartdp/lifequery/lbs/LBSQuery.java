package com.smartdp.lifequery.lbs;

import com.smartdp.lifequery.QueryImp;

public class LBSQuery extends QueryImp {
	

	@Override
	public String getJsonStrResult(String input) {
		String[] paras = input.split(",");
		String datas = super
				.getJsonStrResult("http://api.map.baidu.com/place/v2/search?scope=2&query="
						+ paras[0]
						+ "&location="
						+ paras[1]
						+ ","
						+ paras[2]
						+ "&radius=2000&output=json&ak=9c19b7c324039c48929e2d5902116c17");
		return datas;
	}

	public static void main(String[] args) {
		LBSQuery test = new LBSQuery();
		System.out.println(test.getMapResult("酒店,39.915,116.404"));
	}

}
