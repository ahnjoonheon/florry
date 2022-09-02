package com.florry.user.signup.dto;


import com.florry.domain.user.Member;

public record SignUpRequest(
        String email,
        String password,
        String name,
        String nickName,
        String ssn,
        String mobile) {
    public Member toUser() {
        return Member.createUser(this.email, this.password, this.name, this.nickName, this.ssn, this.mobile);
    }
}
