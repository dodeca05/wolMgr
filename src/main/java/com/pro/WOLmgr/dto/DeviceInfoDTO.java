package com.pro.WOLmgr.dto;

import com.pro.WOLmgr.entity.Device;
import com.pro.WOLmgr.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfoDTO {
    // TODO: 이하 추가 할 DTO를 추가합니다.
    private String deviceName;
    private String macAddress;
    private String ipAddress;

    public static Device toEntity(DeviceInfoDTO dto) {
        return Device
                .builder()
                .deviceName(dto.getDeviceName())
                .macAddress(dto.getMacAddress())
                .ipAddress(dto.getIpAddress())
                .build();
    }

}
