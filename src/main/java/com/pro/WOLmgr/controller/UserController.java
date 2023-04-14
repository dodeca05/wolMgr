package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.UserPrivacyDTO;
import com.pro.WOLmgr.service.MailService;
import com.pro.WOLmgr.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final MailService mailService;
    private final UserService userService;

    @GetMapping("/wol/login")
    public void login(){}

    @GetMapping("/")
    public String index() { return "/wol/login"; }

    //이메일 인증
    @PostMapping("/mailSendNum") // 메일 유효성 검사
    public @ResponseBody HashMap<String,String> mailSendNum(@RequestBody HashMap<String,String> params) {
        HashMap<String,String> result = new HashMap<>();
        String email = params.get("email").toString();
        result.put("checkNumber",mailService.joinEmail(email));
        return result;
    }

    @PostMapping("/join") // 회원가입
    public @ResponseBody HashMap<String,String> join(@RequestBody UserPrivacyDTO userDTO){
        HashMap<String,String> result = new HashMap<>();
        userService.register(userDTO);
        result.put("result","회원가입 완료^^");
        return result;
    }

    @PostMapping("/idCheck") // 아이디 중복 체크
    public @ResponseBody HashMap<String,String> idCheck(@RequestBody HashMap<String,String> params){

        HashMap<String,String> result = new HashMap<>();
        String userId = params.get("userId").toString();
        if (userService.idCheck(userId)){result.put("result","contain");}
        else{result.put("result","noContain");}

        return result;
    }

    @PostMapping("/emailCheck") // 이메일 중복 체크
    public @ResponseBody HashMap<String,String> emailCheck(@RequestBody HashMap<String,String> params){

        HashMap<String,String> result = new HashMap<>();
        String email = params.get("email").toString();
        if (userService.emailCheck(email)){result.put("result","contain");}
        else{result.put("result","noContain");}

        return result;
    }

    @PostMapping("/authCheck")
    public @ResponseBody HashMap<String,String> example(@RequestBody HashMap<String,String> params,
                                          HttpServletResponse response) {
        HashMap<String,String> result = new HashMap<>();

        String token = params.get("Authorization").toString();
        response.setHeader("Authorization", token);

        return result;
    }
}
