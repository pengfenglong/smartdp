package com.smartdp.dbexportdoc;

public class GeneratorMain {

	public static void main(String[] args) {
		try {
			DB2WordUtil.export();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
