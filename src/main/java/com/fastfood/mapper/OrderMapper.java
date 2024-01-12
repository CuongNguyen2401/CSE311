package com.fastfood.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.ItemDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.entity.ItemEntity;
import com.fastfood.entity.OrderEntity;
import com.fastfood.utils.DateUtil;

@Component
public class OrderMapper {
	
	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private ItemMapper itemMapper;

	public OrderDTO mapToDTO(OrderEntity orderEntity) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setCustomerName(orderEntity.getCustomerName());
		orderDTO.setEmail(orderEntity.getEmail());
		orderDTO.setPhonenumber(orderEntity.getPhonenumber());
		orderDTO.setAddress(orderEntity.getAddress());
		orderDTO.setTotalPay(orderEntity.getTotalPay());
		orderDTO.setCreatedDate(orderEntity.getCreatedDate());

		orderDTO.setItems(mapToDTOList(orderEntity.getItems()));

		orderDTO.setAccountDTO(accountMapper.mapToDTO(orderEntity.getAccountEntity()));

		return orderDTO;
	}

	public OrderEntity mapToEntity(OrderDTO orderDTO) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCustomerName(orderDTO.getCustomerName());
		orderEntity.setEmail(orderDTO.getEmail());
		orderEntity.setPhonenumber(orderDTO.getPhonenumber());
		orderEntity.setAddress(orderDTO.getAddress());
		orderEntity.setTotalPay(orderDTO.getTotalPay());
	

		
		orderEntity.setItems(mapToEntityList(orderDTO.getItems()));

		
		orderEntity.setAccountEntity(accountMapper.mapToEntity(orderDTO.getAccountDTO()));

		return orderEntity;
	}

	private List<ItemDTO> mapToDTOList(List<ItemEntity> listItemEnitity) {
		List<ItemDTO> listResult = new ArrayList<ItemDTO>();
		listItemEnitity.forEach(item -> listResult.add(itemMapper.mapToDTO(item)));

		return listResult;
	}

	private List<ItemEntity> mapToEntityList(List<ItemDTO> listItemDTO) {
		List<ItemEntity> listResult = new ArrayList<>();
		listItemDTO.forEach(itemDTO -> listResult.add(itemMapper.mapToEntity(itemDTO)));
		return listResult;
	}

}
