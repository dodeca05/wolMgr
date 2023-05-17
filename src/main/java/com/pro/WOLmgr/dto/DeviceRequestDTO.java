package com.pro.WOLmgr.dto;

import com.pro.WOLmgr.entity.DeviceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequestDTO {
    // TODO: 이하 추가 할 DTO를 추가합니다.
    private String deviceName;
    private String macAddress;
    private String ipAddress;

    public static DeviceEntity fromDTO(DeviceRequestDTO dto) {
        return DeviceEntity
                .builder()
                .deviceName(dto.getDeviceName())
                .macAddress(dto.getMacAddress())
                .ipAddress(dto.getIpAddress())
                .build();
    }

}
