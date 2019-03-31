package com.akvone.configuration;

import lombok.RequiredArgsConstructor;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class ApacheActivitiInitializer {

  private final DataSource dataSource;

  @Bean
  public RuntimeService runtimeService() {
    return processEngine().getRuntimeService();
  }

  @Bean
  public SpringProcessEngineConfiguration processEngineConfiguration() {
    SpringProcessEngineConfiguration conf = new SpringProcessEngineConfiguration();
    conf.setDataSource(dataSource);
    conf.setTransactionManager(transactionManager());
    conf.setDatabaseSchemaUpdate("true");
    conf.setAsyncExecutorActivate(false);
    return conf;
  }

  @Bean
  public ProcessEngine processEngine() {
    ProcessEngineFactoryBean processEngineFactory = new ProcessEngineFactoryBean();
    processEngineFactory.setProcessEngineConfiguration(processEngineConfiguration());
    try {
      return processEngineFactory.getObject();
    } catch (Exception e) {
      throw new ApacheActivitiInitializeException("Can not initialize apache activiti", e);
    }
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);
    return transactionManager;
  }
}
