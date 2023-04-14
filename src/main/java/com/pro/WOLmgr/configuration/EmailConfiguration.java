package com.pro.WOLmgr.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
public class EmailConfiguration {
    private final Environment env;

    @Bean // Spring Bean으로 등록됨을 표시
    public JavaMailSender getJavaMailSender() { // JavaMailSender 객체를 생성하는 메소드
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); // JavaMailSenderImpl 객체 생성

        mailSender.setHost("smtp.gmail.com"); // 메일 서버 호스트 설정
        mailSender.setPort(587); // 메일 서버 포트 설정

        mailSender.setUsername(env.getProperty("auth_email")); // 인증에 사용할 이메일 계정 설정
        mailSender.setPassword(env.getProperty("auth_password")); // 인증에 사용할 이메일 계정의 비밀번호 설정

        Properties props = mailSender.getJavaMailProperties(); // JavaMail의 속성(Properties) 설정 객체 얻기
        props.put("mail.transport.protocol", "smtp"); // 메일 전송 프로토콜 설정
        props.put("mail.smtp.auth", "true"); // SMTP 인증 사용 설정
        props.put("mail.smtp.starttls.enable", "true"); // STARTTLS 사용 설정
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Gmail 서버 신뢰 설정
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // SSL 프로토콜 설정

        return mailSender; // 생성된 JavaMailSender 객체 반환
    }
}
