package com.pro.WOLmgr.dto;

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
    private String device;
    private String macAddress;
    private String ipAddress;

}
