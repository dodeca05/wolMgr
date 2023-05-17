package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.dto.UserInfoDTO;
import com.pro.WOLmgr.dto.UserPrivacyDTO;
import com.pro.WOLmgr.service.DeviceService;
import com.pro.WOLmgr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final DeviceService deviceService;

    @PutMapping("/member")
    public UserInfoDTO updateUser(@RequestBody UserInfoDTO dto){
        return userService.userUpdate(dto);
    }


}
