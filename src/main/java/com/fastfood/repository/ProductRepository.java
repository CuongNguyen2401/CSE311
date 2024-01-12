package com.fastfood.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	List<ProductEntity> findByStatus(Pageable pageable, int status);
	List<ProductEntity> findByCategory_idAndStatus(long category_id, Pageable pageable,int status);
	int countByCategory_id(long category_id);
	List<ProductEntity> findByCategory_idAndStatus(long category_id,int status);
	Optional<ProductEntity> findByIdAndStatus(long id , int status);
	
	
	

}
