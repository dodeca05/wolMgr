package com.pro.WOLmgr.dto;

import com.pro.WOLmgr.entity.DeviceAuthEntity;
import com.pro.WOLmgr.entity.DeviceEntity;
import com.pro.WOLmgr.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAuthResponseDTO {

    private Long userId;
    private Long deviceId;

    public DeviceAuthResponseDTO toEntity(DeviceAuthEntity entity){
        return DeviceAuthResponseDTO
                .builder()
                .userId(entity.getAuthUser().getUserNumber())
                .deviceId(entity.getAuthDevice().getDeviceNumber())
                .build();

    }
}
