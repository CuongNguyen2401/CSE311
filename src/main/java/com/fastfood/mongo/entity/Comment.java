package com.fastfood.mongo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Comment {

	@Id
	private String id;
	private String userId;
	private String parent;
	private String content;
	private Date created;
	private Date modified;
	private String fullname;
	private String createdByCurrentUser;
	private int upvoteCount;
	private boolean userHasUpvoted;

}