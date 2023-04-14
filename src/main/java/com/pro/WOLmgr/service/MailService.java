package com.pro.WOLmgr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Log4j2
public class MailService {

    String emailTitle = "WOLMGR의 인증 이메일입니다.";
    private final JavaMailSender mailSender;
    private final Environment env;

    //이메일 양식
    public String joinEmail(String email) throws IOException, MessagingException {
        int authNumber = makeRandomNumber();
        String setFrom = env.getProperty("auth_email"); // emailConfig에 설정한 자신의 이메일 주소를 입력
        String toMail = email;
        String title = emailTitle; // 이메일 제목
        String msgg = getEmailTemplate(); // HTML 템플릿 파일에서 내용을 읽어옴
        msgg = msgg.replace("{authNumber}", Integer.toString(authNumber)); // {authNumber} 부분을 인증번호로 대체
        mailSend(setFrom, toMail, title, msgg);
        return Integer.toString(authNumber);
    }

    private int makeRandomNumber() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        return r.nextInt(888888) + 111111;
    }

    private String getEmailTemplate() throws IOException {
        String template = "";
        String emailTemplate = env.getProperty("emailPath.template");
        log.info(emailTemplate);
        // 클래스패스(Classpath)에서 템플릿 파일을 읽어옴
        InputStream inputStream = getClass().getResourceAsStream(emailTemplate);
        log.info(inputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line;
        while ((line = reader.readLine()) != null) {
            template += line;
        }
        reader.close();

        return template;
    }

    //이메일 전송 메소드
    private void mailSend(String setFrom,
                          String toMail,
                          String title,
                          String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.

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

    }

}
