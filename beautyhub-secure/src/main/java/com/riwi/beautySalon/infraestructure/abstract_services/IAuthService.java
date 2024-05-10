package com.riwi.beautySalon.infraestructure.abstract_services;

import com.riwi.beautySalon.api.dto.request.LogInRequest;
import com.riwi.beautySalon.api.dto.request.RegisterRequest;
import com.riwi.beautySalon.api.dto.response.AuthResponse;

public interface IAuthService {
    public AuthResponse logIn(LogInRequest request);
    public AuthResponse register(RegisterRequest request);
}
