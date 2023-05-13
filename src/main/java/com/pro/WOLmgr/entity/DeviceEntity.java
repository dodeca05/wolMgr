package com.pro.WOLmgr.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeviceEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String deviceName;
    @Column(nullable = false, unique = true)
    private String macAddress;
    private String ipAddress;
    @OneToMany(mappedBy = "authDevice", cascade = CascadeType.ALL)
    private List<DeviceAuthEntity> deviceAuthEntity;

}
