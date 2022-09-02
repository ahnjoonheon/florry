package com.florry.user.auth.dto;

public record LoginRequest(
        String email,
        String password
) {

}
