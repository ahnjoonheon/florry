package com.florry.user.signup.dto;


import com.florry.domain.user.Member;

public record SignUpRequest(
        String email,
        String password,
        String name,
        String nickName,
        String ssn,
        String mobile) {
}
