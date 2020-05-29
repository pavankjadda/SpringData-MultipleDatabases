package com.pj.multipledatabasesdemo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
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
	public PlatformTransactionManager employeeTransactionManager(@Qualifier("employeeEntityManager")EntityManagerFactory employeeEntityManagerFactory)
	{
		return new JpaTransactionManager(employeeEntityManagerFactory);
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean employeeEntityManager(EntityManagerFactoryBuilder builder,
	                                                                    @Qualifier("employeeDataSource") DataSource dataSource)
	{
		return builder
				.dataSource(dataSource)
				.packages("com.pj.multipledatabasesdemo.domain.employee")
				.persistenceUnit("employee")
				.build();
		/*LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(employeeDataSource());
		em.setPackagesToScan("com.pj.multipledatabasesdemo.domain.employee");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");

		em.setJpaPropertyMap(properties);
		return em;*/
	}
}
