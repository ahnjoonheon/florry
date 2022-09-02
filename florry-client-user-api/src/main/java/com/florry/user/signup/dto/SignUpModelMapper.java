package com.florry.user.signup.dto;

import com.florry.domain.user.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignUpModelMapper {

    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "userRole", ignore = true)
    @Mapping(target = "id", ignore = true)
    Member toUser(SignUpRequest signUpRequest);

    SignUpResponse toSignUpResponse(Member member);







}
