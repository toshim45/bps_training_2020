package com.bps.employee;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Bean(name = "mysqlDataSource")
	@ConfigurationProperties("mysql.datasource")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mysqlJdbcTemplate")
	public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean(name = "mssqlDataSource")
	@ConfigurationProperties("mssql.datasource")
	public DataSource mssqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mssqlJdbcTemplate")
	public JdbcTemplate mssqlJdbcTemplate(@Qualifier("mssqlDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
