package cn.wk.association.dao;

import java.util.List;

import javax.sql.DataSource;

import cn.wk.association.entity.Customer;

public interface CustomerDao {
	/**
	 * ��������Դ
	 */
	void setDataSource(DataSource dataSource);
	/**
	 * �õ������û�����Ϣ
	 * @return û���û�ʱ,���ص�List��sizeΪ0,�����Ƿ���һ��null
	 */
	List<Customer> getAllCustomers();
	/**
	 * ����һ���û�
	 * @return �����и��û�����false
	 */
	public boolean addCustomer(String username, String password, String id, String realname, String classname, String usertype,
			String contact, String headpic);
	/**
	 * ɾ��һ���û�
	 * @return û�п�ɾ�����û�ʱ����false
	 */
	public boolean deleteCustomer(String username);
	/**
	 * �����û�����ѯһ���û�����ϸ��Ϣ
	 * @param username �û���
	 * @return	����Customer,һ���û���ʵ���࣬����null��ʾ�û���������
	 */
	public Customer getCustomer(String username);
	/**
	 * ����ѧ�Ų�ѯһ���û�����ϸ��Ϣ
	 * @param id ѧ��
	 * @return	����Customer,һ���û���ʵ���࣬����null��ʾѧ�Ų�����
	 */
	public Customer getCustomerById(String id);
	/**
	 * �ж��û����������Ƿ���ȷ
	 * @param username �û���
	 * @param password ����
	 * @return ��ȷ�򷵻ظ��û���ʵ���࣬�û��������벻��ȷ����null
	 */
	public Customer checkPassword(String username,String password);
	/**
	 * �õ����Ŵ���˵�ѧ������
	 * @param associationId ����id
	 * @return �����û���ʵ����List
	 */
	List<Customer> getVerifyCustomers(String associationId);
	/**
	 * ��Ա��ˣ�������˳�Ա�Ĺ�ϵ��Ϊ��ͨ��Ա
	 * @return ʧ�ܷ���false
	 */
	boolean verifyCustomer(String associationId,String username);
}
