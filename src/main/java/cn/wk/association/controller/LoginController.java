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
			response.getWriter().write("用户名或密码错误！");
		}else {
			session.setAttribute("customer", customer);
			System.out.println("Begin-----------getAllAssociationSocietiesNews-------------");
			List<Activity> newss = activityDao.getAllAssociationSocietiesNews();
			for (int i = 0; i < newss.size(); i++) {
				System.out.println(newss.get(i));
			}
			System.out.println("End-------------getAllAssociationSocietiesNews-------------");
			session.setAttribute("newsList", newss);
			response.getWriter().write("登陆成功！");
//			return "forward:/index.jsp";
		}
	}
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	public void register(String username, String password,String id,String realname,String classname,String contact, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
//		response.setHeader("Content-type", "text/html;charset=UTF-8");
		System.out.println("接收到的数据:username="+username+"	password="+password+"	id="+id+"	realname="+realname+"	classname="+classname+"	contact="+contact);
		Customer c=customerDao.getCustomer(username);
		if(c!=null) {
			System.out.println("用户名已被注册:"+c);
			response.getWriter().write("用户名已被注册!");
			return;
		}
		c=customerDao.getCustomerById(id);
		if(c!=null) {
			System.out.println("学号已被注册:"+c);
			response.getWriter().write("学号已被注册!");
			return;
		}
		if(customerDao.addCustomer(username, password, id, realname, classname, "学生", contact, null)) {
			response.getWriter().write("注册成功!");
			return;
		}else {
			response.getWriter().write("注册失败!");
			return;
		}
	}
	@RequestMapping(value="exit.do")
	public String exit(HttpSession session) {
		session.invalidate();
		return "redirect:/login.html";
	}
}
