package com.akvone.configuration;

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

    return bean;
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
