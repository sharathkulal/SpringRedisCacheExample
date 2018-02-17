/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author Sharath kulal
 */
public abstract class AbstractSpringJpaDataConfig {
    
    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws SQLException, IOException {
        Properties jdbcProperties = new Properties();
        InputStream is = getClass().getResourceAsStream("/local.properties");
        jdbcProperties.load(is);
        
        final String jdbcURL = jdbcProperties.getProperty("jdbc.url");
        final String driverName = jdbcProperties.getProperty("db.driverName");
        final String dbUser = jdbcProperties.getProperty("db.user");
        final String dbPassword = jdbcProperties.getProperty("db.password");
        final Integer initialConnections = Integer.valueOf(jdbcProperties.getProperty("db.initialConnections"));
        final Integer maxConnections = Integer.valueOf(jdbcProperties.getProperty("db.maxConnections"));
        final Integer loginTimeout = 5;

        final HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(jdbcURL);
        dataSource.setDriverClassName(driverName);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setMinimumIdle(initialConnections);
        dataSource.setMaximumPoolSize(maxConnections);
        dataSource.setConnectionTestQuery("select 1");
        dataSource.setConnectionTimeout(10000);
        dataSource.setLoginTimeout(loginTimeout);

        //dataSource.setIdleTimeout(10000);
        //dataSource.setAutoCommit(true);

        return dataSource;
    }
    
    @Bean( destroyMethod="close" )
    public EntityManagerFactory entityManagerFactory() throws SQLException, IOException { 
       
      final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

      final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setJpaVendorAdapter(vendorAdapter);
            
      factory.setPackagesToScan(getEntityPackagesToScan());
      factory.setDataSource(dataSource());
      factory.afterPropertiesSet();

      return factory.getObject();
    }
    
    /*@Bean
    @Scope("prototype")
    @Qualifier("test")
    public EntityManager testEntityManager() throws SQLException, IOException {
        return entityManagerFactory().createEntityManager();
    }*/
    
    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException, IOException {
      final JpaTransactionManager txManager = new JpaTransactionManager();
      txManager.setEntityManagerFactory(entityManagerFactory());
      return txManager;
    }
    
    public abstract String[] getEntityPackagesToScan();
    
}
