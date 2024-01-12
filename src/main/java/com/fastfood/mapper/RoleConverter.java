package com.fastfood.mapper;

import org.springframework.stereotype.Component;

import com.fastfood.dto.RoleDTO;
import com.fastfood.entity.RoleEntity;

@Component
public class RoleConverter {

	public RoleDTO toDTO(RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());

		if (entity.getCreatedBy() != null) {
			dto.setCreatedBy(entity.getCreatedBy());
		}
		if (entity.getCreatedDate() != null) {
			dto.setCreatedDate(entity.getCreatedDate());
		}
		if (entity.getModifiedBy() != null) {
			dto.setModifiedBy(entity.getModifiedBy());
		}
		if (entity.getModifiedDate() != null) {
			dto.setModifiedDate(entity.getModifiedDate());
		}

		return dto;
	}
	
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setName(dto.getName());

		

		return entity;
	}
}
