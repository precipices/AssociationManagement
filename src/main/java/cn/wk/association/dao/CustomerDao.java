package cn.wk.association.dao;

import java.util.List;

import javax.sql.DataSource;

import cn.wk.association.entity.Customer;

public interface CustomerDao {
	/**
	 * 配置数据源
	 */
	void setDataSource(DataSource dataSource);
	/**
	 * 得到所有用户的信息
	 * @return 没有用户时,返回的List的size为0,而不是返回一个null
	 */
	List<Customer> getAllCustomers();
	/**
	 * 增加一个用户
	 * @return 若已有该用户返回false
	 */
	public boolean addCustomer(String username, String password, String id, String realname, String classname, String usertype,
			String contact, String headpic);
	/**
	 * 删除一个用户
	 * @return 没有可删除的用户时返回false
	 */
	public boolean deleteCustomer(String username);
	/**
	 * 根据用户名查询一个用户的详细信息
	 * @param username 用户名
	 * @return	返回Customer,一个用户的实体类，返回null表示用户名不存在
	 */
	public Customer getCustomer(String username);
	/**
	 * 根据学号查询一个用户的详细信息
	 * @param id 学号
	 * @return	返回Customer,一个用户的实体类，返回null表示学号不存在
	 */
	public Customer getCustomerById(String id);
	/**
	 * 判断用户名和密码是否正确
	 * @param username 用户名
	 * @param password 密码
	 * @return 正确则返回该用户的实体类，用户名或密码不正确返回null
	 */
	public Customer checkPassword(String username,String password);
	/**
	 * 得到社团待审核的学生名单
	 * @param associationId 社团id
	 * @return 返回用户的实体类List
	 */
	List<Customer> getVerifyCustomers(String associationId);
	/**
	 * 成员审核，将待审核成员的关系改为普通成员
	 * @return 失败返回false
	 */
	boolean verifyCustomer(String associationId,String username);
}
