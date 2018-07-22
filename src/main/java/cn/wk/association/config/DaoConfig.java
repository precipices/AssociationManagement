package cn.wk.association.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import cn.wk.association.dao.CustomerDao;

@ComponentScan(basePackageClasses= CustomerDao.class)
@Configuration
public class DaoConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setUrl("jdbc:sqlserver://localhost:1433;databaseName=associationsystem");
		ds.setUsername("sa");
		ds.setPassword("sa");
		return ds;
	}
}
