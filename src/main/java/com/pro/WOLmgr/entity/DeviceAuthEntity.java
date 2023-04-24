package com.pro.WOLmgr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceAuthNumber;
    @ManyToOne
    @JoinColumn
    private DeviceEntity authDevice;
    @ManyToOne
    @JoinColumn
    private UserEntity authUser;

}
