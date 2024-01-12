package com.fastfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastfood.dto.RoleDTO;
import com.fastfood.mapper.RoleConverter;
import com.fastfood.repository.RoleRepository;
import com.fastfood.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;

	@Override
	public RoleDTO findByName(String name) {
		
		return roleConverter.toDTO(roleRepository.findByName(name));
	}
	
	

}
