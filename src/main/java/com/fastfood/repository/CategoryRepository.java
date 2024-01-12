package com.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findByType(String type);
}
