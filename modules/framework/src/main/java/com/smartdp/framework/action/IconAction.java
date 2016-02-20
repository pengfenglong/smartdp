package com.smartdp.framework.action;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smartdp.core.config.PropertyConfigurer;
import com.smartdp.core.web.struts.BaseActionSupport;

@Component("framework-IconAction")
public class IconAction extends BaseActionSupport{
	public String list() throws Exception {
		List<String> fileNames = new ArrayList<String>();
		String iconPath = PropertyConfigurer.get("icon.path")+"/";
		URL url = this.getClass().getClassLoader().getResource("../../"+iconPath);
		if(url != null){
			File file = new File(url.toURI());
			File[] fs = file.listFiles();
			for (File f : fs) {
				String fileName = f.getName().toLowerCase();
				if (fileName.endsWith(".jpg") || fileName.endsWith(".jepg")
						|| fileName.endsWith(".gif") || fileName.endsWith(".png"))
					fileNames.add(iconPath + f.getName());
			}
			result = fileNames;
		}

		return SUCCESS;
	}
}
