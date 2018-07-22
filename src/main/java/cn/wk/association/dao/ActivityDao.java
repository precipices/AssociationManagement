package cn.wk.association.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import cn.wk.association.entity.Activity;

public interface ActivityDao {
	/**
	 * ��������Դ
	 */
	void setDataSource(DataSource dataSource);

	/**
	 * �õ��������Ż�����Ϣ
	 * 
	 * @return û�����Ż�ʱ,���ص�List��sizeΪ0,�����Ƿ���һ��null
	 */
	List<Activity> getAllActivitysAndNews();

	/**
	 * �õ��������ϻ���������
	 * 
	 * @return û������ʱ,���ص�List��sizeΪ0,�����Ƿ���һ��null
	 */
	List<Activity> getAllAssociationSocietiesNews();

	/**
	 * �õ��������ϻ����л
	 * 
	 * @return û�лʱ,���ص�List��sizeΪ0,�����Ƿ���һ��null
	 */
	List<Activity> getAllAssociationSocietiesActivitys();

	/**
	 * �������Ż򹫸�id��ѯһ�����Ż򹫸����ϸ��Ϣ
	 * 
	 * @param id
	 *            ���Ż򹫸�id
	 * @return ����Activity,һ�����Ż򹫸��ʵ���࣬����null��ʾ���Ż򹫸���������
	 */
	Activity getNewsActivity(String id);

	/**
	 * �õ�������������
	 * 
	 * @return û������ʱ,���ص�List��sizeΪ0,�����Ƿ���һ��null
	 */
	List<Activity> getAllAssociationNews(String associationId);

	/**
	 * �õ��������л
	 * 
	 * @return û�лʱ,���ص�List��sizeΪ0,�����Ƿ���һ��null
	 */
	List<Activity> getAllAssociationActivitys(String associationId);

	/**
	 * ���һ�����Ż�
	 * 
	 * @return
	 */
	boolean addActivity(String id, String association_id, Date releaseDate, String title, String content,
			String headpic, String type);
}
