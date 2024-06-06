package com.cristianbyte.learnify.infraestructure.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cristianbyte.learnify.api.dto.request.MessageRequest;
import com.cristianbyte.learnify.api.dto.response.MessageResponse;
import com.cristianbyte.learnify.api.mapper.MessageMapper;
import com.cristianbyte.learnify.domain.entities.Message;
import com.cristianbyte.learnify.domain.repositories.MessageRepository;
import com.cristianbyte.learnify.infraestructure.abstract_service.IMessageService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Service
@Setter
@AllArgsConstructor
public class MessageService implements IMessageService{

    @Autowired
    private final MessageRepository messageRepository;

    @Autowired
    private final MessageMapper messageMapper;

    @Override
    public Page<MessageResponse> getAll(int page, int size) {
        if (page <0) page = 0;
        PageRequest pagination = PageRequest.of(page -1, size);
        return this.messageRepository.findAll(pagination)
                .map(messageMapper::messageToMessageResponse);
    }

    @Override
    public MessageResponse getById(String id) {
        Message message = this.find(id);
        return messageMapper.messageToMessageResponse(message);
    }

    @Override
    public MessageResponse create(MessageRequest request) {
        Message newMessage = this.messageMapper.messageRequestToMessage(request);
        newMessage.setDateTime(LocalDateTime.now());
        return this.messageMapper.messageToMessageResponse(this.messageRepository.save(newMessage));
    }

    @Override
    public MessageResponse update(String id, MessageRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    private Message find(String id){
        return this.messageRepository.findById(id).orElseThrow();
    }
}
