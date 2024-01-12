package com.fastfood.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AbstractDTO<T> {

	private Long id;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private long[] ids;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<T> listResult = new ArrayList<T>();
	private Integer page;
	private Integer limit;
	private Integer totalPage;
	private Integer totalItem;
	private String sortName;
	private String sortBy;
	private String alert;
	private String message;
	private String type;

}