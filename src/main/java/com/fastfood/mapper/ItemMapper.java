package com.fastfood.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.ItemDTO;
import com.fastfood.entity.ItemEntity;

@Component
public class ItemMapper {

	@Autowired
	private ProductMapper productMapper;

	public ItemDTO mapToDTO(ItemEntity itemEntity) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setPrice(itemEntity.getPrice());
		itemDTO.setQuantity(itemEntity.getQuantity());
		itemDTO.setTotalPay(itemEntity.getTotalPay());

		itemDTO.setProductDTO(productMapper.mapToDTO(itemEntity.getProductEntity()));
		return itemDTO;
	}

	public ItemEntity mapToEntity(ItemDTO itemDTO) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setPrice(itemDTO.getPrice());
		itemEntity.setQuantity(itemDTO.getQuantity());
		itemEntity.setTotalPay(itemDTO.getTotalPay());

		itemEntity.setProductEntity(productMapper.mapToEntity(itemDTO.getProductDTO()));

		return itemEntity;
	}

}
