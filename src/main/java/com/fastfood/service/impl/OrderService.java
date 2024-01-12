package com.fastfood.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fastfood.dto.OrderDTO;
import com.fastfood.entity.OrderEntity;
import com.fastfood.exception.ResourceNotFoundException;
import com.fastfood.mapper.OrderMapper;
import com.fastfood.repository.OrderRepository;
import com.fastfood.service.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public OrderDTO save(OrderDTO dto) {
		OrderEntity entity = orderMapper.mapToEntity(dto);
		return orderMapper.mapToDTO(orderRepository.save(entity));
	}

	@Override
	public List<OrderDTO> findAll(Pageable pageable) {
		List<OrderEntity> entities = orderRepository.findAll(pageable).getContent();
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(order -> orderMapper.mapToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public int getTotalOrder() {

		return (int) orderRepository.count();
	}

	@Override
	public List<OrderDTO> findAllByMonthAndYear(int month, int year, Pageable pageable) {
		List<OrderEntity> entities = orderRepository.findAllOrdersByMonthAndYear(month, year, pageable);
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(order -> orderMapper.mapToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> findAll() {
		List<OrderEntity> entities = orderRepository.findAll();
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(order -> orderMapper.mapToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> findAllByMonthAndYear(int month, int year) {
		List<OrderEntity> entities = orderRepository.findAllOrdersByMonthAndYear(month, year);
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(order -> orderMapper.mapToDTO(order)).collect(Collectors.toList());
	}

}
