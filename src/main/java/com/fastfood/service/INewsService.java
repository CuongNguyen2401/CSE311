package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.NewsDTO;

public interface INewsService {
	List<NewsDTO> findAllByPage(Pageable pageable);
	int getTotalNews();
//	NewsDTO findByAuthor(AccountDTO accountDTO); // chỗ này t chịu :)))
	NewsDTO save(NewsDTO newsDTO);
	
	ApiResponse softDeleteNews(Long id);
	ApiResponse activeNews(Long id);
}
