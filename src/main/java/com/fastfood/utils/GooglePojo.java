package com.fastfood.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.ItemDTO;
import com.fastfood.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GooglePojo {
	private String id;
	private String email;
	private boolean verified_email;
	private String name;
	private String given_name;
	private String family_name;
	private String link;
	private String picture;
	@JsonIgnoreProperties
	private String hd;
}
