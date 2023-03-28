package com.pro.WOLmgr.Service;

import com.pro.WOLmgr.dto.UserDTO;
import com.pro.WOLmgr.entity.User;

public interface UserService {

    Long register(UserDTO userDTO);

    default UserDTO userETD(User user){
        return UserDTO
                .builder()
                .userNumber(user.getUserNumber() != null? user.getUserNumber():null)
                .userId(user.getUserId())
                .password(user.getPassword())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
    default User userDTE(UserDTO userDTO){
        return User
                .builder()
                .userNumber(userDTO.getUserNumber())
                .userId(userDTO.getUserId())
                .password(userDTO.getPassword())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .build();
    }
}
