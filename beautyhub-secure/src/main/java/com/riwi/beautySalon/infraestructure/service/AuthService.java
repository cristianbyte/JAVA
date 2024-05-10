package com.riwi.beautySalon.infraestructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.beautySalon.api.dto.request.LogInRequest;
import com.riwi.beautySalon.api.dto.request.RegisterRequest;
import com.riwi.beautySalon.api.dto.response.AuthResponse;
import com.riwi.beautySalon.domain.entities.User;
import com.riwi.beautySalon.domain.repositories.UserRepository;
import com.riwi.beautySalon.infraestructure.abstract_services.IAuthService;
import com.riwi.beautySalon.infraestructure.helpers.JwtService;
import com.riwi.beautySalon.utils.enums.Role;
import com.riwi.beautySalon.utils.exception.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Override
    public AuthResponse logIn(LogInRequest request) {
        return null;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        // Validate that doent exits
        User exist = this.findByUserName(request.getUserName());
        if(exist != null){
            throw new BadRequestException("User name not avalible.");
        }

        User user = User.builder()
                    .userName(request.getUserName())
                    .password(request.getPassword())
                    .role(Role.CLIENT)
                    .build();

        this.userRepository.save(user);

        return AuthResponse.builder()
                    .message("Success!")
                    .token(this.jwtService.getToken(user))
                    .build();
    }

    private User findByUserName(String userName){
        return this.userRepository.findByUserName(userName).orElse(null);
    }
    
}
