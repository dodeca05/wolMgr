package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.UserInfoDTO;
import com.pro.WOLmgr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PutMapping("/member")
    public UserInfoDTO updateUser(@RequestBody UserInfoDTO dto){
        return userService.userUpdate(dto);
    }


}
