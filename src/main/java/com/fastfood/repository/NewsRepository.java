package com.fastfood.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>{
//	Page<NewsEntity> findAll(Pageable pageable);
	
}
