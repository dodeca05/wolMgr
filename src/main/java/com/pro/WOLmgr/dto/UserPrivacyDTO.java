package com.pro.WOLmgr.dto;

import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrivacyDTO {
    private String userId;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
    private String serviceToken;
    private List<String> accessibleDevice;

    // DTO를 entity로 변환함
    public static UserEntity fromDTO(UserPrivacyDTO dto) {
        return UserEntity
                .builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .roles(dto.getRoles())
                .serviceToken(dto.getServiceToken())
                .build();
    }
}
