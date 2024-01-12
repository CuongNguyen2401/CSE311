package com.fastfood.mongo.service;

import java.util.List;

import com.fastfood.dto.ApiResponse;
import com.fastfood.mongo.entity.Comment;

public interface ICommentService {
	Comment save(Comment comments);
	List<Comment> findAll();
	Comment updateCommentById(Comment comments);
	Comment findCommentById(String id);
	ApiResponse deleteCommentById(String id);
	
}