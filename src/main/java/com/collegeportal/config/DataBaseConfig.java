package com.collegeportal.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({ "classpath:properties/persistence-jndi.properties"})
@EnableTransactionManagement
public class DataBaseConfig {
	Logger logger = LogManager.getLogger(DataBaseConfig.class);
	
	@Autowired
    private Environment env;
	
	//DataSource created using hardcoded values
	/*
	@Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jpa");
	      dataSource.setUsername( "tutorialuser" );
	      dataSource.setPassword( "tutorialmy5ql" );
	      return dataSource;
	   }
	*/
	
	@Bean
	public DataSource dataSource() throws NamingException {
		logger.info("DataSource context is loading ...");
		return (DataSource) new JndiTemplate().lookup(env.getProperty("jndi.mysql.name"));
	}
	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() 
      throws NamingException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.collegeportal.entity");
        em.setPersistenceUnitName("persistantUnit");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getJPAProperties());
        return em;
    }
	
	   @Bean
	   public PlatformTransactionManager transactionManager( EntityManagerFactory emf){
	       JpaTransactionManager transactionManager = new JpaTransactionManager();
	       transactionManager.setEntityManagerFactory(emf);
	       return transactionManager;
	   }
	
		@Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	       return new PersistenceExceptionTranslationPostProcessor();
	   } 
		
		public Properties getJPAProperties() {
			Properties jpaProperties = new Properties();
			jpaProperties.put("hibernate.show_sql", true);
	        jpaProperties.put("hibernate.format_sql", true);
	        return jpaProperties;
		}
}
