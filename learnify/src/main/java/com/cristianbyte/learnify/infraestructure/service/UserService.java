package com.cristianbyte.learnify.infraestructure.service;

import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if (page <0) page = 0;

        PageRequest pagination = PageRequest.of(page -1, size);
        
        return this.userRepository.findAll(pagination)
                .map(userMapper::userToUserResponse);
    }
    
    @Override
    public UserResponse getById(String id) {
        User response = this.find(id);
        return  userMapper.userToUserResponse(response);
    }
    
    @Override
    public UserResponse create(UserRequest request){
        User user = userMapper.userRequestToUser(request);
        return userMapper.userToUserResponse(this.userRepository.save(user));
    }
    
    @Override
    public UserResponse update(String id, UserRequest request) {
        User userToUpdate = this.find(id);
        userMapper.updateFromUserRequest(request, userToUpdate);
        userToUpdate.setId(id);
        return userMapper.userToUserResponse(this.userRepository.save(userToUpdate));
    }
    
    @Override
    public void delete(String id) {
        this.userRepository.deleteById(id);
    }

    public User find(String id) {
        return this.userRepository.findById(id).orElseThrow();
    }
}
