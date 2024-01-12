package com.fastfood.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fastfood.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	@Query("SELECT o FROM OrderEntity o " + "WHERE FUNCTION('MONTH', o.createdDate) = :month "
			+ "AND FUNCTION('YEAR', o.createdDate) = :year")
	List<OrderEntity> findAllOrdersByMonthAndYear(@Param("month") int month, @Param("year") int year,
			Pageable pageable);
	
	@Query("SELECT o FROM OrderEntity o " + "WHERE FUNCTION('MONTH', o.createdDate) = :month "
			+ "AND FUNCTION('YEAR', o.createdDate) = :year")
	List<OrderEntity> findAllOrdersByMonthAndYear(@Param("month") int month, @Param("year") int year
			);

}
