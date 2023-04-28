package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceAuthRequestDTO;
import com.pro.WOLmgr.dto.DeviceAuthResponseDTO;
import com.pro.WOLmgr.dto.DeviceRequestDTO;
import com.pro.WOLmgr.entity.DeviceEntity;
import com.pro.WOLmgr.service.DeviceService;
import com.pro.WOLmgr.service.WakeOnLanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Log4j2
public class WolServiceController{
    private final DeviceService deviceService;
    private final WakeOnLanService wakeOnLanService;

    @PutMapping("/wake/{deviceName}")
    public String sendPacket(@PathVariable String deviceName) {
        //TODO : 권한 체크
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