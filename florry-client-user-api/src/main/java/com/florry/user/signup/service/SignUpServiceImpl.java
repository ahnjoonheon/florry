package com.florry.user.signup.service;

import com.florry.common.constant.MemberRole;
import com.florry.common.constant.MemberStatus;
import com.florry.domain.user.MemberRepository;
import com.florry.user.signup.dto.SignUpModelMapper;
import com.florry.user.signup.dto.SignUpRequest;
import com.florry.user.signup.dto.SignUpResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final MemberRepository memberRepository;
    private final SignUpModelMapper signUpModelMapper;

    public SignUpServiceImpl(MemberRepository memberRepository, SignUpModelMapper signUpModelMapper) {
        this.memberRepository = memberRepository;
        this.signUpModelMapper = signUpModelMapper;
    }

    @Transactional
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        return signUpModelMapper.toSignUpResponse(
                memberRepository.save(
                        signUpModelMapper.toUser(signUpRequest)
                                .withRoleBySignUp(MemberRole.USER)
                                .withUserStatusBySignUp(MemberStatus.NORMAL)));
    }
}
