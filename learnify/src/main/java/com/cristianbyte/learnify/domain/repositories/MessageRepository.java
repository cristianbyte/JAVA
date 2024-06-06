package com.cristianbyte.learnify.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianbyte.learnify.domain.entities.Message;

public interface  MessageRepository extends JpaRepository<Message, String>{
    
}
