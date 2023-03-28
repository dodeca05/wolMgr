package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.ComputerInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WolService{

    @PutMapping("/sendPacket/{packet}")
    public boolean sendPacket(@PathVariable String packet){
        return false;
    }

    @GetMapping("/healthCheck/{com}")
    public boolean healthCheck(@PathVariable String com){
        return false;
    }

    @PostMapping("/computerInfoRegister")
    public String computerInfo(ComputerInfoDTO computerInfoDTO){
        return computerInfoDTO.getDevice();
    }

    @DeleteMapping("/deleteNum/{num}")
    public boolean deleteNum(@PathVariable Long num){
        return false;
    }

    @PostMapping("/access/{deviceNum}")
    public boolean access(@PathVariable Long deviceNum){
        return false;
    }

}
