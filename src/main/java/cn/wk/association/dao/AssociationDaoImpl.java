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

import cn.wk.association.entity.Association;
import cn.wk.association.entity.Customer;
import cn.wk.association.entity.Relationship;

@Component
public class AssociationDaoImpl implements AssociationDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	private class RelationshipMapper implements RowMapper<Relationship> {
		@Override
		public Relationship mapRow(ResultSet rs, int rowNum) throws SQLException {
			Relationship r = new Relationship();
			r.setAssociationId(rs.getString("association_id"));
			r.setUsername(rs.getString("username"));
			r.setPosition(rs.getString("position"));
			r.setJoinTime(rs.getDate("join_time"));
			return r;
		}
	}

	private class AssociationMapper implements RowMapper<Association> {
		@Override
		public Association mapRow(ResultSet rs, int rowNum) throws SQLException {
			Association a = new Association();
			a.setId(rs.getString("id"));
			a.setName(rs.getString("name"));
			a.setInformation(rs.getString("information").replaceAll("\n", "<br/>"));
			a.setType(rs.getString("type"));
			a.setHeadpic(rs.getString("headpic"));
			a.setIndex_html(rs.getString("index_html"));
			List<Relationship> relationships = jdbcTemplate.query(
					"select * from relationship  where association_id=? and position!='待审核成员'",
					new RelationshipMapper(), rs.getString("id"));
			a.setRelationships(relationships);
			return a;
		}
	}

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Association> getAllAssociations() {
		return jdbcTemplate.query("select * from association", new AssociationMapper());
	}

	@Override
	public boolean addAssociation(String id, String name, String type, String information, String headpic,
			String index_html, String leaderUsername) {
		try {
			// 执行插入操作
			int result = jdbcTemplate.update(
					"insert into association (id,name ,type,information,headpic,index_html) values (?,?,?,?,?,?)", id,
					name, type, information, headpic, index_html);
			int result2 = jdbcTemplate.update(
					"insert into relationship (association_id,username,position,join_time) values(?,?,?,?)", id,
					leaderUsername, "社长", new Date());
			// 判断是否插入成功
			if (result > 0 && result2 > 0) {
				return true;
			} else {
				if (result <= 0) {
					jdbcTemplate.update("delete from association where id=?", id);
				}
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addAssociationOnly(String id, String name, String type, String information, String headpic,
			String index_html) {
		try {
			// 执行插入操作
			int result = jdbcTemplate.update(
					"insert into association (id,name ,type,information,headpic,index_html) values (?,?,?,?,?,?)", id,
					name, type, information, headpic, index_html);
			// 判断是否插入成功
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteAssociation(String id) {
		try {
			// 执行删除操作
			int result = jdbcTemplate.update("delete from association where id=?", id);
			// 判断是否删除成功
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Association getAssociation(String name) {
		Association a = null;
		try {
			a = jdbcTemplate.queryForObject("select * from association where name = ?", new AssociationMapper(), name);
		} catch (Exception e) {
			return null;
		}
		return a;
	}

	@Override
	public Association getAssociationById(String id) {
		Association a = null;
		try {
			a = jdbcTemplate.queryForObject("select * from association where id = ?", new AssociationMapper(), id);
		} catch (Exception e) {
			return null;
		}
		return a;
	}

	@Override
	public List<Association> getMyAssociations(String username) {
		return jdbcTemplate.query(
				"select * from association ,relationship where username=? and association.id=relationship.association_id and position!='待审核成员'",
				new AssociationMapper(), username);
	}

	@Override
	public Relationship getMyRelationship(String username, String associationId) {
		return jdbcTemplate.queryForObject("select * from relationship where username=? and association_id=?",
				new RelationshipMapper(), username, associationId);
	}

	@Override
	public boolean updateAssociation(String id, String name, String type, String information) {
		try {
			// 执行更新操作
			int result = jdbcTemplate.update("update association set name=? ,type=?,information=? where id=?", name,
					type, information, id);
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

	@Override
	public boolean addRelationship(String username, String associationId) {
		try {
			// 执行插入操作
			int result = jdbcTemplate.update(
					"insert into relationship (association_id,username,position,join_time) values(?,?,'待审核成员',?)",
					associationId, username, new Date());
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
