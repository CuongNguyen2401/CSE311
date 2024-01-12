package com.fastfood.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fastfood.mongo.entity.Status;
import com.fastfood.mongo.entity.User;

import java.util.List;



public interface UserMongoRepository extends MongoRepository<User, String>{
	List<User> findAllByStatus(Status status);
}