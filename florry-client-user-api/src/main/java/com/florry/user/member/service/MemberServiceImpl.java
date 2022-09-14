package com.florry.user.member.service;

import com.florry.common.constant.MemberRole;
import com.florry.common.constant.MemberStatus;
import com.florry.domain.user.Member;
import com.florry.domain.user.MemberRepository;
import com.florry.user.member.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberModelMapper memberModelMapper;

    public MemberServiceImpl(MemberRepository memberRepository, MemberModelMapper memberModelMapper) {
        this.memberRepository = memberRepository;
        this.memberModelMapper = memberModelMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<MemberResponse> findMembersBySearchCondition(MemberSearchCondition memberSearchCondition) {
        return memberModelMapper.toMemberResponses(
                memberRepository.findAll(memberSearchCondition.build()));
    }

    @Transactional
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        return memberModelMapper.toSignUpResponse(
                memberRepository.save(
                        memberModelMapper.toMember(signUpRequest)
                                .withRoleBySignUp(MemberRole.USER)
                                .withUserStatusBySignUp(MemberStatus.NORMAL)));
    }

    @Transactional
    @Override
    public MemberResponse updateMember(Long id, MemberRequest memberRequest) {
        return memberModelMapper.toMemberResponse(
                findMemberOrElseThrow(id)
                .update(memberModelMapper.toMember(memberRequest)));

    }

    @Transactional
    @Override
    public void withdrawMember(Long id) {
        findMemberOrElseThrow(id).withdraw();
    }

    private Member findMemberOrElseThrow(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("cannot find by id(%d)".formatted(id)));
    }

}
