package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.ComputerInfoDTO;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.web.bind.annotation.*;

@RestController
public class WolServiceController{

    @PutMapping("/sendPacket/{packet}")
    public String sendPacket(@PathVariable String packet) {
        // TODO: 패킷을 전송하는 로직 구현
        new ExecutionControl.NotImplementedException("구현이 되지 않은 서비스입니다.");
        return "{ 'key' : 'value' }";
    }

    @GetMapping("/healthCheck/{com}")
    public String healthCheck(@PathVariable String com){
        // TODO: healthCheck 로직 구현
        new ExecutionControl.NotImplementedException("구현이 되지 않은 서비스입니다.");
        return "{ 'key' : 'value' }";
    }

    @PostMapping("/computerInfoRegister")
    public String computerInfo(ComputerInfoDTO computerInfoDTO){
        // TODO: 새로운 디바이스를 등록하는 로직을 추가합니다.
        new ExecutionControl.NotImplementedException("구현이 되지 않은 서비스입니다.");
        return computerInfoDTO.getDevice();
    }

    @DeleteMapping("/deleteNum/{num}")
    public String deleteNum(@PathVariable Long num){
        // TODO: 등록된 디바이스를 삭제하는 로직을 작성합니다.
        new ExecutionControl.NotImplementedException("구현이 되지 않은 서비스입니다.");
        return "{ 'key' : 'value' }";
    }

    @PostMapping("/access/{deviceNum}")
    public String access(@PathVariable Long deviceNum){
        // TODO: 회원에게 권한을 제공하는 로직을 작성합니다.
        new ExecutionControl.NotImplementedException("구현이 되지 않은 서비스입니다.");
        return "{ 'key' : 'value' }";
    }

}