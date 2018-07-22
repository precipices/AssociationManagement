package cn.wk.association.dao;

import java.util.List;

import javax.sql.DataSource;

import cn.wk.association.entity.Association;
import cn.wk.association.entity.Customer;
import cn.wk.association.entity.Relationship;

public interface AssociationDao {
	/**
	 * 配置数据源
	 */
	void setDataSource(DataSource dataSource);

	/**
	 * 得到所有社团的信息
	 * 
	 * @return 没有社团时,返回的List的size为0,而不是返回一个null
	 */
	List<Association> getAllAssociations();

	/**
	 * 增加一个社团并设置社长
	 * 
	 * @return 若已有该社团返回false
	 */
	boolean addAssociation(String id, String name, String type, String information, String headpic,
			String index_html, String leaderUsername);

	/**
	 * 删除一个社团
	 * 
	 * @return 没有可删除的社团时返回false
	 */
	boolean deleteAssociation(String id);

	/**
	 * 根据社团名查询一个社团的详细信息
	 * 
	 * @param name 社团名
	 * @return 返回Association,一个社团的实体类，返回null表示社团名不存在
	 */
	Association getAssociation(String name);

	/**
	 * 根据学号查询一个社团的详细信息
	 * 
	 * @param id
	 *            学号
	 * @return 返回Association,一个社团的实体类，返回null表示学号不存在
	 */
	Association getAssociationById(String id);
	/**
	 * 得到用户参与的所有社团
	 * @param username
	 * @return 没有社团时,返回的List的size为0,而不是返回一个null
	 */
	List<Association> getMyAssociations(String username);
	/**
	 * 得到用户与社团的关系
	 * @param username 用户名 
	 * @param associationId 社团id
	 * @return 返回一个关系的实体类
	 */
	Relationship getMyRelationship(String username,String associationId);
	/**
	 * 仅增加一个社团
	 * 
	 * @return 若已有该社团返回false
	 */
	boolean addAssociationOnly(String id, String name, String type, String information, String headpic, String index_html);
	/**
	 * 修改社团数据
	 * @return 失败返回false
	 */
	boolean updateAssociation(String id, String name, String type, String information);
	/**
	 * 学生申请加入一个社团
	 * @param username 用户名
	 * @param associationId 社团id
	 * @return 失败返回false
	 */
	boolean addRelationship(String username,String associationId);
}
