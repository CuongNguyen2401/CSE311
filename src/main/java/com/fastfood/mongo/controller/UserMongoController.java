package com.fastfood.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fastfood.mongo.entity.User;
import com.fastfood.mongo.service.IUserService;

@Controller
public class UserMongoController {
	@Autowired
	private IUserService userService;

	@MessageMapping("/user.addUser")
	@SendTo("/user/public")
	public User addUser(@Payload User user) {
		userService.saveUser(user);
		return user;
	}

	@MessageMapping("/user.disconnectUser")
	@SendTo("/user/public")
	public User disconnectUser(@Payload User user) {
		userService.disconnect(user);
		return user;
	}	

	@GetMapping("/admin/api/v1/mongo/users")
	public ResponseEntity<List<User>> findConnectedUsers() {
		return ResponseEntity.ok(userService.findConnectedUsers());
	}
}
