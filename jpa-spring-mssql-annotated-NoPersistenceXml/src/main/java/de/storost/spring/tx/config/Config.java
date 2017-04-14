package de.storost.spring.tx.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.storost.spring.tx.HelloWorld;

@Configuration
@ComponentScan({"de.storost.spring.tx.dao.impl","de.storost.spring.tx.user.impl"})
//@PropertySource("classpath:app.properties")
//@ComponentScan(basePackageClasses = {ExampleController.class, ExampleModel.class, ExmapleView.class})
@EnableTransactionManagement
public class Config {
	
	//To resolve ${} in @Value
	//http://shengwangi.blogspot.de/2015/07/how-to-set-dev-test-prod-in-spring-javaconfig.html
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		
		Resource resource;
	    String activeProfile;
		
	    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	    //configurer.setIgnoreUnresolvablePlaceholders(true);
	    //configurer.setIgnoreResourceNotFound(true);
		
		 // get active profile
	    activeProfile = System.getProperty("spring.profiles.active");
	 
	    // choose different property files for different active profile
	    if ("development".equals(activeProfile)) {
	      resource = new ClassPathResource("/META-INF/development.properties");
	    } else if ("test".equals(activeProfile)) {
	      resource = new ClassPathResource("/META-INF/test.properties");
	    } else {
	      resource = new ClassPathResource("/META-INF/production.properties");
	    }
	     
	    // load the property file
	    propertySourcesPlaceholderConfigurer.setLocation(resource);
		
	    return propertySourcesPlaceholderConfigurer;
    }
	
	
	@Bean
	public DataSource dataSource(){
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=DBLIC");
	    dataSource.setUsername("sa");
	    dataSource.setPassword("sasql");
	    dataSource.setDefaultAutoCommit(false);
	    //http://www.programcreek.com/java-api-examples/index.php?source_dir=JMaNGOS-master/Auth/src/main/java/org/jmangos/auth/module/AuthModule.java
	    //dataSource.setMinPoolSize(this.databaseConfig.ACCOUNT_DATABASE_CONNECTIONS_MIN); 
        //dataSource.setMaxPoolSize(this.databaseConfig.ACCOUNT_DATABASE_CONNECTIONS_MAX);
	    return new TransactionAwareDataSourceProxy(dataSource);
	}
	
	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.SQLServerDialect");
		jpaVendorAdapter.setShowSql(true);
		//jpaVendorAdapter.setGenerateDdl(true);
		return jpaVendorAdapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan("de.storost.spring.tx.model");
		return entityManagerFactory;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager =  new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
		return new AutowiredAnnotationBeanPostProcessor();
	}
	
	

}
