package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceInfoDTO;
import com.pro.WOLmgr.repository.DeviceRepository;
import com.pro.WOLmgr.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class WolServiceController{
    private final DeviceService deviceService;

    @PutMapping("/sendPacket/{packet}")
    public String sendPacket(@PathVariable String packet) {
        // TODO: 패킷을 전송하는 로직 구현
        new NotImplementedException("구현이 되지 않은 서비스입니다.");
        return "{ 'key' : 'value' }";
    }

    @GetMapping("/healthCheck/{com}")
    public String healthCheck(@PathVariable String com){
        // TODO: healthCheck 로직 구현
        new NotImplementedException("구현이 되지 않은 서비스입니다.");
        return "{ 'key' : 'value' }";
    }

    @PostMapping("/computerInfoRegister")
    public String computerInfo(@RequestBody DeviceInfoDTO deviceInfoDTO){
        if(!deviceInfoDTO.getDeviceName().matches("[a-zA-Z]+")){
            throw new RuntimeException("영어를 제외한 나머지 멈춰!");
        }
        String result = deviceService.register(deviceInfoDTO)? "suckSex!":"fuck!";
        return result;
    }

    @DeleteMapping("/deleteNum/{deviceNumber}")
    public String deleteNum(@PathVariable Long deviceNumber){
        String result = deviceService.delete(deviceNumber)? "fuck!":"suckSex!";
        return result;
    }

    @PostMapping("/access/{deviceNum}")
    public String access(@PathVariable Long deviceNum){
        // TODO: 회원에게 권한을 제공하는 로직을 작성합니다.
        new NotImplementedException("구현이 되지 않은 서비스입니다.");
        return "{ 'key' : 'value' }";
    }

}