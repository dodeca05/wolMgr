package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.util.CheckVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;

@RestController
@ApiIgnore
public class EmailCheckController {

    @GetMapping("/checkEmail")
    public @ResponseBody Boolean isEmail(){
        if(CheckVariable.getInstance().isEmailSmtpCheck()) return true;
        else return false;
    }

}
