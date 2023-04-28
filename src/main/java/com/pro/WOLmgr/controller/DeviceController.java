package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceRequestDTO;
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
    public ResponseEntity<?> deviceRegister(@RequestBody DeviceRequestDTO deviceRequestDTO){
        if(!deviceRequestDTO.getDeviceName().matches("[a-zA-Z0-9]+")){
            return new ResponseEntity<>("Only English and numbers are available.", HttpStatus.BAD_REQUEST);
        }else if(deviceService.deviceNameCheck(deviceRequestDTO.getDeviceName())){
            return new ResponseEntity<>("Duplicate names are not allowed.",HttpStatus.BAD_REQUEST);
        }else if(deviceService.macAddressCheck(deviceRequestDTO.getIpAddress())){
            return new ResponseEntity<>("Duplicate IP addresses are not allowed.",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deviceService.register(deviceRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/device/{deviceNumber}")
    public ResponseEntity<?> deviceDelete(@PathVariable Long deviceNumber){
        return deviceService.delete(deviceNumber)?
                new ResponseEntity<>("Deletion failed.",HttpStatus.INTERNAL_SERVER_ERROR):
                new ResponseEntity<>(deviceNumber,HttpStatus.NO_CONTENT);
    }
}
