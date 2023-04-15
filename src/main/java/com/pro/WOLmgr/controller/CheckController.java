package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.util.CheckVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CheckController {

    @GetMapping("/checkEamil")
    public @ResponseBody HashMap<String,Boolean> isEmail(){
        HashMap<String,Boolean> result = new HashMap<>();
        if(CheckVariable.getInstance().isEmailSmtpCheck()) result.put("key",true);
        else result.put("key",false);
        return result;
    }

}
