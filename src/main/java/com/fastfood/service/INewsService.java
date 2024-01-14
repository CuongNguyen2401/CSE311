package com.fastfood.service;

import java.util.List;

import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.NewsDTO;

public interface INewsService {
	List<NewsDTO> findAll();
//	NewsDTO findByAuthor(AccountDTO accountDTO); // chỗ này t chịu :)))
	NewsDTO save(NewsDTO newsDTO);
	
	ApiResponse softDeleteNews(Long id);
	ApiResponse activeNews(Long id);
}
