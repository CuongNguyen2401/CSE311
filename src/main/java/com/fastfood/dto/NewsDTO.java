package com.fastfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsDTO extends AbstractDTO<NewsDTO>{

		private String title;
		private String description;
		private AccountDTO accountDTO;
		private String status;
}
