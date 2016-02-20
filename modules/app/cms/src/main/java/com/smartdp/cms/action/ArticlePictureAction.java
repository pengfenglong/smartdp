/*
 * pengfenglong
 */

package com.smartdp.cms.action;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.smartdp.core.dao.IPage;
import com.smartdp.core.web.struts.CrudActionSupport;
import com.smartdp.cms.model.ArticlePicture;

@Component("cms-ArticlePictureAction")
public class ArticlePictureAction extends CrudActionSupport<ArticlePicture>{
	
	private ArticlePicture model;
	
	public void prepareModel(){
		if (id != null) {
			model = (ArticlePicture)baseService.get(id);
		} else {
			model = new ArticlePicture();
		}
	}

	@Override
	public ArticlePicture getModel() {
		return model;
	}
	
	
	public String listAll() {
		List<ArticlePicture> models = baseService.getAll();
		result = models;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<ArticlePicture> modelPage = baseService.findPage(getPage(),getFilters());
		savePage(modelPage);
		return SUCCESS;
	}

	@Override
	public String input() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		if(!StringUtils.isEmpty(getParameter("id"))){
			long id = getParameterLong("id");
			model.setId(id);
		}
		baseService.save(model);
		result = "SUCCESS";
		return SUCCESS;
	}

	@Override
	public String delete() {
		String ids = getParameter("ids");
		baseService.deleteByIds(ids);
		result = "SUCCESS";
		return SUCCESS;
	}

}
