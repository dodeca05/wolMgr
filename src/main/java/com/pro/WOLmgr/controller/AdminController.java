package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.UserInfoDTO;
import com.pro.WOLmgr.dto.UserPrivacyDTO;
import com.pro.WOLmgr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PutMapping("/user")
    public UserInfoDTO updateUser(UserPrivacyDTO dto){
        return userService.userUpdate(dto);
    }

}
