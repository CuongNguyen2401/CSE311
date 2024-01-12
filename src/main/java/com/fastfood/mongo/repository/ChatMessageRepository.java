package com.fastfood.mongo.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fastfood.mongo.entity.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
	List<ChatMessage> findByChatId(String chatId);
}