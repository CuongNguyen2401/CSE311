package com.fastfood.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fastfood.dto.CategoryDTO;
import com.fastfood.dto.ImageDTO;
import com.fastfood.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

	@Column(name = "customername")
	private String customerName;

	@Column(name = "email")
	private String email;

	@Column(name = "phoneNumber")
	private String phonenumber;

	@Column(name = "address")
	private String address;

	@Column(name = "totalpay")
	private Double totalPay;

	@JoinColumn(name = "status")
	private int status;

	@JoinColumn(name = "type")
	private int type;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "orders_item", joinColumns = @JoinColumn(name = "orderid"), inverseJoinColumns = @JoinColumn(name = "itemid"))
	private List<ItemEntity> items = new ArrayList<ItemEntity>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "userid")
	private AccountEntity accountEntity;

}
