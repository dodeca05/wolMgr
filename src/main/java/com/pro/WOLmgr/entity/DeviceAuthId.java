package com.pro.WOLmgr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAuthId implements Serializable {
    private Long authDevice;
    private Long authUser;

}