package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.UserPrivacyDTO;
import com.pro.WOLmgr.service.MailService;
import com.pro.WOLmgr.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final MailService mailService;
    private final UserService userService;


    //이메일 인증
    @GetMapping("/mailSendNum") // 메일 유효성 검사
    public @ResponseBody HashMap<String,String> mailSendNum(@RequestParam("email") String email)
            throws MessagingException, IOException {
        HashMap<String,String> result = new HashMap<>();
        result.put("checkNumber",mailService.joinEmail(email));
        return result;
    }

    @PostMapping("/join") // 회원가입
    public @ResponseBody HashMap<String,String> join(@RequestBody UserPrivacyDTO userDTO){
        HashMap<String,String> result = new HashMap<>();
        userService.userCreate(userDTO);
        result.put("result","회원가입 완료^^");
        return result;
    }

    @GetMapping("/id/duplication") // 아이디 중복 체크
    public @ResponseBody HashMap<String,String> idCheck(@RequestParam("userId") String userId){

        HashMap<String,String> result = new HashMap<>();
        if (userService.idCheck(userId)){result.put("result","contain");}
        else{result.put("result","noContain");}

        return result;
    }

    @GetMapping("/email/duplication") // 이메일 중복 체크
    public @ResponseBody HashMap<String,String> emailCheck(@RequestParam("email") String email){
        HashMap<String,String> result = new HashMap<>();
        if (userService.emailCheck(email)){result.put("result","contain");}
        else{result.put("result","noContain");}

        return result;
    }
    @GetMapping("/auth/check")
    public @ResponseBody ResponseEntity getAuthState(@RequestHeader("Authorization") String authorizationHeader) {
        //Todo : 토큰에 문제 있으면 알아서 Exception 나온다 지금은 토큰에 문제가 있으면 500에러가 나온다. 이를 바꿔주는 코드 추가
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/auth/duplication")
    public @ResponseBody HashMap<String,String> example(@RequestBody HashMap<String,String> params,
                                          HttpServletResponse response) {
        HashMap<String,String> result = new HashMap<>();
        if(params.get("Authorization") == null){
            return result;
        }
        String token = params.get("Authorization").toString();
        response.setHeader("Authorization", token);

        return result;
    }
}
