package org.example.springjpa.domain.message.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/hello")
    public void message(final Message message) {
        // - 해당 채널ID 에 메시지를 보낸다.
        // - 그리고 "/sub/channel/channelID" 에 구독 중인 클라이언트에게 메시지를 보낸다.
        simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getChannel(), message);
    }

}
