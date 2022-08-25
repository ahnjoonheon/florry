package com.florry.user.auth.service;

import com.florry.common.constant.UserStatus;
import com.florry.domain.user.UserRepository;
import com.florry.user.auth.dto.AuthModelMapper;
import com.florry.user.auth.dto.LoginRequest;
import com.florry.user.auth.dto.LoginResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthModelMapper authModelMapper;

    public AuthServiceImpl(UserRepository userRepository, AuthModelMapper authModelMapper) {
        this.userRepository = userRepository;
        this.authModelMapper = authModelMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return authModelMapper.of(userRepository.findByEmailAndPasswordAndUserStatusIn(
                loginRequest.getEmail(), loginRequest.getPassword(), List.of(UserStatus.NORMAL, UserStatus.DORMANT))
                .orElseThrow());
    }
}
