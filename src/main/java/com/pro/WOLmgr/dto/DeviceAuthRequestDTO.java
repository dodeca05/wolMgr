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
public class DeviceAuthRequestDTO {

    private String userId;
    private String deviceId;

    public DeviceAuthEntity toEntity(UserEntity user, DeviceEntity device){
        return DeviceAuthEntity
                .builder()
                .authUser(user)
                .authDevice(device)
                .build();
    }
}
