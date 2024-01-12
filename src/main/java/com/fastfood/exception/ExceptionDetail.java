package com.fastfood.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionDetail {
	
	private String timestamp;
	private String message;
	private String detail;
}
