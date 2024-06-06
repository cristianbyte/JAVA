package com.cristianbyte.learnify.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.cristianbyte.learnify.api.dto.request.MessageRequest;
import com.cristianbyte.learnify.api.dto.response.MessageResponse;
import com.cristianbyte.learnify.domain.entities.Message;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    @Mapping(target = "sender_id.id", source = "sender_id")
    @Mapping(target = "receiver_id.id", source = "receiver_id")
    Message messageRequestToMessage(MessageRequest message);

    @Mapping(target = "sender_id", source = "sender_id.id")
    @Mapping(target = "receiver_id", source = "receiver_id.id")
    MessageResponse messageToMessageResponse(Message message);

    @Mapping(target = "sender_id.id", source = "sender_id")
    @Mapping(target = "receiver_id.id", source = "receiver_id")
    void udpateFromMessageRequest(MessageRequest request, @MappingTarget Message message);
}
