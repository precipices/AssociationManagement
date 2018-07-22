package cn.wk.association.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.wk.association.dao.ActivityDao;
import cn.wk.association.dao.CustomerDao;
import cn.wk.association.entity.Activity;
import cn.wk.association.entity.Customer;

@Controller
public class LoginController {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ActivityDao activityDao;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public void login(String username, String password, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
//		response.setHeader("Content-type", "text/html;charset=UTF-8");
		System.out.println("Begin-----------getAllCustomers-------------");
		List<Customer> customers = customerDao.getAllCustomers();
		for (Customer c : customers) {
			System.out.println(c);
		}
		System.out.println("End-------------getAllCustomers-------------");
		System.out.println("username=" + username + "|password=" + password);
		Customer customer = customerDao.checkPassword(username, password);
		if (customer == null) {
			response.getWriter().write("�û������������");
		}else {
			session.setAttribute("customer", customer);
			System.out.println("Begin-----------getAllAssociationSocietiesNews-------------");
			List<Activity> newss = activityDao.getAllAssociationSocietiesNews();
			for (int i = 0; i < newss.size(); i++) {
				System.out.println(newss.get(i));
			}
			System.out.println("End-------------getAllAssociationSocietiesNews-------------");
			session.setAttribute("newsList", newss);
			response.getWriter().write("��½�ɹ���");
//			return "forward:/index.jsp";
		}
	}
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	public void register(String username, String password,String id,String realname,String classname,String contact, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
//		response.setHeader("Content-type", "text/html;charset=UTF-8");
		System.out.println("���յ�������:username="+username+"	password="+password+"	id="+id+"	realname="+realname+"	classname="+classname+"	contact="+contact);
		Customer c=customerDao.getCustomer(username);
		if(c!=null) {
			System.out.println("�û����ѱ�ע��:"+c);
			response.getWriter().write("�û����ѱ�ע��!");
			return;
		}
		c=customerDao.getCustomerById(id);
		if(c!=null) {
			System.out.println("ѧ���ѱ�ע��:"+c);
			response.getWriter().write("ѧ���ѱ�ע��!");
			return;
		}
		if(customerDao.addCustomer(username, password, id, realname, classname, "ѧ��", contact, null)) {
			response.getWriter().write("ע��ɹ�!");
			return;
		}else {
			response.getWriter().write("ע��ʧ��!");
			return;
		}
	}
	@RequestMapping(value="exit.do")
	public String exit(HttpSession session) {
		session.invalidate();
		return "redirect:/login.html";
	}
}
