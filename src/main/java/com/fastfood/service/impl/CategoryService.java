package com.fastfood.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastfood.dto.CategoryDTO;
import com.fastfood.entity.CategoryEntity;
import com.fastfood.mapper.CategoryMapper;
import com.fastfood.repository.CategoryRepository;
import com.fastfood.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryEntity> categories = categoryRepository.findAll();
		List<CategoryDTO> result = categories.stream().map(categoryEntity -> categoryMapper.mapToDTO(categoryEntity))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public CategoryDTO findByType(String type) {
		CategoryEntity categoryEntity = categoryRepository.findByType(type);
		CategoryDTO dto = categoryMapper.mapToDTO(categoryEntity);
		return dto;
	}

}
