/*
 * pengfenglong
 */

package com.smartdp.album.action;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.smartdp.core.config.PropertyConfigurer;
import com.smartdp.core.web.struts.BaseActionSupport;

@Component("album-PhotoAction")
public class PhotoAction extends BaseActionSupport {

	private static final long serialVersionUID = -5779215555720922696L;

	protected File file;
	protected String fileContentType;
	protected String fileFileName;

	public String list() throws Exception {
		List<String> fileNames = new ArrayList<String>();
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String uploadPath = PropertyConfigurer.get("upload.file.path")+"/"+userDetails.getUsername()+"/";
		
		URL url = this.getClass().getClassLoader().getResource("../../"+uploadPath);
		if(url != null){
			File file = new File(url.toURI());
			File[] fs = file.listFiles();
			for (File f : fs) {
				String fileName = f.getName().toLowerCase();
				if (fileName.endsWith(".jpg") || fileName.endsWith(".jepg")
						|| fileName.endsWith(".gif") || fileName.endsWith(".png"))
					fileNames.add(getRequest().getContextPath()+ "/" + uploadPath + f.getName());
			}
			result = fileNames;
		}

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
				+ "upload/photos/" + userDetails.getUsername() + "/";
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
		file.delete();
		file.deleteOnExit();

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("msg", "上传成功");
		result = m;
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

}
