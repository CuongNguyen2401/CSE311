package com.fastfood.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.dto.ImageDTO;
import com.fastfood.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "createddate")
	@CreatedDate
	private Date createdDate;

	@Column(name = "modifieddate")
	@LastModifiedDate
	private Date modifiedDate;

	@Column(name = "createdBy")
	@CreatedBy
	private String createdBy;

	@Column(name = "modifiedby")
	@LastModifiedBy
	private String modifiedBy;

}
