package cn.wk.association.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import cn.wk.association.entity.Activity;

public interface ActivityDao {
	/**
	 * 配置数据源
	 */
	void setDataSource(DataSource dataSource);

	/**
	 * 得到所有新闻或活动的信息
	 * 
	 * @return 没有新闻或活动时,返回的List的size为0,而不是返回一个null
	 */
	List<Activity> getAllActivitysAndNews();

	/**
	 * 得到社团联合会所有新闻
	 * 
	 * @return 没有新闻时,返回的List的size为0,而不是返回一个null
	 */
	List<Activity> getAllAssociationSocietiesNews();

	/**
	 * 得到社团联合会所有活动
	 * 
	 * @return 没有活动时,返回的List的size为0,而不是返回一个null
	 */
	List<Activity> getAllAssociationSocietiesActivitys();

	/**
	 * 根据新闻或公告id查询一个新闻或公告的详细信息
	 * 
	 * @param id
	 *            新闻或公告id
	 * @return 返回Activity,一个新闻或公告的实体类，返回null表示新闻或公告名不存在
	 */
	Activity getNewsActivity(String id);

	/**
	 * 得到社团所有新闻
	 * 
	 * @return 没有新闻时,返回的List的size为0,而不是返回一个null
	 */
	List<Activity> getAllAssociationNews(String associationId);

	/**
	 * 得到社团所有活动
	 * 
	 * @return 没有活动时,返回的List的size为0,而不是返回一个null
	 */
	List<Activity> getAllAssociationActivitys(String associationId);

	/**
	 * 添加一个新闻或活动
	 * 
	 * @return
	 */
	boolean addActivity(String id, String association_id, Date releaseDate, String title, String content,
			String headpic, String type);
}
