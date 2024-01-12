package com.fastfood.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO extends AbstractDTO<ItemDTO> {
	
	private double price;
	private int quantity;
	private double totalPay;
    @JsonIgnore
	private List<OrderDTO> orders;
    private ProductDTO productDTO;

	
	
}
