package com.pj.multipledatabasesdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.pj.multipledatabasesdemo.repository.project",
		entityManagerFactoryRef = "projectEntityManager",
		transactionManagerRef = "projectTransactionManager")
public class ProjectJpaAutoConfiguration
{
	@Bean
	@ConfigurationProperties(prefix = "spring.second-datasource")
	public DataSource projectDataSource()
	{
		return DataSourceBuilder.create().build();
	}

	@Bean
	public PlatformTransactionManager projectTransactionManager()
	{

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(projectEntityManager().getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean projectEntityManager()
	{
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(projectDataSource());
		em.setPackagesToScan("com.pj.multipledatabasesdemo.domain.project");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		em.setJpaPropertyMap(properties);

		return em;
	}
}
