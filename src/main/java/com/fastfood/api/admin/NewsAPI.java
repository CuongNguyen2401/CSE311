package com.fastfood.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.NewsDTO;
import com.fastfood.service.INewsService;

@RestController
@RequestMapping("admin/api/v1")
public class NewsAPI {

	@Autowired
	private INewsService service;

	@GetMapping("/news")
	public ResponseEntity<NewsDTO> loadAllNews(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setPage(page);
		newsDTO.setLimit(limit);
		Sort sort = new Sort(Sort.Direction.DESC, "createdDate");

		Pageable pageable = new PageRequest(page - 1, limit,sort);
		newsDTO.setListResult(service.findAllByPage(pageable));
		newsDTO.setTotalItem(service.getTotalNews());
		
		newsDTO.setTotalPage((int) Math.ceil((double) newsDTO.getTotalItem() / newsDTO.getLimit()));

		return new ResponseEntity<>(newsDTO, HttpStatus.OK);
	}
	@PostMapping("/news")
	public ResponseEntity<NewsDTO> save(@RequestBody NewsDTO newsDTO) {
		return new ResponseEntity<>(service.save(newsDTO), HttpStatus.OK);
	}
	@DeleteMapping("/news/{id}")
	public ResponseEntity<ApiResponse> disableNewsByID(@PathVariable Long id) {
		return new ResponseEntity<>(service.softDeleteNews(id), HttpStatus.OK);
	}
	@PutMapping("/news/{id}")
	public ResponseEntity<ApiResponse> activeNewsByID(@PathVariable Long id) {
		return new ResponseEntity<>(service.activeNews(id), HttpStatus.OK);
	}

}
