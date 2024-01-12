package com.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>{

}
