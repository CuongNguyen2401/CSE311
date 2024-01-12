package com.fastfood.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fastfood.dto.ApiResponse;
import com.fastfood.mongo.entity.Comment;
import com.fastfood.mongo.repository.CommentRepository;
import com.fastfood.mongo.service.ICommentService;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment save(Comment comments) {

		return commentRepository.save(comments);
	}

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Comment updateCommentById(Comment comments) {
		return commentRepository.save(comments);
	}

	@Override
	public Comment findCommentById(String id) {

		return commentRepository.findOne(id);
	}

	@Override
	public ApiResponse deleteCommentById(String id) {
		
		 commentRepository.delete(id);
		return new ApiResponse().builder()
					.http(HttpStatus.OK)
					.message("Deleted successfull!")
					.success(true)
					.build();
	}

}