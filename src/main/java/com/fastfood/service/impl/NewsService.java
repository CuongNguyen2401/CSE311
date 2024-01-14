package com.fastfood.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.dto.NewsDTO;
import com.fastfood.entity.CategoryEntity;
import com.fastfood.entity.NewsEntity;
import com.fastfood.mapper.NewsMapper;
import com.fastfood.repository.NewsRepository;
import com.fastfood.service.INewsService;

@Service
public class NewsService implements INewsService {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private NewsMapper newsMapper;

	@Override
	public List<NewsDTO> findAll() {
		List<NewsEntity> news = newsRepository.findAll();
		List<NewsDTO> result = news.stream().map(newsEntity -> newsMapper.mapToDTO(newsEntity))
				.collect(Collectors.toList());
		return result;
	}

//	@Override
//	public NewsDTO findByAuthor(AccountDTO accountDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public NewsDTO save(NewsDTO newsDTO) {
		NewsEntity newsEntity = newsMapper.mapToEntity(newsDTO);
		return newsMapper.mapToDTO(newsRepository.save(newsEntity));
	}

	@Override
	public ApiResponse softDeleteNews(Long id) {
		NewsEntity  newsEntity = newsRepository.findOne(id);
		
		if(newsEntity != null) {
			NewsDTO dto = newsMapper.mapToDTO(newsEntity);
			dto.setStatus("DISABLE");
			newsRepository.save(newsMapper.mapToEntity(dto));
			return new ApiResponse().builder()
					.success(true)
					.http(HttpStatus.OK)
					.message("Disable news successed")
					.build();
		}
		return new ApiResponse().builder()
				.success(false)
				.http(HttpStatus.OK)
				.message("Disable news failed")
				.build();		
		
	}

	@Override
	public ApiResponse activeNews(Long id) {
	NewsEntity  newsEntity = newsRepository.findOne(id);
		
		if(newsEntity != null) {
			NewsDTO dto = newsMapper.mapToDTO(newsEntity);
			dto.setStatus("ACTIVE");
			newsRepository.save(newsMapper.mapToEntity(dto));
			return new ApiResponse().builder()
					.success(true)
					.http(HttpStatus.OK)
					.message("Active news successed")
					.build();
		}
		return new ApiResponse().builder()
				.success(false)
				.http(HttpStatus.OK)
				.message("Active news failed")
				.build();		
	
	}

}
