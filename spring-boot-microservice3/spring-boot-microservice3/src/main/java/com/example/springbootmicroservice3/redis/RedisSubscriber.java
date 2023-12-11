package com.example.springbootmicroservice3.redis;

import com.example.springbootmicroservice3.model.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public RedisSubscriber(ObjectMapper objectMapper, RedisTemplate<String, Object> redisTemplate, SimpMessageSendingOperations messagingTemplate) {
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {

            String publishedMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());


            ChatMessage chatMessage = objectMapper.readValue(publishedMessage, ChatMessage.class);


        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
