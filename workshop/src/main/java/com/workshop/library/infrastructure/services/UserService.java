package com.workshop.library.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.library.api.dto.request.UserRequest;
import com.workshop.library.api.dto.response.UserResponseFull;
import com.workshop.library.domain.entities.User;
import com.workshop.library.domain.repositories.UserRepository;
import com.workshop.library.infrastructure.abstract_services.IUserService;
import com.workshop.library.utils.mappers.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public UserResponseFull getById(Long id) {
        return this.userMapper.entityToResponseFull(this.findById(id));
    }

    @Override
    public UserResponseFull create(UserRequest request) {
        User userToCreate = this.userMapper.requestToEntity(request);
        User userSaved = this.userRepository.save(userToCreate);
        return this.userMapper.entityToResponseFull(userSaved);
    }

    @Override
    public UserResponseFull update(Long id, UserRequest request) {
        this.findById(id);
        User userToUpdate = this.userMapper.requestToEntity(request);
        userToUpdate.setId(id);
        User userSaved = this.userRepository.save(userToUpdate);
        return this.userMapper.entityToResponseFull(userSaved);
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        this.userRepository.deleteById(id);
        return;
    }

    private User findById(Long id){
        return this.userRepository.findById(id).orElseThrow();
    }
    
}
