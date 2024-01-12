package com.fastfood.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fastfood.mongo.entity.ChatRoom;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
	Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
