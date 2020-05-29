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

	@Primary
	@Bean
	public PlatformTransactionManager projectTransactionManager(@Qualifier("projectEntityManager") EntityManagerFactory projectEntityManagerFactory)
	{
		return new JpaTransactionManager(projectEntityManagerFactory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean projectEntityManager(EntityManagerFactoryBuilder builder,
	                                                                   @Qualifier("projectDataSource") DataSource dataSource)
	{
		return builder
				.dataSource(dataSource)
				.packages("com.pj.multipledatabasesdemo.domain.project")
				.persistenceUnit("project")
				.build();
		/*LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(projectDataSource());
		em.setPackagesToScan("com.pj.multipledatabasesdemo.domain.project");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		em.setJpaPropertyMap(properties);

		return em;*/
	}
}
