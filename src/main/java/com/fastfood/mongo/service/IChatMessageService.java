package com.fastfood.mongo.service;

import java.util.List;

import com.fastfood.mongo.entity.ChatMessage;

public interface IChatMessageService {
	ChatMessage save(ChatMessage chatMessage);
	List<ChatMessage> findChatMessages(String senderId, String recipientId);
}
