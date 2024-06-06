package com.cristianbyte.learnify.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private LocalDateTime dateTime;
    private String content;
    private String sender_id;
    private String receiver_id;
}
