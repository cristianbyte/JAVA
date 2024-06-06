package com.cristianbyte.learnify.infraestructure.abstract_service;

import com.cristianbyte.learnify.api.dto.request.MessageRequest;
import com.cristianbyte.learnify.api.dto.response.MessageResponse;

public interface IMessageService extends CrudAbstractService<MessageRequest, MessageResponse, String>{
    
}
