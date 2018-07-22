package cn.wk.association.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.wk.association.dao.ActivityDao;
import cn.wk.association.entity.Activity;

@Controller
public class NewsController {
	@Autowired
	private ActivityDao activityDao;

	@RequestMapping("getAllAssociationSocietiesNews.do")
	public void getAllAssociationSocietiesNews(HttpSession session) {
		System.out.println("Begin-----------getAllAssociationSocietiesNews-------------");
		List<Activity> newss = activityDao.getAllAssociationSocietiesNews();
		for (int i = 0; i < newss.size(); i++) {
			System.out.println(newss.get(i));
		}
		System.out.println("End-------------getAllAssociationSocietiesNews-------------");
		session.setAttribute("newsList", newss);
	}

	@RequestMapping("getNewsActivity.do")
	public String getNewsActivity(String id, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Begin-----------getNewsActivity-------------");
		// System.out.println("id="+id);
		Activity a = activityDao.getNewsActivity(id);
		System.out.println(a);
		System.out.println("End-------------getNewsActivity-------------");
		request.setAttribute("returnNews", a);
		return "forward:/showNews.jsp";
	}

	@RequestMapping("showAllNews.do")
	public String showAllNews(String id, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// 得到社团所有新闻
		System.out.println("Begin-----------getAllAssociationNews-------------");
		List<Activity> newss = activityDao.getAllAssociationNews(id);
		for (int i = 0; i < newss.size(); i++) {
			System.out.println(newss.get(i));
		}
		System.out.println("End-------------getAllAssociationNews-------------");
		request.setAttribute("returnNewsList", newss);
		return "forward:/showAllNews.jsp";
	}

	@RequestMapping("showAllActivitys.do")
	public String showAllActivitys(String id, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// 得到社团所有活动
		System.out.println("Begin-----------getAllAssociationActivitys-------------");
		List<Activity> activitys = activityDao.getAllAssociationActivitys(id);
		for (int i = 0; i < activitys.size(); i++) {
			System.out.println(activitys.get(i));
		}
		System.out.println("End-------------getAllAssociationActivitys-------------");
		request.setAttribute("returnActivityList", activitys);
		return "forward:/showAllActivitys.jsp";
	}

//	@RequestMapping("doAddNews.do")
//	public String doAddNews(String id, String title, String content) {
//		// String id, String association_id, Date releaseDate, String title, String
//		// content, String headpic,String type
//		String newsId = UUID.randomUUID().toString();
//		System.out.println("news=" + newsId + "assoId=" + id + "title=" + title + "content=" + content);
//		Activity a = new Activity(newsId, id, new Date(), title, content, null, "新闻");
//		System.out.println(a);
//		return "forward:/addNews.jsp";
//	}
	@RequestMapping(value = "doAddNews.do", method = RequestMethod.POST)
	public String doAddNewsPic(HttpServletRequest request) {
		// 要接收的内容
		String title = "";
		String content = "";
		String associationId="";
		String headpic ="";
		// 上传路径
		File uploadPath = new File(request.getServletContext().getRealPath("images/news"));
		// 当文件过大时，需要设置一个临时路径
		File tempPath = new File(request.getServletContext().getRealPath("temp"));
		 System.out.println("uploadPath=====" + uploadPath);
		// 如果目录不存在
		if (!uploadPath.exists()) {
			// 创建目录
			uploadPath.mkdir();
		}
		// 临时目录
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 内存存储的最大值
		factory.setSizeThreshold(4096);
		factory.setRepository(tempPath);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置文件上传大小
		upload.setSizeMax(1000000 * 20);
		try {
			System.out.println("test1-------------");
			List<FileItem> fileItems = upload.parseRequest(request);
			System.out.println(fileItems.size());
			for (FileItem item : fileItems) {
				// = (FileItem) iter.next();

				System.out.println("test2-------------");
				// 是普通的表单输入域
				if (item.isFormField()) {
					if ("title".equals(item.getFieldName())) {
						title = item.getString("UTF-8");
					}
					if ("content".equals(item.getFieldName())) {
						content = item.getString("UTF-8");
					}
					if ("associationId".equals(item.getFieldName())) {
						associationId = item.getString("UTF-8");
					}
				}
				// 是否为input="type"输入域
				if (!item.isFormField()) {
					String fileName = item.getName();
					long size = item.getSize();
					if ((fileName == null || fileName.equals("")) && size == 0) {
						continue;
					}
					// 截取字符串 如：C:\WINDOWS\Debug\PASSWD.LOG
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
					// item.write(new File(uploadPath + itemNo + ".gif"));
					System.out.println("fileName=" + fileName);
					// item.write(new File(uploadPath, title + ".gif"));
					// 创建输入流和文件输出流
					InputStream in = item.getInputStream();
					headpic=UUID.randomUUID() + ".jpg";
					FileOutputStream out = new FileOutputStream(uploadPath + "/"+headpic);
					headpic="images/news/" + headpic;
					// 设置每次读取的大小
					byte buffer[] = new byte[1024];
					// 实际读取的长度
					int len = 0;
					// 将输入流写入输出流
					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					// 关闭输入流和文件输出流
					in.close();
					out.close();
					// 删除临时文件
					item.delete();
				}
			}
			// 重定向页面
			// res.sendRedirect(req.getContextPath() + "/servlet/item/SearchItemServlet");
			String newsId = UUID.randomUUID().toString();
			System.out.println("news=" + newsId + "associationId=" + associationId + "title=" + title + "content=" + content);
//			Activity a = new Activity(newsId, associationId, new Date(), title, content, headpic, "新闻");
			boolean flag=activityDao.addActivity(newsId, associationId, new Date(), title, content, headpic, "新闻");
			System.out.println("添加新闻："+flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/showAllNews.do?id="+associationId;
	}
	@RequestMapping(value = "doAddActivity.do", method = RequestMethod.POST)
	public String doAddActivity(HttpServletRequest request) {
		// 要接收的内容
		String title = "";
		String content = "";
		String associationId="";
		String headpic ="";
		// 上传路径
		File uploadPath = new File(request.getServletContext().getRealPath("images/activity"));
		// 当文件过大时，需要设置一个临时路径
		File tempPath = new File(request.getServletContext().getRealPath("temp"));
		// System.out.println("uploadPath=====" + uploadPath);
		// 如果目录不存在
		if (!uploadPath.exists()) {
			// 创建目录
			uploadPath.mkdir();
		}
		// 临时目录
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 内存存储的最大值
		factory.setSizeThreshold(4096);
		factory.setRepository(tempPath);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置文件上传大小
		upload.setSizeMax(1000000 * 20);
		try {
			System.out.println("test1-------------");
			List<FileItem> fileItems = upload.parseRequest(request);
			System.out.println(fileItems.size());
			for (FileItem item : fileItems) {
				// = (FileItem) iter.next();
				System.out.println("test2-------------");
				// 是普通的表单输入域
				if (item.isFormField()) {
					if ("title".equals(item.getFieldName())) {
						title = item.getString("UTF-8");
					}
					if ("content".equals(item.getFieldName())) {
						content = item.getString("UTF-8");
					}
					if ("associationId".equals(item.getFieldName())) {
						associationId = item.getString("UTF-8");
					}
				}
				// 是否为input="type"输入域
				if (!item.isFormField()) {
					String fileName = item.getName();
					long size = item.getSize();
					if ((fileName == null || fileName.equals("")) && size == 0) {
						continue;
					}
					// 截取字符串 如：C:\WINDOWS\Debug\PASSWD.LOG
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
					// item.write(new File(uploadPath + itemNo + ".gif"));
					System.out.println("fileName=" + fileName);
					// item.write(new File(uploadPath, title + ".gif"));
					// 创建输入流和文件输出流
					InputStream in = item.getInputStream();headpic=UUID.randomUUID() + ".jpg";
					FileOutputStream out = new FileOutputStream(uploadPath + "/"+headpic);
					headpic="images/activity/" + headpic;
					// 设置每次读取的大小
					byte buffer[] = new byte[1024];
					// 实际读取的长度
					int len = 0;
					// 将输入流写入输出流
					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					// 关闭输入流和文件输出流
					in.close();
					out.close();
					// 删除临时文件
					item.delete();
				}
			}
			// 重定向页面
			// res.sendRedirect(req.getContextPath() + "/servlet/item/SearchItemServlet");
			String newsId = UUID.randomUUID().toString();
			System.out.println("news=" + newsId + "associationId=" + associationId + "title=" + title + "content=" + content);
//			Activity a = new Activity(newsId, associationId, new Date(), title, content, headpic, "新闻");
			activityDao.addActivity(newsId, associationId, new Date(), title, content, headpic, "活动");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/showAllActivitys.do?id="+associationId;
	}
}
