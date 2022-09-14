package com.florry.user.member.dto;

public record SignUpRequest(
        String email,
        String password,
        String name,
        String nickName,
        String ssn,
        String mobile) {
}
