package com.leaf.common.controller.updownload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.leaf.base.constant.Constant;
import com.leaf.base.util.ImageUtil;
import com.leaf.base.vo.ResponseVO;
import com.leaf.common.vo.CommonVO;

/**
 * 文件上传Controller
 * 
 * @author admin
 * 
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

	private static final String DISK_PATH = "D:\\development\\tools\\tomcat_workspace\\PROJECT_LEAF_RESOURCE\\";
	private static final Logger LOG = Logger
			.getLogger(FileUploadController.class);

	private void checkDangerFile(MultipartFile file) {
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename().toLowerCase();
			if (fileName.indexOf(".jsp") > 0 || fileName.indexOf(".exe") > 0
					|| fileName.indexOf(".sh") > 0
					|| fileName.indexOf(".bat") > 0) {
				throw new IllegalArgumentException(file + "禁止上传！");
			}
		}
	}

	/*
	 * 例子模板
	 * 
	 * @RequestMapping(value="/add", method=RequestMethod.POST) public String
	 * addUser(User user, @RequestParam MultipartFile[] myfiles,
	 * HttpServletRequest request) throws IOException{
	 * //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，显式指定@RequestParam注解
	 * //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
	 * //并且上传多个文件时，前台表单中的所有<input
	 * type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件 for(MultipartFile
	 * myfile : myfiles){ if(myfile.isEmpty()){ System.out.println("文件未上传");
	 * }else{ System.out.println("文件长度: " + myfile.getSize());
	 * System.out.println("文件类型: " + myfile.getContentType());
	 * System.out.println("文件名称: " + myfile.getName());
	 * System.out.println("文件原名: " + myfile.getOriginalFilename());
	 * System.out.println("========================================");
	 * //如果用的是Tomcat服务器
	 * ，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
	 * String realPath =
	 * request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
	 * //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，
	 * 我是看它的源码才知道的 FileUtils.copyInputStreamToFile(myfile.getInputStream(), new
	 * File(realPath, myfile.getOriginalFilename())); } }
	 * users.put(user.getUsername(), user); return "redirect:/user/list"; }
	 */
	/**
	 * 单图片上传
	 * @param uploadImg
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "uploadImage.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO uploadImage(@RequestParam MultipartFile uploadImg,
			HttpServletRequest request, HttpServletResponse response) {
		CommonVO rsp = new CommonVO();
		checkDangerFile(uploadImg);
		if (uploadImg.isEmpty()) {
			rsp.setMessage("上传失败，上传图片不能为空");
			return rsp;
		}

//		String savePath = request.getSession().getServletContext()
//				.getRealPath("/");
		String savePath = DISK_PATH;
		savePath += "/uploads";
		String imageWidth = request.getParameter("imageWidth");
		String imageHeight = request.getParameter("imageHeight");
		String saveFolder = request.getParameter("saveFolder");

		if (StringUtils.isEmpty(imageWidth)) {
			imageWidth = "40";
		}
		if (StringUtils.isEmpty(imageHeight)) {
			imageHeight = "40";
		}
		if (StringUtils.isNotEmpty(saveFolder)) {
			savePath = savePath  + saveFolder;
		}

		if (!savePath.endsWith("/")) {
			savePath += "/";
		}

		File f = new File(savePath);
		if (!f.exists()) {
			f.mkdirs();
		}

		String fileName = uploadImg.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf("."));
		String name = UUID.randomUUID().toString().replaceAll("-","");
		String filePath = savePath + name + extName;
		File file = new File(filePath);
		try {
			ImageUtil
					.writeImage(uploadImg.getInputStream(), file,
							Integer.parseInt(imageWidth),
							Integer.parseInt(imageHeight));

		} catch (IOException e) {
			LOG.error("保存文件失败！", e);
			rsp.setMessage("上传失败，后台错误");
			return rsp;
		}

		rsp.setStatus(Constant.RESPONSE_SUCCESS);
		rsp.setMessage("上传成功");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("picUrl", filePath.substring(filePath.indexOf("/uploads")));
		rsp.setData(data);
		return rsp;
	}
	
	/**
	 * 单文件上传
	 * @param uploadImg
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "uploadFile.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO uploadFile(@RequestParam MultipartFile uploadFile,
			HttpServletRequest request, HttpServletResponse response) {
		CommonVO rsp = new CommonVO();
		checkDangerFile(uploadFile);
		if (uploadFile.isEmpty()) {
			rsp.setMessage("上传失败，上传文件不能为空");
			return rsp;
		}

//		String savePath = request.getSession().getServletContext()
//				.getRealPath("/");
		String savePath = DISK_PATH;
		savePath += "/uploads";
		String saveFolder = request.getParameter("saveFolder");

		if (StringUtils.isNotEmpty(saveFolder)) {
			savePath = savePath  + saveFolder;
		}

		if (!savePath.endsWith("/")) {
			savePath += "/";
		}

		File f = new File(savePath);
		if (!f.exists()) {
			f.mkdirs();
		}

		String fileName = uploadFile.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf("."));
		String name = UUID.randomUUID().toString().replaceAll("-","");
		String filePath = savePath + name + extName;
		File file = new File(filePath);
		try {
			FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), file);
		} catch (IOException e) {
			LOG.error("保存文件失败！", e);
			rsp.setMessage("上传失败，后台错误");
			return rsp;
		}

		rsp.setStatus(Constant.RESPONSE_SUCCESS);
		rsp.setMessage("上传成功");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("fileUrl", filePath.substring(filePath.indexOf("/uploads")));
		rsp.setData(data);
		return rsp;
	}	
}