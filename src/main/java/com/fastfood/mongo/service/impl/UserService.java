package com.fastfood.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fastfood.mongo.entity.Status;
import com.fastfood.mongo.entity.User;
import com.fastfood.mongo.repository.UserMongoRepository;
import com.fastfood.mongo.service.IUserService;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserMongoRepository repository;

	public void saveUser(User user) {
		user.setStatus(Status.ONLINE);
		repository.save(user);
	}

	public void disconnect(User user) {
		User storedUser = repository.findOne(user.getId());
		if (storedUser != null) {
			storedUser.setStatus(Status.OFFLINE);
			repository.save(storedUser);
		}
	}

	public List<User> findConnectedUsers() {
		return repository.findAllByStatus(Status.ONLINE);
	}

	
}