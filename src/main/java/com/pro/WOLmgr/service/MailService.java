package com.pro.WOLmgr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    String emailTitle = "WOLMGR의 인증 이메일입니다.";
    private final JavaMailSender mailSender;
    private final Environment env;

    //이메일 양식
    public String joinEmail(String email) {
        int authNumber = makeRandomNumber();
        String setFrom = env.getProperty("auth_email"); // emailConfig에 설정한 자신의 이메일 주소를 입력
        String toMail = email;
        String title = emailTitle; // 이메일 제목
        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 WOLMGR입니다.^^ </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= authNumber+"</strong><div><br/> ";
        msgg+= "</div>";
        mailSend(setFrom, toMail, title, msgg);
        return Integer.toString(authNumber);
    }

    public int makeRandomNumber() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        return r.nextInt(888888) + 111111;
    }

    //이메일 전송 메소드
    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
        try {
            // MimeMessageHelper 객체를 생성합니다. true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능하며, 문자 인코딩 설정도 가능합니다.
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            // 메일 발신자를 설정합니다.
            helper.setFrom(setFrom);
            // 메일 수신자를 설정합니다.
            helper.setTo(toMail);
            // 메일 제목을 설정합니다.
            helper.setSubject(title);
            // 메일 내용을 설정합니다. true를 전달하면 html 형식으로 전송되며, 작성하지 않으면 단순 텍스트로 전송됩니다.
            helper.setText(content, true);
            // 보냅니다.
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
