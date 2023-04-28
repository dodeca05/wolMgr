package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceAuthRequestDTO;
import com.pro.WOLmgr.dto.DeviceAuthResponseDTO;
import com.pro.WOLmgr.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AccessController {
    private final DeviceService deviceService;

    @GetMapping("/access")
    public ResponseEntity<?> accessRead(@ModelAttribute DeviceAuthRequestDTO deviceAuthRequestDTO){
        return deviceService.accessCheck(deviceAuthRequestDTO)?
                new ResponseEntity<>(deviceAuthRequestDTO, HttpStatus.OK):
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
