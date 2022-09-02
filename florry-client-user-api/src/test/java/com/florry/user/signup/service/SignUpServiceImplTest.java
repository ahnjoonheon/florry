package com.florry.user.signup.service;

import com.florry.common.constant.MemberRole;
import com.florry.common.constant.MemberStatus;
import com.florry.domain.user.Member;
import com.florry.domain.user.MemberRepository;
import com.florry.user.signup.dto.SignUpModelMapperImpl;
import com.florry.user.signup.dto.SignUpRequest;
import com.florry.user.signup.dto.SignUpResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DisplayName("SignUpServiceImpl 테스트")
class SignUpServiceImplTest {
    @InjectMocks
    private SignUpServiceImpl signUpService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private SignUpModelMapperImpl signUpModelMapper;
    @Mock
    private SignUpRequest mockSignUpRequest;
    @Mock
    private SignUpResponse mockSignUpResponse;
    @Mock
    private Member member;

    @Test
    @DisplayName("사용자 가입")
    void signUp() {
        // given
        given(signUpModelMapper.toUser(mockSignUpRequest)).willReturn(member);
        given(memberRepository.save(member)).willReturn(member);
        given(signUpModelMapper.toSignUpResponse(member)).willReturn(mockSignUpResponse);
        given(member.withRoleBySignUp(any(MemberRole.class))).willReturn(member);
        given(member.withUserStatusBySignUp(any(MemberStatus.class))).willReturn(member);

        // when
        SignUpResponse signUpResponse = signUpService.signUp(mockSignUpRequest);

        // then
        Assertions.assertThat(signUpResponse).isNotNull();
    }
}
