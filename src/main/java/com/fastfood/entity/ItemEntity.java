package com.fastfood.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "item")
public class ItemEntity extends BaseEntity {

	@Column(name = "price")
	private double price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "totalpay")
	private double totalPay;

	@ManyToMany(mappedBy = "items", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderEntity> orders = new ArrayList<OrderEntity>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	private ProductEntity productEntity;

}
