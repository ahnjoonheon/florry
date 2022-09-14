package com.florry.user.member.dto;

import com.florry.domain.user.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberModelMapper {

    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "userRole", ignore = true)
    @Mapping(target = "id", ignore = true)
    Member toMember(SignUpRequest signUpRequest);

    @Mapping(target = "id", ignore = true)
    Member toMember(MemberRequest memberRequest);

    SignUpResponse toSignUpResponse(Member member);

    List<MemberResponse> toMemberResponses(Iterable<Member> member);

    MemberResponse toMemberResponse(Member member);









}
