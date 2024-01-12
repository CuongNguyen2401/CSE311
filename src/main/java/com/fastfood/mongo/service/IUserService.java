package com.fastfood.mongo.service;

import java.util.List;

import com.fastfood.mongo.entity.User;

public interface IUserService {
	void saveUser(User user);

	void disconnect(User user);

	List<User> findConnectedUsers();

}
