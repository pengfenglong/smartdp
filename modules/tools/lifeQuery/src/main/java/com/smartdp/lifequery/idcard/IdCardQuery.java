package com.smartdp.lifequery.idcard;

import com.smartdp.lifequery.QueryImp;

public class IdCardQuery extends QueryImp {

	@Override
	public String getJsonStrResult(String input) {

		String datas = super
				.getJsonStrResult("http://api.k780.com:88/?app=idcard.get&idcard="
						+ input
						+ "&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json");
		return datas;
	}

	public static void main(String[] args) {
		IdCardQuery test = new IdCardQuery();
		System.out.println(test.getMapResult("36220319821026681X"));
	}

}
