package com.fastfood.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.CategoryDTO;
import com.fastfood.entity.CategoryEntity;

@Component
public class CategoryMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CategoryDTO mapToDTO(CategoryEntity categoryEntity) {
		return modelMapper.map(categoryEntity, CategoryDTO.class);
	}

	public CategoryEntity mapToEntity(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, CategoryEntity.class);
	}
}
