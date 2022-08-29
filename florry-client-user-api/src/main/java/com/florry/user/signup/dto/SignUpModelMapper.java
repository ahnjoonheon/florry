package com.florry.user.signup.dto;

import com.florry.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignUpModelMapper {

    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "userRole", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUser(SignUpRequest signUpRequest);

    SignUpResponse toSignUpResponse(User user);







}
