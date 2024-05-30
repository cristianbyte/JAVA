package com.cristianbyte.learnify.infraestructure.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cristianbyte.learnify.api.dto.request.UserRequest;
import com.cristianbyte.learnify.api.dto.response.UserResponse;
import com.cristianbyte.learnify.api.mapper.UserMapper;
import com.cristianbyte.learnify.domain.entities.User;
import com.cristianbyte.learnify.domain.repositories.UserRepository;
import com.cristianbyte.learnify.infraestructure.abstract_service.IUserService;



@Service
@Transactional
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if(page < 0) {
            page = 0;
        }

        PageRequest pagination = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pagination);
        
        return userPage.map(userMapper::userToUserResponse);
    }

    @Override
    public UserResponse getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public UserResponse create(UserRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
