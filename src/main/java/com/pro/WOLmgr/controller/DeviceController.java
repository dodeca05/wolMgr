package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceRequestDTO;
import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/device")
    public ResponseEntity<DeviceResponseDTO> deviceRegister(@RequestBody DeviceRequestDTO deviceRequestDTO){
//        TODO: 뷰단에서 자바스크립트 코드로 검증하도록 하자.
//        if(!deviceRequestDTO.getDeviceName().matches("[a-zA-Z0-9]+")){
//            return new ResponseEntity<>("Only English and numbers are available.",HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(deviceService.register(deviceRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/device/duplication/{deviceName}")
    public ResponseEntity<Boolean> deviceNameRead(@PathVariable String deviceName){
        return new ResponseEntity<>(deviceService.deviceNameCheck(deviceName),HttpStatus.OK);
    }

    @GetMapping("/device/duplication/{macAddress}")
    public ResponseEntity<Boolean> deviceMacAddressRead(@PathVariable String macAddress){
        return new ResponseEntity<>(deviceService.macAddressCheck(macAddress),HttpStatus.OK);
    }

    @DeleteMapping("/device/{deviceNumber}")
    public ResponseEntity<Long> deviceDelete(@PathVariable Long deviceNumber){
        deviceService.delete(deviceNumber);
        return new ResponseEntity<>(deviceNumber,HttpStatus.NO_CONTENT);
    }
}
