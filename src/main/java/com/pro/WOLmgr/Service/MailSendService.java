package com.pro.WOLmgr.Service;

public interface MailSendService {

    String emailTitle = "WOLMGR의 인증 이메일입니다.";
    String emailTop = "홈페이지를 방문해주셔서 감사합니다.";
    String emailBottom = "해당 인증번호를 인증번호 확인란에 기입하면 회원가입이 완료됩니다.";
    public String joinEmail(String email);

}
