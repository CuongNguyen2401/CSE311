package com.fastfood.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.NewsDTO;
import com.fastfood.entity.NewsEntity;

@Component
public class NewsMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public NewsDTO mapToDTO(NewsEntity newsEntity) {
		return modelMapper.map(newsEntity, NewsDTO.class);
	}
	
	public NewsEntity mapToEntity(NewsDTO newsDTO) {
		return modelMapper.map(newsDTO, NewsEntity.class);
	}
}
