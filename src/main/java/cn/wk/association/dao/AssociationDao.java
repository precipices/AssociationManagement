package cn.wk.association.dao;

import java.util.List;

import javax.sql.DataSource;

import cn.wk.association.entity.Association;
import cn.wk.association.entity.Customer;
import cn.wk.association.entity.Relationship;

public interface AssociationDao {
	/**
	 * ��������Դ
	 */
	void setDataSource(DataSource dataSource);

	/**
	 * �õ��������ŵ���Ϣ
	 * 
	 * @return û������ʱ,���ص�List��sizeΪ0,�����Ƿ���һ��null
	 */
	List<Association> getAllAssociations();

	/**
	 * ����һ�����Ų������糤
	 * 
	 * @return �����и����ŷ���false
	 */
	boolean addAssociation(String id, String name, String type, String information, String headpic,
			String index_html, String leaderUsername);

	/**
	 * ɾ��һ������
	 * 
	 * @return û�п�ɾ��������ʱ����false
	 */
	boolean deleteAssociation(String id);

	/**
	 * ������������ѯһ�����ŵ���ϸ��Ϣ
	 * 
	 * @param name ������
	 * @return ����Association,һ�����ŵ�ʵ���࣬����null��ʾ������������
	 */
	Association getAssociation(String name);

	/**
	 * ����ѧ�Ų�ѯһ�����ŵ���ϸ��Ϣ
	 * 
	 * @param id
	 *            ѧ��
	 * @return ����Association,һ�����ŵ�ʵ���࣬����null��ʾѧ�Ų�����
	 */
	Association getAssociationById(String id);
	/**
	 * �õ��û��������������
	 * @param username
	 * @return û������ʱ,���ص�List��sizeΪ0,�����Ƿ���һ��null
	 */
	List<Association> getMyAssociations(String username);
	/**
	 * �õ��û������ŵĹ�ϵ
	 * @param username �û��� 
	 * @param associationId ����id
	 * @return ����һ����ϵ��ʵ����
	 */
	Relationship getMyRelationship(String username,String associationId);
	/**
	 * ������һ������
	 * 
	 * @return �����и����ŷ���false
	 */
	boolean addAssociationOnly(String id, String name, String type, String information, String headpic, String index_html);
	/**
	 * �޸���������
	 * @return ʧ�ܷ���false
	 */
	boolean updateAssociation(String id, String name, String type, String information);
	/**
	 * ѧ���������һ������
	 * @param username �û���
	 * @param associationId ����id
	 * @return ʧ�ܷ���false
	 */
	boolean addRelationship(String username,String associationId);
}
