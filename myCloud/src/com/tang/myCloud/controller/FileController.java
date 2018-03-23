package com.tang.myCloud.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tang.myCloud.bean.PageInfo;
import com.tang.myCloud.service.FileService;

@Controller
@RequestMapping("file")
public class FileController {
	@Resource(name="fileService")
	private FileService fileService;
	@RequestMapping("download.do")
	@ResponseBody
	public void download(String path, HttpServletRequest req,HttpServletResponse resp){
		try {
			path = URLDecoder.decode(path, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fileService.download(new File(path),
				req.getServletContext(), resp);
	}
	@RequestMapping(value="upload.do", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file,Model model, HttpServletRequest req) {
		if( file.getSize() > 50*1024*1024 ){
			model.addAttribute("uploadMsg", "文件大小不能超过50M");
		}
		else if( fileService.upload(file, req) ){
			// get root path from config param
			System.out.println("upload successful");
			//fileService.upload(request);
			model.addAttribute("uploadMsg", "上传成功");
		}
		else 
			model.addAttribute("uploadMsg", "上传失败");
		return "WEB-INF/jsp/myCloud";
	}
	@RequestMapping("delete.do")
	public String delete(String fileId, String path, Model model, HttpServletRequest request){
		if( path == null || path.trim().isEmpty())
			model.addAttribute("deleteMsgError", "路径错误");
		try {
			if( !fileService.deleteFile(request, Long.parseLong(fileId), new File((URLEncoder.encode(path, "utf-8")))))
				model.addAttribute("deleteMsgError", "文件不存在");
		} catch (NumberFormatException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "WEB-INF/jsp/myCloud";
			
	}
	@RequestMapping("changePage.do")
	public String changePage(String pageNum, HttpServletRequest request){
		HttpSession session = request.getSession();
		PageInfo info =  (PageInfo) session.getAttribute("pageInfo");
		if( info == null){
			info = fileService.getPageInfo(request);
			session.setAttribute("pageInfo", info);
		}
		if( pageNum != null && !pageNum.trim().isEmpty())
			info.setCurrPage(Integer.parseInt(pageNum));
		return "WEB-INF/jsp/myCloud";
	}
	@RequestMapping("refresh.do")
	public String refresh(HttpServletRequest req){
		PageInfo pageInfo = fileService.getPageInfo(req);
		if( pageInfo != null){
			HttpSession session = req.getSession();
			session.setAttribute("pageInfo", pageInfo);
		}
		return "WEB-INF/jsp/myCloud";
	}
}
