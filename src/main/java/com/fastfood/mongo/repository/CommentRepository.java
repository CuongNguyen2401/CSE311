package com.fastfood.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fastfood.mongo.entity.Comment;

public interface CommentRepository  extends MongoRepository<Comment, String>{

}