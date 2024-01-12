package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> findAll(Pageable pageable);
	List<ProductDTO> findAllByStatus(Pageable pageable , int status);
	List<ProductDTO> findByCategory_idAndStatus(long category_id, Pageable pageable, int status);
	List<ProductDTO> findByCategory_idAndStatus(long category_id, int status);
	ProductDTO findById(long id);
	int countByCategoryId(long categoryId);
	ApiResponse softDelete(long productId);
	ProductDTO save(ProductDTO dto);
	int getTotalItem();
	ProductDTO update(ProductDTO dto);
	
	
}
