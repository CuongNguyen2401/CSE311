package com.fastfood.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.AccountDTO;
import com.fastfood.entity.AccountEntity;

@Component
public class AccountMapper {

	private final ModelMapper modelMapper;

	@Autowired
	public AccountMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;

		modelMapper.createTypeMap(AccountEntity.class, AccountDTO.class)
		.addMapping(src -> src.getRoles(),AccountDTO::setListRole);

		modelMapper.createTypeMap(AccountDTO.class, AccountEntity.class)
		.addMapping(scr -> scr.getListRole(), AccountEntity::setRoles);

	}

	public AccountDTO mapToDTO(AccountEntity accountEntity) {
		return modelMapper.map(accountEntity, AccountDTO.class);
	}

	public AccountEntity mapToEntity(AccountDTO accountDTO) {
		return modelMapper.map(accountDTO, AccountEntity.class);
	}

}
