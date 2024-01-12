package com.fastfood.service;

import com.fastfood.dto.RoleDTO;

public interface IRoleService {
	RoleDTO findByName(String name);
}
