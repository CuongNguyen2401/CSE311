package com.fastfood.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.ProductEntity;
import com.fastfood.exception.ResourceNotFoundException;
import com.fastfood.mapper.ProductMapper;
import com.fastfood.repository.ProductRepository;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IProductService;
import com.fastfood.utils.MessageUtil;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<ProductDTO> findAll(Pageable pageable) {

		List<ProductEntity> entities = productRepository.findAll(pageable).getContent();
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(product -> productMapper.mapToDTO(product)).collect(Collectors.toList());

	}

	@Override
	public int getTotalItem() {
		return (int) productRepository.count();
	}

	@Override
	public List<ProductDTO> findByCategory_idAndStatus(long category_id, Pageable pageable, int status) {
		List<ProductDTO> models = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findByCategory_idAndStatus(category_id, pageable, status);

		for (ProductEntity item : entities) {
			ProductDTO product = productMapper.mapToDTO(item);

			models.add(product);
		}
		return models;
	}

	@Override
	public ProductDTO findById(long id) {
		ProductEntity product = productRepository.findByIdAndStatus(id, SystemConstant.ACTIVE_STATUS)
				.orElseThrow(() -> new ResourceNotFoundException(MessageUtil.PRODUCT_ID_NOT_FOUND + id));
		ProductDTO productDTO = productMapper.mapToDTO(product);
		return productDTO;

	}

	@Override
	public int countByCategoryId(long categoryId) {

		return productRepository.countByCategory_id(categoryId);
	}

	@Override
	public ApiResponse softDelete(long productId) {

		ProductEntity entity = productRepository.findByIdAndStatus(productId, SystemConstant.ACTIVE_STATUS)
				.orElseThrow(() -> new ResourceNotFoundException(MessageUtil.PRODUCT_ID_NOT_FOUND + productId));

		if (entity != null) {
			entity.setStatus(SystemConstant.INACTIVE_STATUS);
			productRepository.save(entity);
			return new ApiResponse(Boolean.TRUE, MessageUtil.SUCCESS_DELETE, HttpStatus.OK);
		}

		return new ApiResponse(Boolean.FALSE, MessageUtil.DELETE_FAILURE, HttpStatus.OK);

	}

	@Override
	public List<ProductDTO> findAllByStatus(Pageable pageable, int status) {
		List<ProductEntity> entities = productRepository.findByStatus(pageable, status);
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(product -> productMapper.mapToDTO(product)).collect(Collectors.toList());

	}

	@Override
	public ProductDTO save(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();
		if (dto.getId() != null) {
			ProductEntity oldEntity = productRepository.findOne(dto.getId());
			entity = productMapper.mapToEntity(dto);
		} else {
			entity = productMapper.mapToEntity(dto);
		}
		return productMapper.mapToDTO(productRepository.save(entity));
	}

	@Override
	public ProductDTO update(ProductDTO dto) {

		return null;
	}

	@Override
	public List<ProductDTO> findByCategory_idAndStatus(long category_id, int status) {
		List<ProductDTO> models = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findByCategory_idAndStatus(category_id, status);
		
		for (ProductEntity item : entities) {
			ProductDTO product = productMapper.mapToDTO(item);

			models.add(product);
		}
		return models;
	}

}
