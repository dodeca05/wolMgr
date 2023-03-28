package com.pro.WOLmgr.ServiceImpl;

import com.pro.WOLmgr.Service.UserService;
import com.pro.WOLmgr.dto.UserDTO;
import com.pro.WOLmgr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long register(UserDTO userDTO) {
        userRepository.save(userDTE(userDTO));
        return userRepository.findByEmail(userDTO.getEmail());
    }
}
