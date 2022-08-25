package com.florry.user.api.auth.service;

import com.florry.user.api.auth.dto.LoginRequest;
import com.florry.user.api.auth.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
