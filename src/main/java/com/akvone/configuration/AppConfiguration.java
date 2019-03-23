package com.akvone.configuration;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class AppConfiguration {

  @Bean
  public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
    LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setPackagesToScan("com.akvone.entity");
    bean.setHibernateProperties(hibernateProperties());

    return bean;
  }

  Properties hibernateProperties() {
    return new Properties() {
      {
        setProperty("hibernate.hbm2ddl.auto", "create-drop");
        setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        setProperty("hibernate.globally_quoted_identifiers", "true");
      }
    };
  }

//  @Bean
//  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//
//    HibernateTransactionManager txManager = new HibernateTransactionManager();
//    txManager.setSessionFactory(sessionFactory);
//
//    return txManager;
//  }
}
