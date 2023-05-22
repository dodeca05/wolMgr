package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.Jwt.JwtTokenReader;
import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.dto.UserInfoDTO;
import com.pro.WOLmgr.dto.UserPrivacyDTO;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.service.DeviceService;
import com.pro.WOLmgr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final DeviceService deviceService;
    private final Environment env;
    private final UserRepository userRepository;

    @PutMapping("/member")
    public UserInfoDTO updateUser(@RequestBody UserInfoDTO dto,@RequestHeader("Authorization") String authorizationHeader) throws AccessException {
        JwtTokenReader jwtTokenReader=new JwtTokenReader(env,userRepository,authorizationHeader);
        if(!jwtTokenReader.isItAdmin())
            throw new AccessException("권한이 없습니다.");
        return userService.userUpdate(dto);
    }


}
