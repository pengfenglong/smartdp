package com.smartdp.tag.easyui;

import java.util.HashMap;

import org.hibernate.mapping.Map;

import com.smartdp.core.utils.JsonBinder;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 JsonBinder binder = JsonBinder.buildNonDefaultBinder();
		 HashMap map = new HashMap();
		 map.put("a", "aaaa");
		 map.put("b", "bbbb");
		System.out.println(binder.toJson(map));

	}

}
