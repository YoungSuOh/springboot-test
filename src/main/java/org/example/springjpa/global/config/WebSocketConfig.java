package org.example.springjpa.global.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker // 브로커 활성화 => subscriber에서 원하는 시점에 메세지 처리가 가능하고, scaleout에 용이하다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub"); // 내장된 메시지 브로커를 활성화하여 /sub로 시작하는 주소로 발행되는 메시지를 구독하는 클라이언트에게 전달
        registry.setApplicationDestinationPrefixes("/pub"); // 클라이언트가 메시지를 발행할 때 사용하는 주소의 접두어를 설정. 클라이언트가 메시지를 서버로 전송할 때 **/pub/**로 시작하는 경로를 사용해야 한다.
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry
                .addEndpoint("/websocket")  // 엔드 포인트 : /websocket
                .setAllowedOrigins("*");
    }
}
