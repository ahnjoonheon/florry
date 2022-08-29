package com.florry.user.signup.dto;


public class SignUpRequest {
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String ssn;
    private String mobile;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getSsn() {
        return ssn;
    }

    public String getMobile() {
        return mobile;
    }
}
