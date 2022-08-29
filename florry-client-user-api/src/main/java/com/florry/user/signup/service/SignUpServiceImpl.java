package com.florry.user.signup.service;

import com.florry.domain.user.UserRepository;
import com.florry.user.signup.dto.SignUpModelMapper;
import com.florry.user.signup.dto.SignUpRequest;
import com.florry.user.signup.dto.SignUpResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    private final SignUpModelMapper signUpModelMapper;

    public SignUpServiceImpl(UserRepository userRepository, SignUpModelMapper signUpModelMapper) {
        this.userRepository = userRepository;
        this.signUpModelMapper = signUpModelMapper;
    }

    @Transactional
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        return signUpModelMapper.toSignUpResponse(
                userRepository.save(
                        signUpModelMapper.toUser(signUpRequest)));
    }
}
