package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceInfoDTO;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;

@RestController
public class WolServiceController{

    @PutMapping("/sendPacket/{packet}")
    public @ResponseBody HashMap<String,String> sendPacket(@PathVariable String packet) {
        HashMap<String,String> result = new HashMap<>();
        // TODO: 패킷을 전송하는 로직 구현
        new NotImplementedException("구현이 되지 않은 서비스입니다.");
        return result;
    }

    @GetMapping("/healthCheck/{com}")
    public @ResponseBody HashMap<String,String> healthCheck(@PathVariable String com){
        HashMap<String,String> result = new HashMap<>();
        // TODO: healthCheck 로직 구현
        new NotImplementedException("구현이 되지 않은 서비스입니다.");
        return result;
    }

    @PostMapping("/computerInfoRegister")
    public @ResponseBody HashMap<String,String> computerInfo(@RequestBody DeviceInfoDTO deviceInfoDTO){
        HashMap<String,String> result = new HashMap<>();
        // TODO: 새로운 디바이스를 등록하는 로직을 추가합니다.
        new NotImplementedException("구현이 되지 않은 서비스입니다.");
        return result;
    }

    @DeleteMapping("/deleteNum/{num}")
    public @ResponseBody HashMap<String,String> deleteNum(@PathVariable Long num){
        HashMap<String,String> result = new HashMap<>();
        // TODO: 등록된 디바이스를 삭제하는 로직을 작성합니다.
        new NotImplementedException("구현이 되지 않은 서비스입니다.");
        return result;
    }

    @PostMapping("/access/{deviceNum}")
    public @ResponseBody HashMap<String,String> access(@PathVariable Long deviceNum){
        HashMap<String,String> result = new HashMap<>();
        // TODO: 회원에게 권한을 제공하는 로직을 작성합니다.
        new NotImplementedException("구현이 되지 않은 서비스입니다.");
        return result;
    }

}