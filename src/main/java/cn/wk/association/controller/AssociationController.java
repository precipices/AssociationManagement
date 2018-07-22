package cn.wk.association.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import cn.wk.association.dao.AssociationDao;
import cn.wk.association.dao.CustomerDao;
import cn.wk.association.entity.Activity;
import cn.wk.association.entity.Association;
import cn.wk.association.entity.Customer;
import cn.wk.association.entity.Relationship;

@Controller
public class AssociationController {
	@Autowired
	private AssociationDao associationDao;
	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private CustomerDao customerDao;

	@RequestMapping("showAllAssociations.do")
	public String getAllAssociations(HttpSession session) {
		System.out.println("Begin-----------getAllAssociations-------------");
		List<Association> associations = associationDao.getAllAssociations();
		for (int i = 0; i < associations.size(); i++) {
			System.out.println(associations.get(i));
		}
		System.out.println("End-------------getAllAssociations-------------");
		session.setAttribute("associationList", associations);
		return "forward:/showAllAssociations.jsp";
	}

	@RequestMapping("getAssociation.do")
	public String getAssociationById(String id, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Begin-----------getAssociationById-------------");
		Association a = associationDao.getAssociationById(id);
		System.out.println(a);
		System.out.println("End-------------getAssociationById-------------");
		request.setAttribute("returnAssociation", a);
		return "forward:/showAssociation.jsp";
	}

	@RequestMapping("showMyAssociations.do")
	public String getMyAssociations(HttpSession session) {
		System.out.println("Begin-----------getMyAssociations-------------");
		Customer c = (Customer) session.getAttribute("customer");
		List<Association> associations = associationDao.getMyAssociations(c.getUsername());
		for (int i = 0; i < associations.size(); i++) {
			System.out.println(associations.get(i));
		}
		System.out.println("End-------------getMyAssociations-------------");
		session.setAttribute("myAssociationList", associations);
		return "forward:/showMyAssociations.jsp";
	}

	@RequestMapping("getMyAssociation.do")
	public String getMyAssociation(String id, HttpServletRequest request, HttpSession session)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// 得到社团信息
		System.out.println("Begin-----------getAssociationById-------------");
		Association a = associationDao.getAssociationById(id);
		System.out.println(a);
		System.out.println("End-------------getAssociationById-------------");
		request.setAttribute("returnAssociation", a);
		// 得到社团所有新闻
		System.out.println("Begin-----------getAllAssociationNews-------------");
		List<Activity> newss = activityDao.getAllAssociationNews(id);
		for (int i = 0; i < newss.size(); i++) {
			System.out.println(newss.get(i));
		}
		System.out.println("End-------------getAllAssociationNews-------------");
		request.setAttribute("returnNewsList", newss);
		// 得到社团所有活动
		System.out.println("Begin-----------getAllAssociationActivitys-------------");
		List<Activity> activitys = activityDao.getAllAssociationActivitys(id);
		for (int i = 0; i < activitys.size(); i++) {
			System.out.println(activitys.get(i));
		}
		System.out.println("End-------------getAllAssociationActivitys-------------");
		request.setAttribute("returnActivityList", activitys);
		// 得到用户与该社团的关系
		Customer customer = (Customer) session.getAttribute("customer");
		Relationship r = associationDao.getMyRelationship(customer.getUsername(), id);
		request.setAttribute("returnRelationship", r);
		return "forward:/showMyAssociation.jsp";
	}

	@RequestMapping("manageAssociations.do")
	public String manageAssociations(HttpServletRequest request, HttpSession session)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// 得到社团信息
		System.out.println("Begin-----------getAllAssociations-------------");
		List<Association> associations = associationDao.getAllAssociations();
		for (int i = 0; i < associations.size(); i++) {
			System.out.println(associations.get(i));
		}
		System.out.println("End-------------getAllAssociations-------------");
		session.setAttribute("associationList", associations);
		return "forward:/manageAssociations.jsp";
	}

	@RequestMapping("addNews.do")
	public String addNews(String id, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Begin-----------getAssociationById-------------");
		Association a = associationDao.getAssociationById(id);
		System.out.println(a);
		System.out.println("End-------------getAssociationById-------------");
		request.setAttribute("returnAssociation", a);
		return "forward:/addNews.jsp";
	}

	@RequestMapping("addActivity.do")
	public String addActivity(String id, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Begin-----------getAssociationById-------------");
		Association a = associationDao.getAssociationById(id);
		System.out.println(a);
		System.out.println("End-------------getAssociationById-------------");
		request.setAttribute("returnAssociation", a);
		return "forward:/addActivity.jsp";
	}

	@RequestMapping(value = "doAddAssociation.do", method = RequestMethod.POST)
	public String doAddAssociation(String id, HttpServletRequest request) throws UnsupportedEncodingException {
		// 要接收的内容
		String name = "";
		String type = "";
		String information = "";
		String headpic = "";
		// 上传路径
		File uploadPath = new File(request.getServletContext().getRealPath("images/association"));
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
					if ("name".equals(item.getFieldName())) {
						name = item.getString("UTF-8");
					}
					if ("type".equals(item.getFieldName())) {
						type = item.getString("UTF-8");
					}
					if ("information".equals(item.getFieldName())) {
						information = item.getString("UTF-8");
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
					headpic = UUID.randomUUID() + ".jpg";
					FileOutputStream out = new FileOutputStream(uploadPath + "/" + headpic);
					headpic = "images/association/" + headpic;
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
			String associationId = UUID.randomUUID().toString();
			System.out.println("associationId=" + associationId + "name=" + name + "type=" + type + "information="
					+ information + "headpic=" + headpic);
			boolean flag = associationDao.addAssociationOnly(associationId, name, type, information, headpic, null);
			System.out.println("添加社团：" + flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/showAllAssociations.do";
	}

	@RequestMapping("manageAssociation.do")
	public String manageAssociation(String id, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Begin-----------getAssociationById-------------");
		Association a = associationDao.getAssociationById(id);
		System.out.println(a);
		System.out.println("End-------------getAssociationById-------------");
		request.setAttribute("returnAssociation", a);
		return "forward:/manageAssociation.jsp";
	}

	@RequestMapping("doChangeAssociation.do")
	public String doChangeAssociation(String id, String name, String type, String information,
			HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("id=" + id + "name=" + name + "type=" + type + "information=" + information);
		System.out.println("Begin-----------updateAssociation-------------");
		boolean a = associationDao.updateAssociation(id, name, type, information);
		System.out.println(a);
		System.out.println("End-------------updateAssociation-------------");
		return "redirect:/getAssociation.do?id=" + id;
	}

	@RequestMapping("joinus.do")
	public String joinus(String id, HttpServletRequest request, HttpSession session)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Begin-----------addRelationship-------------");
		Customer customer = (Customer) session.getAttribute("customer");
		boolean flag = associationDao.addRelationship(customer.getUsername(), id);
		System.out.println(flag);
		System.out.println("End-------------addRelationship-------------");
		return "redirect:/showMyAssociations.do";
	}

	// manageMember.do?id=${returnAssociation.id}
	@RequestMapping("manageMember.do")
	public String manageMember(String id, HttpServletRequest request, HttpSession session)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Begin-----------getAssociationById-------------");
		Association a = associationDao.getAssociationById(id);
		System.out.println(a);
		System.out.println("End-------------getAssociationById-------------");
		request.setAttribute("returnAssociation", a);

		System.out.println("Begin-----------getVerifyCustomers-------------");
		List<Customer> customers = customerDao.getVerifyCustomers(id);
		for (int i = 0; i < customers.size(); i++) {
			System.out.println(customers.get(i));
		}
		System.out.println("End-------------getVerifyCustomers-------------");
		request.setAttribute("returnCustomers", customers);
		return "forward:/manageMember.jsp";
	}
	//verifyCustomer.do?id=${customer.id}
	@RequestMapping("verifyCustomer.do")
	public String verifyCustomer(String id,String username, HttpServletRequest request, HttpSession session)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
//		System.out.println("Begin-----------getAssociationById-------------");
//		Association a = associationDao.getAssociationById(id);
//		System.out.println(a);
//		System.out.println("End-------------getAssociationById-------------");
//		request.setAttribute("returnAssociation", a);

		System.out.println("Begin-----------verifyCustomer-------------");
		boolean flag=customerDao.verifyCustomer(id, username);
		System.out.println(flag);
		System.out.println("End-------------verifyCustomer-------------");
		return "redirect:/manageMember.do?id="+id;
	}
}
