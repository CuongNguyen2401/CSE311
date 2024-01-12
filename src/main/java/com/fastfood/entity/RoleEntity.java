package com.fastfood.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<AccountEntity> accounts = new ArrayList<AccountEntity>();



}
