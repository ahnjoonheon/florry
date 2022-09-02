package com.florry.user.auth.service;

import com.florry.common.constant.MemberStatus;
import com.florry.domain.user.MemberRepository;
import com.florry.user.auth.dto.AuthModelMapper;
import com.florry.user.auth.dto.LoginRequest;
import com.florry.user.auth.dto.LoginResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final AuthModelMapper authModelMapper;

    public AuthServiceImpl(MemberRepository memberRepository, AuthModelMapper authModelMapper) {
        this.memberRepository = memberRepository;
        this.authModelMapper = authModelMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return authModelMapper.of(memberRepository.findByEmailAndPasswordAndMemberStatusIn(
                loginRequest.email(), loginRequest.password(), List.of(MemberStatus.NORMAL, MemberStatus.DORMANT))
                .orElseThrow());
    }
}
