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
    private String userId;
    private String username;
    private String email;
    private Set<Role> roles;

    // entity를 DTO로 변환함

    public static UserInfoDTO toDto(UserEntity userEntity) {

        return UserInfoDTO
                .builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .build();
    }

    public static UserEntity toEntity(UserInfoDTO dto) {
        return UserEntity
                .builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .roles(dto.getRoles())
                .build();
    }
}
