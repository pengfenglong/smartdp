package com.smartdp.lifequery.phone;

import com.smartdp.lifequery.QueryImp;

/**
 * 手机归属地查询
 * 
 * @author pengfenglong
 * 
 */
public class PhoneAttributionQuery extends QueryImp {

	public String getEncoding() {
		return "GBK";
	}

	@Override
	public String getJsonStrResult(String input) {

		String datas = super
				.getJsonStrResult("http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="
						+ input);
		int begin = datas.indexOf("{");
		int end = datas.indexOf("}") + 1;
		String jsoStr = datas.substring(begin, end);
		return jsoStr;
	}

	@Override
	public String getXmlStrResult(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		PhoneAttributionQuery test = new PhoneAttributionQuery();
		System.out.println(test.getMapResult("13717010765"));
	}
}
