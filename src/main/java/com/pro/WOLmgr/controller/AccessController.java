package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceAuthDTO;
import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AccessController {
    private final DeviceService deviceService;

    @GetMapping("/access/{username}")
    public List<DeviceResponseDTO> readDeviceList(@PathVariable String username){
        return deviceService.userAccessDevices(username);
    }

    @PostMapping("/access")
    public ResponseEntity<DeviceAuthDTO> accessCreate(@RequestBody DeviceAuthDTO deviceAuthDTO){
        return new ResponseEntity<>(deviceService.accessRegister(deviceAuthDTO),HttpStatus.OK);
    }

    @DeleteMapping("/access")
    public ResponseEntity<DeviceAuthDTO> accessDelete(@RequestBody DeviceAuthDTO deviceAuthDTO){
        deviceService.accessDelete(deviceAuthDTO);
        return new ResponseEntity<>(deviceAuthDTO, HttpStatus.NO_CONTENT);
    }
}
