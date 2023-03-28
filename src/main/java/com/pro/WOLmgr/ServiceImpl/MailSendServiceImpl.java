package com.pro.WOLmgr.ServiceImpl;

import com.pro.WOLmgr.Service.MailSendService;
import com.pro.WOLmgr.Service.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
@Service
public class MailSendServiceImpl implements MailSendService {
    private final UtilService utilService;

    //이메일 양식
    public String joinEmail(String email) {
        int authNumber = utilService.makeRandomNumber();
        String setFrom = "wolmgr587@gmail.com"; // emailConfig에 설정한 자신의 이메일 주소를 입력
        String toMail = email;
        String title = emailTitle; // 이메일 제목
        String content =
                        emailTop + 	//html 형식으로 작성해야함
                        "<br><br>" +
                        "인증 번호는 [" + authNumber + "] 입니다." +
                        "<br>" +
                        emailBottom; //이메일 내용 삽입
        utilService.mailSend(setFrom, toMail, title, content);
        return Integer.toString(authNumber);
    }


}