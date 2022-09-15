package com.florry.user.member.service;

import com.florry.user.member.dto.*;

import java.util.List;

public interface MemberService {
    SignUpResponse signUp(SignUpRequest signUpRequest);
    List<MemberResponse> findMembersBySearchCondition(MemberSearchCondition memberSearchCondition);
    MemberResponse updateMember(Long id, MemberRequest memberRequest);
    void withdrawMember(Long id);

    MemberResponse findMember(Long id);
}
