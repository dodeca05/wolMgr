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
public class UserInfoDTO {
    private String oldName;
    private String userId;
    private String username;
    private String email;
    private Set<Role> roles;
    private List<String> serviceToken;

}
