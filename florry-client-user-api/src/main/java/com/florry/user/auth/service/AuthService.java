package com.florry.user.auth.service;

import com.florry.user.auth.dto.LoginRequest;
import com.florry.user.auth.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
