package com.example.springbootmicroservice3.redis;

import com.example.springbootmicroservice3.model.ChatMessage;
import com.example.springbootmicroservice3.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class RedisPublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChatMessageRepository chatMessageRepository;



}