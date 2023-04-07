package com.pro.WOLmgr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true, // secured 어노테이션 활성화 메서드 위에다가 한 권한에 권한 주는 것
        prePostEnabled = true) // preAuthorize,postAuthorize(잘 안씀) 어노테이션 활성화 메서드 위에 여러권한에게 권한 주는것
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // 해당 메서드의 리턴부분을 의존성주입해줌
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/**").permitAll() // 모든 경로에 대해 권한 없이 접근 가능하도록 수정
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행함
                .defaultSuccessUrl("/");
    }
}
