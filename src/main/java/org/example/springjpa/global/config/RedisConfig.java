package org.example.springjpa.global.config;


import org.example.springjpa.domain.message.service.RedisMessageStringSubscriber;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    // Redis 연결을 생성하는 팩토리
    public RedisConnectionFactory redisConnectionFactory() {
        final LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(); // Lettuce를 사용해 Redis와 연결을 설정
        return lettuceConnectionFactory;
    }
    public RedisTemplate<String, String> redisTemplate() {  //  RedisTemplate : Redis에서 데이터를 읽고 쓰기 위한 템플릿 -> <String, String>을 통해 키와 값을 문자열로 설정함.
        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // 키를 문자열로 직렬화하기 위해 StringRedisSerializer를 사용
        redisTemplate.setKeySerializer(new StringRedisSerializer());  // 값도 문자열로 직렬화하기 위해 StringRedisSerializer를 사용
        redisTemplate.setConnectionFactory(redisConnectionFactory()); //  위에서 정의한 redisConnectionFactory()를 설정하여 Redis 연결을 관리
        return redisTemplate;
    }

    /**
     * 메시지 수신을 위한 설정: RedisMessageListenerContainer
     *
     * Redis 의 channel 로부터 메시지를 수신받아 해당 MessageListenerAdapter 에게 디스패치
     */
    public RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(redisConnectionFactory());
        container.addMessageListener(messageStringListener(), channelTopic());

        return container;
    }

    /**
     * 메시지 수신을 위한 설정: MessageListenerAdapter
     *
     * RedisMessageListenerContainer 로부터 메시지를 받아서 등록된 리스너(구독자)에게 전달
     */
    public MessageListenerAdapter messageStringListener() {
        return new MessageListenerAdapter(new RedisMessageStringSubscriber());
    }

    /**
     * Redis 에서 주고 받을 채널 설정
     */
    public ChannelTopic channelTopic() {
        return new ChannelTopic("ch01");
    }
}
