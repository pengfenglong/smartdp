package com.smartdp.lifequery.bus;

import com.smartdp.lifequery.QueryImp;

public class BusQuery extends QueryImp {
	
	/**
	 * input(深圳|365:查询的是深圳365路，深圳|科技园:查询的是深圳科技园站)
	 */
	@Override
	public String getJsonStrResult(String input) {
		//http://openapi.aibang.com/bus/lines?app_key=f41c8afccc586de03a99c86097e98ccb&alt=json&city=深圳&q=365
		//http://openapi.aibang.com/bus/stats?app_key=f41c8afccc586de03a99c86097e98ccb&alt=json&city=深圳&q=科技园
		return input;

	
	}

	public static void main(String[] args) {
		BusQuery test = new BusQuery();
		System.out.println(test.getMapResult(""));
	}
}
