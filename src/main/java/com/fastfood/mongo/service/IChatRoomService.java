package com.fastfood.mongo.service;

import java.util.Optional;

public interface IChatRoomService {
	Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists);

}
