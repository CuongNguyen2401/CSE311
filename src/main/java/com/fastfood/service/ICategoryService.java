package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;



public interface ICategoryService {
	List<CategoryDTO> findAllbyPage(Pageable pageable);
	CategoryDTO findByType(String type);
	ApiResponse softDeleteCategory(Long id);
	ApiResponse activeCategory(Long id);
	CategoryDTO save(CategoryDTO categoryDTO);
	int getTotalCate();
	List<CategoryDTO> findAll();
	CategoryDTO findByID(Long id);
}
