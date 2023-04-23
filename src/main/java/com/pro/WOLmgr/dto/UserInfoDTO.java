package com.pro.WOLmgr.dto;

import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {

    private Long userNumber;
    private String userId;
    private String username;
    private String email;
    private Set<Role> roles;

    // entity를 DTO로 변환함
    public static UserInfoDTO toDto(UserEntity userEntity) {
        return UserInfoDTO
                .builder()
                .userNumber(userEntity.getUserNumber())
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .build();
    }
}
