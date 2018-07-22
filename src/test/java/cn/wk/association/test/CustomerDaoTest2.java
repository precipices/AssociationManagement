package cn.wk.association.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.wk.association.config.DaoConfig;
import cn.wk.association.dao.CustomerDao;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=DaoConfig.class)
public class CustomerDaoTest2 {
//	@Autowired
//	public CustomerDao customerDao;
//	@Test
//	public void testCustomer() {
//		System.out.println(customerDao.getAllCustomer());
//	}
	public static void main(String[] args) {
    	ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
    	CustomerDao cd=context.getBean(CustomerDao.class);
    	System.out.println(cd.getAllCustomers());
	}
}
