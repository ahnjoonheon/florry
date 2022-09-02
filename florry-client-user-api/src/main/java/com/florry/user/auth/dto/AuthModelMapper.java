package com.florry.user.auth.dto;

import com.florry.domain.user.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthModelMapper {

    Member to(LoginRequest loginRequest);

    LoginResponse of(Member member);







}
