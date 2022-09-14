package com.florry.user.signup.service;

import com.florry.common.constant.MemberRole;
import com.florry.common.constant.MemberStatus;
import com.florry.domain.user.Member;
import com.florry.domain.user.MemberRepository;
import com.florry.user.member.dto.*;
import com.florry.user.member.service.MemberService;
import com.florry.user.member.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DisplayName("MemberServiceImpl 테스트")
class MemberServiceImplTest {
    @InjectMocks
    private MemberServiceImpl memberService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberModelMapper memberModelMapper;
    @Mock
    private SignUpRequest mockSignUpRequest;
    @Mock
    private SignUpResponse mockSignUpResponse;
    @Mock
    private Member member;
    @Mock
    private Iterable<Member> memberIterable;
    @Mock
    private MemberSearchCondition mockMemberSearchCondition;
    @Mock
    private MemberResponse memberResponse;
    @Mock
    private MemberRequest memberRequest;

    @Test
    @DisplayName("회원 가입")
    void signUp() {
        // given
        given(memberModelMapper.toMember(mockSignUpRequest)).willReturn(member);
        given(memberRepository.save(member)).willReturn(member);
        given(memberModelMapper.toSignUpResponse(member)).willReturn(mockSignUpResponse);
        given(member.withRoleBySignUp(any(MemberRole.class))).willReturn(member);
        given(member.withUserStatusBySignUp(any(MemberStatus.class))).willReturn(member);

        // when
        SignUpResponse signUpResponse = memberService.signUp(mockSignUpRequest);

        // then
        assertThat(signUpResponse).isNotNull();
    }

    @Test
    @DisplayName("회원 조회")
    void findMembers() {
        // given
        given(memberRepository.findAll(mockMemberSearchCondition.build())).willReturn(memberIterable);
        given(memberModelMapper.toMemberResponses(memberIterable)).willReturn(List.of(memberResponse));

        // when
        List<MemberResponse> memberResponses = memberService.findMembersBySearchCondition(mockMemberSearchCondition);

        // then
        assertThat(memberResponses).isNotNull();
    }

    @Test
    @DisplayName("회원 수정")
    void updateMember() {
        // given
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));
        given(memberModelMapper.toMember(memberRequest)).willReturn(member);

        // when
        memberService.updateMember(anyLong(), memberRequest);

        // then
        verify(member).update(any());
    }

    @Test
    @DisplayName("회원 삭제")
    void withdrawMember() {
        // given
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));

        // when
        memberService.withdrawMember(anyLong());

        // then
        verify(member).withdraw();
    }
}
