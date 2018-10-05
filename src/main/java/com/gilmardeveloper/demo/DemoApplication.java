package com.gilmardeveloper.demo;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@SpringBootApplication
public class DemoApplication  {
	
	
	@Bean
	public DataSource getDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		dataSource.setUser("root");
		dataSource.setPassword("toor");
		
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setJdbcUrl("jdbc:mysql://localhost/demo?createDatabaseIfNotExist=true&useUnicode=yes&characterEncoding=UTF-8");

		dataSource.setMinPoolSize(1);
		dataSource.setMaxPoolSize(10);
		dataSource.setNumHelperThreads(5);
		dataSource.setIdleConnectionTestPeriod(100);
		
		return dataSource;
	}
	
	public static void main(String[] args) throws Exception{
		SpringApplication.run(DemoApplication.class, args);
	}

	
}
