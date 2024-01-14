package com.fastfood.service;

import java.util.List;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;



public interface ICategoryService {
	List<CategoryDTO> findAll();
	CategoryDTO findByType(String type);
	ApiResponse softDeleteCategory(Long id);
	ApiResponse activeCategory(Long id);
	CategoryDTO save(CategoryDTO categoryDTO);
}
