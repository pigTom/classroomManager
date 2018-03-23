package com.tang.myCloud.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tang.myCloud.bean.CloudFile;
import com.tang.myCloud.bean.PageInfo;
import com.tang.myCloud.dao.FileDao;

@Service("fileService")
public class FileService {
	@Resource(name = "fileDao")
	private FileDao dao;

	/**
	 * 上传文件，文件信息放入数据库并将文件存入磁盘
	 */
	public boolean upload(MultipartFile file, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {

			return false;
		}
		long id = Long.parseLong(userId);
		// store in disk
		String parent = request.getServletContext().getInitParameter(
				"parentPath")
				+ "/" + userId;
		File diskFile = saveFile(file, parent);
		if (diskFile == null)
			return false;

		// add file information to database
		CloudFile cloudFile = null;
		String path = diskFile.getParent().replaceAll("\\\\", "/");
		cloudFile = new CloudFile(diskFile.getName(),
				id, path, file.getSize(), new Date(diskFile.lastModified()));

		dao.addFile(cloudFile);
		// update file list view
		PageInfo info = dao.getFileInfo(id);
		session.setAttribute("pageInfo", info);
		return true;

	}

	public PageInfo getPageInfo(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("userId");

		if (id != null) {
			return dao.getFileInfo(Long.parseLong(id));
		}
		return null;
	}

	public boolean download(File file,ServletContext context, HttpServletResponse resp) {
		if (!file.exists())
			return false;
		resp.setCharacterEncoding("utf-8");
		String contentType = context.getMimeType(file.getName());
		resp.setContentType(contentType);
		try {
			resp.setHeader("Content-Disposition",
					"attachment; filename="+URLEncoder.encode(file.getName(), "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			
			
//			/*byte[] b = new byte[1024];
//			int len = 0;
//			while ((len = in.read(b)) != -1) {
//				out.write(b, 0, len);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		} finally {
			OutputStream out = resp.getOutputStream();
			out.flush();
			org.apache.commons.io.IOUtils.copy(in, out );

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}

	public boolean deleteFile(HttpServletRequest request, long fileId, File file) {
		HttpSession session = request.getSession();
		// 删除pageInfo中的信息

		PageInfo info = (PageInfo) session.getAttribute("pageInfo");

		info.deleteById(fileId);
		// 从数据库中删除文件信息
		if (!dao.deleteFile(fileId))
			return false;

		// // 是否删除物理数据
		if (file == null || !file.exists())
			return false;
		if (!file.delete())
			return false;
		return true;
	}

	private File saveFile(MultipartFile part, String parent) {
		File folder = new File(parent);
		File file = null;
		boolean flag = true;
		if (!folder.exists()) {
			System.out.println("file is not exists");
			flag = folder.mkdir();
			System.out.println("parent file created: " + flag);
		}
		// 文件已经存在
		if (flag) {
			String filename = part.getOriginalFilename();
			file = new File(parent, filename);
			int count = 1;
			// 处理重名的问题
			while (file.exists()) {
				file = new File(parent, filename.substring(0,
						filename.indexOf("."))
						+ count++ + filename.substring(filename.indexOf(".")));
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				part.transferTo(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
}
