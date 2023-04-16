package com.pro.WOLmgr.configuration;

import com.pro.WOLmgr.util.CheckVariable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Log4j2
public class CheckConfig {
    private final Environment env;
    @PostConstruct
    public void init() {
        if(env.getProperty("1auth_email") == null||env.getProperty("auth_password") == null){
            CheckVariable.getInstance().setEmailSmtpCheck(false);
            log.info("STMP 기능이 비활성화 되었습니다.");
        }
    }
}
