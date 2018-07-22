package cn.wk.association.test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wk.association.config.DaoConfig;
import cn.wk.association.dao.ActivityDao;
import cn.wk.association.entity.Activity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class ActivityDaoTest {
	@Autowired
	public ActivityDao newsDao;

	@Test
	public void getAllActivitys() {
		System.out.println("Begin-----------getAllNewss-------------");
		List<Activity> newss = newsDao.getAllActivitysAndNews();
		for (int i = 0; i < newss.size(); i++) {
			System.out.println(newss.get(i));
		}
		System.out.println("End-------------getAllNewss-------------");
	}

	@Test
	public void getAllAssociationSocietiesNews() {
		System.out.println("Begin-----------getAllAssociationSocietiesNews-------------");
		List<Activity> newss = newsDao.getAllAssociationSocietiesNews();
		for (int i = 0; i < newss.size(); i++) {
			System.out.println(newss.get(i));
		}
		System.out.println("End-------------getAllAssociationSocietiesNews-------------");
	}

	@Test
	public void getAllAssociationSocietiesActivitys() {
		System.out.println("Begin-----------getAllAssociationSocietiesActivitys-------------");
		List<Activity> newss = newsDao.getAllAssociationSocietiesActivitys();
		for (int i = 0; i < newss.size(); i++) {
			System.out.println(newss.get(i));
		}
		System.out.println("End-------------getAllAssociationSocietiesActivitys-------------");
	}

	@Test
	public void getNewsActivity() {
		System.out.println("Begin-----------getNewsActivity-------------");
		System.out.println(newsDao.getNewsActivity("9E575173-621F-4161-A3B2-C349AABF7B2C"));
		System.out.println("End-------------getNewsActivity-------------");
	}

	@Test
	public void addActivity() {
		System.out.println("Begin-----------addActivity-------------");
		String newsId = UUID.randomUUID().toString();
		System.out.println(newsDao.addActivity(newsId, "1", new Date(), "123", "372908sdfa", "images/news/3.jpg", "新闻"));
		System.out.println("End-------------addActivity-------------");
	}
	// @Test
	// public void addCustomer() {
	// System.out.println("Begin-----------addCustomer-------------");
	// System.out.println(customerDao.addCustomer("z5", "123", "1512480305", "张五",
	// "计科一班", "管理员", "15800857205", null));
	// System.out.println("End-------------addCustomer-------------");
	// }
	// @Test
	// public void deleteCustomer() {
	// System.out.println("Begin-----------deleteCustomer-------------");
	// System.out.println(customerDao.deleteCustomer("z5"));
	// System.out.println("End-------------deleteCustomer-------------");
	// }
	// @Test
	// public void getCustomer() {
	// System.out.println("Begin-----------getCustomer-------------");
	// System.out.println(customerDao.getCustomer("z5"));
	// System.out.println("End-------------getCustomer-------------");
	// }
	// @Test
	// public void checkPassword() {
	// System.out.println("Begin-----------checkPassword-------------");
	// System.out.println(customerDao.checkPassword("z5","12"));
	// System.out.println("End-------------checkPassword-------------");
	// }
	// @Test
	// public void order() {
	// getAllCustomers();
	// addCustomer();
	// getCustomer();
	// checkPassword();
	// getAllCustomers();
	// deleteCustomer();
	// }
}
