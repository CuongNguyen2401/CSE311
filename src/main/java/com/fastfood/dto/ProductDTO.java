/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfood.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO extends AbstractDTO<ProductDTO> {

	private String productName;
	private String description;
	private double price;
	private List<ImageDTO> listImage;
	private CategoryDTO categoryDTO;
	private double salePrice;
	private int status;

}
