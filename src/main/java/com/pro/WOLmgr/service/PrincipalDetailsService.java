package com.pro.WOLmgr.service;

import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.util.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return new PrincipalDetails(userRepository.findByUserId(id));
    }
}
