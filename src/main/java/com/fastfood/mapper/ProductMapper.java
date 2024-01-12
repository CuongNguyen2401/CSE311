package com.fastfood.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.ProductEntity;

@Component
public class ProductMapper {

	private final ModelMapper modelMapper;

	@Autowired
	public ProductMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;

		modelMapper.createTypeMap(ProductEntity.class, ProductDTO.class)
				.addMapping(src -> src.getCategory(), ProductDTO::setCategoryDTO)
				.addMapping(src -> src.getImageEntities(), ProductDTO::setListImage);

		modelMapper.createTypeMap(ProductDTO.class, ProductEntity.class)
				.addMapping(scr -> scr.getCategoryDTO(), ProductEntity::setCategory)
				.addMapping(scr -> scr.getListImage(), ProductEntity::setImageEntities);
	}

	public ProductDTO mapToDTO(ProductEntity productEntity) {
		return modelMapper.map(productEntity, ProductDTO.class);
	}

	public ProductEntity mapToEntity(ProductDTO productDTO) {
		return modelMapper.map(productDTO, ProductEntity.class);
	}

}
