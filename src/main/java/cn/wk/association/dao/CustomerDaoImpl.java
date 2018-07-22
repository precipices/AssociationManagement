package cn.wk.association.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cn.wk.association.entity.Customer;

@Component
public class CustomerDaoImpl implements CustomerDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	private class CustomerMapper implements RowMapper<Customer> {
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Customer(rs.getString("username"), rs.getString("password"), rs.getString("id"),
					rs.getString("realname"), rs.getString("classname"), rs.getString("usertype"),
					rs.getString("contact"), rs.getString("headpic"));
		}
	}

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return jdbcTemplate.query("select * from customer", new CustomerMapper());
	}

	@Override
	public boolean addCustomer(String username, String password, String id, String realname, String classname,
			String usertype, String contact, String headpic) {
		try {
			// 执行插入操作
			int result = jdbcTemplate.update(
					"insert into customer (username,password,id,realname,classname,usertype,contact,headpic) values(?,?,?,?,?,?,?,?)",
					username, password, id, realname, classname, usertype, contact, headpic);
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
	public boolean deleteCustomer(String username) {
		try {
			// 执行删除操作
			int result = jdbcTemplate.update("delete from customer where username = ? ", username);
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
	public Customer getCustomer(String username) {
		Customer c = null;
		try {
			c = jdbcTemplate.queryForObject("select * from customer where username = ?", new CustomerMapper(),
					username);
		} catch (Exception e) {
			return null;
		}
		return c;
	}

	@Override
	public Customer getCustomerById(String id) {
		Customer c = null;
		try {
			c = jdbcTemplate.queryForObject("select * from customer where id = ?", new CustomerMapper(),
					id);
		} catch (Exception e) {
			return null;
		}
		return c;
	}

	@Override
	public Customer checkPassword(String username, String password) {
		Customer c = null;
		try {
			c = jdbcTemplate.queryForObject("select * from customer where username = ? and password = ?",
					new CustomerMapper(), username, password);
		} catch (Exception e) {
			return null;
		}
		return c;
	}
	@Override
	public List<Customer> getVerifyCustomers(String associationId) {
		return jdbcTemplate.query(
				"select * from customer ,relationship where customer.username=relationship.username and relationship.association_id=? and position='待审核成员'",
				new CustomerMapper(), associationId);
	}

	@Override
	public boolean verifyCustomer(String associationId, String username) {
		try {
			// 执行更新操作
			int result = jdbcTemplate.update("update relationship set position='普通成员' where position='待审核成员' and username = ? and association_id=?", username,associationId);
			// 判断是否更新成功
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
