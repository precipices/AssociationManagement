package cn.wk.association.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cn.wk.association.entity.Activity;

@Component
public class ActivityDaoImpl implements ActivityDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	private class ActivityMapper implements RowMapper<Activity> {
		@Override
		public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Activity(rs.getString("id"), rs.getString("association_id"), rs.getTimestamp("release_date"),
					rs.getString("title"), rs.getString("content").replaceAll("\n", "<br/>"), rs.getString("headpic"),
					rs.getString("type"));
		}
	}

	private class SimpleActivityMapper implements RowMapper<Activity> {
		@Override
		public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Activity(rs.getString("id"), rs.getString("association_id"), rs.getTimestamp("release_date"),
					rs.getString("title"), rs.getString("headpic"), rs.getString("type"));
		}
	}

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Activity> getAllActivitysAndNews() {
		return jdbcTemplate.query("select * from activity order by release_date desc", new ActivityMapper());
	}

	@Override
	public List<Activity> getAllAssociationSocietiesNews() {
		return jdbcTemplate.query(
				"select id ,association_id ,release_date ,title,headpic,type from activity where type='新闻' and association_id='0' order by release_date desc",
				new SimpleActivityMapper());
	}

	@Override
	public List<Activity> getAllAssociationSocietiesActivitys() {
		return jdbcTemplate.query(
				"select id ,association_id ,release_date ,title,headpic,type from activity where type='活动' and association_id='0' order by release_date desc",
				new ActivityMapper());
	}

	@Override
	public Activity getNewsActivity(String id) {
		return jdbcTemplate.queryForObject("select * from activity where id = ?", new ActivityMapper(), id);
	}

	@Override
	public List<Activity> getAllAssociationNews(String associationId) {
		return jdbcTemplate.query(
				"select id ,association_id ,release_date ,title,headpic,type from activity where type='新闻' and association_id=? order by release_date desc",
				new SimpleActivityMapper(), associationId);
	}

	@Override
	public List<Activity> getAllAssociationActivitys(String associationId) {
		return jdbcTemplate.query(
				"select id ,association_id ,release_date ,title,headpic,type from activity where type='活动' and association_id=? order by release_date desc",
				new SimpleActivityMapper(), associationId);
	}

	@Override
	public boolean addActivity(String id, String association_id, Date releaseDate, String title, String content,
			String headpic, String type) {
		try {
			// 执行插入操作
			int result = jdbcTemplate.update(
					"insert into activity (id,association_id ,release_date,title,content,headpic,type) values (?,?,?,?,?,?,?)", id,
					association_id, releaseDate, title, content,headpic, type);
			// 判断是否插入成功
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
