package com.cristianbyte.learnify.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    @NotBlank(message = "the content of the message can't be blank")
    private String content;
    @NotBlank(message = "the id of the sender can't be blank")
    private String sender_id;
    @NotBlank(message = "the id of the reciber can't be blank")
    private String receiver_id;
}
