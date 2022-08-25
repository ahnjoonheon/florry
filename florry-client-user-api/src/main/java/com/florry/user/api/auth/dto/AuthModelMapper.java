package com.florry.user.api.auth.dto;

import com.florry.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthModelMapper {

    User to(LoginRequest loginRequest);

    LoginResponse of(User user);







}
