package com.fastfood.mongo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastfood.exception.ResourceNotFoundException;
import com.fastfood.mongo.entity.ChatMessage;
import com.fastfood.mongo.repository.ChatMessageRepository;
import com.fastfood.mongo.service.IChatMessageService;
import com.fastfood.mongo.service.IChatRoomService;
import com.fastfood.utils.MessageUtil;

@Service
public class ChatMessageService implements IChatMessageService {
	@Autowired
	private ChatMessageRepository repository;
	@Autowired
	private IChatRoomService chatRoomService;

	@Override
	public ChatMessage save(ChatMessage chatMessage) {
		String chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
				.orElseThrow(() -> new ResourceNotFoundException(MessageUtil.CHATROOM_NOT_FOUND));
		chatMessage.setChatId(chatId);
		repository.save(chatMessage);
		return chatMessage;
	}

	@Override
	public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
		Optional<String> chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
		return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
	}

}
