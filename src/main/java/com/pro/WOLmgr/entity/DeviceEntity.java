package com.pro.WOLmgr.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceNumber; // pk로 사용함, 자동으로 생성되는 ID 값
    @Column(nullable = false, unique = true)
    private String deviceName;
    @Column(nullable = false, unique = true)
    private String macAddress;
    private String ipAddress;

}
