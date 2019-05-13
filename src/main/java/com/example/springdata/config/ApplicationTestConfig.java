package com.example.springdata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories("com.example.springdata.repository")
@ComponentScan("com.example.springdata")
@PropertySource("testdb.properties")
public class ApplicationTestConfig {
    @Value("${url}")
    private String url;
    @Value("${user}")
    private String user;
    @Value("${driver}")
    private String driver;
    @Value("${password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${ddl-auto}")
    private String ddlAuto;

    private final
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @Autowired
    public ApplicationTestConfig(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        this.entityManagerFactoryBean = entityManagerFactoryBean;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
