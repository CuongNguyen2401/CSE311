package com.fastfood.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.entity.CategoryEntity;
import com.fastfood.entity.OrderEntity;
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
	public CategoryDTO save(CategoryDTO categoryDTO) {
		CategoryEntity entity = categoryMapper.mapToEntity(categoryDTO);
		return categoryMapper.mapToDTO(categoryRepository.save(entity));
		
	}

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

	@Override
	public ApiResponse softDeleteCategory(Long id) {
		CategoryEntity categoryEntity = categoryRepository.findOne(id);
		
		if (categoryEntity!=null) {
			CategoryDTO dto = categoryMapper.mapToDTO(categoryEntity);
			dto.setStatus("DISABLE");
			categoryRepository.save(categoryMapper.mapToEntity(dto));
			return new ApiResponse().builder()
					.success(true)
					.http(HttpStatus.OK)
					.message("Disable category success")
					.build();
		}
		return new ApiResponse().builder()
					.success(false)
					.http(HttpStatus.ALREADY_REPORTED)
					.message("Disable category failed!")
					.build();
		
	}

	@Override
	public ApiResponse activeCategory(Long id) {
		CategoryEntity categoryEntity = categoryRepository.findOne(id);
		
		if (categoryEntity!=null) {
			CategoryDTO dto = categoryMapper.mapToDTO(categoryEntity);
			dto.setStatus("ACTIVE");
			categoryRepository.save(categoryMapper.mapToEntity(dto));
			return new ApiResponse().builder()
					.success(true)
					.http(HttpStatus.OK)
					.message("Active category success")
					.build();
		}
		return new ApiResponse().builder()
					.success(false)
					.http(HttpStatus.ALREADY_REPORTED)
					.message("Active category failed!")
					.build();
	}


}
