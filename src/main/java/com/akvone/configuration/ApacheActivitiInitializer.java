package com.akvone.configuration;

import lombok.RequiredArgsConstructor;
import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class ApacheActivitiInitializer {

  private final DataSource dataSource;

  @Bean
  public RepositoryService repositoryService() {
    return processEngine().getRepositoryService();
  }

  @Bean
  public RuntimeService runtimeService() {
    return processEngine().getRuntimeService();
  }

  @Bean
  public TaskService taskService() {
    return processEngine().getTaskService();
  }

  @Bean
  public HistoryService historyService() {
    return processEngine().getHistoryService();
  }

  @Bean
  public ManagementService managementService() {
    return processEngine().getManagementService();
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
    ProcessEngineFactoryBean processEngineFactory = processEngineFactoryBean();
    processEngineFactory.setProcessEngineConfiguration(processEngineConfiguration());
    try {
      return processEngineFactory.getObject();
    } catch (Exception e) {
      throw new ApacheActivitiInitializeException("Can not initialize apache activiti", e);
    }
  }

  @Bean
  public ProcessEngineFactoryBean processEngineFactoryBean() {
    return new ProcessEngineFactoryBean();
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);
    return transactionManager;
  }

  @PostConstruct
  public void initProcesses() {
    RepositoryService repositoryService = repositoryService();
    repositoryService
            .createDeployment()
            .addClasspathResource("processes/aplicant.bpmn20.xml")
            .deploy()
            .getId();
  }
}
