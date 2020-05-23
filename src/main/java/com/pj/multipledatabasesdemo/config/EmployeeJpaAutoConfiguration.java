package com.pj.multipledatabasesdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.pj.multipledatabasesdemo.repository.employee",
		entityManagerFactoryRef = "employeeEntityManager",
		transactionManagerRef = "employeeTransactionManager")
public class EmployeeJpaAutoConfiguration
{
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource employeeDataSource()
	{
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager employeeTransactionManager()
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(employeeEntityManager().getObject());
		return transactionManager;
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean employeeEntityManager()
	{
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(employeeDataSource());
		em.setPackagesToScan("com.pj.multipledatabasesdemo.domain.employee");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");

		em.setJpaPropertyMap(properties);


		return em;
	}
}
