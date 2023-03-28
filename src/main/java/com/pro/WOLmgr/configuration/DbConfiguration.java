package com.pro.WOLmgr.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.yml")
public class DbConfiguration {
    @Autowired
    private Environment env;

    private final String DBDRIVERNAME = env.getProperty("db.driverClassName");
    private final String DBURL = env.getProperty("db.url");
    private final String USERNAME = env.getProperty("db.username");
    private final String DBPASSWORD = env.getProperty("db.password");

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DBDRIVERNAME);
        dataSource.setUrl(DBURL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(DBPASSWORD);
        return dataSource;
    }
}
