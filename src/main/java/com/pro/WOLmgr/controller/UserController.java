package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.Service.MailSendService;
import com.pro.WOLmgr.Service.UserService;
import com.pro.WOLmgr.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final MailSendService mailService;
    private final UserService userService;

    @GetMapping("/Wol/login")
    public void login(){}

    //이메일 인증
    @GetMapping("/mailCheck")
    @ResponseBody
    public String mailCheck(String email) {
        return mailService.joinEmail(email);
    }

    @PostMapping("/userRegister")
    public Long userRegister(UserDTO userDto){
        return userService.register(userDto);
    }
}
