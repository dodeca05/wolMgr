package com.pro.WOLmgr.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
@Log4j2
public class DatabaseConfiguration {
    private final Environment env;
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            dataSource.setDriverClassName(env.getProperty("db_driverClassName"));
            dataSource.setUrl(env.getProperty("db_url"));
            dataSource.setUsername(env.getProperty("db_username"));
            dataSource.setPassword(env.getProperty("db_password"));
        }
        catch (IllegalArgumentException e)
        {
            String msg="\n***************************\n"+
                    "[KOR]\n"+
                    "DB와 관련된 환경 변수를 찾을 수 없습니다.\n"+
                    "환경 변수에 다음과 같은 항목을 추가 해주세요\n"+
                    "db_driverClassName - db 드라이버\n"+
                    "db_url - db 주소\n"+
                    "db_username - db 계정 ID\n"+
                    "db_password - db 계정 비밀번호"+
                    "\n***************************\n"+
                    "[ENG]\n"+
                    "We couldn't find environment variables related to DB. \n" +
                    "Please add the following items to the environment variable.\n" +
                    "db_driverClassName - db driver\n" +
                    "db_url - db address\n" +
                    "db_username - db account ID\n" +
                    "db_password - db account password"+
                    "\n***************************\n";
            log.error(msg);
            throw e;
        }
        return dataSource;
    }
}
