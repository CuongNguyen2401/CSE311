package com.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findByName(String name);
}
