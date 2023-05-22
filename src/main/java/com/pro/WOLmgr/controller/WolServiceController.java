package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.Jwt.JwtTokenReader;
import com.pro.WOLmgr.entity.DeviceAuthEntity;
import com.pro.WOLmgr.entity.DeviceEntity;
import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.repository.DeviceAuthRepository;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.service.DeviceService;
import com.pro.WOLmgr.service.WakeOnLanService;
import com.pro.WOLmgr.util.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.core.env.Environment;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.*;
import com.pro.WOLmgr.util.Role;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class WolServiceController{
    private final DeviceService deviceService;
    private final WakeOnLanService wakeOnLanService;
    private final Environment env;
    private final UserRepository userRepository;

    @PutMapping("/wake/{deviceName}")
    public String sendPacket(@PathVariable String deviceName,@RequestHeader("Authorization") String authorizationHeader) throws AccessException {
        JwtTokenReader tokenReader=new JwtTokenReader(env,userRepository,authorizationHeader);
        String userName=tokenReader.getName();
        Role role=tokenReader.getRole();

        if(role != Role.ADMIN)
        {
            if(!deviceService.hasUserPermission(userName,deviceName))
                throw new AccessException("권한이 없습니다.");

        }

        DeviceEntity deviceEntity=deviceService.getDeviceEntity(deviceName);
        try {
            wakeOnLanService.sendMagicPacket(deviceEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "{'status':'success'}";
    }

    @GetMapping("/healthCheck/{deviceName}")
    public String healthCheck(@PathVariable String deviceName){
        // TODO: healthCheck 로직 구현
        throw new NotImplementedException("구현이 되지 않은 서비스입니다.");
        //return "{ 'key' : 'value' }";
    }





}