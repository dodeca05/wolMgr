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
    @EmbeddedId
    private DeviceAuthId id;

    @ManyToOne
    @JoinColumn(name = "authDevice", insertable = false, updatable = false)
    private DeviceEntity authDevice;

    @ManyToOne
    @JoinColumn(name = "authUser", insertable = false, updatable = false)
    private UserEntity authUser;

}
