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
public class UserPrivacyDTO {

    private Long userNumber;
    private String userId;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

    // DTO를 entity로 변환함
    public static UserEntity toEntity(UserPrivacyDTO dto) {
        return UserEntity
                .builder()
                .userNumber(dto.getUserNumber())
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .roles(dto.getRoles())
                .build();
    }
}
