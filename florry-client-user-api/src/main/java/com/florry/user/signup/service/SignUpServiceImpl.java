package com.florry.user.signup.service;

import com.florry.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;

    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
