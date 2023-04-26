package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceAuthRequestDTO;
import com.pro.WOLmgr.dto.DeviceAuthResponseDTO;
import com.pro.WOLmgr.dto.DeviceRequestDTO;
import com.pro.WOLmgr.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class WolServiceController{
    private final DeviceService deviceService;

    @PutMapping("/packet/{packet}")
    public String sendPacket(@PathVariable String packet) {
        // TODO: 패킷을 전송하는 로직 구현
        throw new NotImplementedException("구현이 되지 않은 서비스입니다.");
        //return "{ 'key' : 'value' }";
    }

    @GetMapping("/healthCheck/{com}")
    public String healthCheck(@PathVariable String com){
        // TODO: healthCheck 로직 구현
        throw new NotImplementedException("구현이 되지 않은 서비스입니다.");
        //return "{ 'key' : 'value' }";
    }

    @PostMapping("/device")
    public ResponseEntity<?> deviceRegister(@RequestBody DeviceRequestDTO deviceRequestDTO){
        if(!deviceRequestDTO.getDeviceName().matches("[a-zA-Z0-9]+")){
            return new ResponseEntity<>("Only English and numbers are available.",HttpStatus.BAD_REQUEST);
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

    @GetMapping("/access")
    public ResponseEntity<?> accessRead(@ModelAttribute DeviceAuthRequestDTO deviceAuthRequestDTO){
        return deviceService.accessCheck(deviceAuthRequestDTO)?
                new ResponseEntity<>(deviceAuthRequestDTO,HttpStatus.OK):
                new ResponseEntity<>("Access failed.",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/access")
    public ResponseEntity<DeviceAuthResponseDTO> accessCreate(@RequestBody DeviceAuthRequestDTO deviceAuthRequestDTO){
        return new ResponseEntity<>(deviceService.accessRegister(deviceAuthRequestDTO),HttpStatus.OK);
    }

    @DeleteMapping("/access")
    public ResponseEntity<?> accessDelete(@ModelAttribute DeviceAuthRequestDTO deviceAuthRequestDTO){
        return deviceService.accessDelete(deviceAuthRequestDTO)?
                new ResponseEntity<>(deviceAuthRequestDTO, HttpStatus.NO_CONTENT):
                new ResponseEntity<>("Deletion failed.",HttpStatus.INTERNAL_SERVER_ERROR);


    }

}