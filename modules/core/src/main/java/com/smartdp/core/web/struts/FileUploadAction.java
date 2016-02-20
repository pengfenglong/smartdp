package com.smartdp.core.web.struts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 文件上传公用action 
 * pengfenglong
 */
public abstract class FileUploadAction extends BaseActionSupport {
	private static final long serialVersionUID = -9208910183310010569L;
	protected File file;
	protected String fileContentType;
	protected String fileFileName;
	protected String name;

	/**
	 * 业务名称
	 * 
	 * @return
	 */
	protected abstract String getBusName();

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
				+ "upload/"
				+ getBusName()
				+ "/"
				+ userDetails.getUsername()
				+ "/";
		File saveDir = new File(savePath);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}

		// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
		sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
		nowTimeStr = sDateFormat.format(new Date()); // 当前时间

		// 获取拓展名
		if (fileFileName.lastIndexOf(".") >= 0) {
			extName = fileFileName.substring(fileFileName.lastIndexOf("."));
		}

		newFileName = nowTimeStr + rannum + extName; // 文件重命名后的名字
		file.renameTo(new File(savePath + newFileName)); // 保存文件
		file.deleteOnExit();

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("msg", "上传成功");
		result = m;
		return SUCCESS;
	}

	/**
	 * Default method - returns "input"
	 * 
	 * @return "input"
	 */
	public String execute() {
		return INPUT;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public File getFile() {
		return file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate() {
		if (getRequest().getMethod().equalsIgnoreCase("post")) {
			getFieldErrors().clear();
			if ("".equals(fileFileName) || file == null) {
				super.addFieldError(
						"file",
						getText("errors.requiredField",
								new String[] { getText("uploadForm.file") }));
			} else if (file.length() > 2097152) {
				addActionError(getText("maxLengthExceeded"));
			}
		}
	}
}
