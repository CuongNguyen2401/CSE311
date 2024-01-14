package com.fastfood.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

	@Column(name = "type")
	private String type;

	@OneToMany(mappedBy = "category")
	private List<ProductEntity> products = new ArrayList<>();
	
	@Column(name= "status")
	private String status;
	
	

}
