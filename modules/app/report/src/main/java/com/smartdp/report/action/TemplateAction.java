/*
 * pengfenglong
 */

package com.smartdp.report.action;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.smartdp.core.web.struts.BaseActionSupport;
import com.smartdp.report.pojo.Chart;

@Component("report-TemplateAction")
public class TemplateAction extends BaseActionSupport{
	
	private static final long serialVersionUID = -5779215555720922696L;
	
	public String list() throws Exception {
		URI uri = this.getClass().getClassLoader().getResource("../../component/report/template").toURI();
		File file = new File(uri);
		File[] fs = file.listFiles();
		List<Chart> charts = new ArrayList<Chart>();
		for(File f : fs){
			BufferedReader in = new BufferedReader(new FileReader(f));
			StringBuffer s = new StringBuffer();
            String str = "";
            while ((str = in.readLine()) != null) 
            {
                  s.append(str);
            }
            in.close();
            Chart chart = null;  
            JAXBContext jaxbContext = JAXBContext.newInstance(Chart.class);  
            Unmarshaller um = jaxbContext.createUnmarshaller();  
            chart = (Chart)um.unmarshal(new ByteArrayInputStream(s.toString().getBytes()));  
            charts.add(chart);
		}
		result = charts;	
		return SUCCESS;
	}


}
