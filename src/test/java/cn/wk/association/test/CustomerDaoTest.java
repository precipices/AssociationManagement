package cn.wk.association.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wk.association.config.DaoConfig;
import cn.wk.association.dao.CustomerDao;
import cn.wk.association.entity.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DaoConfig.class)
public class CustomerDaoTest {
	@Autowired
	public CustomerDao customerDao;
	@Test
	public void getAllCustomers() {
		System.out.println("Begin-----------getAllCustomers-------------");
		List<Customer> customers=customerDao.getAllCustomers();
		for(Customer c:customers) {
			System.out.println(c);
		}
		System.out.println("End-------------getAllCustomers-------------");
	}
	@Test
	public void addCustomer() {
		System.out.println("Begin-----------addCustomer-------------");
		System.out.println(customerDao.addCustomer("z5", "123", "1512480305", "张五", "计科一班", "管理员", "15800857205", null));
		System.out.println("End-------------addCustomer-------------");
	}
	@Test
	public void deleteCustomer() {
		System.out.println("Begin-----------deleteCustomer-------------");
		System.out.println(customerDao.deleteCustomer("z5"));
		System.out.println("End-------------deleteCustomer-------------");
	}
	@Test
	public void getCustomer() {
		System.out.println("Begin-----------getCustomer-------------");
		System.out.println(customerDao.getCustomer("z5"));
		System.out.println("End-------------getCustomer-------------");
	}
	@Test
	public void checkPassword() {
		System.out.println("Begin-----------checkPassword-------------");
		System.out.println(customerDao.checkPassword("z5","12"));
		System.out.println("End-------------checkPassword-------------");
	}
	@Test
	public void order() {
		getAllCustomers();
		addCustomer();
		getCustomer();
		checkPassword();
		getAllCustomers();
		deleteCustomer();
	}
}
