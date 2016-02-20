package com.smartdp.tag.select;

import java.util.List;

import com.smartdp.core.utils.JsonBinder;
import com.thoughtworks.xstream.XStream;

public class DictUtils {

	public static List<DictType> getDictTypes() {
		XStream xstream = new XStream();
		xstream.alias("DictList", DictList.class);
		xstream.alias("DictType", DictType.class);
		xstream.alias("Dict", Dict.class);
		DictList dictList = (DictList) xstream.fromXML(DictUtils.class
				.getClassLoader().getResourceAsStream(
						"com/smartdp/tag/select/dict.xml"));

		List<DictType> dictTypes = dictList.getDictTypes();
		return dictTypes;
	}
	
	public static String getValueByTypeAndKey(String type,String key){
		String value = "";
		List<DictType> dictTypes = DictUtils.getDictTypes();
		for(DictType dictType : dictTypes){
			if (type.equals(dictType.getNameEn())) {
				List<Dict> dicts = dictType.getDicts();
				for(Dict dict : dicts){
					if(key.equals(dict.getNameEn())){
						value = dict.getName();
					}
				}
			}
		}
		return value;
	}
	
	public static String dictToJsonObject(){
		List<DictType> dictTypes = DictUtils.getDictTypes();
		JsonBinder binder = JsonBinder.buildNonDefaultBinder();
		return binder.toJson(dictTypes);
	}

}
