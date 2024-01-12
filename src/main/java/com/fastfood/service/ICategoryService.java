package com.fastfood.service;

import java.util.List;

import com.fastfood.dto.CategoryDTO;



public interface ICategoryService {
	List<CategoryDTO> findAll();
	CategoryDTO findByType(String type);
}
