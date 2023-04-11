package com.pro.WOLmgr.dto;

import com.pro.WOLmgr.entity.User;
import com.pro.WOLmgr.util.Role;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long userNumber;
    private String userId;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

    // entity를 DTO로 변환함
    public static UserDTO toDto(User user) {
        return UserDTO
                .builder()
                .userNumber(user.getUserNumber())
                .userId(user.getUserId())
                .password(user.getPassword())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

    // DTO를 entity로 변환함
    public static User toEntity(UserDTO dto) {
        return User
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
