/*
 * pengfenglong
 */

package com.smartdp.cms.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.smartdp.cms.model.Template;
import com.smartdp.core.config.PropertyConfigurer;
import com.smartdp.core.dao.IPage;
import com.smartdp.core.utils.ZipUtils;
import com.smartdp.core.web.struts.CrudActionSupport;

@Component("cms-TemplateAction")
@Scope("prototype")
public class TemplateAction extends CrudActionSupport<Template> {

	private Template model;

	protected File file;
	protected String fileContentType;
	protected String fileFileName;

	private String code;

	public void prepareModel() {
		if (id != null) {
			model = (Template) baseService.get(id);
		} else {
			model = new Template();
		}
	}

	@Override
	public Template getModel() {
		return model;
	}

	public String listAll() {
		List<Template> models = baseService.getAll();
		result = models;
		return SUCCESS;
	}

	@Override
	public String list() {
		IPage<Template> modelPage = baseService.findPage(getPage(),
				getFilters());
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
		if (!StringUtils.isEmpty(getParameter("id"))) {
			long id = getParameterLong("id");
			model.setId(id);
		}
		baseService.save(model);
		result = resultJson;
		return SUCCESS;
	}

	@Override
	public String delete() {
		String ids = getParameter("ids");
		baseService.deleteByIds(ids);
		result = resultJson;
		return SUCCESS;
	}

	/**
	 * 文件上传
	 * 
	 */
	public String upload() {
		getResponse().setCharacterEncoding("utf-8");

		String extName = ""; // 保存文件拓展名
		String newFileName = ""; // 保存新的文件名
		String nowTimeStr = ""; // 保存当前时间
		SimpleDateFormat sDateFormat;
		Random r = new Random();

		// 登陆用户
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		// 文件保存目录路径
		String savePath = ServletActionContext.getServletContext().getRealPath(
				"/")
				+ PropertyConfigurer.get("upload.template.path") + "/";
		File saveDir = new File(savePath);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}

		// // 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
		// int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; //
		// 获取随机数
		// sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
		// nowTimeStr = sDateFormat.format(new Date()); // 当前时间
		//
		// // 获取拓展名
		// if (fileFileName.lastIndexOf(".") >= 0) {
		// extName = fileFileName.substring(fileFileName.lastIndexOf("."));
		// }
		//
		// newFileName = nowTimeStr + rannum + extName; // 文件重命名后的名字
		// file.renameTo(new File(savePath + newFileName)); // 保存文件
		// file.delete();
		// file.deleteOnExit();

		File templateDir = new File(savePath + code);
		if (!templateDir.exists()) {
			templateDir.mkdir();
		}
		try {
			new ZipUtils().releaseZipToFile(file, savePath + code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("msg", "上传成功");
		result = m;
		return SUCCESS;
	}

	/**
	 * 模板文件
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	public String fileTree() throws URISyntaxException {
		List tree = new ArrayList();
		String parentId = getParameter("parentId");
		URL url = this
				.getClass()
				.getClassLoader()
				.getResource(
						"../../"
								+ PropertyConfigurer
										.get("upload.template.path") + "/"
								+ parentId);
		File file = new File(url.toURI());
		File[] files = file.listFiles();
		for (File f : files) {
			Map json = new HashMap();
			json.put("id", parentId + "/" + f.getName());
			json.put("text", f.getName());
			if (f.isDirectory()) {
				json.put("state", "closed");
			} else {
				json.put("state", "open");
			}

			tree.add(json);
		}
		result = tree;
		return SUCCESS;
	}

	/**
	 * 模板内容
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	public String getFileContent() throws Exception {
		String filepath = getParameter("filepath");
		URL url = this
				.getClass()
				.getClassLoader()
				.getResource(
						"../../"
								+ PropertyConfigurer
										.get("upload.template.path") + "/"
								+ filepath);
		File f = new File(url.toURI());
		if (f.isFile()) {
			InputStream is = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			int size = 0;
			byte[] b = new byte[1024];
			while ((size = is.read(b)) != -1) {
				out.write(b, 0, size);
			}
			is.close();
			result = new String(out.toByteArray(), "utf-8");
		}
		return SUCCESS;
	}

	/**
	 * 模板列表
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	public String lists() throws URISyntaxException {
		URL url = this
				.getClass()
				.getClassLoader()
				.getResource(
						"../../"
								+ PropertyConfigurer
										.get("upload.template.path"));
		File file = new File(url.toURI());
		File[] files = file.listFiles();
		List<String> list = new ArrayList<String>();
		for (File f : files) {
			list.add(f.getName());
		}
		Map map = new HashMap();
		map.put("dir", PropertyConfigurer.get("upload.template.path"));
		map.put("list", list);
		result = map;
		return SUCCESS;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
